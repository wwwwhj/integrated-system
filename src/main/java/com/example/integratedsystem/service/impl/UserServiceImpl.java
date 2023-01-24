package com.example.integratedsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.integratedsystem.pojo.User;
import com.example.integratedsystem.service.UserService;
import com.example.integratedsystem.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 伍
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-12-12 17:01:32
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public String getUserTypeName(Integer typeId) {
        return baseMapper.getUserTypeName(typeId);
    }
}




