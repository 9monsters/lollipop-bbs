package com.just.lollipop.bbs.dao.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public interface IHibernateDao<T> {
    /**
     * 删除
     *
     * @param entity
     */
    public void delete(T entity);

    /**
     * 批量修改或删除
     *
     * @param queryString
     * @param values
     */
    public int bulkUpdate(String queryString, Object[] values);

    /**
     * 批量删除
     *
     * @param entities
     */
    public void deleteAll(Collection<T> entities);

    /**
     * Execute an HQL query.
     *
     * @param queryString
     * @return
     */
    public List<T> find(String queryString);

    /**
     * Execute an HQL query.
     *
     * @param bean
     * @return
     */
    public List<T> find(Class<T> bean);

    /**
     * Execute an HQL query, binding a number of values to "?"
     *
     * @param queryString
     * @param values
     * @return
     */
    public List<?> find(String queryString, Object... values);

    /**
     * 获取唯一实体
     *
     * @param queryString HQL query string
     * @param params      query object array params
     * @return unique object
     * @throws IllegalStateException if queryString is null
     * @see org.hibernate.Session
     * @see org.springframework.orm.hibernate3.HibernateCallback
     */
    public T findUniqueEntity(String queryString, Object... params);

    /**
     * 多条件分页查询
     *
     * @param queryString      query string
     * @param startRow begin row
     * @param pageSize page number
     * @param params   query object params array
     * @return the query list<?> result
     * @throws IllegalStateException if queryString is null
     * @see org.hibernate.Session
     * @see org.springframework.orm.hibernate3.HibernateCallback
     */
    public List<T> findByPage(String queryString, Integer startRow,
                              Integer pageSize, Object... params);

    /**
     * Execute a named query.
     *
     * @param queryName
     * @return
     */
    public List<T> findByNamedQuery(String queryName);

    /**
     * Execute a named query binding a number of values to "?"
     *
     * @param queryName
     * @param values
     * @return
     */
    public List<T> findByNamedQuery(String queryName, Object... values);

    /**
     * 获取单个实体
     *
     * @param entityClass
     * @param id
     * @return
     */
    public T get(Class<T> entityClass, Serializable id);

    /**
     * Execute a query for persistent instances.
     *
     * @param queryString
     * @return
     */
    public Iterator<T> iterate(String queryString);

    /**
     * 条件查询返回一个迭代器
     *
     * @param queryString
     * @param values
     * @return
     */
    public Iterator<T> iterate(String queryString, Object... values);

    /**
     * load获取一个实体
     *
     * @param entityClass
     * @param id
     * @return
     */
    public T load(Class<T> entityClass, Serializable id);

    /**
     * 持久化一个对象
     *
     * @param entity
     */
    public void persist(T entity);

    /**
     * 刷新一个对象
     *
     * @param entity
     */
    public void refresh(T entity);

    /**
     * 保存一个对象
     *
     * @param entity
     * @return
     */
    public Serializable save(T entity);

    /**
     * 保存一个集合
     *
     * @param entities
     */
    public void saveAll(Collection<T> entities);

    /**
     * 保存或修改一个实体
     *
     * @param entity
     */
    public void saveOrUpdate(T entity);

    /**
     * 保存或修改一个集合
     *
     * @param entities
     */
    public void saveOrUpdateAll(Collection<T> entities);

    /**
     * 修改一个实体
     *
     * @param entity
     */
    public void update(T entity);

    /**
     * 修改一个集合
     *
     * @param entities
     */
    public void updateAll(Collection<T> entities);

    /**
     * id对应的对象是否存在
     *
     * @param c
     * @param id
     * @return
     */
    public boolean exist(Class<T> c, Serializable id);

    /**
     * 统计总条数
     *
     * @param hql
     * @return
     */
    public Integer count(String hql);

    /**
     * 根据条件统计总条数
     *
     * @param hql
     * @param obj
     * @return
     */
    public Integer count(String hql, Object... obj);

    /**
     * 多条件分页查询
     *
     * @param query    接口
     * @param startRow 开始行数
     * @param pageSize 页数
     * @return 分页查询后的数据集合
     * @see #findByPage(String, Integer, Integer, Object...)
     */
    public List<T> findByPage(IConditionQuery query, Integer startRow, Integer pageSize);

    /**
     * 根据条件统计总条数
     *
     * @param query 接口
     * @return the count
     * @see #count(String, Object...)
     */
    public Integer count(IConditionQuery query);

}