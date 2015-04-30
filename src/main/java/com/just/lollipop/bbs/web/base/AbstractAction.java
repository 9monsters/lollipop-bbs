package com.just.lollipop.bbs.web.base;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

public abstract class AbstractAction extends ActionSupport {
	private static final long serialVersionUID = 8687455982024646434L;
	protected static Logger logger =  LoggerFactory.getLogger(AbstractAction.class);
	public static final String RESULT_PAGES ="PAGES";
	public static final String RESULT_JSON ="json";
	public static final String RESULT_REDIRECT_ACTION ="REDIRECT_ACTION";
	private String tip;
	private String definedMethodTemp; // 返回哪个页面
	private String returnUrl; //登录后返回的地址 
	
	protected int currentPage = 0; //当前第几页
	protected int pageSize = 0; //每页显示的结果数
	
	/**
	 * 获取HTTP Request
	 * @return HttpServletRequest
	 */
	protected HttpServletRequest getServletRequest(){
		return ServletActionContext.getRequest();
	}
	
	/**
	 * 获取HTTP Response
	 * @return HttpServletResponse
	 */
	protected HttpServletResponse getServletResponse(){
		return ServletActionContext.getResponse();
	}
	
	/**
     * 获取HTTP Session
     * @return
     */
    public HttpSession getSession() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        return session;
    }
	
	/**
	 * 获取ServletContext
	 * @return ServletContext
	 */
	protected ServletContext getServletContext(){
		return ServletActionContext.getServletContext();
	}
	
	public String getTip() {
		return tip;
	}
	
	public void setTip(String tip) {
		this.tip = tip;
	}
	
    public String getDefinedMethodTemp() {
        return definedMethodTemp;
    }
    
    public void setDefinedMethodTemp(String definedMethodTemp) {
        this.definedMethodTemp = definedMethodTemp;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
	
}
