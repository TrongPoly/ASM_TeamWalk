package com.fpoly.dao;

import org.springframework.data.jpa.repository.JpaRepository;	

import com.fpoly.entity.TaiKhoan;

public interface TaiKhoanDAO extends JpaRepository<TaiKhoan, String> {

}
