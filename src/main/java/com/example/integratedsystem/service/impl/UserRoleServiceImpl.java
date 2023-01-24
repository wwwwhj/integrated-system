package com.example.integratedsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.integratedsystem.pojo.UserRole;
import com.example.integratedsystem.service.UserRoleService;
import com.example.integratedsystem.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 伍
* @description 针对表【user_role】的数据库操作Service实现
* @createDate 2023-01-09 16:13:13
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




