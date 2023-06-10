package com.fpoly.dao;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.entity.SanPham;
import com.fpoly.entity.LoaiSanPham;

public interface SanPhamDAO extends JpaRepository<SanPham, Long> {
	List<SanPham> findAllById(long id);

	SanPham getByTenSanPham(String tenSanPham);

}