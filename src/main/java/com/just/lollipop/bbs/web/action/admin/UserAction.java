package com.just.lollipop.bbs.web.action.admin;

import java.lang.reflect.InvocationTargetException;

import com.just.lollipop.bbs.exception.PtException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.just.lollipop.bbs.service.UserService;
import com.just.lollipop.bbs.vo.UserVo;
import com.just.lollipop.bbs.web.base.CommonAction;


public class UserAction extends CommonAction {
	private static final long serialVersionUID = 1L;
	private final Log log = LogFactory.getLog(UserAction.class);
	
	private UserVo userVo;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 显示用户列表数据
	 */
	public String listUsers() {
		try {
			setLigerGrid(userService.findUsersByPage(userVo, getStart(), getPagesize()));
		} catch (IllegalAccessException e) {
			setResult("服务器发生异常，请联系管理员.");
			log.error("论坛管理平台发生异常：", e);
		} catch (InvocationTargetException e) {
			setResult("服务器发生异常，请联系管理员.");
			log.error("论坛管理平台发生异常：", e);
		} catch (PtException e) {
			setResult("服务器发生异常，请联系管理员.");
			log.error("论坛管理平台发生异常：", e);
		}
		return RESULT_JSON;
	}
	
	/**
	 * 添加用户
	 */
	public String addUser() {
		try {
			setResult(userService.addUser(userVo));
		} catch (PtException e) {
			setResult("服务器发生异常，请联系管理员.");
			log.error("论坛管理平台发生异常：", e);
		}
		return RESULT_JSON;
	}
	
	/**
	 * 进入修改用户页面
	 */
	public String forwardEditUserPage(){
		try {
			userVo = userService.getUser(userVo.getId());
		} catch (PtException e) {
			log.error("论坛管理平台发生异常：", e);
		}
		setDefinedMethodTemp("edituser");
		return RESULT_PAGES;
	}
	
	/**
	 * 修改用户
	 */
	public String updateUser(){
		try {
			userService.updateUser(userVo);
			setResult("ok");
		} catch (PtException e) {
			setResult("服务器发生异常，请联系管理员.");
			log.error("论坛管理平台发生异常：", e);
		}
		return RESULT_JSON;
	}
	
	/**
	 * 重置密码
	 */
	public String resetPassword(){
		try {
			userService.resetPassword(userVo);
			setResult("ok");
		} catch (PtException e) {
			setResult("服务器发生异常，请联系管理员.");
			log.error("论坛管理平台发生异常：", e);
		}
		return RESULT_JSON;
	}
	
	/**
	 * 冻结用户
	 */
	public String deleteUser(){
		try {
			userService.deleteUser(userVo.getId());
			setResult("ok");
		} catch (PtException e) {
			setResult("服务器发生异常，请联系管理员.");
			log.error("论坛管理平台发生异常：", e);
		}
		return RESULT_JSON;
	}
	
	/**
	 * 激活用户
	 */
	public String activateUser(){
		try {
			userService.activateUser(userVo.getId());
			setResult("ok");
		} catch (PtException e) {
			setResult("服务器发生异常，请联系管理员.");
			log.error("论坛管理平台发生异常：", e);
		}
		return RESULT_JSON;
	}
	
	/**
	 * 获取下拉框用户数据
	 */
	public String listComboUsers(){
		JSONObject jsonObject = new JSONObject();
		try {
			String result = userService.getComboUsers();
			jsonObject.put("status", "true");
			jsonObject.put("result", result);
			setResult("{status:'true',result:'" + result + "'}");
		} catch (JSONException e) {
			setResult("{status:'false',result:'服务器发生异常，请联系管理员.'}");
			log.error("论坛管理平台发生异常：", e);
		} catch (PtException e) {
			setResult("{status:'false',result:'服务器发生异常，请联系管理员.'}");
			log.error("论坛管理平台发生异常：", e);
		}
		return RESULT_JSON;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	
}
