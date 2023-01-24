package com.example.integratedsystem.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.integratedsystem.pojo.*;
import com.example.integratedsystem.service.*;
import com.example.integratedsystem.util.FileUtils;
import com.example.integratedsystem.util.RoleUtils;
import com.example.integratedsystem.util.SubjectStateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.sql.Date;

/**
 * @author Wu Hongjian
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Resource
    private SubjectService subjectService;
    @Resource
    private StudentService studentService;
    @Resource
    private InstructorService instructorService;
    @Resource
    private SubjectStateService subjectStateService;
    @Value("${my-data.file-road}")
    private String fileRoad;

    @GetMapping("/getSubject")
    @SaCheckRole(value = {"instructor","student"},mode = SaMode.OR)
    public SaResult getSubject(){
        String role = StpUtil.getRoleList().get(0);
        if (RoleUtils.isInstructor(role)){
            SaResult result = SaResult.ok();
            List<Subject> list = getSubjectsByInstructorId();
            if (list.size()!=0){
                handleSubjectRelevantInfo(list,result);
            }else {
                result.setData("暂无课题");
            }
            return result;
        }
        if (RoleUtils.isStudent(role)){
            SaResult result = SaResult.ok();
            Subject subject = getSubjectByStuId();
            if (subject==null){
                return result.setData("暂无课题");
            }
            handleSubjectRelevantInfo(subject,result);
            return result;
        }
        return SaResult.error("身份异常");
    }

    @SaCheckRole("instructor")
    @RequestMapping("/getSubjectByState/{state}")
    public SaResult getSubjectByState(@PathVariable("state") Integer state){
        SaResult result = SaResult.ok();
        List<Subject> list = subjectService
                .getSubjectByState(Integer.parseInt((String) StpUtil.getLoginId()), state);
        handleSubjectRelevantInfo(list,result);
        return result;
    }

    @GetMapping("/getSubjectHaveOpeningReport")
    @SaCheckRole("instructor")
    public SaResult getSubjectHaveOpeningReport(){
        SaResult result = SaResult.ok();
        List<Subject> list =
                getSubjectByStateAndInstructorId(SubjectStateUtils.reviewOpeningReport());
        if (list.size()!=0){
            handleSubjectRelevantInfo(list,result);
        }else {
            result.setData("暂无开题报告");
        }
        return result;
    }

    @GetMapping("/getSubjectHaveThesis")
    @SaCheckRole("instructor")
    public SaResult getSubjectHaveThesis(){
        SaResult result = SaResult.ok();
        List<Subject> list =
                getSubjectByStateAndInstructorId(SubjectStateUtils.reviewThesis());
        if (list.size()!=0){
            handleSubjectRelevantInfo(list,result);
        }else {
            result.setData("暂无论文定稿");
        }
        return result;
    }

    @SaCheckRole("instructor")
    @RequestMapping("/countSubjectState")
    public SaResult countSubjectState(){
        SaResult result = SaResult.ok();
        Integer id = Integer.parseInt((String) StpUtil.getLoginId());
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        for (SubjectState subjectState : subjectStateService.list()) {
            String stateString = SubjectStateUtils.getStateString(subjectState.getStateId());
            Integer num = subjectService.countNumByState(id, subjectState.getStateId());
            map.put(stateString,num);
        }
        return result.set("countResult",map);
    }

    @SaCheckRole(value = {"instructor","student"},mode = SaMode.OR)
    @GetMapping("/getSubjectIntroduction/{subjectId}")
    public SaResult getSubjectIntroduction(@PathVariable("subjectId") Integer subjectId){
        String subjectIntroduction = subjectService.getSubjectIntroduction(subjectId);
        Subject subject = subjectService.getById(subjectId);
        if (!StpUtil.getLoginId().equals(subject.getStuId()+"")
                &&!StpUtil.getLoginId().equals(subject.getInstructorId()+"")){
            return SaResult.error("非本人查询");
        }
        if (subjectIntroduction!=null&&subjectIntroduction.length()!=0){
            return SaResult.ok().set("introduction",subjectIntroduction);
        }
        return SaResult.error();
    }

    @SaCheckRole("student")
    @PostMapping("/commitSubject")
    public SaResult commitSubject(@RequestBody HashMap<String,Object> param){
        String introduction = (String) param.get("introduction");
        String instructorIdString = (String) param.get("instructorId");
        String subjectName = (String) param.get("subjectName");
        if (introduction==null||introduction.length()==0
                ||instructorIdString==null||instructorIdString.length()==0
                ||subjectName==null||subjectName.length()==0){
            return SaResult.error("信息不全");
        }

        Subject subject = new Subject();
        try{
            subject.setInstructorId(Integer.parseInt(instructorIdString));
        }catch (Exception e){
            return SaResult.error("非法信息");
        }
        subject.setSubjectName(subjectName);
        if (subjectService.isSubjectExist(Integer.parseInt((String) StpUtil.getLoginId()))){
            return SaResult.error().setData("已有课题");
        }
        subject.setApplicationTime(new Date(System.currentTimeMillis()));
        subject.setStuId(Integer.parseInt((String) StpUtil.getLoginId()));
        subject.setSubjectState(SubjectStateUtils.applyTopic());
        boolean save = subjectService.save(subject);
        if (save){
            boolean saveIntroduction = subjectService
                    .saveSubjectIntroduction(subject.getSubjectId(), introduction);
            if (saveIntroduction){
                return SaResult.ok();
            }else {
                subjectService.removeById(subject.getSubjectId());
            }
        }
        return SaResult.error();
    }

    @SaCheckRole("student")
    @RequestMapping("/updateSubject")
    public SaResult updateSubject(@RequestBody HashMap<String,Object> param){
        Integer subjectId = (Integer) param.get("subjectId");
        String subjectName = (String) param.get("subjectName");
        String introduction = (String) param.get("introduction");
        Subject subject = subjectService.getById(subjectId);
        if (subject==null
            ||!SubjectStateUtils.isTopicModify(subject.getSubjectState())){
            return SaResult.error("非法操作");
        }
        if (StpUtil.getLoginId().equals(subject.getStuId()+"")){
            subject.setSubjectState(SubjectStateUtils.applyTopic());
            boolean update = subjectService
                    .updateSubjectNameById(subjectId, subjectName);
            if (update){
                if (subjectService.updateSubjectIntroduction(subjectId, introduction)) {
                    if (subjectService.updateStateBySubjectId(subjectId, SubjectStateUtils.applyTopic())) {
                        return SaResult.ok();
                    }
                }else {
                    subjectService
                            .updateSubjectNameById(subject.getSubjectId(), subject.getSubjectName());
                }
            }
            return SaResult.error();
        }
        return SaResult.error("非本人");
    }

    @SaCheckRole("instructor")
    @GetMapping("/deleteSubject/{subjectId}")
    public SaResult deleteSubject(@PathVariable("subjectId") Integer subjectId){
        Integer instructorId = subjectService.getInstructorIdBySubjectId(subjectId);
        Subject subject = subjectService.getById(subjectId);
        if (subject==null){
            return SaResult.error("课题不存在");
        }
        if (StpUtil.getLoginId().equals(instructorId+"")){
            if (subjectService.removeById(subjectId)) {
                if (subjectService.removeSubjectIntroduction(subjectId)) {
                    return SaResult.ok();
                }else {
                    subjectService.save(subject);
                }
            }
        }
        return SaResult.error("");
    }

    @SaCheckRole(value = {"instructor","student"},mode = SaMode.OR)
    @GetMapping("/updateSubjectState/{subjectId}/{state}")
    public SaResult updateSubjectState(@PathVariable("subjectId") Integer subjectId
            , @PathVariable("state")Integer state){
        String role = StpUtil.getRoleList().get(0);
        Subject subject = subjectService.getById(subjectId);
        if (!StpUtil.getLoginId().equals(subject.getStuId()+"")
                &&!StpUtil.getLoginId().equals(subject.getInstructorId()+"")){
            return SaResult.error();
        }
        Integer currentState = subject.getSubjectState();
        System.out.println(currentState);
        System.out.println(state);
        if (RoleUtils.isStudent(role)){
            if ((SubjectStateUtils.isTopicModify(currentState)
                    &&SubjectStateUtils.isTopicReview(state))
                ||(SubjectStateUtils.isOpeningReportReview(state)
                    &&(SubjectStateUtils.isOpeningReportCommit(currentState)
                        ||SubjectStateUtils.isOpeningReportModify(currentState)))
                ||(SubjectStateUtils.isThesisReview(state)
                    &&(SubjectStateUtils.isThesisCommit(currentState)
                        ||SubjectStateUtils.isThesisModify(currentState)))
            ){
                boolean update = subjectService.updateStateBySubjectId(subjectId, state);
                return update? SaResult.ok() : SaResult.error();
            }
        }
        if (RoleUtils.isInstructor(role)){
            if ((SubjectStateUtils.isTopicReview(currentState)
                    &&(SubjectStateUtils.isTopicModify(state)
                        ||SubjectStateUtils.isOpeningReportCommit(state)))
                ||(SubjectStateUtils.isOpeningReportReview(currentState)
                    &&(SubjectStateUtils.isOpeningReportModify(state)
                        ||SubjectStateUtils.isThesisCommit(state)))
                ||(SubjectStateUtils.isThesisReview(currentState)
                    &&(SubjectStateUtils.isThesisModify(state))
                        ||SubjectStateUtils.isSubjectFinal(state))
            ){
                boolean update = subjectService.updateStateBySubjectId(subjectId, state);
                return update? SaResult.ok() : SaResult.error();
            }
        }
        return SaResult.error("异常修改");
    }

    @SaCheckRole("student")
    @PostMapping("/fileUp")
    public ModelAndView thesisFileUp(MultipartFile file){
        Subject subject = getSubjectByStuId();
        Student stu = studentService.getById((Serializable) StpUtil.getLoginId());
        String fileName = FileUtils.generateFileName(subject.getSubjectId(), stu.getStuId()
                , stu.getStuName(), subject.getSubjectName());
        boolean save = FileUtils.saveFile(file, fileRoad, fileName);
        if (save) {
            subject.setSubjectState(SubjectStateUtils.reviewThesis());
            boolean update = subjectService.updateById(subject);
        }
        return new ModelAndView("redirect:/index-student");
    }

    @SaCheckRole(value = {"instructor","student"},mode = SaMode.OR)
    @RequestMapping("/fileDown/{subjectId}")
    public ResponseEntity<byte[]> thesisFileDown(@PathVariable Integer subjectId) throws UnsupportedEncodingException {
        Subject subject = subjectService.getById(subjectId);
        if (!StpUtil.getLoginId().equals(subject.getStuId()+"")
                &&!StpUtil.getLoginId().equals(subject.getInstructorId()+"")){
            return null;
        }
        Student stu = studentService.getById(subject.getStuId());
        String fileName = FileUtils.generateFileName(subject.getSubjectId(), stu.getStuId()
                , stu.getStuName(), subject.getSubjectName());
        String fileFullName = FileUtils.getFileFullName(fileRoad, fileName);
        byte[] bytes = FileUtils.getFileBytes(fileRoad, fileFullName);
        //创建响应头
        MultiValueMap<String,String> headers = new HttpHeaders();
        //下载方式和文件名
        headers.add("Content-Disposition","attachment;filename="+fileFullName);
        //设置响应码
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        return new ResponseEntity<>(bytes, headers, statusCode);
    }

    private Subject getSubjectByStuId(){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("stu_id", StpUtil.getLoginId());
        return subjectService.getOne(wrapper);
    }

    private List<Subject> getSubjectsByInstructorId(){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("instructor_id",StpUtil.getLoginId());
        return subjectService.list(wrapper);
    }

    private List<Subject> getSubjectByStateAndInstructorId(Integer state){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("instructor_id",StpUtil.getLoginId())
                .ge("subject_state",state);
        return  subjectService.list(wrapper);
    }

    /**
     * 查询关联信息并保存到返回结果
     */
    private void handleSubjectRelevantInfo(List<Subject> list, SaResult result){
        if (list.size()==0){
            return;
        }
        ArrayList<Subject> subjectList = new ArrayList<>();
        ArrayList<String> subjectStateList = new ArrayList<>();
        ArrayList<Student> studentList = new ArrayList<>();
        ArrayList<String> stuClassList = new ArrayList<>();
        for (Subject subject : list){
            Student stu = studentService.getById(subject.getStuId());
            String stateString = SubjectStateUtils.getStateString(subject.getSubjectState());
            String stuClass = studentService.getStuClassByStuId(stu.getStuId());
            subjectList.add(subject);
            subjectStateList.add(stateString);
            studentList.add(stu);
            stuClassList.add(stuClass);
        }
        Instructor instructor = instructorService.getById(list.get(0).getInstructorId());
        String collegeName = instructorService
                .getCollegeNameByInstructorId(instructor.getInstructorId());
        result.set("subject",subjectList)
                .set("subjectState",subjectStateList)
                .set("student",studentList)
                .set("stuClass",stuClassList)
                .set("instructorId",instructor.getInstructorId())
                .set("instructorName",instructor.getInstructorName())
                .set("instructorCollege",collegeName);
    }

    private void handleSubjectRelevantInfo(Subject subject, SaResult result) {
        String stateString = SubjectStateUtils.getStateString(subject.getSubjectState());
        Student stu = studentService.getById(subject.getStuId());
        String stuClass = studentService.getStuClassByStuId(stu.getStuId());
        Instructor instructor = instructorService.getById(subject.getInstructorId());
        String collegeName = instructorService
                .getCollegeNameByInstructorId(instructor.getInstructorId());
        result.set("subject",subject)
                .set("subjectState",stateString)
                .set("student",stu)
                .set("stuClass",stuClass)
                .set("instructorId",instructor.getInstructorId())
                .set("instructorName",instructor.getInstructorName())
                .set("instructorCollege",collegeName);
    }
}
