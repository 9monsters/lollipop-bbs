package com.just.lollipop.bbs.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.just.lollipop.bbs.common.PaginationSupport;
import com.just.lollipop.bbs.dao.UserDao;
import com.just.lollipop.bbs.domain.User;
import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.service.UserService;
import com.just.lollipop.bbs.vo.UserVo;
import com.just.lollipop.bbs.web.base.Constants;
import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务实现类
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public UserVo getUser(String userName, String userPass) throws PtException {
		User user = userDao.getUser(userName, userPass);
		UserVo userVo = null;
		if (user != null){
			userVo = new UserVo();
			userVo.setId(user.getId());
			userVo.setUserName(user.getUserName());
			userVo.setSexy(user.getSexy());
			userVo.setRole(user.getRole());
			userVo.setStatus(user.getStatus());
		}
		return userVo;
	}

	@Override
	public PaginationSupport<UserVo> findUsersByPage(UserVo userVo, int start,
			int pagesize) throws IllegalAccessException
			, InvocationTargetException, PtException {
		List<User> users = userDao.findUsers(userVo, start, pagesize);
		List<UserVo> voList = new ArrayList<UserVo>();
		for (User u : users){
			UserVo vo = new UserVo();
			BeanUtils.copyProperties(vo, u);
			voList.add(vo);
		}
		int total = userDao.getTotalOfUsers(userVo);
		PaginationSupport<UserVo> pager = new PaginationSupport<UserVo>(voList
				, start, total, pagesize);
		return pager;
	}

	@Override
	public String addUser(UserVo userVo) throws PtException {
	    User user = userDao.getUser(userVo.getUserName().trim());
	    String msg = "";
	    if (user == null){
	        User u = new User();
	        u.setUserName(userVo.getUserName().trim());
	        u.setUserPass(userVo.getUserPass());
	        u.setSexy(userVo.getSexy());
	        u.setPhone(userVo.getPhone());
	        u.setEmail(userVo.getEmail());
	        userDao.addUser(u);
	        msg = "ok";
	    }else{
	        msg = "该用户名已经存在.";
	    }
		return msg;
	}

	@Override
	public UserVo getUser(Integer userId) throws PtException {
		User u = userDao.getUser(userId);
		UserVo vo = new UserVo();
		try {
            BeanUtils.copyProperties(vo, u);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return vo;
	}

	@Override
	public void updateUser(UserVo userVo) throws PtException {
		User u = userDao.getUser(userVo.getId());
		u.setSexy(userVo.getSexy());
		u.setPhone(userVo.getPhone());
		u.setEmail(userVo.getEmail());
		userDao.updateUser(u);
	}

	@Override
	public void resetPassword(UserVo userVo) throws PtException {
		User u = userDao.getUser(userVo.getId());
		u.setUserPass("123456");
		userDao.updateUser(u);
	}

	@Override
	public void deleteUser(Integer userId) throws PtException {
		User u = userDao.getUser(userId);
		u.setStatus(2);
		userDao.updateUser(u);
	}

	@Override
	public void activateUser(Integer userId) throws PtException {
		User u = userDao.getUser(userId);
		u.setStatus(1);
		userDao.updateUser(u);
	}

	@Override
	public String getComboUsers() throws JSONException, PtException {
		List<User> users = userDao.getUsers();
		JSONArray result = new JSONArray();
		JSONObject json = null;
		for (User u : users){
		    if (u.getRole() != Constants.ROLE_ADMIN){
		        json = new JSONObject();
		        json.put("id", u.getId());
		        json.put("name", u.getUserName());
		        result.put(json);
		    }
		}
		return result.toString();
	}

    @Override
    public String updatePass(UserVo userVo) throws PtException {
        User user = userDao.getUser(userVo.getId());
        if (!userVo.getOldPass().equals(user.getUserPass())){
            return "密码不正确.";
        }
        user.setUserPass(userVo.getUserPass());
        userDao.updateUser(user);
        return "ok";
    }
	
}
