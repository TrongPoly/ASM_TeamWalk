package com.fpoly.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "loai_san_pham")
public class LoaiSanPham  {
	@Id
	@Column(name = "ma_loai", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Nationalized
	@Lob
	@Column(name = "ten_loai")
	private String tenLoai;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public LoaiSanPham(Long id, String tenLoai) {
		super();
		this.id = id;
		this.tenLoai = tenLoai;
	}

	public LoaiSanPham() {
		super();
		// TODO Auto-generated constructor stub
	}

}