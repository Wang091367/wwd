package com.xiaoshu.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class EmpVo extends Emp{

	private String dName;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date start;
	
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

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date end;

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}
	
}
