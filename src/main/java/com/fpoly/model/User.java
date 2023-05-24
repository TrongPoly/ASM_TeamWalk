package com.fpoly.model;

import jakarta.validation.constraints.NotBlank;

// hello
public class User {
	@NotBlank(message = "vui lòng nhập username")
	String username;
	String password;
	public User(@NotBlank(message = "vui lòng nhập username") String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public User() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
