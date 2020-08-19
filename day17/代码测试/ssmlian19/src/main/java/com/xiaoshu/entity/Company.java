package com.xiaoshu.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "p_company")
public class Company implements Serializable {
    @Id
    private Integer cid;

    @Column(name = "c_name")
    private String cName;

    private static final long serialVersionUID = 1L;

    /**
     * @return cid
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * @param cid
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * @return c_name
     */
    public String getcName() {
        return cName;
    }

    /**
     * @param cName
     */
    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cid=").append(cid);
        sb.append(", cName=").append(cName);
        sb.append("]");
        return sb.toString();
    }
}