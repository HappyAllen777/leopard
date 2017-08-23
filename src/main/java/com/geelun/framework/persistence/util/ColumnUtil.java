package com.geelun.framework.persistence.util;

import java.lang.reflect.Field;

import com.geelun.framework.constant.ExceptionType;
import com.geelun.framework.persistence.annotation.StringColumnDescriptor;
import com.geelun.framework.persistence.exception.FieldTypeException;

public class ColumnUtil {
	/**
	 * <pre>
	 * 获取String属性的类型描述
	 * @param column
	 * @return
	 * @throws FieldTypeException ： 当column不是String类型时抛出该异常，30001
	 * 
	 * <pre>
	 */
	public static StringColumnDescriptor getStringColumnDescriptor(Field column) throws FieldTypeException {
		Class columnType = column.getType();
		if (columnType != String.class) {
			// 只能处理String类型的属性，如果不是String类型的属性，则抛出
			String fieldName = column.getName();
			int errCode = ExceptionType.FieldTypeIsUncorrectCode;
			String errMsg = ExceptionType.FieldTypeIsUncorrectMsg + ":属性[" + fieldName + "]必须为String";
			throw new FieldTypeException(errCode, errMsg);
		}

		boolean hasStringColumnDescriptor = column.isAnnotationPresent(StringColumnDescriptor.class);
		if (!hasStringColumnDescriptor) {
			return null;
		}

		StringColumnDescriptor descriptor = column.getAnnotation(StringColumnDescriptor.class);
		return descriptor;
	}
}
