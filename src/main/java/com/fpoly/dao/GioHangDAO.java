package com.fpoly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.entity.GioHang;
import java.util.List;
import com.fpoly.entity.KhachHang;

public interface GioHangDAO extends JpaRepository<GioHang, Integer> {
	GioHang getByNguoiSoHuu(KhachHang nguoiSoHuu);
}
