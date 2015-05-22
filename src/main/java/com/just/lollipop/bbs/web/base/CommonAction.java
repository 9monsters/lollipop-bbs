package com.just.lollipop.bbs.web.base;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.just.lollipop.bbs.common.PaginationSupport;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class CommonAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	public static final String RESULT_JSON = "JSON";
	public static final String RESULT_METHOD = "METHOD";
	public static final String RESULT_ERROR_REDIRECT = "ERROR_REDIRECT";
	public static final String RESULT_DEFINED = "DEFINED";
	public static final String RESULT_PAGES ="PAGES";
	public static final String RESULT_DOWNLOAD ="DOWNLOAD";
	public static final String RESULT_CHART ="CHART";
	public static final String RESULT_STREAM ="STREAM";
	protected int page = 1;// 默认当前页数
	protected int pagesize = 10;// 默认每页的记录数
	protected List<?> rows;// 分页数据
	protected long total; // 总记录数
	private String sortname;
	private String sortorder;
	private String definedMethodTemp; // 返回哪个页面
	private String result; // 处理结果

	/**
	 * 得到requst对象
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return request;
	}

	/**
	 * 得到session对象
	 * 
	 * @return
	 */
	public HttpSession getSession() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		return session;
	}
	
	/**
	 * 根据虚拟路径获取其真实路径
	 * 
	 * @param virtualPath
	 * @return
	 */
	public String getRealPath(String virtualPath) {
		return ServletActionContext.getServletContext()
				.getRealPath(virtualPath);
	}
	
	/**
	 * 根据虚拟路径获取其真实路径
	 *
	 * @return
	 */
	public String getServerPath() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getLocalPort() + request.getContextPath()
				+ "/";
	}
	
	/**
	 * 获取服务器真实的目录
	 * @param
	 * @return
	 * @throws
	 */
	public String getBasePath(){
		HttpServletRequest request = ServletActionContext.getRequest();
		return request.getRequestURI();
	}
	
	/**
	 * 得到方法定义
	 * 
	 * @return
	 */
	public String getDefinedMethodTemp() {
		return definedMethodTemp;
	}

	public void setDefinedMethodTemp(String definedMethodTemp) {
		this.definedMethodTemp = definedMethodTemp;
	}

	/**
	 * 启始页数
	 * 
	 * @return
	 */
	public int getStart() {
		return (page - 1) * pagesize;
	}

	public int getPageNo() {
		return page == 0 ? 1 : page;
	}

	/**
	 * 设置分页数据
	 * 
	 * @param rows
	 *            分页数据
	 * @param total
	 *            总记录数
	 */
	@SuppressWarnings("rawtypes")
	public void setLigerGrid(List<?> rows, long total) {
		this.rows = (rows == null ? new ArrayList(0) : rows);
		this.total = total;
	}

	/**
	 * 设置分页数据
	 * 
	 * @param page
	 *            分页对象
	 */
	@SuppressWarnings("rawtypes")
	public void setLigerGrid(PaginationSupport<?> page) {
		List<?> list = page.getItems();
		this.rows = (list == null || list.size() == 0 ? new ArrayList(0) : list);
		this.total = page.getTotalCount();
	}
	
	/**
	 * 设置表格数据
	 */
	@SuppressWarnings("rawtypes")
	public void setLigerGrid(List<?> rows) {
		this.rows = (rows == null ? new ArrayList(0) : rows);
	}

	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * 得到 每页分页数据
	 */
	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	/**
	 * 得到分页数据
	 * 
	 * @return
	 */
	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	/**
	 * 得到总页数
	 * 
	 * @return
	 */
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
