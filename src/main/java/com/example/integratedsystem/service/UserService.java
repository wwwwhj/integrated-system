package com.example.integratedsystem.service;

import com.example.integratedsystem.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 伍
* @description 针对表【user】的数据库操作Service
* @createDate 2022-12-12 17:01:32
*/
public interface UserService extends IService<User> {

    String getUserTypeName(Integer typeId);

}
