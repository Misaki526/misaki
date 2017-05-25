package com.misaki.account.user.entity;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
	private static final long serialVersionUID = -8659964132448713945L;
	private String userId;
	private String userAccount;
	private String userName;
	private String userPass;
	
	public User() {
		this.userId = "user" + UUID.randomUUID().toString().replace("-", "");
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userAccount=" + userAccount
				+ ", userName=" + userName + ", userPass=" + userPass + "]";
	}
}
