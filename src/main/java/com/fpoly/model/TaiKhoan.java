package com.fpoly.model;

import jakarta.validation.constraints.NotEmpty;

public class TaiKhoan {
	@NotEmpty(message = "Không được để trống")
	String userName;
	String password;

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

	public TaiKhoan(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public TaiKhoan() {
		super();
	}

}
