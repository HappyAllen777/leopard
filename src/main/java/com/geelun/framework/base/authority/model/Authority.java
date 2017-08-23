package com.geelun.framework.base.authority.model;

import com.geelun.framework.base.superclass.model.BaseModel;
import com.geelun.framework.persistence.annotation.StringColumnDescriptor;
import com.geelun.framework.persistence.column.StringColumnType;

public class Authority extends BaseModel {
	private static final long serialVersionUID = 6836458409078703214L;

	public String getProtocol() {
		return protocol;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	@Override
	public String getTableName() {
		return "s_authority";
	}

	public String getUri() {
		return uri;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@StringColumnDescriptor(name = "protocol", minLen = 1, maxLen = 16, type = StringColumnType.UNFIXEDLEN)
	private String protocol;

	@StringColumnDescriptor(name = "requestMethod", minLen = 1, maxLen = 16, type = StringColumnType.UNFIXEDLEN)
	private String requestMethod;

	@StringColumnDescriptor(name = "uri", minLen = 1, maxLen = 128, type = StringColumnType.UNFIXEDLEN)
	private String uri;

}
