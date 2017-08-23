package com.geelun.framework.validate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.geelun.framework.base.superclass.model.BaseModel;
import com.geelun.framework.persistence.annotation.StringColumnDescriptor;
import com.geelun.framework.persistence.column.StringColumnType;
import com.geelun.framework.persistence.exception.FieldTypeException;
import com.geelun.framework.persistence.util.ColumnUtil;
import com.geelun.framework.util.ArrayUtil;
import com.geelun.framework.util.ReflectUtil;
import com.geelun.framework.util.StringUtil;

@Component
public class BaseModelValidator {

	/**
	 * <pre>
	 * 校验对象的持久化字段值是否符合定义规范
	 * @param t
	 * @return
	 * 
	 * <pre>
	 */
	public static BaseModelValidateResult validatePersistentModel(BaseModel t) {
		BaseModelValidateResult result = createSuccessResult("校验成功");
		List<Field> fields = ReflectUtil.getPersistentFields(t);
		if (ArrayUtil.isEmpty(fields)) {
			// 如果没有字段则视为校验通过
			return result;
		}

		for (Field field : fields) {
			Object fieldValue = ReflectUtil.getFieldValue(t, field);
			result = validateField(field, fieldValue);
			if (!result.isSuccess()) {
				return result;
			}
		}

		return result;
	}

	/**
	 * <pre>
	 * 校验字段
	 * @param field
	 * @param t
	 * @return
	 * 
	 * <pre>
	 */
	private static BaseModelValidateResult validateField(Field field, Object fieldValue) {
		BaseModelValidateResult result = createSuccessResult("校验成功");

		Class fieldType = field.getType();
		if (fieldType == String.class) {
			// 校验String类型字段
			result = validateStringField(field, (String) fieldValue);
		} else if (fieldType == Integer.class || StringUtil.equals(fieldType.getSimpleName(), "int")) {
			// 校验Integer类型或者int类型字段
		}

		return result;
	}

	private static BaseModelValidateResult validateStringField(Field stringField, String fieldValue) {
		BaseModelValidateResult result = createSuccessResult("校验成功");
		try {
			StringColumnDescriptor descriptor = ColumnUtil.getStringColumnDescriptor(stringField);
			if (descriptor == null) {
				// 没有指定列描述器则视为任何值都是符合校验规则
				return result;
			}
			result = validateStringByDescriptor(fieldValue, descriptor);
		} catch (FieldTypeException e) {
		}
		return result;
	}

	public static BaseModelValidateResult createSuccessResult(String successMsg) {
		BaseModelValidateResult successResult = new BaseModelValidateResult();
		successResult.setMsg(successMsg);
		successResult.setSuccess(true);
		return successResult;
	}

	public static BaseModelValidateResult validateStringByDescriptor(String value, StringColumnDescriptor descriptor) {
		BaseModelValidateResult result = new BaseModelValidateResult();
		result.setSuccess(false);
		result.setMsg("校验失败");

		StringColumnType[] types = descriptor.type();
		if (ArrayUtil.isEmpty(types)) {
			// 如果没有描述任何类型，则视为合法
			return createSuccessResult("校验成功");
		}

		int realLen = StringUtil.getRealLength(value);
		int minLen = descriptor.minLen();
		minLen = minLen < 0 ? 0 : minLen;
		int maxLen = descriptor.maxLen();
		maxLen = maxLen < 0 ? Integer.MAX_VALUE : maxLen;

		List<BaseModelValidateResult> results = new ArrayList<BaseModelValidateResult>(10);

		for (StringColumnType type : types) {
			if (type == StringColumnType.NULL) {
				if (value == null) {
					results.add(createSuccessResult("校验成功"));
				} else {
					BaseModelValidateResult failResult = new BaseModelValidateResult();
					failResult.setSuccess(false);
					failResult.setMsg("值[" + value + "]不合法，它必须为null");
					results.add(failResult);
				}
			} else if (type == StringColumnType.EMPTY) {
				if (StringUtil.isEmpty(value)) {
					results.add(createSuccessResult("校验成功"));
				} else {
					BaseModelValidateResult failResult = new BaseModelValidateResult();
					failResult.setSuccess(false);
					failResult.setMsg("值[" + value + "]不合法，它必须是空字符串");
					results.add(failResult);
				}
			} else if (type == StringColumnType.IDCARDNO) {
				// TODO : 身份证号码校验规则
			} else if (type == StringColumnType.UUID) {
				// TODO : uuid校验规则
			} else if (type == StringColumnType.PHONENUMBER) {
				// TODO : 手机好吗规则
			} else if (type == StringColumnType.DATE) {
				// TODO : 手机好吗规则
			} else if (type == StringColumnType.DATETIME) {
				// TODO : 手机好吗规则
			} else if (type == StringColumnType.UNFIXEDLEN) {
				if (realLen >= minLen && realLen <= maxLen) {
					results.add(createSuccessResult("校验成功"));
				} else {
					BaseModelValidateResult failResult = new BaseModelValidateResult();
					failResult.setSuccess(false);
					failResult.setMsg("值[" + value + "]不合法，它的长度必须在" + minLen + "~" + maxLen + "之间");
					results.add(failResult);
				}
			} else {
				// 其他类型
			}
		}

		BaseModelValidateResult failResult = null;
		for (BaseModelValidateResult baseModelValidateResult : results) {
			if (baseModelValidateResult.isSuccess()) {
				return createSuccessResult("校验成功");
			} else {
				failResult = baseModelValidateResult;
			}
		}
		return failResult;
	}
}
