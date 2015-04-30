package com.just.lollipop.bbs.common.base;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.just.lollipop.bbs.util.str.StringUtils;

/**
 * Helper class for cookie generation
 * @author LiXiaoHua
 * @since 1.0
 * @see #addCookie(HttpServletResponse, String)
 * @see #addCookie(HttpServletRequest, HttpServletResponse, String)
 * @see #removeCookie(HttpServletResponse)
 * @see #getCookie(HttpServletRequest)
 * @see #getCookie(HttpServletRequest, String)
 */
public class CookieTools {
	
	/** 定义默认的Cookie路径 */
	public static final String DEFAULT_COOKIE_PATH = "/";
	/** 定义默认的Cookie有效时间  */
	public static final int DEFAULT_COOKIE_MAX_AGE = Integer.MAX_VALUE;
	private String cookieName;
	private String cookieDomain;
	private String cookiePath = DEFAULT_COOKIE_PATH;
	private int cookieMaxAge = DEFAULT_COOKIE_MAX_AGE;
	/** HTTP协议为false HTTPS下要设置为true */
	private boolean cookieSecure = false;
	private static CookieTools cookieGenerator;
	
	/** 私有构造器 */
	private CookieTools(){
	}
	
	/**
	 * 获取CookieGenerator实例
	 * @return the CookieGenerator
	 */
	public static CookieTools getInstance(){
		if (cookieGenerator == null){
			cookieGenerator = new CookieTools();
		}
		return cookieGenerator;
	}
	
	/**
	 * 添加一个Cookie(如果已存在则复盖否创建一个新Cookie)
	 * @param response HTTP request
	 * @param response HTTP response
	 * @param cookieValue cookie值
	 * @see #setCookieDomain
	 * @see #setCookieMaxAge
	 * @see #setCookieName
	 * @see #setCookiePath
	 * @see #createCookie
	 * @see #getCookie(HttpServletRequest)
	 */
	public void addCookie(HttpServletRequest request, HttpServletResponse response, String cookieValue){
		Cookie cookie = getCookie(request);
		if (cookie != null){
			cookie.setValue(cookieValue);
			if (getCookieDomain() != null){
				cookie.setDomain(getCookieDomain());
			}
			cookie.setPath(getCookiePath());
		}else{
			cookie = createCookie(cookieValue);
		}
		cookie.setMaxAge(getCookieMaxAge());
		if (isCookieSecure()){
			cookie.setSecure(true);
		}
		response.addCookie(cookie);
	}
	
	/**
	 * 添加一个Cookie
	 * @param response HTTP response
	 * @param cookieValue cookie值
	 * @see #setCookieDomain
	 * @see #setCookieMaxAge
	 * @see #setCookieName
	 * @see #setCookiePath
	 * @see #createCookie
	 */
	public void addCookie(HttpServletResponse response, String cookieValue){
		Cookie cookie = createCookie(cookieValue);
		cookie.setMaxAge(getCookieMaxAge());
		if (isCookieSecure()){
			cookie.setSecure(true);
		}
		response.addCookie(cookie);
	}
	
	/**
	 * 删除一个Cookie
	 * @param response HTTP response
	 * @see #setCookieName
	 * @see #setCookieDomain
	 * @see #setCookiePath
	 * @see #createCookie
	 */
	public void removeCookie(HttpServletResponse response){
		Cookie cookie = StringUtils.hasLength(this.cookieName) ?
					createCookie(this.cookieName) : createCookie("") ;
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	
	/**
	 * 获取单个Cookie
	 * @param request HTTP request
	 * @see #setCookieName
	 * @return the Cookie or <code>null</code> if not find
	 */
	public Cookie getCookie(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if (cookies != null){
			for (int i = 0; i < cookies.length; i++){
				if (cookieName.equals(cookies[i].getName())){
					return cookies[i];
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取单个Cookie
	 * @param request HTTP request
	 * @param cookieName cookie名称
	 * @return the Cookie or <code>null</code> if not find
	 */
	public static Cookie getCookie(HttpServletRequest request, String cookieName){
		Cookie[] cookies = request.getCookies();
		if (cookies != null){
			for (int i = 0; i < cookies.length; i++){
				if (cookieName.equals(cookies[i].getName())){
					return cookies[i];
				}
			}
		}
		return null;
	}
	
	/**
	 * 创建一个Cookie 
	 * @param cookieValue Cookie值
	 * @return the Cookie
	 * @see #setCookieDomain
	 * @see #setCookieMaxAge
	 * @see #setCookieName
	 * @see #setCookiePath
	 */
	protected  Cookie createCookie(String cookieValue){
		Cookie cookie = new Cookie(getCookieName(), cookieValue);
		if (this.getCookieDomain() != null){
			cookie.setDomain(this.getCookieDomain());
		}
		cookie.setPath(this.getCookiePath());
		return cookie;
	}
	
	
	/** setter and getter method */
	public String getCookieName() {
		return cookieName;
	}
	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}
	public String getCookieDomain() {
		return cookieDomain;
	}
	public void setCookieDomain(String cookieDomain) {
		this.cookieDomain = cookieDomain;
	}
	public String getCookiePath() {
		return cookiePath;
	}
	public void setCookiePath(String cookiePath) {
		this.cookiePath = cookiePath;
	}
	public int getCookieMaxAge() {
		return cookieMaxAge;
	}
	public void setCookieMaxAge(int cookieMaxAge) {
		this.cookieMaxAge = cookieMaxAge;
	}
	public boolean isCookieSecure() {
		return cookieSecure;
	}
	public void setCookieSecure(boolean cookieSecure) {
		this.cookieSecure = cookieSecure;
	}
}