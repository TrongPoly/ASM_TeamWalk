package com.fpoly.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "hoa_don_chi_tiet")
public class HoaDonChiTiet {
	@EmbeddedId
	private HoaDonChiTietId id;

	@MapsId("maHoaDon")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ma_hoa_don", nullable = false)
	private HoaDon maHoaDon;

	@MapsId("maSanPham")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ma_san_pham", nullable = false)
	private SanPham maSanPham;

	@Column(name = "so_luong")
	private Integer soLuong;

	@Column(name = "don_gia", precision = 19, scale = 4)
	private BigDecimal donGia;

	@Nationalized
	@Lob
	@Column(name = "ghi_chu")
	private String ghiChu;

	public HoaDonChiTietId getId() {
		return id;
	}

	public void setId(HoaDonChiTietId id) {
		this.id = id;
	}

	public HoaDon getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(HoaDon maHoaDon) {
		this.maHoaDon = maHoaDon;
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

	public BigDecimal getDonGia() {
		return donGia;
	}

	public void setDonGia(BigDecimal donGia) {
		this.donGia = donGia;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public HoaDonChiTiet(HoaDonChiTietId id, HoaDon maHoaDon, SanPham maSanPham, Integer soLuong, BigDecimal donGia,
			String ghiChu) {
		super();
		this.id = id;
		this.maHoaDon = maHoaDon;
		this.maSanPham = maSanPham;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.ghiChu = ghiChu;
	}

	public HoaDonChiTiet() {
		super();
		// TODO Auto-generated constructor stub
	}

}