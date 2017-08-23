package com.geelun.object.article.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.geelun.framework.util.StringUtil;
import com.geelun.framework.validate.BaseModelValidateResult;
import com.geelun.framework.validate.BaseModelValidator;
import com.geelun.object.article.model.Article;
import com.geelun.object.article.service.ArticleService;

@Controller
@RequestMapping("/article")
public class ArticleController extends PagedController<Article> {

	@Autowired
	private ArticleService articleService;

	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public void add(Article article, HttpServletRequest request, HttpServletResponse response) {
		String title = RequestUtil.getParam(request, "title");

		if (StringUtil.isBlank(title)) {
			JsonUtil.writeJsonResponse(response, AjaxResultUtil.fieldIsEmpty("标题"));
			return;
		}

		Timestamp now = new Timestamp(new Date().getTime());
		article.setTitle(title);
		article.setCreateTime(now);
		article.setId(IDUtil.getUUID());
		article.setLastUpdateTime(now);
		article.setAuthor("Allen");
		article.setCreateBy("Allen");
		article.setPublished(false);
		BaseModelValidateResult result = BaseModelValidator.validatePersistentModel(article);
		if (!result.isSuccess()) {
			JsonUtil.writeJsonResponse(response, new AjaxResult().setErrCode(1).setErrMsg("参数非法"));
		} else {
			articleService.add(article);
			JsonUtil.writeJsonResponse(response, AjaxResultUtil.success("保存成功"));
		}
	}

	@Override
	protected String getControllerName() {
		return "article";
	}

	@Override
	protected BaseModelService getService() {
		return articleService;
	}

	@Override
	public List<String> getExportProperties() {
		List<String> exportProperties = new ArrayList<String>(5);
		exportProperties.add("author");
		exportProperties.add("title");
		exportProperties.add("createTime");
		exportProperties.add("id");
		return exportProperties;
	}

	@Override
	public List<String> getUpdateProperties() {
		List<String> exportProperties = new ArrayList<String>(5);
		exportProperties.add("title");
		exportProperties.add("content");
		exportProperties.add("lastUpdateTime");
		exportProperties.add("id");
		return exportProperties;
	}

	@Override
	public String getDefaultOrderBy() {
		return "createTime desc";
	}

	@Override
	protected String getDefaultPageOrderBy() {
		return "categoryId, title";
	}

}
