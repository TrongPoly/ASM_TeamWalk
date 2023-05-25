package com.fpoly.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class KhachHang {
	int maKhachHang;
	String maHang;
	@NotBlank(message = "{Blank.Fullname}")
	String hoTen;
	@NotBlank(message = "{Blank.PhoneNumber")
	@Size(max = 10, message = "{Size.PhoneNumber}")
	String soDienThoai;
	@NotBlank(message = "{Blank.Located}")
	String diaChi;
	@NotBlank(message = "{Blank.Email}")
	@Email(message = "{Format.Email}")
	String email;
	@NotBlank(message = "{Blank.CCCD")
	@Size(max = 12, message = "{Size.CCCD}")
	String soCanCuoc;
	String diemTichLuy;
	String trangThai;

	public int getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(int maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getMaHang() {
		return maHang;
	}

	public void setMaHang(String maHang) {
		this.maHang = maHang;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSoCanCuoc() {
		return soCanCuoc;
	}

	public void setSoCanCuoc(String soCanCuoc) {
		this.soCanCuoc = soCanCuoc;
	}

	public String getDiemTichLuy() {
		return diemTichLuy;
	}

	public void setDiemTichLuy(String diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public KhachHang(int maKhachHang, String maHang, @NotBlank(message = "{Blank.Fullname") String hoTen,
			@NotBlank(message = "{Blank.PhoneNumber") @Size(max = 10, message = "{Size.PhoneNumber}") String soDienThoai,
			@NotBlank(message = "{Blank.Located}") String diaChi,
			@NotBlank(message = "{Blank.Email}") @Email(message = "{Format.Email}") String email,
			@NotBlank(message = "{Blank.CCCD") @Size(max = 12, message = "{Size.CCCD}") String soCanCuoc,
			String diemTichLuy, String trangThai) {
		super();
		this.maKhachHang = maKhachHang;
		this.maHang = maHang;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.email = email;
		this.soCanCuoc = soCanCuoc;
		this.diemTichLuy = diemTichLuy;
		this.trangThai = trangThai;
	}

	public KhachHang() {
		super();
	}

}
