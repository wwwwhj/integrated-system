package com.example.integratedsystem;

import cn.dev33.satoken.util.SaResult;
import com.example.integratedsystem.controller.InfoController;
import com.example.integratedsystem.controller.UserController;
import com.example.integratedsystem.pojo.College;
import com.example.integratedsystem.pojo.StuClass;
import com.example.integratedsystem.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class IntegratedSystemApplicationTests {

    @Resource
    UserController userController;
    @Resource
    InfoController infoController;
    
    @Test
    void addUser() {
        SaResult allClass = infoController.getAllClass();
        SaResult allCollege = infoController.getAllCollege();
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setUserId(5000+i);
            user.setUserPassword(5000+i+"");
            user.setUserName(UUID.randomUUID().toString().substring(0,5));
            double random = Math.random();
//            if (random<0.1){
//                user.setUserType(0);
//            }else if (random<0.3){
//                user.setUserType(1);
//                int index = ((int)(Math.random() * 100) )% ( (List<College>)(allCollege.getData())).size();
//                College college = ((List<College>) (allCollege.getData())).get(index);
//                userController.addUser(user,college.getCollegeId());
//            }else {
//                user.setUserType(2);
//                int index = ((int)(Math.random() * 100) )% ( (List<StuClass>)(allClass.getData())).size();
//                StuClass stuClass = ((List<StuClass>) (allClass.getData())).get(index);
//                userController.addUser(user,stuClass.getClassId());
//            }
        }
    }

    @Test
    void addSubject() {

    }

}
