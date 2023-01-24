package com.example.integratedsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.integratedsystem.pojo.College;
import com.example.integratedsystem.service.CollegeService;
import com.example.integratedsystem.mapper.CollegeMapper;
import org.springframework.stereotype.Service;

/**
* @author 伍
* @description 针对表【college】的数据库操作Service实现
* @createDate 2023-01-09 16:05:25
*/
@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College>
    implements CollegeService{

}




