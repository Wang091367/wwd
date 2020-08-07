package com.xiaoshu.entity;

import java.io.Serializable;
import javax.persistence.*;

public class Teacher implements Serializable {
    @Id
    private Integer tid;

    private String tname;

    private static final long serialVersionUID = 1L;

    /**
     * @return tid
     */
    public Integer getTid() {
        return tid;
    }

    /**
     * @param tid
     */
    public void setTid(Integer tid) {
        this.tid = tid;
    }

    /**
     * @return tname
     */
    public String getTname() {
        return tname;
    }

    /**
     * @param tname
     */
    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tid=").append(tid);
        sb.append(", tname=").append(tname);
        sb.append("]");
        return sb.toString();
    }
}