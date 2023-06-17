package com.fpoly.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tai_khoan")
public class TaiKhoan {
	@Id
	@Size(max = 50)
	@Column(name = "email", nullable = false, length = 50)
	@NotBlank(message = "{Blank.Email}")
	private String email;

	@Size(max = 10)
	@Column(name = "mat_khau", length = 10)
	@NotBlank(message = "{Blank.Password}")
	private String matKhau;

	@Column(name = "phan_quyen")
	private Boolean phanQuyen;

	@Column(name = "trang_thai")
	private Boolean trangThai;

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

	public Boolean getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
	}

	public TaiKhoan(@Size(max = 50) String email, @Size(max = 10) String matKhau, Boolean phanQuyen,
			Boolean trangThai) {
		super();
		this.email = email;
		this.matKhau = matKhau;
		this.phanQuyen = phanQuyen;
		this.trangThai = trangThai;
	}

	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}

}