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
		String error = "";

		if (user == null) {
			error = "Please login!";
		} else if (!user.getPhanQuyen() && uri.startsWith("/admin")) {
			error = "Access denied!";
		}

		if (error.length() > 0) {
			// quay lại trang index
			response.sendRedirect("/errorPage");
			return false;
		}
		// }

		return true;
	}
}
