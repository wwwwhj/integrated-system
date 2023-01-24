package com.example.integratedsystem.mapper;

import com.example.integratedsystem.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 伍
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-12-12 17:01:32
* @Entity com.example.integratedsystem.pojo.User
*/
public interface UserMapper extends BaseMapper<User> {

    String getUserTypeName(@Param("typeId") Integer typeId);

}




