package com.fpoly.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "trang_thai_hoa_don")
public class TrangThaiHoaDon {
	@Id
	@Column(name = "ma_trang_thai", nullable = false)
	private Integer id;

	@Size(max = 100)
	@Nationalized
	@Column(name = "ten_trang_thai", length = 100)
	private String tenTrangThai;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenTrangThai() {
		return tenTrangThai;
	}

	public void setTenTrangThai(String tenTrangThai) {
		this.tenTrangThai = tenTrangThai;
	}

	public TrangThaiHoaDon(Integer id, @Size(max = 100) String tenTrangThai) {
		super();
		this.id = id;
		this.tenTrangThai = tenTrangThai;
	}

	public TrangThaiHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

}