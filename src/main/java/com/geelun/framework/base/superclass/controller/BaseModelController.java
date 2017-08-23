package com.geelun.framework.base.superclass.controller;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.geelun.framework.base.superclass.model.BaseModel;
import com.geelun.framework.base.superclass.service.BaseModelService;
import com.geelun.framework.util.AjaxResultUtil;
import com.geelun.framework.util.JsonUtil;
import com.geelun.framework.util.ReflectUtil;
import com.geelun.framework.util.RequestUtil;
import com.geelun.framework.util.StringUtil;

public abstract class BaseModelController<T extends BaseModel> {
	protected abstract String getControllerName();

	protected abstract BaseModelService<T> getService();

	/**
	 * 获取实体类允许修改的属性
	 * 
	 * @return
	 */
	public abstract List<String> getUpdateProperties();

	/**
	 * 数据默认排序规则
	 * 
	 * @return
	 */
	public abstract String getDefaultOrderBy();

	private T t;

	/**
	 * 获取服务器主机名，俗称网站域名
	 * 
	 * @return
	 */
	protected String getHostName() {
		return RequestUtil.getHostName(request);
	}

	/**
	 * 获取项目名
	 * 
	 * @return
	 */
	protected String getProjectName() {
		return RequestUtil.getProjectName(request);
	}

	/**
	 * 获取访问程序的url的前缀
	 * 
	 * @return
	 */
	protected String getUrlPrefix() {
		return RequestUtil.getUrlPrefix(request);
	}

	/**
	 * 错误页面的访问url
	 * 
	 * @param errorCode
	 * @return
	 */
	protected String getErrorPageUrl(int errorCode) {
		return getUrlPrefix() + "/static/html/" + errorCode + ".html";
	}

	/**
	 * 将当前controller的方法重定向到错误页面
	 * 
	 * @param errorCode
	 * @return
	 */
	protected String redirectToErrorPage(int errorCode) {
		return "redirect:" + getErrorPageUrl(errorCode);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/view")
	public String view() {
		String id = RequestUtil.getParam(request, "id");
		if (StringUtil.isBlank(id)) {
			return redirectToErrorPage(404);
		}
		t = getService().get(id);
		if (t == null) {
			return redirectToErrorPage(404);
		}
		request.setAttribute("baseModel", t);
		return "/" + getControllerName() + "/view";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/create")
	public String create() {
		return "/" + getControllerName() + "/create";
	}

	/**
	 * 删除对象
	 * 
	 * @param request
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/delete")
	public void delete(HttpServletRequest request) {
		String objectId = RequestUtil.getParam(request, "id");
		int rows = getService().delete(objectId);
		if (rows > 0) {
			JsonUtil.writeJsonResponse(getResponse(), AjaxResultUtil.success("删除成功"));
		} else {
			JsonUtil.writeJsonResponse(getResponse(), AjaxResultUtil.objectNotFound());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/edit")
	public String edit(HttpServletRequest request) {
		String id = RequestUtil.getParam(request, "id");
		if (StringUtil.isBlank(id)) {
			return redirectToErrorPage(404);
		}
		t = getService().get(id);
		if (t == null) {
			return redirectToErrorPage(404);
		}
		request.setAttribute("baseModel", t);
		return "/" + getControllerName() + "/edit";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public void update(HttpServletRequest request) {
		String id = RequestUtil.getParam(request, "id");
		t = getService().get(id);
		List<String> updateProperties = getUpdateProperties();
		Object[] newValues = null;
		try {
			newValues = getNewValues(request, updateProperties);
		} catch (SecurityException e) {
			JsonUtil.writeJsonResponse(getResponse(), AjaxResultUtil.getPropertyFailed());
		} catch (NoSuchFieldException e) {
			JsonUtil.writeJsonResponse(getResponse(), AjaxResultUtil.getPropertyFailed());
			return;
		}
		int rows = getService().update(id, updateProperties, newValues);
		if (rows == 1) {
			JsonUtil.writeJsonResponse(getResponse(), AjaxResultUtil.success("修改成功"));
		} else {
			JsonUtil.writeJsonResponse(getResponse(), AjaxResultUtil.objectNotFound());
		}
	}

	/**
	 * <pre>
	 * 获取修改对象的相关属性的新值
	 * @param request
	 * @param updateProperties
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * 
	 * <pre>
	 */
	private Object[] getNewValues(HttpServletRequest request, List<String> updateProperties) throws SecurityException, NoSuchFieldException {
		Class clazz = t.getClass();
		Object[] newValues = new Object[updateProperties.size()];
		int index = 0;
		for (String propertyName : updateProperties) {
			Field field = ReflectUtil.getDeclaredField(clazz, propertyName);
			Class type = field.getType();
			if (type == String.class) {
				newValues[index++] = RequestUtil.getParam(request, propertyName);
			} else if (type == Integer.class) {
				newValues[index++] = RequestUtil.getInt(request, propertyName);
			} else if (type == Date.class) {
				if (StringUtil.equals(propertyName, "lastUpdateTime")) {
					newValues[index++] = new Date();
				} else {
					newValues[index++] = RequestUtil.getParam(request, propertyName);
				}
			} else if (type == Double.class) {
				// TODO 处理double类型
			}
		}
		return newValues;
	}

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	protected HttpServletRequest getRequest() {
		return request;
	}

	protected HttpServletResponse getResponse() {
		return response;
	}

	protected HttpSession getSession() {
		return session;
	}

}
