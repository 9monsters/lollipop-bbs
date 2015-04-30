package com.just.lollipop.bbs.service;

import java.lang.reflect.InvocationTargetException;

import com.just.lollipop.bbs.common.PaginationSupport;
import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.vo.UserVo;
import org.json.JSONException;

/**
 * 用户业务接口
 */
public interface UserService {
	
	/**
	 * 根据用户名和密码获取UserVo对象
	 * @param userName 用户名
	 * @param userPass 用户密码
	 * @return
	 */
	public UserVo getUser(String userName, String userPass) throws PtException;

	/**
	 * 分页获取用户数据
	 * @param userVo 条件查询，封装查询参数
	 * @param start 第几页
	 * @param pagesize 每页显示结果数
	 * @return
	 * @throws IllegalAccessException, InvocationTargetException, PtException
	 */
	public PaginationSupport<UserVo> findUsersByPage(UserVo userVo, int start,
													 int pagesize) throws IllegalAccessException, InvocationTargetException
			, PtException;

	/**
	 * 添加用户
	 * @param userVo
	 * @return
	 * @throws PtException
	 */
	public String addUser(UserVo userVo) throws PtException;

	/**
	 * 根据ID获取UserVo对象
	 * @param userId
	 * @return
	 * @throws IllegalAccessException, InvocationTargetException, PtException
	 */
	public UserVo getUser(Integer userId) throws PtException;

	/**
	 * 修改用户
	 * @param userVo
	 * @throws PtException
	 */
	public void updateUser(UserVo userVo) throws PtException;

	/**
	 * 重置密码
	 * @param userVo
	 * @throws PtException
	 */
	public void resetPassword(UserVo userVo) throws PtException;

	/**
	 * 删除用户
	 * @param userIds
	 * @throws PtException
	 */
	public void deleteUser(Integer userId) throws PtException;

	/**
	 * 激活用户
	 * @param userIds
	 * @throws PtException
	 */
	public void activateUser(Integer userId) throws PtException;

	/**
	 * 获取下拉框用户
	 * @return JSON字符串
	 * @throws PtException
	 */
	public String getComboUsers() throws JSONException, PtException;

	/**
	 * 修改密码
	 * @param userVo
	 * @throws PtException
	 */
    public String updatePass(UserVo userVo) throws PtException;

}
