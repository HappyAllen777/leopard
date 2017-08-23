package com.geelun.framework.base.attachment.model;

import com.geelun.framework.base.superclass.model.BaseModel;
import com.geelun.framework.persistence.annotation.StringColumnDescriptor;
import com.geelun.framework.persistence.column.StringColumnType;

public class Attachment extends BaseModel {
	private static final long serialVersionUID = 803439035990637679L;

	public String getName() {
		return name;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public long getSize() {
		return size;
	}

	@Override
	public String getTableName() {
		return "s_attachment";
	}

	public String getType() {
		return type;
	}

	public String getUri() {
		return uri;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@StringColumnDescriptor(name = "name", maxLen = 64, minLen = 1, type = { StringColumnType.UNFIXEDLEN })
	private String name;
	@StringColumnDescriptor(name = "otherInfo", maxLen = 32, minLen = 1, type = { StringColumnType.UNFIXEDLEN })
	private String otherInfo;
	private long size;

	@StringColumnDescriptor(name = "type", maxLen = 32, minLen = 1, type = { StringColumnType.UNFIXEDLEN })
	private String type;
	@StringColumnDescriptor(name = "uri", maxLen = 128, minLen = 1, type = { StringColumnType.UNFIXEDLEN })
	private String uri;
}
