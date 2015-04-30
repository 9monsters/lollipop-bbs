package com.just.lollipop.bbs.domain;

import java.util.Date;
import java.util.Set;

/**
 * 用户实体类
 */
public class User {
	private Integer id; /** 用户标识  */
	
	private String userName; /** 用户名  */
	
	private String userPass; /** 用户密码  */
	
	private Integer sexy; /** 性别  */
	
	private String phone; /** 联系电话  */
	
	private String email; /** 电子邮件  */
	
	private Integer status = 1; /** 用户状态， 1代表激活状态 2代表冻结状态  */
	
	private Integer role = 1; /** 用户角色，1代表普通会员 2代表版主 3代表管理员  */
	
	private Date registTime = new Date(); /** 注册时间  */
	
	private Set<Article> articles; /** 发布的文章  */
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public Integer getSexy() {
		return sexy;
	}

	public void setSexy(Integer sexy) {
		this.sexy = sexy;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	
}	
