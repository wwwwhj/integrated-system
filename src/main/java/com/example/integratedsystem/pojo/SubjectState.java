package com.example.integratedsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName subject_state
 */
@TableName(value ="subject_state")
public class SubjectState implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer stateId;

    /**
     * 
     */
    private String stateName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getStateId() {
        return stateId;
    }

    /**
     * 
     */
    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    /**
     * 
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * 
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
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
        SubjectState other = (SubjectState) that;
        return (this.getStateId() == null ? other.getStateId() == null : this.getStateId().equals(other.getStateId()))
            && (this.getStateName() == null ? other.getStateName() == null : this.getStateName().equals(other.getStateName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStateId() == null) ? 0 : getStateId().hashCode());
        result = prime * result + ((getStateName() == null) ? 0 : getStateName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", stateId=").append(stateId);
        sb.append(", stateName=").append(stateName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}