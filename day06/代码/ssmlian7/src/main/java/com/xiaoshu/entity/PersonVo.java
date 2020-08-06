package com.xiaoshu.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PersonVo extends Person{

	
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date start;
    
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date end;
    
    
    private String ename;
    
    
    private Integer num;


	public Date getStart() {
		return start;
	}


	public void setStart(Date start) {
		this.start = start;
	}


	public Date getEnd() {
		return end;
	}


	public void setEnd(Date end) {
		this.end = end;
	}


	
	


	public String getEname() {
		return ename;
	}


	public void setEname(String ename) {
		this.ename = ename;
	}


	public Integer getNum() {
		return num;
	}


	public void setNum(Integer num) {
		this.num = num;
	}
    
}
