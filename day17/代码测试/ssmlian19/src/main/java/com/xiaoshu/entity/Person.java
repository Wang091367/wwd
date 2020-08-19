package com.xiaoshu.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "p_person")
public class Person implements Serializable {
    @Id
    private Integer pid;

    @Column(name = "p_name")
    private String pName;

    @Column(name = "p_gender")
    private String pGender;

    @Column(name = "p_cid")
    private Integer pCid;

    @Column(name = "p_salary")
    private String pSalary;

    @Column(name = "p_age")
    private Integer pAge;

    @Column(name = "p_entrytime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date pEntrytime;

    private static final long serialVersionUID = 1L;

    /**
     * @return pid
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * @return p_name
     */
    public String getpName() {
        return pName;
    }

    /**
     * @param pName
     */
    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    /**
     * @return p_gender
     */
    public String getpGender() {
        return pGender;
    }

    /**
     * @param pGender
     */
    public void setpGender(String pGender) {
        this.pGender = pGender == null ? null : pGender.trim();
    }

    /**
     * @return p_cid
     */
    public Integer getpCid() {
        return pCid;
    }

    /**
     * @param pCid
     */
    public void setpCid(Integer pCid) {
        this.pCid = pCid;
    }

    /**
     * @return p_salary
     */
    public String getpSalary() {
        return pSalary;
    }

    /**
     * @param pSalary
     */
    public void setpSalary(String pSalary) {
        this.pSalary = pSalary == null ? null : pSalary.trim();
    }

    /**
     * @return p_age
     */
    public Integer getpAge() {
        return pAge;
    }

    /**
     * @param pAge
     */
    public void setpAge(Integer pAge) {
        this.pAge = pAge;
    }

    /**
     * @return p_entrytime
     */
    public Date getpEntrytime() {
        return pEntrytime;
    }

    /**
     * @param pEntrytime
     */
    public void setpEntrytime(Date pEntrytime) {
        this.pEntrytime = pEntrytime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pid=").append(pid);
        sb.append(", pName=").append(pName);
        sb.append(", pGender=").append(pGender);
        sb.append(", pCid=").append(pCid);
        sb.append(", pSalary=").append(pSalary);
        sb.append(", pAge=").append(pAge);
        sb.append(", pEntrytime=").append(pEntrytime);
        sb.append("]");
        return sb.toString();
    }
}