package com.fpoly.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "gio_hang")
public class GioHang {
	@Id
	@Column(name = "ma_gio_hang", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "ngay_tao")
	private LocalDate ngayTao;

	@Column(name = "ngay_cap_nhat")
	private LocalDate ngayCapNhat;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nguoi_so_huu")
	private KhachHang nguoiSoHuu;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDate getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(LocalDate ngayTao) {
		this.ngayTao = ngayTao;
	}

	public LocalDate getNgayCapNhat() {
		return ngayCapNhat;
	}

	public void setNgayCapNhat(LocalDate ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}

	public KhachHang getNguoiSoHuu() {
		return nguoiSoHuu;
	}

	public void setNguoiSoHuu(KhachHang nguoiSoHuu) {
		this.nguoiSoHuu = nguoiSoHuu;
	}

	public GioHang(UUID id, LocalDate ngayTao, LocalDate ngayCapNhat, KhachHang nguoiSoHuu) {
		super();
		this.id = id;
		this.ngayTao = ngayTao;
		this.ngayCapNhat = ngayCapNhat;
		this.nguoiSoHuu = nguoiSoHuu;
	}

	public GioHang() {
		super();
		// TODO Auto-generated constructor stub
	}

}