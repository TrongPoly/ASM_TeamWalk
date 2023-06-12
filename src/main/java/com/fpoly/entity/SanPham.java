package com.fpoly.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "san_pham")
public class SanPham implements Serializable{
	@Id
	@Column(name = "ma_san_pham", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 100, message = "{Size.tenSanPham}")
	@Nationalized
	@Column(name = "ten_san_pham", length = 100)
	@NotBlank(message = "{Blank.tenSanPham}")
	private String tenSanPham;

	@Size(max = 50)
	@Column(name = "anh_san_pham", length = 50)
	@NotNull(message = "{Null.anhSanPham}")
	private String anhSanPham;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ma_loai")
	@NotNull(message = "{Null.maLoai}")
	private LoaiSanPham maLoai;

	@Column(name = "don_gia")
	@NotNull(message = "{Null.donGia}")
	private Integer donGia;

	@Column(name = "so_luong_ton")
	@NotNull(message = "{Null.soLuongTon}")
	private Integer soLuongTon;

	@Column(name = "trang_thai")
	@NotNull(message = "{Null.trangThai}")
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