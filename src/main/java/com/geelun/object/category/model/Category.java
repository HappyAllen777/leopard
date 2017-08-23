package com.geelun.object.category.model;

import com.geelun.framework.base.superclass.model.BaseModel;
import com.geelun.framework.persistence.annotation.StringColumnDescriptor;
import com.geelun.framework.persistence.column.StringColumnType;
import com.geelun.framework.util.StringUtil;

public class Category extends BaseModel {

	private static final long serialVersionUID = 2573901221046590011L;

	public String getName() {
		return name;
	}

	public String getParentId() {
		return parentId;
	}

	public String getRemarks() {
		return remarks;
	}

	public int getSortNumber() {
		return sortNumber;
	}

	@Override
	public String getTableName() {
		return "b_category";
	}

	public String getType() {
		return type;
	}

	public String getTypeName() {
		if (StringUtil.equals(type, "article")) {
			return "文章";
		}
		return "未定义";
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setSortNumber(int sortNumber) {
		this.sortNumber = sortNumber;
	}

	public void setType(String type) {
		this.type = type;
	}

	@StringColumnDescriptor(name = "name", minLen = 1, maxLen = 64, type = StringColumnType.UNFIXEDLEN)
	private String name;
	@StringColumnDescriptor(name = "parentId", minLen = 32, maxLen = 32, type = { StringColumnType.UUID, StringColumnType.NULL })
	private String parentId;

	@StringColumnDescriptor(name = "remarks", minLen = 1, maxLen = 128, type = StringColumnType.UNFIXEDLEN)
	private String remarks;

	private int sortNumber;

	@StringColumnDescriptor(name = "type", minLen = 1, maxLen = 64, type = StringColumnType.UNFIXEDLEN)
	private String type;

}
