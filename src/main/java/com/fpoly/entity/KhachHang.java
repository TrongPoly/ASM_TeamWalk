package com.fpoly.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "khach_hang")
public class KhachHang implements Serializable{
	@Id
	@Column(name = "ma_khach_hang", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 50)
	@Nationalized
	@Column(name = "ten_khach_hang", length = 50)
	private String tenKhachHang;

	@Nationalized
	@Lob
	@Column(name = "dia_chi")
	private String diaChi;

	@Size(max = 15)
	@Column(name = "so_dien_thoai", length = 15)
	@NotNull
	private String soDienThoai;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "email")
	private TaiKhoan email;

	@Size(max = 15)
	@Column(name = "so_can_cuoc", length = 15)
	private String soCanCuoc;

	@Column(name = "diem_tich_luy")
	private Integer diemTichLuy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hang_khach_hang")
	private HangKhachHang hangKhachHang;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public TaiKhoan getEmail() {
		return email;
	}

	public void setEmail(TaiKhoan email) {
		this.email = email;
	}

	public String getSoCanCuoc() {
		return soCanCuoc;
	}

	public void setSoCanCuoc(String soCanCuoc) {
		this.soCanCuoc = soCanCuoc;
	}

	public Integer getDiemTichLuy() {
		return diemTichLuy;
	}

	public void setDiemTichLuy(Integer diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
	}

	public HangKhachHang getHangKhachHang() {
		return hangKhachHang;
	}

	public void setHangKhachHang(HangKhachHang hangKhachHang) {
		this.hangKhachHang = hangKhachHang;
	}

	public KhachHang(Long id, @Size(max = 50) String tenKhachHang, String diaChi, @Size(max = 15) String soDienThoai,
			TaiKhoan email, @Size(max = 15) String soCanCuoc, Integer diemTichLuy, HangKhachHang hangKhachHang) {
		super();
		this.id = id;
		this.tenKhachHang = tenKhachHang;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.soCanCuoc = soCanCuoc;
		this.diemTichLuy = diemTichLuy;
		this.hangKhachHang = hangKhachHang;
	}

	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}

}