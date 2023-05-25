package com.fpoly.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class TaiKhoan {

	@NotBlank(message = "{Blank.Email}")
	@Email(message = "{Format.Email}")
	String email;
	@NotBlank(message = "{Blank.Password}")
	String password;
	Boolean rule;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getRule() {
		return rule;
	}

	public void setRule(Boolean rule) {
		this.rule = rule;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TaiKhoan() {
		super();
	}

	public TaiKhoan(String email, String password, Boolean rule) {
		super();
		this.email = email;
		this.password = password;
		this.rule = rule;
	}

}
