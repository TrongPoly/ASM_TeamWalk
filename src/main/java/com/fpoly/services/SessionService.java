package com.fpoly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.entity.TaiKhoan;

import jakarta.servlet.http.HttpSession;

@Service
public class SessionService {
	@Autowired
	HttpSession session;

	public TaiKhoan get(String name) {
		return (TaiKhoan) session.getAttribute(name);
	}

	public void set(String name, Object value) {
		session.setMaxInactiveInterval(18000); // Thời gian số giây cho session này là 30 phút
		session.setAttribute(name, value);
	}

	public void remove(String name) {
		session.removeAttribute(name);
	}
}
