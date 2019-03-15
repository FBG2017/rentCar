package com.fbg.cn.rent_springboot.dao.model.wrap;

import java.io.Serializable;

public class UserWrap implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private Integer userId;
	 private String userName;
	 private String password;
	 private byte role;
	 private String IP;
	 private String tokens;
	 private boolean isRemember;
	public boolean isRemember() {
		return isRemember;
	}
	public void setRemember(boolean isRemember) {
		this.isRemember = isRemember;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public byte getRole() {
		return role;
	}
	public void setRole(byte role) {
		this.role = role;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getTokens() {
		return tokens;
	}
	public void setTokens(String tokens) {
		this.tokens = tokens;
	}
}
