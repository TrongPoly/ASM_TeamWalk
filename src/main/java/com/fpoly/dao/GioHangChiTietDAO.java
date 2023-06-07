package com.fpoly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.entity.GioHangChiTiet;
import com.fpoly.entity.GioHangChiTietId;
import com.fpoly.entity.SanPham;
import java.util.List;
import com.fpoly.entity.GioHang;

public interface GioHangChiTietDAO extends JpaRepository<GioHangChiTiet, GioHangChiTietId> {
	GioHangChiTiet getByMaSanPhamAndMaGioHang(SanPham maSanPham, GioHang maGioHang);
}
