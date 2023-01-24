package com.example.integratedsystem.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.example.integratedsystem.pojo.Instructor;
import com.example.integratedsystem.pojo.Student;
import com.example.integratedsystem.pojo.User;
import com.example.integratedsystem.pojo.UserRole;
import com.example.integratedsystem.service.*;
import com.example.integratedsystem.util.RoleUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Wu Hongjian
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private StudentService studentService;
    @Resource
    private InstructorService instructorService;
    @Resource
    private CollegeService collegeService;
    @Resource
    private StuClassService stuClassService;
    @Resource
    private UserRoleService userRoleService;

    @PostMapping("/login")
    public SaResult login(@RequestBody User login) {
        User user = userService.getById(login.getUserId());
        if (user != null && user.getUserPassword().equals(login.getUserPassword())) {
            StpUtil.login(user.getUserId());
            return getUser();
        }
        return SaResult.error();
    }

    @RequestMapping("/logout")
    public ModelAndView logout() {
        StpUtil.logout();
        return new ModelAndView("/login");
    }

    @SaCheckRole("admin")
    @PostMapping("/add")
    public SaResult addUser(@RequestBody HashMap<String,Object> param) {
        Integer userId = Integer.parseInt((String)param.get("userId"));
        String userPassword = (String) param.get("userPassword");
        String userName = (String) param.get("userName");
        Integer userType = Integer.parseInt((String)param.get("userType"));
        Integer belongTo = Integer.parseInt((String)param.get("belongTo"));
        User user = new User();
        user.setUserId(userId);
        user.setUserPassword(userPassword);
        user.setUserName(userName);
        user.setUserType(userType);
        boolean save = userService.save(user);
        if (save) {
            if (RoleUtils.isStudent(user.getUserType())) {
                Student student = new Student();
                student.setStuId(user.getUserId());
                student.setStuClass(belongTo);
                student.setStuName(user.getUserName());
                studentService.save(student);
            } else if (RoleUtils.isInstructor(user.getUserType())) {
                Instructor instructor = new Instructor();
                instructor.setInstructorId(user.getUserId());
                instructor.setInstructorName(user.getUserName());
                instructor.setInstructorCollegeId(belongTo);
                instructorService.save(instructor);
            }
            return SaResult.ok();
        } else {
            userService.removeById(user.getUserId());
        }
        return SaResult.error();
    }

    @SaCheckRole("admin")
    @PostMapping("/delete/{id}")
    public SaResult deleteUser(@PathVariable("id") int id) {
        return userService.removeById(id) ? SaResult.ok() : SaResult.error();
    }

    @SaCheckRole("admin")
    @PostMapping("/update")
    public SaResult updateUser(@RequestBody User user) {
        return userService.updateById(user) ? SaResult.ok() : SaResult.error();
    }

    @RequestMapping("getUser")
    public SaResult getUser() {
        User user = userService.getById((Serializable) StpUtil.getLoginId());
        Integer userId = user.getUserId();
        Integer userType = user.getUserType();
        String userTypeName = userService.getUserTypeName(userType);
        SaResult saResult = SaResult.ok().set("userId", userId)
                .set("name", user.getUserName())
                .set("userType", userTypeName);
        if (RoleUtils.isInstructor(userType)) {
            String college = instructorService.getCollegeNameByInstructorId(userId);
            saResult.set("belongTo", college);
        } else if (RoleUtils.isStudent(userType)) {
            String stuClass = studentService.getStuClassByStuId(userId);
            saResult.set("belongTo", stuClass);
        }
        return saResult;
    }

    @SaCheckRole("admin")
    @RequestMapping("/getAllUsers")
    public SaResult getAllUser() {
        SaResult result = SaResult.ok();
        List<User> users = getUser(result);
        return result.set("users",users);
    }

    @SaCheckRole("admin")
    @RequestMapping("/getAllUser/{pageNum}")
    public SaResult getAllUser(@PathVariable("pageNum") Integer pageNum) {
        SaResult result = SaResult.ok();
        PageHelper.startPage(pageNum, 10);
        List<User> users = getUser(result);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return result.set("pageInfo", pageInfo);
    }

    private List<User> getUser(SaResult result){
        List<User> users = userService.list();
        ArrayList<String> userTypeList = new ArrayList<>();
        ArrayList<String> belongToList = new ArrayList<>();
        for (User user : users) {
            user.setUserPassword("****");
            Integer userType = user.getUserType();
            String userTypeString = RoleUtils.getRoleType(userType);
            String belongTo = "";
            if (RoleUtils.isInstructor(userType)) {
                belongTo = instructorService.getCollegeNameByInstructorId(user.getUserId());
            } else if (RoleUtils.isStudent(userType)) {
                belongTo = studentService.getStuClassByStuId(user.getUserId());
            }
            userTypeList.add(userTypeString);
            belongToList.add(belongTo);
        }
        result.set("userType", userTypeList)
                .set("belongTo", belongToList);
        return users;
    }

}
