package com.example.integratedsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Date;

/**
 * 
 * @TableName subject
 */
@TableName(value ="subject")
public class Subject implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer subjectId;

    /**
     * 
     */
    private String subjectName;

    /**
     * 
     */
    private Integer stuId;

    /**
     * 
     */
    private Integer instructorId;

    /**
     * 
     */
    private Date applicationTime;

    /**
     * 
     */
    private Integer subjectState;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getSubjectId() {
        return subjectId;
    }

    /**
     * 
     */
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * 
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * 
     */
    public Integer getStuId() {
        return stuId;
    }

    /**
     * 
     */
    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    /**
     * 
     */
    public Integer getInstructorId() {
        return instructorId;
    }

    /**
     * 
     */
    public void setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
    }

    /**
     * 
     */
    public Date getApplicationTime() {
        return applicationTime;
    }

    /**
     * 
     */
    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    /**
     * 
     */
    public Integer getSubjectState() {
        return subjectState;
    }

    /**
     * 
     */
    public void setSubjectState(Integer subjectState) {
        this.subjectState = subjectState;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Subject other = (Subject) that;
        return (this.getSubjectId() == null ? other.getSubjectId() == null : this.getSubjectId().equals(other.getSubjectId()))
            && (this.getSubjectName() == null ? other.getSubjectName() == null : this.getSubjectName().equals(other.getSubjectName()))
            && (this.getStuId() == null ? other.getStuId() == null : this.getStuId().equals(other.getStuId()))
            && (this.getInstructorId() == null ? other.getInstructorId() == null : this.getInstructorId().equals(other.getInstructorId()))
            && (this.getApplicationTime() == null ? other.getApplicationTime() == null : this.getApplicationTime().equals(other.getApplicationTime()))
            && (this.getSubjectState() == null ? other.getSubjectState() == null : this.getSubjectState().equals(other.getSubjectState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSubjectId() == null) ? 0 : getSubjectId().hashCode());
        result = prime * result + ((getSubjectName() == null) ? 0 : getSubjectName().hashCode());
        result = prime * result + ((getStuId() == null) ? 0 : getStuId().hashCode());
        result = prime * result + ((getInstructorId() == null) ? 0 : getInstructorId().hashCode());
        result = prime * result + ((getApplicationTime() == null) ? 0 : getApplicationTime().hashCode());
        result = prime * result + ((getSubjectState() == null) ? 0 : getSubjectState().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", subjectId=").append(subjectId);
        sb.append(", subjectName=").append(subjectName);
        sb.append(", stuId=").append(stuId);
        sb.append(", instructorId=").append(instructorId);
        sb.append(", applicationTime=").append(applicationTime);
        sb.append(", subjectState=").append(subjectState);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}