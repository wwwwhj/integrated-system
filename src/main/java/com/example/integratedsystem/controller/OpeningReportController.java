package com.example.integratedsystem.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.integratedsystem.pojo.Instructor;
import com.example.integratedsystem.pojo.OpeningReport;
import com.example.integratedsystem.pojo.Student;
import com.example.integratedsystem.pojo.Subject;
import com.example.integratedsystem.service.*;
import com.example.integratedsystem.util.RoleUtils;
import com.example.integratedsystem.util.SubjectStateUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wu Hongjian
 */
@RestController
@RequestMapping("/openingReport")
public class OpeningReportController {

    @Resource
    private OpeningReportService openingReportService;
    @Resource
    private StudentService studentService;
    @Resource
    private SubjectService subjectService;
    @Resource
    private InstructorService instructorService;

    @GetMapping("/getOpeningReport")
    @SaCheckRole(value = {"instructor", "student"}, mode = SaMode.OR)
    public SaResult getOpeningReport() {
        String role = StpUtil.getRoleList().get(0);
        if (RoleUtils.isInstructor(role)) {
            List<OpeningReport> list = getOpeningReportByInstructorId();
            if (list.size() != 0) {
                ArrayList<OpeningReport> openingReportList = new ArrayList<>();
                ArrayList<String> subjectStateList = new ArrayList<>();
                ArrayList<Subject> subjectList = new ArrayList<>();
                ArrayList<Student> studentList = new ArrayList<>();
                ArrayList<String> stuClassList = new ArrayList<>();
                for (OpeningReport openingReport : list) {
                    Subject subject = subjectService.getById(openingReport.getSubjectId());
                    Student stu = studentService.getById(subject.getStuId());
                    String stateString = SubjectStateUtils.getStateString(subject.getSubjectState());
                    String stuClass = studentService.getStuClassByStuId(subject.getStuId());
                    subjectList.add(subject);
                    openingReportList.add(openingReport);
                    subjectStateList.add(stateString);
                    studentList.add(stu);
                    stuClassList.add(stuClass);
                }
                Instructor instructor = instructorService
                        .getById((Serializable) StpUtil.getLoginId());
                String collegeName = instructorService
                        .getCollegeNameByInstructorId(instructor.getInstructorId());
                return SaResult.ok().set("subjectList", subjectList)
                        .set("openingReport", openingReportList)
                        .set("subjectState", subjectStateList)
                        .set("student", studentList)
                        .set("stuClass", stuClassList)
                        .set("instructorId", instructor.getInstructorId())
                        .set("instructorName", instructor.getInstructorName())
                        .set("instructorCollege", collegeName);
            } else {
                return SaResult.ok().setData("暂无开题报告");
            }
        }
        if (RoleUtils.isStudent(role)) {
            Subject subject = getSubjectByStuId();
            if (subject == null) {
                return SaResult.error("尚无课题");
            }
            OpeningReport openingReport = openingReportService.getById(subject.getSubjectId());
            String subjectName = subject.getSubjectName();
            String stateString = SubjectStateUtils.getStateString(subject.getSubjectState());
            Student stu = studentService.getById(subject.getStuId());
            String stuClass = studentService.getStuClassByStuId(subject.getStuId());
            Instructor instructor = instructorService.getById(subject.getInstructorId());
            String collegeName = instructorService
                    .getCollegeNameByInstructorId(instructor.getInstructorId());
            return SaResult.ok().set("subjectId",subject.getSubjectId())
                    .set("subjectName",subjectName)
                    .set("openingReport", openingReport)
                    .set("subjectState", stateString)
                    .set("student", stu)
                    .set("stuClass", stuClass)
                    .set("instructorId", instructor.getInstructorId())
                    .set("instructorName", instructor.getInstructorName())
                    .set("instructorCollege", collegeName);
        }
        return SaResult.error();
    }

    @GetMapping("/getOpeningReport/{subjectId}")
    @SaCheckRole("instructor")
    public SaResult getOpeningReport(@PathVariable("subjectId") Integer subjectId) {
        Subject subject = subjectService.getById(subjectId);
        OpeningReport openingReport = openingReportService.getById(subjectId);
        if (subject == null || openingReport == null) {
            return SaResult.error("不存在课题或报告");
        }
        if (!StpUtil.getLoginId().equals(subject.getInstructorId() + "")) {
            return SaResult.error("非法修改");
        }
        return SaResult.ok().set("openingReport", openingReport);
    }

    @SaCheckRole("student")
    @RequestMapping("/saveOpeningReport")
    public SaResult saveOpeningReport(@RequestBody OpeningReport openingReport) {
        Subject subject = getSubjectByStuId();
        if (subject == null) {
            return SaResult.error("尚无课题");
        }
        if (openingReport.getSignificance() == null
                || openingReport.getSolvedProblems() == null
                || openingReport.getResearchPlan() == null
                || openingReport.getInnovation() == null) {
            return SaResult.error("信息不全");
        }
        openingReport.setSubjectId(subject.getSubjectId());
        subject.setSubjectState(SubjectStateUtils.reviewOpeningReport());
        subjectService.updateById(subject);
        boolean save = openingReportService.save(openingReport);
        return save ? SaResult.ok() : SaResult.error();
    }

    @SaCheckRole("student")
    @RequestMapping("/updateOpeningReport")
    public SaResult updateOpeningReport(@RequestBody OpeningReport openingReport) {
        Subject subject = subjectService.getById(openingReport.getSubjectId());
        if (StpUtil.getLoginId().equals(subject.getStuId() + "")) {
            subject.setSubjectState(SubjectStateUtils.reviewOpeningReport());
            subjectService.updateById(subject);
            boolean update = openingReportService.updateById(openingReport);
            return update ? SaResult.ok() : SaResult.error();
        }
        return SaResult.error("非本人");
    }


    private Subject getSubjectByStuId() {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_id", StpUtil.getLoginId());
        return subjectService.getOne(wrapper);
    }

    private OpeningReport getOpeningReportByStuId() {
        Subject subject = getSubjectByStuId();
        return openingReportService.getById(subject.getSubjectId());
    }

    private List<OpeningReport> getOpeningReportByInstructorId() {
        QueryWrapper<Subject> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("instructor_id", StpUtil.getLoginId());
        List<Subject> subjectList = subjectService.list(wrapper1);
        List<Integer> subjectIdList = subjectList.stream()
                .map(Subject::getSubjectId)
                .collect(Collectors.toList());
        QueryWrapper<OpeningReport> wrapper = new QueryWrapper<>();
        wrapper.in("subject_id", subjectIdList);
        return openingReportService.list(wrapper);
    }

}
