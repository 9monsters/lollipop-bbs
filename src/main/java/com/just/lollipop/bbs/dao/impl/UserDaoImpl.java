package com.just.lollipop.bbs.dao.impl;

import com.just.lollipop.bbs.dao.UserDao;
import com.just.lollipop.bbs.dao.base.impl.HibernateDaoImpl;
import com.just.lollipop.bbs.domain.User;
import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.vo.UserVo;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户数据访问实现类
 */
public class UserDaoImpl extends HibernateDaoImpl<User>
        implements UserDao {

    @Override
    public User getUser(String userName, String userPass) throws PtException {
        String hql = "select u from User u where u.userName = ? and u.userPass= ?";
        return this.findUniqueEntity(hql, userName, userPass);
    }

    @Override
    public List<User> findUsers(UserVo userVo, int start, int pagesize)
            throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT u ");
        sb.append("FROM User u ");
        sb.append("WHERE 1=1 ");
        List<Object> params = new ArrayList<Object>();
        if (userVo != null) {
            if (userVo.getUserName() != null && !"".equals(userVo.getUserName())) {
                sb.append("AND u.userName like ? ");
                params.add("%" + userVo.getUserName() + "%");
            }
            if (userVo.getStatus() != null && userVo.getStatus() != 0) {
                sb.append("AND u.status = ? ");
                params.add(userVo.getStatus());
            }
        }
        return this.findByPage(sb.toString(), start, pagesize, params.toArray());
    }

    @Override
    public int getTotalOfUsers(UserVo userVo) throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(u.id) ");
        sb.append("FROM User u ");
        sb.append("WHERE 1=1 ");
        List<Object> params = new ArrayList<Object>();
        if (userVo != null) {
            if (userVo.getUserName() != null && !"".equals(userVo.getUserName())) {
                sb.append("AND u.userName like ? ");
                params.add("%" + userVo.getUserName() + "%");
            }
            if (userVo.getStatus() != null && userVo.getStatus() != 0) {
                sb.append("AND u.status = ? ");
                params.add(userVo.getStatus());
            }
        }
        return this.count(sb.toString(), params.toArray());
    }

    @Override
    public void addUser(User user) throws PtException {
        this.save(user);
    }

    @Override
    public User getUser(Integer id) throws PtException {
        return this.get(User.class, id);
    }

    @Override
    public void updateUser(User user) throws PtException {
        this.saveOrUpdate(user);
    }

    @Override
    public List<User> getUsers() throws PtException {
        return this.find(User.class);
    }

    @Override
    public User getUser(String userName) throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT u ");
        sb.append("FROM User u ");
        sb.append("WHERE u.userName = ? ");
        return this.findUniqueEntity(sb.toString(), new Object[]{userName});
    }

}
