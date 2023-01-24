package com.example.integratedsystem.util;

import java.util.Arrays;
import java.util.List;

/**
 * @author Wu Hongjian
 */
public enum SubjectStateUtils {

    TOPIC_REVIEW(2,"待审核课题申请"),
    TOPIC_MODIFY(3,"待修改课题申请"),

    OPENING_REPORT_COMMIT(11,"待提交开题报告"),
    OPENING_REPORT_REVIEW(12,"待审核开题报告"),
    OPENING_REPORT_MODIFY(13,"待修改开题报告"),

    THESIS_COMMIT(21,"待提交论文定稿"),
    THESIS_REVIEW(22,"待审核论文定稿"),
    THESIS_MODIFY(23,"待修改论文定稿"),

    SUBJECT_FINAL(100,"课题完成");

    private int stateId;
    private String stateString;

    private static List<SubjectStateUtils> states =Arrays.asList(
            TOPIC_REVIEW, TOPIC_MODIFY
            , OPENING_REPORT_COMMIT, OPENING_REPORT_REVIEW, OPENING_REPORT_MODIFY
            , THESIS_COMMIT, THESIS_REVIEW, THESIS_MODIFY
            , SUBJECT_FINAL);

    private SubjectStateUtils(int stateId, String stateString) {
        this.stateId = stateId;
        this.stateString = stateString;
    }

    public static String getStateString(int stateId){
        for (SubjectStateUtils state : states){
            if (state.stateId==stateId){
                return state.stateString;
            }
        }
        return "";
    }

    public static boolean isTopicReview(int stateId){
        return TOPIC_REVIEW.stateId==stateId;
    }
    public static boolean isTopicModify(int stateId){
        return TOPIC_MODIFY.stateId==stateId;
    }
    public static boolean isOpeningReportCommit(int stateId){
        return OPENING_REPORT_COMMIT.stateId==stateId;
    }
    public static boolean isOpeningReportReview(int stateId){
        return OPENING_REPORT_REVIEW.stateId==stateId;
    }
    public static boolean isOpeningReportModify(int stateId){
        return OPENING_REPORT_MODIFY.stateId==stateId;
    }
    public static boolean isThesisCommit(int stateId){
        return THESIS_COMMIT.stateId==stateId;
    }

    public static boolean isThesisReview(int stateId){
        return THESIS_REVIEW.stateId==stateId;
    }

    public static boolean isThesisModify(int stateId){
        return THESIS_MODIFY.stateId==stateId;
    }
    public static boolean isSubjectFinal(int stateId){
        return SUBJECT_FINAL.stateId==stateId;
    }

    public static Integer applyTopic(){
        return TOPIC_REVIEW.stateId;
    }
    public static Integer reviewOpeningReport(){
        return OPENING_REPORT_REVIEW.stateId;
    }
    public static Integer reviewThesis(){
        return THESIS_REVIEW.stateId;
    }


}
