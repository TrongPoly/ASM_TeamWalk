package com.fpoly.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "hoa_don")
public class HoaDon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_hoa_don", nullable = false)
	private Long id;

	@Column(name = "ngay_lap")
	private LocalDate ngayLap;

	@Nationalized
	@Lob
	@Column(name = "ghi_chu")
	private String ghiChu;

	@Column(name = "ngay_thanh_toan")
	private LocalDate ngayThanhToan;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trang_thai")
	private TrangThaiHoaDon trangThai;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nguoi_mua")
	private KhachHang nguoiMua;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public LocalDate getNgayThanhToan() {
		return ngayThanhToan;
	}

	public void setNgayThanhToan(LocalDate ngayThanhToan) {
		this.ngayThanhToan = ngayThanhToan;
	}

	public TrangThaiHoaDon getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(TrangThaiHoaDon trangThai) {
		this.trangThai = trangThai;
	}

	public KhachHang getNguoiMua() {
		return nguoiMua;
	}

	public void setNguoiMua(KhachHang nguoiMua) {
		this.nguoiMua = nguoiMua;
	}

	public HoaDon(Long id, LocalDate ngayLap, String ghiChu, LocalDate ngayThanhToan, TrangThaiHoaDon trangThai,
			KhachHang nguoiMua) {
		super();
		this.id = id;
		this.ngayLap = ngayLap;
		this.ghiChu = ghiChu;
		this.ngayThanhToan = ngayThanhToan;
		this.trangThai = trangThai;
		this.nguoiMua = nguoiMua;
	}

	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

}