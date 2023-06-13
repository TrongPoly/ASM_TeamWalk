package com.fpoly.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ReportDoanhThu implements java.io.Serializable {
	@Id
	String tenLoaiHang;
	long soLuong;
	BigDecimal tongTien;

	public String getTenLoaiHang() {
		return tenLoaiHang;
	}

	public void setTenLoaiHang(String tenLoaiHang) {
		this.tenLoaiHang = tenLoaiHang;
	}

	public long getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(long soLuong) {
		this.soLuong = soLuong;
	}

	public BigDecimal getTongTien() {
		return tongTien;
	}

	public void setTongTien(BigDecimal tongTien) {
		this.tongTien = tongTien;
	}

	public ReportDoanhThu(String tenLoaiHang, long soLuong, BigDecimal tongTien) {
		super();
		this.tenLoaiHang = tenLoaiHang;
		this.soLuong = soLuong;
		this.tongTien = tongTien;
	}

	public ReportDoanhThu() {
		super();
		// TODO Auto-generated constructor stub
	}

}
