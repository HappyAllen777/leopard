package com.geelun.framework.util;

import java.util.List;

public class ArrayUtil {
	public static <T> boolean isEmpty(List<T> list) {
		return (list == null || list.isEmpty());
	}

	public static <T> boolean isNotEmpty(List<T> list) {
		return !isEmpty(list);
	}

	public static int getRealSize(List<?> list) {
		return isEmpty(list) ? 0 : list.size();
	}

	public static <T> boolean isEmpty(T[] array) {
		return (array == null || array.length == 0);
	}

	public static <T> boolean isNotEmpty(T[] array) {
		return !isEmpty(array);
	}
}
