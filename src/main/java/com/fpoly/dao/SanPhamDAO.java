package com.fpoly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.entity.SanPham;

public interface SanPhamDAO extends JpaRepository<SanPham, Long> {
	List<SanPham> findAllById(long id);
}
