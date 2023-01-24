package com.example.integratedsystem.mapper;

import com.example.integratedsystem.pojo.StateCount;
import com.example.integratedsystem.pojo.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
* @author 伍
* @description 针对表【subject】的数据库操作Mapper
* @createDate 2022-12-14 21:16:37
* @Entity com.example.integratedsystem.pojo.Subject
*/
public interface SubjectMapper extends BaseMapper<Subject> {

    int getInstructorIdBySubjectId(@Param("subjectId") Integer subjectId);

    boolean isSubjectExist(@Param("stuId")Integer stuId);

    boolean updateStateBySubjectId(@Param("subjectId") Integer subjectId
            , @Param("stateId") Integer stateId);

    String getSubjectIntroduction(@Param("subjectId") Integer subjectId);

    boolean insertSubjectIntroduction(@Param("subjectId") Integer subjectId
            , @Param("introduction") String introduction);

    boolean updateSubjectIntroduction(@Param("subjectId") Integer subjectId
            , @Param("introduction") String introduction);

    boolean deleteIntroductionBySubjectId(@Param("subjectId") Integer subjectId);

    List<StateCount> selectCountByState(@Param("instructorId") Integer instructorId);

    Integer countNumByState(@Param("instructorId") Integer instructorId
            , @Param("state") Integer state);

    boolean updateSubjectNameById(@Param("subjectId") Integer subjectId
            , @Param("subjectName") String subjectName);

    List<Subject> getSubjectByState(@Param("instructorId") Integer instructorId
            , @Param("state") Integer state);

}




