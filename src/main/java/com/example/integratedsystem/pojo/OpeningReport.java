package com.example.integratedsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName opening_report
 */
@TableName(value ="opening_report")
public class OpeningReport implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer subjectId;

    /**
     * 
     */
    private String significance;

    /**
     * 
     */
    private String solvedProblems;

    /**
     * 
     */
    private String researchPlan;

    /**
     * 
     */
    private String innovation;

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
    public String getSignificance() {
        return significance;
    }

    /**
     * 
     */
    public void setSignificance(String significance) {
        this.significance = significance;
    }

    /**
     * 
     */
    public String getSolvedProblems() {
        return solvedProblems;
    }

    /**
     * 
     */
    public void setSolvedProblems(String solvedProblems) {
        this.solvedProblems = solvedProblems;
    }

    /**
     * 
     */
    public String getResearchPlan() {
        return researchPlan;
    }

    /**
     * 
     */
    public void setResearchPlan(String researchPlan) {
        this.researchPlan = researchPlan;
    }

    /**
     * 
     */
    public String getInnovation() {
        return innovation;
    }

    /**
     * 
     */
    public void setInnovation(String innovation) {
        this.innovation = innovation;
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
        OpeningReport other = (OpeningReport) that;
        return (this.getSubjectId() == null ? other.getSubjectId() == null : this.getSubjectId().equals(other.getSubjectId()))
            && (this.getSignificance() == null ? other.getSignificance() == null : this.getSignificance().equals(other.getSignificance()))
            && (this.getSolvedProblems() == null ? other.getSolvedProblems() == null : this.getSolvedProblems().equals(other.getSolvedProblems()))
            && (this.getResearchPlan() == null ? other.getResearchPlan() == null : this.getResearchPlan().equals(other.getResearchPlan()))
            && (this.getInnovation() == null ? other.getInnovation() == null : this.getInnovation().equals(other.getInnovation()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSubjectId() == null) ? 0 : getSubjectId().hashCode());
        result = prime * result + ((getSignificance() == null) ? 0 : getSignificance().hashCode());
        result = prime * result + ((getSolvedProblems() == null) ? 0 : getSolvedProblems().hashCode());
        result = prime * result + ((getResearchPlan() == null) ? 0 : getResearchPlan().hashCode());
        result = prime * result + ((getInnovation() == null) ? 0 : getInnovation().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", subjectId=").append(subjectId);
        sb.append(", significance=").append(significance);
        sb.append(", solvedProblems=").append(solvedProblems);
        sb.append(", researchPlan=").append(researchPlan);
        sb.append(", innovation=").append(innovation);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}