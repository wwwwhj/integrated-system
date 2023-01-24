package com.example.integratedsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName student
 */
@TableName(value ="student")
public class Student implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer stuId;

    /**
     * 
     */
    private Integer stuClass;

    /**
     * 
     */
    private String stuName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
    public Integer getStuClass() {
        return stuClass;
    }

    /**
     * 
     */
    public void setStuClass(Integer stuClass) {
        this.stuClass = stuClass;
    }

    /**
     * 
     */
    public String getStuName() {
        return stuName;
    }

    /**
     * 
     */
    public void setStuName(String stuName) {
        this.stuName = stuName;
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
        Student other = (Student) that;
        return (this.getStuId() == null ? other.getStuId() == null : this.getStuId().equals(other.getStuId()))
            && (this.getStuClass() == null ? other.getStuClass() == null : this.getStuClass().equals(other.getStuClass()))
            && (this.getStuName() == null ? other.getStuName() == null : this.getStuName().equals(other.getStuName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStuId() == null) ? 0 : getStuId().hashCode());
        result = prime * result + ((getStuClass() == null) ? 0 : getStuClass().hashCode());
        result = prime * result + ((getStuName() == null) ? 0 : getStuName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", stuId=").append(stuId);
        sb.append(", stuClass=").append(stuClass);
        sb.append(", stuName=").append(stuName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}