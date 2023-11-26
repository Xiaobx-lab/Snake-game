package com.ls.bean;

public class Player {
	private Integer pid;
	private String pname;
	private String pass;
	private String pnick;
	
	public Player() {
		
	}
	
	public Player(Integer pid, String pname, String pass, String pnick) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pass = pass;
		this.pnick = pnick;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPnick() {
		return pnick;
	}
	public void setPnick(String pnick) {
		this.pnick = pnick;
	}
	@Override
	public String toString() {
		return "playerDao [pid=" + pid + ", pname=" + pname + ", pass=" + pass + ", pnick=" + pnick + "]";
	}

	public Player login(Player p) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
