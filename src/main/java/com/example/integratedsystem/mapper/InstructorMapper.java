package com.example.integratedsystem.mapper;

import com.example.integratedsystem.pojo.Instructor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 伍
* @description 针对表【instructor】的数据库操作Mapper
* @createDate 2022-12-12 17:01:32
* @Entity com.example.integratedsystem.pojo.Instructor
*/
public interface InstructorMapper extends BaseMapper<Instructor> {

    String getCollegeNameByInstructorId(@Param("instructorId") Integer instructorId);

}




