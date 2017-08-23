package com.geelun.framework.constant;

public class ExceptionType {
	public static final int FieldTypeIsUncorrectCode = 300001;
	public static final String FieldTypeIsUncorrectMsg = "字段类型不正确";

	public static final int FieldIsEmptyCode = 300011;
	public static final String FieldIsEmptyMsg = "字段不能为空字符串";

	public static final int FieldIsTooLongCode = 300012;
	public static final String FieldIsTooLongMsg = "字段长度过长";

	public static final int NoObjectFoundCode = 300021;
	public static final String NoObjectFoundMsg = "该对象不存在或已被删除";

	public static final int ConvertPictureFailedCode = 500001;
	public static final String ConvertPictureFailedMsg = "图片转码失败";

	public static final int UploadPictureFailedCode = 500002;
	public static final String UploadPictureFailedMsg = "图片上传失败";

	public static final int ReflectReadPropertyFailedCode = 500011;
	public static final String ReflectReadPropertyFailedMsg = "属性读取失败";

	public static final int ReflectWritePropertyFailedCode = 500012;
	public static final String ReflectWritePropertyFailedMsg = "属性设置失败";

	public static final int ReflectInvokeMethodFailedCode = 500013;
	public static final String ReflectInvokeMethodFailedMsg = "方法调用失败";

	public static final int ReflectGetMethodFailedCode = 500014;
	public static final String ReflectGetMethodFailedMsg = "方法获取失败";

}
