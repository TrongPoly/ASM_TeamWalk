package com.fpoly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.entity.GioHangChiTiet;
import com.fpoly.entity.GioHangChiTietId;
import com.fpoly.entity.SanPham;
import java.util.List;
import java.util.UUID;

import com.fpoly.entity.GioHang;

public interface GioHangChiTietDAO extends JpaRepository<GioHangChiTiet, GioHangChiTietId> {
	GioHangChiTiet getByMaSanPhamAndMaGioHang(SanPham maSanPham, GioHang maGioHang);

	List<GioHangChiTiet> findByMaGioHang(GioHang maGioHang);

	@Query("select sum(ghct.soLuong * ghct.maSanPham.donGia) from GioHangChiTiet ghct where ghct.maGioHang = ?1")
	Long total(GioHang ma);

	void deleteAllByMaGioHang(GioHang maGioHang);
}
