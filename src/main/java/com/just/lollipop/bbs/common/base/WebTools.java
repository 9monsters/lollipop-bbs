/**
 *  Copyright 2011-2015 the original author or authors.
 */

package com.just.lollipop.bbs.common.base;

import java.io.File;
import java.io.FileNotFoundException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Helper class for Web
 * @author LiXiaoHua
 * @since 1.0
 * @see #setWebAppRootSystemProperty(ServletContext)
 * @see #setSessionAttribute(HttpServletRequest, String, Object)
 * @see #getRealPath(ServletContext, String)
 * @see #getSessionAttribute(HttpServletRequest, String)
 * @see #getTempDir(ServletContext)
 */
public class WebTools {
	
	public static final String TEMP_DIR_CONTEXT_ATTRIBUTE = "javax.servlet.context.tempdir";
	public static final String WEB_APP_ROOT_KEY_PARAM = "webAppRootKey";
	public static final String DEFAULT_WEB_APP_ROOT_KEY = "webapp.root";
	
	/**
	 * 设置WEB应用目录到系统属性中
	 * @param servletContext  the servlet context of the web application
	 * @throws IllegalStateException if the system property is already set
	 * @see #DEFAULT_WEB_APP_ROOT_KEY
	 * @see #WEB_APP_ROOT_KEY_PARAM
	 */
	public static void setWebAppRootSystemProperty(ServletContext servletContext) throws IllegalStateException {
		String root = servletContext.getRealPath("/");
		if (root == null) {
			throw new IllegalStateException(
			    "Cannot set web app root system property when WAR file is not expanded");
		}
		String param = servletContext.getInitParameter(WEB_APP_ROOT_KEY_PARAM);
		String key = (param != null) ? param :  DEFAULT_WEB_APP_ROOT_KEY;
		String oldValue = System.getProperty(key);
		if (oldValue != null) {
			throw new IllegalStateException(
			    "系统属性已存在 '" + key + "' = [" + oldValue + "] instead of [" + root + "]");
		}
		System.setProperty(key, root);
		servletContext.log("Set web app root system property: '" + key + "' = [" + root + "]");
	}
	
	/**
	 * 把系统属性中的WEB应用目录删除
	 * @param servletContext the servlet context of the web application
	 * @see #DEFAULT_WEB_APP_ROOT_KEY
	 * @see #WEB_APP_ROOT_KEY_PARAM
	 */
	public static void removeWebAppRootSystemProperty(ServletContext servletContext) {
		String param = servletContext.getInitParameter(WEB_APP_ROOT_KEY_PARAM);
		String key = (param != null) ? param :  DEFAULT_WEB_APP_ROOT_KEY;
		System.getProperties().remove(key);
	}
	/**
	 * 获取WEB应用临时文件
	 * as provided by the servlet container.
	 * @param servletContext the servlet context of the web application
	 * @return the File representing the temporary directory
	 */
	public static File getTempDir(ServletContext servletContext){
		return (File)servletContext.getAttribute(TEMP_DIR_CONTEXT_ATTRIBUTE);
	}
	/**
	 * 获取RealPath路径
	 * @param servletContext the servlet context of the web application
	 * @param path 当前路径
	 * @return the realPath
	 * @throws FileNotFoundException if cannot absolute file path
	 */
	public static String getRealPath(ServletContext servletContext, String path) throws FileNotFoundException {
		if (!path.startsWith("/")){
			path += "/" + path;
		}
		String realPath = servletContext.getRealPath(path);
		if (realPath == null) {
			throw new FileNotFoundException(
					"ServletContext resource [" + path + "] cannot be resolved to absolute file path - " +
					"web application archive not expanded?");
		}
		return realPath;
	}
	/**
	 * 获取Session属性值
	 * @param request HTTP request
	 * @param name 属性名称
	 * @return the object
	 */
	public static Object getSessionAttribute(HttpServletRequest request, String name) {
		HttpSession session =  request.getSession(false);
		return session == null ? null : session.getAttribute(name);
	}
	/**
	 * 设置Session属性值
	 * @param request HTTP request
	 * @param name 属性名称
	 * @param value 属性值
	 */
	public static void setSessionAttribute(HttpServletRequest request, String name, Object value) {
		if (value != null){
			request.getSession().setAttribute(name, value);
		}else{
			HttpSession session = request.getSession();
			if (session != null){
				session.removeAttribute(name);
			}
		}
	}
}
