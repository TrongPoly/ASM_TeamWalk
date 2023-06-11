package com.fpoly.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "san_pham")
public class SanPham {
	@Id
	@Column(name = "ma_san_pham", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 100)
	@Nationalized
	@Column(name = "ten_san_pham", length = 100)
	private String tenSanPham;

	@Size(max = 50)
	@Column(name = "anh_san_pham", length = 50)
	private String anhSanPham;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ma_loai")
	private LoaiSanPham maLoai;

	@Column(name = "don_gia")
	private Integer donGia;

	@Column(name = "so_luong_ton")
	private Integer soLuongTon;

	@Column(name = "trang_thai")
	private Boolean trangThai;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public String getAnhSanPham() {
		return anhSanPham;
	}

	public void setAnhSanPham(String anhSanPham) {
		this.anhSanPham = anhSanPham;
	}

	public LoaiSanPham getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(LoaiSanPham maLoai) {
		this.maLoai = maLoai;
	}

	public Integer getDonGia() {
		return donGia;
	}

	public void setDonGia(Integer donGia) {
		this.donGia = donGia;
	}

	public Integer getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(Integer soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public Boolean getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
	}
	

}