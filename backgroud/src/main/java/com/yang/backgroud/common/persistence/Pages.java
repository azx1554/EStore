package com.yang.backgroud.common.persistence;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页类
 *
 */
public class Pages<T> {
	private int pageNo = 1; // 当前页码
	private int pageSize = 10; // 页面大小，设置为“-1”表示不进行分页（分页无效）
	private long total;// 总记录数，设置为“-1”表示不查询总数
	private List<T> rows = new ArrayList<T>();

	public Pages() {
		this.pageSize = -1;
	}

	/**
	 * 构造方法
	 *
	 * @param pageNo   当前页码
	 * @param pageSize 分页大小
	 */
	public Pages(int pageNo, int pageSize) {
		this(pageNo, pageSize, 0);
	}

	public Pages(HttpServletRequest request, HttpServletResponse response) {
		this(request, response, -2);
	}

	public Pages(HttpServletRequest request, HttpServletResponse response, int defaultPageSize) {
		String page = request.getParameter("page");
		this.setPageNo(Integer.parseInt(page));
		String size = request.getParameter("rows");
		this.setPageSize(Integer.parseInt(size));
	}

	/**
	 * 构造方法
	 *
	 * @param pageNo   当前页码
	 * @param pageSize 分页大小
	 * @param count    数据条数
	 */
	public Pages(int pageNo, int pageSize, long count) {
		this(pageNo, pageSize, count, new ArrayList<T>());
	}

	/**
	 * 构造方法
	 *
	 * @param pageNo   当前页码
	 * @param pageSize 分页大小
	 * @param count    数据条数
	 * @param list     本页数据对象列表
	 */
	public Pages(int pageNo, int pageSize, long count, List<T> list) {
		this.setTotal(count);
		this.setPageNo(pageNo);
		this.pageSize = pageSize;
		this.rows = list;
	}

	/**
	 * 获取设置总数
	 *
	 * @return
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * 设置数据总数
	 *
	 * @param count
	 */
	public void setTotal(long count) {
		this.total = count;
		if (pageSize >= count) {
			pageNo = 1;
		}
	}

	/**
	 * 获取当前页码
	 *
	 * @return
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置当前页码
	 *
	 * @param pageNo
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 获取页面大小
	 *
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置页面大小（最大500）
	 *
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize <= 0 ? 10 : pageSize;// > 500 ? 500 : pageSize;
	}

	/**
	 * 获取本页数据对象列表
	 *
	 * @return List<T>
	 */
	public List<T> getRows() {
		return rows;
	}

	/**
	 * 设置本页数据对象列表
	 *
	 * @param list
	 */
	public Pages<T> setRows(List<T> list) {
		this.rows = list;
		return this;
	}

	/**
	 * 分页是否有效
	 *
	 * @return this.pageSize==-1
	 */
	public boolean isDisabled() {
		return this.pageSize == -1;
	}

	/**
	 * 是否进行总数统计
	 *
	 * @return this.count==-1
	 */
	public boolean isNotCount() {
		return this.total == -1;
	}

	/**
	 * 获取 Hibernate FirstResult
	 */
	public int getFirstResult() {
		int firstResult = (getPageNo() - 1) * getPageSize();
		if (firstResult >= getTotal()) {
			firstResult = 0;
		}
		return firstResult;
	}

	/**
	 * 获取 Hibernate MaxResults
	 */
	public int getMaxResults() {
		return getPageSize();
	}
}
