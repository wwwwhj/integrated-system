package com.example.integratedsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName user_role
 */
@TableName(value ="user_role")
public class UserRole implements Serializable {
    /**
     * 
     */
    private Integer userTypeId;

    /**
     * 
     */
    private String userTypeName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getUserTypeId() {
        return userTypeId;
    }

    /**
     * 
     */
    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    /**
     * 
     */
    public String getUserTypeName() {
        return userTypeName;
    }

    /**
     * 
     */
    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
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
        UserRole other = (UserRole) that;
        return (this.getUserTypeId() == null ? other.getUserTypeId() == null : this.getUserTypeId().equals(other.getUserTypeId()))
            && (this.getUserTypeName() == null ? other.getUserTypeName() == null : this.getUserTypeName().equals(other.getUserTypeName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserTypeId() == null) ? 0 : getUserTypeId().hashCode());
        result = prime * result + ((getUserTypeName() == null) ? 0 : getUserTypeName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userTypeId=").append(userTypeId);
        sb.append(", userTypeName=").append(userTypeName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}