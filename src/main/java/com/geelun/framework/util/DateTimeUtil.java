package com.geelun.framework.util;

import java.text.SimpleDateFormat;

public class DateTimeUtil {
	private static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

	private static SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");

	public static SimpleDateFormat getDateTimeFormatter() {
		return dateTimeFormatter;
	}

	public static SimpleDateFormat getDateFormatter() {
		return dateFormatter;
	}

	public static SimpleDateFormat getTimeFormatter() {
		return timeFormatter;
	}
}
