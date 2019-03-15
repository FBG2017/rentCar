package com.fbg.cn.rent_springboot.common.token;

public class UserTokenSubject {
	private String userName;
	private String loginIp;
	
	public UserTokenSubject(){
		
	}

	public UserTokenSubject(String userName, String loginIp) {
		this.userName = userName;
		this.loginIp = loginIp;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
}
