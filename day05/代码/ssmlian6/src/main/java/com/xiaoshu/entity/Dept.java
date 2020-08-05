package com.xiaoshu.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "d_dept")
public class Dept implements Serializable {
    @Id
    @Column(name = "d_id")
    private Integer dId;

    @Column(name = "d_name")
    private String dName;

    private static final long serialVersionUID = 1L;

    /**
     * @return d_id
     */
    public Integer getdId() {
        return dId;
    }

    /**
     * @param dId
     */
    public void setdId(Integer dId) {
        this.dId = dId;
    }

    /**
     * @return d_name
     */
    public String getdName() {
        return dName;
    }

    /**
     * @param dName
     */
    public void setdName(String dName) {
        this.dName = dName == null ? null : dName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dId=").append(dId);
        sb.append(", dName=").append(dName);
        sb.append("]");
        return sb.toString();
    }
}