package com.just.lollipop.bbs.vo;

import java.io.Serializable;
import java.util.Date;

public class UserVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; /** 用户标识  */
	
	private String userName; /** 用户名  */
	
	private String oldPass; /** 旧密码 */
	
	private String userPass; /** 用户密码  */
	
	private Integer sexy; /** 性别  */
	
	private String phone; /** 联系电话  */
	
	private String email; /** 电子邮件  */
	
	private Integer status; /** 用户状态， 1代表激活 2代表删除  */
	
	private Integer role; /** 用户角色，1代表普通会员 2代表版主 3代表管理员  */
	
	private Date registTime; /** 注册时间  */
	
	public UserVo(){}
	
	public UserVo(Integer id, String userName){
		this.id = id;
		this.userName = userName;
	}

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
	
	public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj != null && obj.getClass() == UserVo.class){
			UserVo userVo = (UserVo) obj;
			if (this.getId().intValue() == userVo.getId().intValue()){
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
}
