package com.fpoly.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ReportSanPham {
	@Id
	String tenSP;
	long soLuong;
	long soLuongTon;

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public long getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(long soLuong) {
		this.soLuong = soLuong;
	}

	public long getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(long soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public ReportSanPham(String tenSP, long soLuong, long soLuongTon) {
		super();
		this.tenSP = tenSP;
		this.soLuong = soLuong;
		this.soLuongTon = soLuongTon;
	}

	public ReportSanPham() {
		super();
		// TODO Auto-generated constructor stub
	}

}
