package com.fpoly.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tai_khoan")
public class TaiKhoan {
	@Id
	@Size(max = 50)
	@Column(name = "email", nullable = false, length = 50)
	@NotBlank(message = "{Blank.Email}")
	private String email;

	@Size(max = 10, message = "{Size.Password}")
	@Column(name = "mat_khau", length = 10)
	@NotBlank(message = "{Blank.Password}")
	private String matKhau;

	@Column(name = "phan_quyen", columnDefinition = "boolean default false")
	private Boolean phanQuyen;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public Boolean getPhanQuyen() {
		return phanQuyen;
	}

	public void setPhanQuyen(Boolean phanQuyen) {
		this.phanQuyen = phanQuyen;
	}

}