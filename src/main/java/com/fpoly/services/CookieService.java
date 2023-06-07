package com.fpoly.services;

import jakarta.servlet.http.Cookie;

public interface CookieService {
	Cookie add(String name, String value, int hours);

	void remove(String name);

	String getValue(String name);

}
