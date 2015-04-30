package com.just.lollipop.bbs.web.action.admin;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.just.lollipop.bbs.service.UserService;
import com.just.lollipop.bbs.vo.UserVo;
import com.just.lollipop.bbs.web.base.CommonAction;
import com.just.lollipop.bbs.web.base.Constants;


public class IndexAction extends CommonAction {
	private static final long serialVersionUID = 1L;
	private final Log log = LogFactory.getLog(IndexAction.class);
	
	private UserVo userVo;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 用户登录
	 */
	public String login() {
		try {
			String username = userVo.getUserName();
			String userpass = userVo.getUserPass();
			UserVo u = userService.getUser(username, userpass);
			if (u != null){
				if (u.getStatus() == 1){
					if (u.getRole() == 1){
						setResult("对不起，您无权访问.");
					}else{
					    HttpSession session = getSession();
			        	session.setAttribute(Constants.USER, u);
						setResult("ok");
					}
				}else{
					setResult("该账号已被冻结，请联系管理员.");
				}
			}else{
				setResult("用户名或密码不正确.");
			}
		} catch (Exception e) {
			setResult("服务器发生异常，请联系管理员.");
			log.error("论坛管理平台发生异常：", e);
		}
		return RESULT_JSON;
	}
	
	/**
	 * 用户退出
	 */
	public String logout() {
		getSession().invalidate();
		setDefinedMethodTemp("login");
		return RESULT_PAGES;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	
}
