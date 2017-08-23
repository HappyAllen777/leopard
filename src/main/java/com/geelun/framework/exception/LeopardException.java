package com.geelun.framework.exception;


public class LeopardException extends RuntimeException {

	private static final long serialVersionUID = 6577824860474621537L;

	/**
	 * <pre>
	 * 异常代码，用大于0的数字表示
	 * 1开头：web权限异常
	 * 2开头：web参数异常
	 * 3开头：基础异常
	 * 
	 * <pre>
	 */
	private int errCode;
	private String errMsg;

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public LeopardException() {
	}

	public LeopardException(int errCode, String errMsg) {
		setErrCode(errCode);
		setErrMsg(errMsg);
	}
}
