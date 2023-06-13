package com.fpoly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.entity.TaiKhoan;
import java.util.List;

public interface TaiKhoanDAO extends JpaRepository<TaiKhoan, String> {
	TaiKhoan findByEmail(String email);
}
