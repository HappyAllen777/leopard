package com.geelun.framework.util;

import java.io.Serializable;

public class AjaxResult implements Serializable {
	private static final long serialVersionUID = -3211009696299762483L;

	private String errMsg;

	private int errCode;

	public String getErrMsg() {
		return errMsg;
	}

	public int getErrCode() {
		return errCode;
	}

	public AjaxResult setErrMsg(String errMsg) {
		this.errMsg = errMsg;
		return this;
	}

	public AjaxResult setErrCode(int errCode) {
		this.errCode = errCode;
		return this;
	}

	public AjaxResult() {

	}

	public AjaxResult(int errCode, String errMsg) {
		setErrCode(errCode).setErrMsg(errMsg);
	}

	@Override
	public String toString() {
		return "{\"errCode\":" + errCode + ",\"errMsg\":\"" + errMsg + "\"}";
	}

}
