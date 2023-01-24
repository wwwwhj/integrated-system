package com.example.integratedsystem.controller;

import cn.dev33.satoken.util.SaResult;
import com.example.integratedsystem.service.CollegeService;
import com.example.integratedsystem.service.InstructorService;
import com.example.integratedsystem.service.StuClassService;
import com.example.integratedsystem.service.UserRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Wu Hongjian
 */
@RestController
public class InfoController {
    @Resource
    private StuClassService stuClassService;
    @Resource
    private CollegeService collegeService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private InstructorService instructorService;

    @RequestMapping("/getAllClass")
    public SaResult getAllClass(){
        return SaResult.ok().set("stuClass",stuClassService.list());
    }

    @RequestMapping("/getAllCollege")
    public SaResult getAllCollege(){
        return SaResult.ok().set("college",collegeService.list());
    }

    @RequestMapping("/getAllUserType")
    public SaResult getAllUserType(){
        return SaResult.ok().set("userType",userRoleService.list());
    }

    @RequestMapping("/getAllInstructor")
    public SaResult getAllInstructor(){
        return SaResult.ok().set("instructor",instructorService.list());
    }

}
