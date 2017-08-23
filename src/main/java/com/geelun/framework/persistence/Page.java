package com.geelun.framework.persistence;

import java.io.Serializable;
import java.util.List;

import com.geelun.framework.base.superclass.model.BaseModel;

public class Page<T extends BaseModel> implements Serializable {

	private static final long serialVersionUID = 6563170829269963015L;

	/**
	 * 计算页面范围
	 */
	public void countVisibility() {
		totalPageNumber = (int) Math.ceil((totalSize) / (size * 1.0));
		minVisiblePageNumber = (number - size >= 1) ? (number - defaultVisibleSize) : 1;
		maxVisiblePageNumber = (number + defaultVisibleSize >= totalPageNumber) ? totalPageNumber : (number + defaultVisibleSize);
	}

	/**
	 * @return：获取数据的链接
	 */
	public String getDataUrl() {
		return dataUrl;
	}

	/**
	 * @return：默认页面可视范围
	 */
	public int getDefaultVisibleSize() {
		return defaultVisibleSize;
	}

	/**
	 * @return：要输出的实体类的属性
	 */
	public List<String> getExportProperties() {
		return exportProperties;
	}

	/**
	 * @return ： 当前查询条件，当前页码，当天页大小的结果集
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * @return：最大可视页码
	 */
	public int getMaxVisiblePageNumber() {
		return maxVisiblePageNumber;
	}

	/**
	 * @return：最小可视页码
	 */
	public int getMinVisiblePageNumber() {
		return minVisiblePageNumber;
	}

	/**
	 * @return ： 当前页码
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @return ： 当前查询条件
	 */
	public SearchForm getSearchForm() {
		return searchForm;
	}

	/**
	 * @return ： 页面数据量
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 
	 * @return：总页数
	 */
	public int getTotalPageNumber() {
		return totalPageNumber;
	}

	/**
	 * @return ： 当前条件下所有结果集的数量
	 */
	public long getTotalSize() {
		return totalSize;
	}

	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

	public void setDefaultVisibleSize(int defaultVisibleSize) {
		this.defaultVisibleSize = defaultVisibleSize;
	}

	public void setExportProperties(List<String> exportProperties) {
		this.exportProperties = exportProperties;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public void setMaxVisiblePageNumber(int maxVisiblePageNumber) {
		this.maxVisiblePageNumber = maxVisiblePageNumber;
	}

	public void setMinVisiblePageNumber(int minVisiblePageNumber) {
		this.minVisiblePageNumber = minVisiblePageNumber;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setSearchForm(SearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setTotalPageNumber(int totalPageNumber) {
		this.totalPageNumber = totalPageNumber;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	private String dataUrl;

	private int defaultVisibleSize = 2;

	private List<String> exportProperties;

	private List<T> list;
	private int maxVisiblePageNumber;
	private int minVisiblePageNumber;

	private int number = 1;
	private SearchForm searchForm;
	private int size = 10;

	private int totalPageNumber;

	private int totalSize;
	
	private String orderBy;

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
