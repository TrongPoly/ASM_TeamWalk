package com.fpoly.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "gio_hang_chi_tiet")
public class GioHangChiTiet {
	@EmbeddedId
	private GioHangChiTietId id;

	@MapsId("maGioHang")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ma_gio_hang", nullable = false)
	private GioHang maGioHang;

	@MapsId("maSanPham")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ma_san_pham", nullable = false)
	private SanPham maSanPham;

	@Column(name = "so_luong")
	private Integer soLuong;

	public GioHangChiTietId getId() {
		return id;
	}

	public void setId(GioHangChiTietId id) {
		this.id = id;
	}

	public GioHang getMaGioHang() {
		return maGioHang;
	}

	public void setMaGioHang(GioHang maGioHang) {
		this.maGioHang = maGioHang;
	}

	public SanPham getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(SanPham maSanPham) {
		this.maSanPham = maSanPham;
	}

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	public GioHangChiTiet(GioHangChiTietId id, GioHang maGioHang, SanPham maSanPham, Integer soLuong) {
		super();
		this.id = id;
		this.maGioHang = maGioHang;
		this.maSanPham = maSanPham;
		this.soLuong = soLuong;
	}

	public GioHangChiTiet() {
		super();
		// TODO Auto-generated constructor stub
	}

}