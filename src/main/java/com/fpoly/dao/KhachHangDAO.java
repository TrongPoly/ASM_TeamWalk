package com.fpoly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.entity.KhachHang;
import java.util.List;
import com.fpoly.entity.TaiKhoan;

public interface KhachHangDAO extends JpaRepository<KhachHang, Long> {
	KhachHang getByEmail(TaiKhoan email);

}
