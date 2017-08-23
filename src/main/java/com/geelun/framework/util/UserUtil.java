package com.geelun.framework.util;

import javax.servlet.http.HttpSession;

public class UserUtil {
	public static String getCurrentUserName(HttpSession session) {
		// TODO : 这里的username写死的
		return "Allen";
	}
	
}
