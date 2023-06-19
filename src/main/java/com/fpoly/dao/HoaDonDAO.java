package com.fpoly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.entity.HoaDon;
import java.util.List;
import com.fpoly.entity.KhachHang;

public interface HoaDonDAO extends JpaRepository<HoaDon, Long> {
	List<HoaDon> findByNguoiMua(KhachHang nguoiMua);

	@Query("select distinct YEAR(hd.ngayThanhToan) from HoaDon hd where hd.ngayThanhToan is not null")
	List<String> getYearHoaDon();
}
