package com.just.lollipop.bbs.dao;

import com.just.lollipop.bbs.domain.User;
import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.vo.UserVo;

import java.util.List;

/**
 * 用户数据访问接口
 */
public interface UserDao {

    /**
     * 根据用户名称和密码获取User对象
     *
     * @param userName
     * @param userPass
     * @return
     * @throws PtException
     */
    public User getUser(String userName, String userPass) throws PtException;

    /**
     * 根据查询条件，分页获取User对象
     *
     * @param userVo
     * @param start
     * @param pagesize
     * @return
     * @throws PtException
     */
    public List<User> findUsers(UserVo userVo, int start, int pagesize) throws PtException;

    /**
     * 根据查询条件获取查询的结果总数
     *
     * @param userVo
     * @return
     * @throws PtException
     */
    public int getTotalOfUsers(UserVo userVo) throws PtException;

    /**
     * 添加用户
     *
     * @param user
     * @throws PtException
     */
    public void addUser(User user) throws PtException;

    /**
     * 根据ID获取User对象
     *
     * @param id
     * @return
     * @throws PtException
     */
    public User getUser(Integer id) throws PtException;

    /**
     * 修改用户
     *
     * @param user
     * @throws PtException
     */
    public void updateUser(User user) throws PtException;

    /**
     * 获取用户
     *
     * @return
     * @throws PtException
     */
    public List<User> getUsers() throws PtException;

    /**
     * 根据用户名获取User对象
     *
     * @param userName
     * @return
     * @throws PtException
     */
    public User getUser(String userName) throws PtException;

}
