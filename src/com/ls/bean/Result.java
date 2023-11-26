package com.ls.bean;

import java.util.Date;

public class Result {
	private Integer pid;
	private Integer rid;
	private int score;
	private Date date;
	
	public Result() {
		
	}

	public Result(Integer pid, Integer rid, int score, Date date) {
		super();
		this.pid = pid;
		this.rid = rid;
		this.score = score;
		this.date = date;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
