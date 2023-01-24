package com.example.integratedsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.integratedsystem.pojo.Instructor;
import com.example.integratedsystem.service.InstructorService;
import com.example.integratedsystem.mapper.InstructorMapper;
import org.springframework.stereotype.Service;

/**
* @author 伍
* @description 针对表【instructor】的数据库操作Service实现
* @createDate 2022-12-12 17:01:32
*/
@Service
public class InstructorServiceImpl extends ServiceImpl<InstructorMapper, Instructor>
    implements InstructorService{

    @Override
    public String getCollegeNameByInstructorId(Integer instructorId) {
        return baseMapper.getCollegeNameByInstructorId(instructorId);
    }
}




