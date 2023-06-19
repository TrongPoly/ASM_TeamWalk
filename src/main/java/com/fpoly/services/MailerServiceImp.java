package com.fpoly.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fpoly.entity.MailModel;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailerServiceImp {
	@Autowired
	JavaMailSender sender;

	public void send(String KhachHang, String maDon) throws Exception {
		// Tạo message
		MimeMessage message = sender.createMimeMessage();
		// Sử dụng Helper để thiết lập các thông tin cần thiết cho message
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		helper.setFrom("trongnppc02979@fpt.edu.vn");
		helper.setTo("trongnppc02979@gmail.com");
		helper.setSubject("XÁC NHẬN ĐÃ NHẬN HÀNG VÀ THANH TOÁN");
		helper.setText(
				"Bạn có yêu cầu xác nhận trạng thái đã thanh toán đơn cho " + KhachHang + " với mã đơn hàng " + maDon,
				true);
		sender.send(message);
	}

	public void datHang(String KhachHang, Long hoaDon) throws Exception {
		// Tạo message
		MimeMessage message = sender.createMimeMessage();
		// Sử dụng Helper để thiết lập các thông tin cần thiết cho message
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		helper.setFrom("trongnppc02979@fpt.edu.vn");
		helper.setTo("trongnppc02979@gmail.com");
		helper.setSubject("CÓ ĐƠN ĐẶT HÀNG MỚI");
		helper.setText("Bạn có đơn hàng mới cần xử lý cho " + KhachHang + " với mã đơn hàng " + hoaDon, true);
		sender.send(message);
	}

	public void huyDon(String body, String KhachHang, String maDon) throws Exception {
		// Tạo message
		MimeMessage message = sender.createMimeMessage();
		// Sử dụng Helper để thiết lập các thông tin cần thiết cho message
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		helper.setFrom("trongnppc02979@fpt.edu.vn");
		helper.setTo("trongnppc02979@gmail.com");
		helper.setSubject("YÊU CẦU HỦY ĐƠN");
		helper.setText(
				"Bạn có yêu cầu xác nhận hủy đơn cho " + KhachHang + " với mã đơn hàng " + maDon + ". Lý do: " + body,
				true);
		sender.send(message);
	}

	public void SendCodeFogotPw(String email, int code) throws Exception {
		// Tạo message
		MimeMessage message = sender.createMimeMessage();
		// Sử dụng Helper để thiết lập các thông tin cần thiết cho message
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		helper.setFrom("trongnppc02979@fpt.edu.vn");
		helper.setTo(email);
		helper.setSubject("YÊU CẦU CẤP LẠI MẬT KHẨU");
		helper.setText("[Cảnh báo] Yêu cầu cấp lại mật khẩu của Quý khách đang được thực hiện.\r\n"
				+ "Vui lòng nhập mã OTP sau để xác thực: " + code, true);
		sender.send(message);
	}

	public void SendSuccessRegister(String email) throws Exception {
		// Tạo message
		MimeMessage message = sender.createMimeMessage();
		// Sử dụng Helper để thiết lập các thông tin cần thiết cho message
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		helper.setFrom("trongnppc02979@fpt.edu.vn");
		helper.setTo(email);
		helper.setSubject("(T-ELECTRONIC) ĐĂNG KÝ TÀI KHOẢN THÀNH CÔNG");
		helper.setText("T-Electronic xin thông báo Quý khách đã đăng ký tài khoản thành công ", true);
		sender.send(message);
	}

}
