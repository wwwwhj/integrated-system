package com.example.integratedsystem.service;

import com.example.integratedsystem.pojo.StateCount;
import com.example.integratedsystem.pojo.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
* @author 伍
* @description 针对表【subject】的数据库操作Service
* @createDate 2022-12-14 21:16:37
*/
public interface SubjectService extends IService<Subject> {

    Integer getInstructorIdBySubjectId(Integer subjectId);

    boolean isSubjectExist(Integer stuId);

    boolean updateStateBySubjectId(Integer subjectId, Integer stateId);

    String getSubjectIntroduction(Integer subjectId);

    boolean saveSubjectIntroduction(Integer subjectId, String introduction);

    boolean updateSubjectIntroduction(Integer subjectId, String introduction);

    boolean removeSubjectIntroduction(Integer subjectId);

    List<StateCount> countCountByState(Integer instructorId);

    Integer countNumByState(Integer instructorId, Integer state);

    boolean updateSubjectNameById(Integer subjectId, String subjectName);

    List<Subject> getSubjectByState(Integer instructorId, Integer state);

}
