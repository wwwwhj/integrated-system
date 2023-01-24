package com.example.integratedsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.integratedsystem.pojo.Student;
import com.example.integratedsystem.service.StudentService;
import com.example.integratedsystem.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author 伍
* @description 针对表【student】的数据库操作Service实现
* @createDate 2022-12-12 17:01:32
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

    @Override
    public String getStuClassByStuId(Integer stuId) {
        return baseMapper.getStuClass(stuId);
    }
}




