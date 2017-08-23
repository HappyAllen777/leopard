package com.geelun.framework.util;

import com.geelun.framework.constant.ExceptionType;

public class AjaxResultUtil {
	/**
	 * <pre>
	 * 返回成功的结果
	 * @param errMsg
	 * @return
	 * 
	 * <pre>
	 */
	public static AjaxResult success(String errMsg) {
		AjaxResult json = new AjaxResult();
		return json.setErrCode(0).setErrMsg(errMsg);
	}

	/**
	 * <pre>
	 * 返回字段为空的错误结果
	 * @param fieldName
	 * @return
	 * 
	 * <pre>
	 */
	public static AjaxResult fieldIsEmpty(String fieldName) {
		AjaxResult json = new AjaxResult();
		return json.setErrCode(ExceptionType.FieldIsEmptyCode).setErrMsg("[" + fieldName + "]" + ExceptionType.FieldIsEmptyMsg);
	}

	/**
	 * <pre>
	 * 返回字段过长的错误结果
	 * @param fieldName
	 * @return
	 * 
	 * <pre>
	 */
	public static AjaxResult fieldIsTooLong(String fieldName) {
		AjaxResult json = new AjaxResult();
		return json.setErrCode(ExceptionType.FieldIsTooLongCode).setErrMsg("[" + fieldName + "]" + ExceptionType.FieldIsTooLongMsg);
	}

	/**
	 * <pre>
	 * 返回对象不存在的错误结果
	 * @return
	 * 
	 * <pre>
	 */
	public static AjaxResult objectNotFound() {
		AjaxResult json = new AjaxResult();
		return json.setErrCode(ExceptionType.NoObjectFoundCode).setErrMsg(ExceptionType.NoObjectFoundMsg);
	}

	/**
	 * <pre>
	 * 返回图片转换失败的错误结果
	 * @return
	 * 
	 * <pre>
	 */
	public static AjaxResult convertPictureFailed() {
		AjaxResult json = new AjaxResult();
		return json.setErrCode(ExceptionType.ConvertPictureFailedCode).setErrMsg(ExceptionType.ConvertPictureFailedMsg);
	}

	/**
	 * <pre>
	 * 返回图片上传失败的错误结果
	 * @return
	 * 
	 * <pre>
	 */
	public static AjaxResult uploadPictureFailed() {
		AjaxResult json = new AjaxResult();
		return json.setErrCode(ExceptionType.UploadPictureFailedCode).setErrMsg(ExceptionType.UploadPictureFailedMsg);
	}

	/**
	 * <pre>
	 * 反射获取值失败
	 * @return
	 * 
	 * <pre>
	 */
	public static AjaxResult getPropertyFailed() {
		AjaxResult json = new AjaxResult();
		return json.setErrCode(ExceptionType.ReflectReadPropertyFailedCode).setErrMsg(ExceptionType.ReflectReadPropertyFailedMsg);
	}
}
