package com.geelun.framework.base.superclass.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.geelun.framework.base.superclass.model.BaseModel;
import com.geelun.framework.persistence.Page;
import com.geelun.framework.util.JsonUtil;
import com.geelun.framework.util.RequestUtil;
@Controller
public abstract class PagedController<T extends BaseModel> extends BaseModelController<T> {

	/**
	 * 获取实体类输出的属性
	 * 
	 * @return
	 */
	protected abstract List<String> getExportProperties();

	protected abstract String getDefaultPageOrderBy();

	/**
	 * 默认分页页面
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public String list() {
		return "/" + getControllerName() + "/list";
	}

	/**
	 * 默认分页数据获取方法
	 * 
	 * @param request
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/list")
	public void list(HttpServletRequest request) {
		Page page = createPage(getDefaultPageOrderBy());
		queryPage(page);
		writePageResponse(page);
	}

	protected void queryPage(Page page) {
		getService().list(page);
	}

	protected void writePageResponse(Page page) {
		JsonUtil.writePageResponse(getResponse(), page);
	}

	/**
	 * 获取分页数据来源的页面的url
	 * 
	 * @return
	 */
	protected String getListDataUrl() {
		return getUrlPrefix() + "/" + getControllerName() + "/list" ;
	}

	protected Page createPage(String orderBy) {
		HttpServletRequest request = getRequest();
		int pageNumber = RequestUtil.getInt(request, "pn", 1);
		int pageSize = RequestUtil.getInt(request, "ps", 10);
		Page<T> page = new Page<T>();
		page.setOrderBy(orderBy);
		page.setNumber(pageNumber);
		page.setSize(pageSize);
		List<String> exportProperties = getExportProperties();
		page.setExportProperties(exportProperties);
		page.setDataUrl(getListDataUrl());
		return page;
	}

}
