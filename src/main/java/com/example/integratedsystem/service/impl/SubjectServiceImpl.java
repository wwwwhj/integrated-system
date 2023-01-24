package com.example.integratedsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.integratedsystem.pojo.StateCount;
import com.example.integratedsystem.pojo.Subject;
import com.example.integratedsystem.service.SubjectService;
import com.example.integratedsystem.mapper.SubjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @author 伍
* @description 针对表【subject】的数据库操作Service实现
* @createDate 2022-12-14 21:16:37
*/
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject>
    implements SubjectService{

    @Override
    public Integer getInstructorIdBySubjectId(Integer subjectId) {
        return baseMapper.getInstructorIdBySubjectId(subjectId);
    }

    @Override
    public boolean isSubjectExist(Integer stuId) {
        return baseMapper.isSubjectExist(stuId);
    }

    @Override
    public boolean updateStateBySubjectId(Integer subjectId, Integer stateId) {
        return baseMapper.updateStateBySubjectId(subjectId,stateId);
    }

    @Override
    public String getSubjectIntroduction(Integer subjectId) {
        return baseMapper.getSubjectIntroduction(subjectId);
    }

    @Override
    public boolean saveSubjectIntroduction(Integer subjectId, String introduction) {
        return baseMapper.insertSubjectIntroduction(subjectId,introduction);
    }

    @Override
    public boolean updateSubjectIntroduction(Integer subjectId, String introduction) {
        return baseMapper.updateSubjectIntroduction(subjectId, introduction);
    }

    @Override
    public boolean removeSubjectIntroduction(Integer subjectId) {
        return baseMapper.deleteIntroductionBySubjectId(subjectId);
    }

    @Override
    public List<StateCount> countCountByState(Integer instructorId) {
        return baseMapper.selectCountByState(instructorId);
    }

    @Override
    public Integer countNumByState(Integer instructorId, Integer state) {
        return baseMapper.countNumByState(instructorId,state);
    }

    @Override
    public boolean updateSubjectNameById(Integer subjectId, String subjectName) {
        return baseMapper.updateSubjectNameById(subjectId, subjectName);
    }

    @Override
    public List<Subject> getSubjectByState(Integer instructorId, Integer state) {
        return baseMapper.getSubjectByState(instructorId, state);
    }
}




