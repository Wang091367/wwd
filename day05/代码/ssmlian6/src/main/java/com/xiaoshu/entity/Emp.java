package com.xiaoshu.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "e_emp")
public class Emp implements Serializable {
    @Id
    @Column(name = "e_id")
    private Integer eId;

    @Column(name = "e_name")
    private String eName;

    private String gender;

    private String age;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    private String hobby;

    private String pic;

    @Column(name = "d_id")
    private Integer dId;

    private static final long serialVersionUID = 1L;

    /**
     * @return e_id
     */
    public Integer geteId() {
        return eId;
    }

    /**
     * @param eId
     */
    public void seteId(Integer eId) {
        this.eId = eId;
    }

    /**
     * @return e_name
     */
    public String geteName() {
        return eName;
    }

    /**
     * @param eName
     */
    public void seteName(String eName) {
        this.eName = eName == null ? null : eName.trim();
    }

    /**
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * @return age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    /**
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return hobby
     */
    public String getHobby() {
        return hobby;
    }

    /**
     * @param hobby
     */
    public void setHobby(String hobby) {
        this.hobby = hobby == null ? null : hobby.trim();
    }

    /**
     * @return pic
     */
    public String getPic() {
        return pic;
    }

    /**
     * @param pic
     */
    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", eId=").append(eId);
        sb.append(", eName=").append(eName);
        sb.append(", gender=").append(gender);
        sb.append(", age=").append(age);
        sb.append(", birthday=").append(birthday);
        sb.append(", hobby=").append(hobby);
        sb.append(", pic=").append(pic);
        sb.append(", dId=").append(dId);
        sb.append("]");
        return sb.toString();
    }
}