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
		helper.setTo("trongnguyen32293@gmail.com");
		helper.setSubject("XÁC NHẬN ĐÃ NHẬN HÀNG VÀ THANH TOÁN");
		helper.setText(
				"Bạn có yêu cầu xác nhận trạng thái đã thanh toán đơn cho " + KhachHang + " với mã đơn hàng " + maDon,
				true);
		sender.send(message);
	}

}
