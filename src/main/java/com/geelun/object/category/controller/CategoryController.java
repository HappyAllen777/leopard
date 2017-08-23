package com.geelun.object.category.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.geelun.framework.base.superclass.controller.PagedController;
import com.geelun.framework.base.superclass.service.BaseModelService;
import com.geelun.framework.util.AjaxResult;
import com.geelun.framework.util.AjaxResultUtil;
import com.geelun.framework.util.IDUtil;
import com.geelun.framework.util.JsonUtil;
import com.geelun.framework.util.RequestUtil;
import com.geelun.framework.validate.BaseModelValidateResult;
import com.geelun.framework.validate.BaseModelValidator;
import com.geelun.object.category.model.Category;
import com.geelun.object.category.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController extends PagedController<Category> {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public void add(HttpServletRequest request) {
		String name = RequestUtil.getParam(request, "name");
		String remarks = RequestUtil.getParam(request, "remarks");
		String type = RequestUtil.getParam(request, "type");
		Timestamp timeStamp = new Timestamp(new Date().getTime());
		Category category = new Category();
		category.setCreateBy("Allen");
		category.setCreateTime(timeStamp);
		category.setId(IDUtil.getUUID());
		category.setLastUpdateTime(timeStamp);
		category.setName(name);
		category.setRemarks(remarks);
		category.setType(type);
		BaseModelValidateResult result = BaseModelValidator.validatePersistentModel(category);
		if (result.isSuccess()) {
			categoryService.add(category);
			JsonUtil.writeJsonResponse(getResponse(), AjaxResultUtil.success("保存成功"));
		} else {
			JsonUtil.writeJsonResponse(getResponse(), new AjaxResult().setErrCode(1).setErrMsg(result.getMsg()));
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/move")
	public String move(HttpServletRequest request) {
		return "move";
	}

	@Override
	public List<String> getExportProperties() {
		List<String> exportProperties = new ArrayList<String>();
		exportProperties.add("id");
		exportProperties.add("name");
		exportProperties.add("remarks");
		exportProperties.add("typeName");
		exportProperties.add("createTime");
		return exportProperties;
	}

	@Override
	public List<String> getUpdateProperties() {
		List<String> exportProperties = new ArrayList<String>();
		exportProperties.add("name");
		exportProperties.add("remarks");
		exportProperties.add("type");
		exportProperties.add("lastUpdateTime");
		return exportProperties;
	}

	@Override
	protected BaseModelService<Category> getService() {
		return categoryService;
	}

	@Override
	protected String getControllerName() {
		return "category";
	}

	@Override
	public String getDefaultOrderBy() {
		return "createTime desc";
	}

	@Override
	protected String getDefaultPageOrderBy() {
		return "name";
	}
}
