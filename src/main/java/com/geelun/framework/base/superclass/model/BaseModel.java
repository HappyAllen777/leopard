package com.geelun.framework.base.superclass.model;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseModel implements Serializable {
	private static final long serialVersionUID = 2848300327260484864L;

	public String getCreateBy() {
		return createBy;   
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getId() {
		return id;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public abstract String getTableName();

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	private String createBy;

	private Date createTime;

	private String id;

	private Date lastUpdateTime;

}
