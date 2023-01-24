package com.example.integratedsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName instructor
 */
@TableName(value ="instructor")
public class Instructor implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer instructorId;

    /**
     * 
     */
    private String instructorName;

    /**
     * 
     */
    private Integer instructorCollegeId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
    public String getInstructorName() {
        return instructorName;
    }

    /**
     * 
     */
    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    /**
     * 
     */
    public Integer getInstructorCollegeId() {
        return instructorCollegeId;
    }

    /**
     * 
     */
    public void setInstructorCollegeId(Integer instructorCollegeId) {
        this.instructorCollegeId = instructorCollegeId;
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
        Instructor other = (Instructor) that;
        return (this.getInstructorId() == null ? other.getInstructorId() == null : this.getInstructorId().equals(other.getInstructorId()))
            && (this.getInstructorName() == null ? other.getInstructorName() == null : this.getInstructorName().equals(other.getInstructorName()))
            && (this.getInstructorCollegeId() == null ? other.getInstructorCollegeId() == null : this.getInstructorCollegeId().equals(other.getInstructorCollegeId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getInstructorId() == null) ? 0 : getInstructorId().hashCode());
        result = prime * result + ((getInstructorName() == null) ? 0 : getInstructorName().hashCode());
        result = prime * result + ((getInstructorCollegeId() == null) ? 0 : getInstructorCollegeId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", instructorId=").append(instructorId);
        sb.append(", instructorName=").append(instructorName);
        sb.append(", instructorCollegeId=").append(instructorCollegeId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}