package com.fpoly.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.entity.SanPham;

import com.fpoly.entity.GioHangChiTiet;
import com.fpoly.entity.Report;

public interface SanPhamDAO extends JpaRepository<SanPham, Long> {
	List<SanPham> findAllById(long id);

	SanPham getByTenSanPham(String tenSanPham);

	@Query("select sp from SanPham sp where sp.maLoai.id = ?1")
	List<SanPham> findByCategory(long id, Pageable page);

	@Query("select sp from SanPham sp where sp.maLoai.id = ?1")
	List<SanPham> findByCategory(long id);

	@Query("SELECT new Report(sp.maLoai.id, SUM(sp.donGia), COUNT(sp.id)) " + "FROM SanPham sp "
			+ "GROUP BY sp.maLoai.id")

	List<Report> getThongKe();

	@Query(name = "findByTenSanPham")
	List<SanPham> findByTenSanPham(String tenSanPham, Pageable page);

}