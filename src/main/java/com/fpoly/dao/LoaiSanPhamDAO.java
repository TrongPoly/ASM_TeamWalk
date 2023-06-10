package com.fpoly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.entity.LoaiSanPham;

public interface LoaiSanPhamDAO extends JpaRepository<LoaiSanPham, Long>{
	@Query("select o from LoaiSanPham o where o.id like ?1")
	List<LoaiSanPham> findbykeywords(String key);
}
