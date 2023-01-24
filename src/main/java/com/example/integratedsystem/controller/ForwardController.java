package com.example.integratedsystem.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Wu Hongjian
 */
@Controller
public class ForwardController {

    @RequestMapping("/")
    public String login(){
        return "login";
    }



    @RequestMapping("/index-instructor")
    @SaCheckRole("instructor")
    public String instructorIndex(){
        return "index-instructor";
    }

    @RequestMapping("/subject-instructor")
    @SaCheckRole("instructor")
    public String subjectInstructor(){
        return "subject-instructor";
    }

    @RequestMapping("/openingReport-instructor")
    @SaCheckRole("instructor")
    public String openingReportInstructor(){
        return "openingReport-instructor";
    }

    @RequestMapping("/thesis-instructor")
    @SaCheckRole("instructor")
    public String thesisInstructor(){
        return "thesis-instructor";
    }



    @RequestMapping("/index-student")
    @SaCheckRole("student")
    public String studentIndex(){
        return "index-student";
    }

    @RequestMapping("/subject-student")
    @SaCheckRole("student")
    public String subjectStudent(){
        return "subject-student";
    }

    @RequestMapping("/openingReport-student")
    @SaCheckRole("student")
    public String openingReportStudent(){
        return "openingReport-student";
    }

    @RequestMapping("/test")
    @SaCheckRole("student")
    public String test(){
        return "test";
    }




    @RequestMapping("/user-manager")
    @SaCheckRole("admin")
    public String user(){
        return "user-manager";
    }
}
