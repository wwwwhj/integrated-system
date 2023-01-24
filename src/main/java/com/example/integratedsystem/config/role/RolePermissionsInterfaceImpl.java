package com.example.integratedsystem.config.role;

import cn.dev33.satoken.stp.StpInterface;
import com.example.integratedsystem.pojo.User;
import com.example.integratedsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wu Hongjian
 */
@Component
public class RolePermissionsInterfaceImpl implements StpInterface {

    @Autowired
    private UserService userService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        User user = userService.getById((Serializable) loginId);
        String userTypeName = userService.getUserTypeName(user.getUserType());
        ArrayList<String> list = new ArrayList<>();
        list.add(userTypeName);
        return list;
    }
}
