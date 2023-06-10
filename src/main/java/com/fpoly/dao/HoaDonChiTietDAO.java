package com.fpoly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.entity.HoaDonChiTiet;
import com.fpoly.entity.HoaDonChiTietId;
import java.util.List;

import com.fpoly.entity.HoaDon;

public interface HoaDonChiTietDAO extends JpaRepository<HoaDonChiTiet, HoaDonChiTietId> {
	List<HoaDonChiTiet> findByMaHoaDon(HoaDon maHoaDon);

	@Query("select sum(hdct.soLuong * hdct.donGia) from HoaDonChiTiet hdct where hdct.maHoaDon = ?1")
	Long total(HoaDon maHD);
}
