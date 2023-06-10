package com.fpoly.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class HoaDonChiTietId implements Serializable {
	private static final long serialVersionUID = 3077465354933783014L;
	@NotNull
	@Column(name = "ma_hoa_don", nullable = false)
	private Long maHoaDon;

	@NotNull
	@Column(name = "ma_san_pham", nullable = false)
	private Long maSanPham;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		HoaDonChiTietId entity = (HoaDonChiTietId) o;
		return Objects.equals(this.maSanPham, entity.maSanPham) && Objects.equals(this.maHoaDon, entity.maHoaDon);
	}

	@Override
	public int hashCode() {
		return Objects.hash(maSanPham, maHoaDon);
	}

	public Long getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(Long maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public Long getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(Long maSanPham) {
		this.maSanPham = maSanPham;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public HoaDonChiTietId(@NotNull Long maHoaDon, @NotNull Long maSanPham) {
		super();
		this.maHoaDon = maHoaDon;
		this.maSanPham = maSanPham;
	}

	public HoaDonChiTietId() {
		super();
		// TODO Auto-generated constructor stub
	}

}