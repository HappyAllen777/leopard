package com.geelun.framework.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidateCodeServlet extends HttpServlet {

	private static final long serialVersionUID = 5099031939518497297L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ValidateCodeServlet doget1");
		Cookie newcookie = new Cookie("key", "value2");
		newcookie.setMaxAge(1000);
		newcookie.setPath("/CookieTest/");
		response.addCookie(newcookie);
		
		String cookieName = request.getParameter("cookieName");

		Cookie[] cookies = request.getCookies();

		if (cookies != null && cookies.length > 0) {
			boolean hasFound = false;
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				if (name.equals(cookieName)) {
					hasFound = true;
					System.out.println("cookie value:" + cookie.getValue());
				}
			}
			if (!hasFound) {
				System.out.println("no " + cookieName + " found");
			}
		}
	}

}
