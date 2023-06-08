package com.fpoly.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "hang_khach_hang")
public class HangKhachHang {
    @Id
    @Column(name = "ma_hang", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Nationalized
    @Column(name = "ten_hang", length = 50)
    private String tenHang;

    @Nationalized
    @Lob
    @Column(name = "mo_ta")
    private String moTa;

    @NotNull
    @Column(name = "diem_toi_thieu", nullable = false)
    private Integer diemToiThieu;

    @Column(name = "trang_thai")
    private Boolean trangThai;

	public HangKhachHang(Integer id, @Size(max = 50) String tenHang, String moTa, @NotNull Integer diemToiThieu,
			Boolean trangThai) {
		super();
		this.id = id;
		this.tenHang = tenHang;
		this.moTa = moTa;
		this.diemToiThieu = diemToiThieu;
		this.trangThai = trangThai;
	}

	public HangKhachHang() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenHang() {
		return tenHang;
	}

	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public Integer getDiemToiThieu() {
		return diemToiThieu;
	}

	public void setDiemToiThieu(Integer diemToiThieu) {
		this.diemToiThieu = diemToiThieu;
	}

	public Boolean getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
	}

    
}