package com.fpoly.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class GioHangChiTietId implements Serializable {
	private static final long serialVersionUID = -3341550009504898151L;
	@NotNull
	@Column(name = "ma_gio_hang", nullable = false)
	private UUID maGioHang;

	@NotNull
	@Column(name = "ma_san_pham", nullable = false)
	private Long maSanPham;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		GioHangChiTietId entity = (GioHangChiTietId) o;
		return Objects.equals(this.maGioHang, entity.maGioHang) && Objects.equals(this.maSanPham, entity.maSanPham);
	}

	@Override
	public int hashCode() {
		return Objects.hash(maGioHang, maSanPham);
	}

	public UUID getMaGioHang() {
		return maGioHang;
	}

	public void setMaGioHang(UUID maGioHang) {
		this.maGioHang = maGioHang;
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

	public GioHangChiTietId(@NotNull UUID maGioHang, @NotNull Long maSanPham) {
		super();
		this.maGioHang = maGioHang;
		this.maSanPham = maSanPham;
	}

	public GioHangChiTietId() {
		super();
		// TODO Auto-generated constructor stub
	}

}