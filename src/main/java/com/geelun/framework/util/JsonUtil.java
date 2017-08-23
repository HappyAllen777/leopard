package com.geelun.framework.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.geelun.framework.base.superclass.model.BaseModel;
import com.geelun.framework.exception.ReflectException;
import com.geelun.framework.persistence.Page;

public class JsonUtil {

	public static String baseModel2Json(BaseModel baseModel) throws ReflectException {
		if (baseModel == null) {
			return "{}";
		}
		Class clazz = baseModel.getClass();
		List<Method> properties = ReflectUtil.ensurePropertiesMethodIncludeSuperClass(clazz);

		StringBuffer json = new StringBuffer("{");
		for (Method getter : properties) {
			if (StringUtil.equals(getter.getName(), "getTableName")) {
				continue;
			}
			String propertyName = ReflectUtil.getPropertyNameByGetter(getter);
			getter.setAccessible(true);
			Object property = ReflectUtil.invoke(getter, baseModel, new Object[] {});
			json.append("\"" + propertyName + "\":" + getJsonStringByProperty(property) + ",");
		}
		StringUtil.deleteLastChar(json);
		json.append("}");
		return json.toString();
	}

	public static String baseModel2Json(BaseModel baseModel, List<String> properties) throws ReflectException {
		if (ArrayUtil.isEmpty(properties)) {
			return baseModel2Json(baseModel);
		}
		Class clazz = baseModel.getClass();
		StringBuffer json = new StringBuffer("{");
		for (String propertyName : properties) {
			Method getter = ReflectUtil.getGetterByPropertyName(clazz, propertyName);
			Object property = ReflectUtil.invoke(getter, baseModel, new Object[] {});
			json.append("\"" + propertyName + "\":" + getJsonStringByProperty(property) + ",");
		}
		StringUtil.deleteLastChar(json);
		json.append("}");
		return json.toString();
	}

	public static String baseModelList2Json(List<? extends BaseModel> baseModelList) throws ReflectException {
		if (ArrayUtil.isEmpty(baseModelList)) {
			return "[]";
		}
		Class clazz = baseModelList.get(0).getClass();
		List<Method> getters = ReflectUtil.ensurePropertiesMethodIncludeSuperClass(clazz);

		Map<String, String> methodPropertyCache = new HashMap<String, String>();
		StringBuffer json = new StringBuffer("[");
		for (BaseModel baseModel : baseModelList) {
			json.append("{");
			for (Method getter : getters) {
				String methodName = getter.getName();
				if (StringUtil.equals(methodName, "getTableName")) {
					continue;
				}
				String propertyName = methodPropertyCache.get(methodName);

				if (propertyName == null) {
					propertyName = ReflectUtil.getPropertyNameByGetter(getter);
					methodPropertyCache.put(methodName, propertyName);
				}

				getter.setAccessible(true);

				Object property = ReflectUtil.invoke(getter, baseModel, new Object[] {});
				json.append("\"" + propertyName + "\":" + getJsonStringByProperty(property) + ",");
			}
			StringUtil.deleteLastChar(json);
			json.append("},");
		}
		StringUtil.deleteLastChar(json);
		json.append("]");

		return json.toString();
	}

	public static String baseModelList2Json(List<? extends BaseModel> baseModelList, List<String> properties) throws ReflectException {
		if (ArrayUtil.isEmpty(properties)) {
			return baseModelList2Json(baseModelList);
		}
		if (ArrayUtil.isEmpty(baseModelList)) {
			return "[]";
		}
		Class clazz = baseModelList.get(0).getClass();

		Map<String, Method> propertyMethodCache = new HashMap<String, Method>();
		StringBuffer json = new StringBuffer("[");
		for (BaseModel baseModel : baseModelList) {
			json.append("{");
			for (String propertyName : properties) {
				Method getter = propertyMethodCache.get(propertyName);
				if (getter == null) {
					getter = ReflectUtil.getGetterByPropertyName(clazz, propertyName);
					propertyMethodCache.put(propertyName, getter);
				}

				Object property = ReflectUtil.invoke(getter, baseModel, new Object[] {});
				json.append("\"" + propertyName + "\":" + getJsonStringByProperty(property) + ",");
			}
			StringUtil.deleteLastChar(json);
			json.append("},");
		}
		StringUtil.deleteLastChar(json);
		json.append("]");

		return json.toString();
	}

	public static String page2Json(Page<? extends BaseModel> page) throws ReflectException {
		long totalSize = page.getTotalSize();
		int pageSize = page.getSize();
		int pageNumber = page.getNumber();
		int totalPageNumber = page.getTotalPageNumber();
		int minVisiblePageNumber = page.getMinVisiblePageNumber();
		int maxVisiblePageNumber = page.getMaxVisiblePageNumber();
		String dataUrl = page.getDataUrl();
		List<? extends BaseModel> list = page.getList();

		StringBuffer json = new StringBuffer();
		List<String> exportProperties = page.getExportProperties();
		String listJson = baseModelList2Json(list, exportProperties);
		return json
				.append("{\"totalPageNumber\":" + totalPageNumber + ",\"minVisiblePageNumber\":" + minVisiblePageNumber
						+ ",\"maxVisiblePageNumber\":" + maxVisiblePageNumber + ",\"totalSize\":" + totalSize + ",\"pageSize\":" + pageSize
						+ ",\"pageNumber\":" + pageNumber + ",\"dataUrl\":\"" + dataUrl + "\",\"list\":").append(listJson).append("}")
				.toString();
	}

	/**
	 * <pre>
	 * response输出ajax调用结果
	 * @param response
	 * @param json
	 * 
	 * <pre>
	 */
	public static void writeJsonResponse(HttpServletResponse response, AjaxResult ajaxResult) {
		String strJson = ajaxResult.toString();
		writeJsonResponse(response, strJson);
	}

	/**
	 * <pre>
	 * response输出json
	 * @param response
	 * @param json
	 * 
	 * <pre>
	 */
	public static void writeJsonResponse(HttpServletResponse response, String json) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.write(json);
			out.close();
		} catch (IOException e) {
			System.out.println("[error] " + e);
		}
	}

	public static void writePageResponse(HttpServletResponse response, Page<? extends BaseModel> page) {
		String pageJson = null;
		try {
			pageJson = page2Json(page);
		} catch (ReflectException e) {
			writeJsonResponse(response, new AjaxResult().setErrCode(e.getErrCode()).setErrMsg(e.getErrMsg()));
			return;
		}
		writeJsonResponse(response, pageJson);
	}

	private static String getJsonStringByProperty(Object property) {
		if (property == null) {
			return null;
		}
		if (property instanceof String) {
			return "\"" + property + "\"";
		} else if (property instanceof Number) {
			return property + "";
		} else if (property instanceof Boolean) {
			return property + "";
		} else if (property instanceof Date) {
			SimpleDateFormat datetimeFormatter = DateTimeUtil.getDateTimeFormatter();
			if (property instanceof Timestamp) {
				Timestamp timestamp = (Timestamp) property;
				String datetime = datetimeFormatter.format(timestamp);
				return "\"" + datetime + "\"";
			}
		} else {
			return "\"" + property + "\"";
		}
		return null;
	}
}
