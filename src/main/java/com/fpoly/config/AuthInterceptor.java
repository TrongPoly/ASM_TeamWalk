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
	@Autowired
	TaiKhoanDAO taiKhoanDAO;
	@Autowired
	HttpSession session;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		String rollBack = "index";
		TaiKhoan user = sessionService.get("user");
//		String vlCookie = cookieImpl.getValue("cuser");
//		if (vlCookie != null) {
//		TaiKhoan user = taiKhoanDAO.getById(vlCookie);
		String error = "";

		if (user == null) {
			error = "Please login!";
		} else if (!user.getPhanQuyen() && uri.startsWith("/admin")) {
			System.out.println("URI: " + uri);
			error = "Access denied!";
		}

		if (error.length() > 0) {
			session.setAttribute("security-uri", rollBack);
			response.sendRedirect("/index");
			return false;
		}
//		}

		return true;
	}
}
