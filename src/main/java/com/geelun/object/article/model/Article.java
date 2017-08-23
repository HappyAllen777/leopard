package com.geelun.object.article.model;

import java.util.Date;

import com.geelun.framework.base.superclass.model.BaseModel;
import com.geelun.framework.persistence.annotation.StringColumnDescriptor;
import com.geelun.framework.persistence.column.StringColumnType;

public class Article extends BaseModel {
	private static final long serialVersionUID = 6927919097857327597L;

	public String getAuthor() {
		return author;
	}

	public String getContent() {
		return content;
	}

	public String getDescription() {
		return description;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	@Override
	public String getTableName() {
		return "b_article";
	}

	public String getTitle() {
		return title;
	}

	public boolean isPublished() {
		return published;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@StringColumnDescriptor(name = "author", minLen = 1, maxLen = 32, type = { StringColumnType.UNFIXEDLEN, StringColumnType.NULL })
	private String author;
	@StringColumnDescriptor(name = "content", minLen = 1, type = { StringColumnType.UNFIXEDLEN, StringColumnType.EMPTY })
	private String content;

	@StringColumnDescriptor(name = "description", minLen = 1, maxLen = 64, type = { StringColumnType.UNFIXEDLEN, StringColumnType.EMPTY })
	private String description;

	@StringColumnDescriptor(name = "categoryId", minLen = 1, maxLen = 64, type = { StringColumnType.UNFIXEDLEN, StringColumnType.EMPTY })
	private String categoryId;
	
	private boolean published;

	private Date publishTime;

	@StringColumnDescriptor(name = "title", minLen = 1, maxLen = 32, type = { StringColumnType.UNFIXEDLEN })
	private String title;

}
