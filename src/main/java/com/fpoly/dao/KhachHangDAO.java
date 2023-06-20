package com.fpoly.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpoly.entity.KhachHang;
import com.fpoly.entity.TaiKhoan;

public interface KhachHangDAO extends JpaRepository<KhachHang, Long> {
	KhachHang getByEmail(TaiKhoan email);

	@Query("SELECT kh FROM KhachHang kh WHERE kh.tenKhachHang LIKE %:tenKhachHang%")
	List<KhachHang> findByTenKhachHang(String tenKhachHang, Pageable pageable);

	KhachHang findByTenKhachHang(String tenKhachHang);
}
