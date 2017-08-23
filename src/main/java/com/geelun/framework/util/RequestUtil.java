package com.geelun.framework.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

	public static String getParam(HttpServletRequest request, String paramName) {
		return getParam(request, paramName, null);
	}

	public static String getParam(HttpServletRequest request, String paramName, String defaultValue) {
		String paramValue = request.getParameter(paramName);
		if (paramValue == null) {
			return defaultValue;
		}
		return paramValue;
	}

	public static int getInt(HttpServletRequest request, String paramName) {
		return getInt(request, paramName, 0);
	}

	public static int getInt(HttpServletRequest request, String paramName, int defaultValue) {
		String strValue = request.getParameter(paramName);
		if (StringUtil.isInt(strValue)) {
			return Integer.parseInt(strValue);
		} else {
			return defaultValue;
		}
	}

	public static String getHostName(HttpServletRequest request) {
		return request.getServerName() + ":" + request.getServerPort();
	}

	public static String getProjectName(HttpServletRequest request) {
		return request.getContextPath();
	}

	public static String getProtocol(HttpServletRequest request) {
		return request.getScheme();
	}

	public static String getUrlPrefix(HttpServletRequest request) {
		return RequestUtil.getProtocol(request) + "://" + getHostName(request) + getProjectName(request);
	}

	public static String getAccessURI(HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		if (requestUri.contains(".")) {
			requestUri = requestUri.split("\\.")[0];
		}
		if (requestUri.contains("?")) {
			requestUri = requestUri.split("\\?")[0];
		}
		return requestUri;
	}
}
