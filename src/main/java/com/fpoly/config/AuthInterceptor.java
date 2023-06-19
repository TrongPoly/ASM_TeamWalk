package com.fpoly.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fpoly.dao.TaiKhoanDAO;
import com.fpoly.entity.TaiKhoan;
import com.fpoly.services.CookieImpl;
import com.fpoly.services.SessionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthInterceptor implements HandlerInterceptor {
	@Autowired
	SessionService sessionService;
	@Autowired
	CookieImpl cookieImpl;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Lấy URL của request
		String uri = request.getRequestURI();
		// lấy thông tin về user từ session.
		TaiKhoan user = sessionService.get("user");

		if (user == null) {
			response.sendRedirect("/errorPage");
			return false;
		} else if (user.getPhanQuyen() == false || !user.getPhanQuyen() && uri.startsWith("/admin")) {
			response.sendRedirect("/errorPage");
			return false;
		}

		return true;
	}
}
