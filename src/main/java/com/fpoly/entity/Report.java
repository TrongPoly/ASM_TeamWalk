package com.fpoly.entity;

import org.springframework.mail.javamail.JavaMailSender;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Report implements java.io.Serializable{
	
	@Id
	Long loai; // ten loai san pham
	double sum;	// tong tien san pham
	long count; // tong so san pham
	public Report(Long loai, double sum, long count) {
		super();
		this.loai = loai;
		this.sum = sum;
		this.count = count;
	}
	public Report() {
		super();
	}
	public Long getLoai() {
		return loai;
	}
	public void setLoai(Long loai) {
		this.loai = loai;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
	
	
	
	
	
	
	
}
