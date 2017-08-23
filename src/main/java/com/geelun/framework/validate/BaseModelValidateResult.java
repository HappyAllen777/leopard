package com.geelun.framework.validate;

import java.io.Serializable;

public class BaseModelValidateResult implements Serializable {
	private static final long serialVersionUID = -465696021180577884L;


	public String getFieldName() {
		return fieldName;
	}

	public String getMsg() {
		return msg;
	}

	public String getObjId() {
		return objId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	private String fieldName;

	private String msg;

	private String objId;

	private boolean success;

}
