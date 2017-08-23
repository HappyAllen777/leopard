package com.geelun.framework.base.user.model;

import com.geelun.framework.base.superclass.model.BaseModel;
import com.geelun.framework.persistence.annotation.StringColumnDescriptor;
import com.geelun.framework.persistence.column.StringColumnType;

public class User extends BaseModel {
	private static final long serialVersionUID = -2488564255992290496L;

	public String getEmail() {
		return email;
	}

	public String getNickname() {
		return nickname;
	}

	public String getPassword() {
		return password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public String getTableName() {
		return "s_user";
	}

	public String getUserName() {
		return userName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@StringColumnDescriptor(name = "email", maxLen = 32, minLen = 1, type = { StringColumnType.UNFIXEDLEN })
	private String email;

	@StringColumnDescriptor(name = "nickname", maxLen = 32, minLen = 1, type = { StringColumnType.UNFIXEDLEN })
	private String nickname;

	@StringColumnDescriptor(name = "userName", maxLen = 16, minLen = 1, type = { StringColumnType.UNFIXEDLEN })
	private String password;

	@StringColumnDescriptor(name = "phoneNumber", type = { StringColumnType.PHONENUMBER })
	private String phoneNumber;

	@StringColumnDescriptor(name = "userName", maxLen = 32, minLen = 1, type = { StringColumnType.UNFIXEDLEN })
	private String userName;

}
