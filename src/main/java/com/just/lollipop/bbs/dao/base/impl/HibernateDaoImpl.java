package com.just.lollipop.bbs.dao.base.impl;

import com.just.lollipop.bbs.common.Condition;
import com.just.lollipop.bbs.dao.base.IConditionQuery;
import com.just.lollipop.bbs.dao.base.IHibernateDao;
import org.apache.commons.lang.StringUtils;
import org.hibernate.*;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.event.EventSource;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class HibernateDaoImpl<T> extends HibernateDaoSupport implements IHibernateDao<T> {

    private boolean cacheQueries = false;

    @Override
    protected HibernateTemplate createHibernateTemplate(SessionFactory sessionFactory) {
        HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
        if (cacheQueries) {
            hibernateTemplate.setCacheQueries(cacheQueries);
        }
        return hibernateTemplate;
    }

    /**
     * 统计总条数
     *
     * @param hql 查询语句
     */
    @Override
    public Integer count(final String hql) {
        if (StringUtils.isEmpty(hql)) {
            throw new IllegalStateException("hql is null");
        }
        Object result = this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createQuery(hql).uniqueResult();
            }
        });
        return ((Long) result).intValue();
    }

    /**
     * 按条件统计总条数
     *
     * @param hql
     * @param obj
     */
    @Override
    public Integer count(final String hql, final Object... obj) {
        if (ObjectUtils.isEmpty(obj)) {
            return count(hql);
        } else {
            if (StringUtils.isEmpty(hql)) {
                throw new IllegalStateException("hql is null");
            }
            Object result = this.getHibernateTemplate().execute(new HibernateCallback() {

                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    Query query = session.createQuery(hql);
                    for (int i = 0; i < obj.length; i++) {
                        query.setParameter(i, obj[i]);
                    }
                    return query.uniqueResult();
                }
            });
            return ((Long) result).intValue();
        }
    }

    /**
     * 删除
     *
     * @param entity
     */
    @Override
    public void delete(T entity) {
        getHibernateTemplate().delete(entity);
    }

    /**
     * 批量修改或删除
     *
     * @param queryString
     * @param values
     */
    @Override
    public int bulkUpdate(String queryString, Object[] values) {
        return getHibernateTemplate().bulkUpdate(queryString, values);
    }

    /**
     * 删除全部
     *
     * @param entities
     */
    @Override
    public void deleteAll(Collection<T> entities) {
        getHibernateTemplate().deleteAll(entities);
    }

    /**
     * 判断是否存在
     *
     * @param c, id
     */
    @Override
    public boolean exist(Class<T> c, Serializable id) {
        if (get(c, id) != null)
            return true;
        return false;
    }

    /**
     * 查询全部
     *
     * @param queryString
     */
    @Override
    public List<T> find(String queryString) {
        return (List<T>) getHibernateTemplate().find(queryString);
    }

    /**
     * Execute an HQL query.
     *
     * @param bean
     * @return
     */
    @Override
    public List<T> find(Class<T> bean) {
        String hql = "FROM " + bean.getSimpleName();
        return find(hql);
    }

    /**
     * 按条件查询全部
     *
     * @param queryString
     * @param values
     */
    @Override
    public List<T> find(String queryString, Object... values) {
        if (ObjectUtils.isEmpty(values)) {
            return find(queryString);
        } else {
            return (List<T>) getHibernateTemplate().find(queryString, values);
        }
    }

    /**
     * 获取唯一实体
     *
     * @param queryString HQL query string
     * @param params      query object array params
     * @return unique object
     * @throws IllegalStateException if queryString is null
     * @see org.hibernate.Session
     * @see #getHibernateTemplate
     * @see org.springframework.orm.hibernate3.HibernateCallback
     */
    @Override
    public T findUniqueEntity(final String queryString, final Object... params) {
        if (StringUtils.isEmpty(queryString)) {
            throw new IllegalStateException("queryString is null");
        }
        if (ObjectUtils.isEmpty(params)) {
            return (T) getHibernateTemplate().execute(new HibernateCallback() {
                public Object doInHibernate(Session session) {
                    return session.createQuery(queryString).uniqueResult();
                }
            });
        } else {
            return (T) getHibernateTemplate().execute(new HibernateCallback() {
                public Object doInHibernate(Session session) {
                    Query query = session.createQuery(queryString);
                    for (int i = 0; i < params.length; i++) {
                        query.setParameter(i, params[i]);
                    }
                    return query.uniqueResult();
                }
            });
        }
    }

    /**
     * 命名查询
     *
     * @param queryName
     */
    @Override
    public List<T> findByNamedQuery(String queryName) {
        return getHibernateTemplate().findByNamedQuery(queryName);
    }

    /**
     * 条件命名查询
     *
     * @param queryName
     * @param values
     */
    @Override
    public List<T> findByNamedQuery(String queryName, Object... values) {
        return getHibernateTemplate().findByNamedQuery(queryName, values);
    }

    /**
     * 多条件分页查询
     *
     * @param hql      query string
     * @param startRow begin row
     * @param pageSize page number
     * @param params   query object params array
     * @return the query list<?> result
     * @throws IllegalStateException if queryString is null
     * @see org.hibernate.Session
     * @see #getHibernateTemplate
     * @see org.springframework.orm.hibernate3.HibernateCallback
     */
    @Override
    public List<T> findByPage(final String hql, final Integer startRow,
                              final Integer pageSize, final Object... params) {
        if (StringUtils.isEmpty(hql)) {
            throw new IllegalStateException("hql is null");
        }
        if (ObjectUtils.isEmpty(params)) {
            return getHibernateTemplate().executeFind(new HibernateCallback() {
                public Object doInHibernate(Session session) {
                    return session.createQuery(hql).setFirstResult(startRow)
                            .setMaxResults(pageSize).list();
                }
            });
        } else {
            return getHibernateTemplate().executeFind(new HibernateCallback() {
                public Object doInHibernate(Session session) {
                    Query query = session.createQuery(hql);
                    for (int i = 0; i < params.length; i++) {
                        query.setParameter(i, params[i]);
                    }
                    return query.setFirstResult(startRow).setMaxResults(
                            pageSize).list();
                }
            });
        }
    }

    /**
     * 获取一个实体
     *
     * @param entityClass
     * @param id
     */
    @Override
    public T get(Class<T> entityClass, Serializable id) {
        return (T) getHibernateTemplate().get(entityClass, id);
    }

    @Override
    public Iterator<T> iterate(String queryString) {
        return (Iterator<T>) getHibernateTemplate().iterate(queryString);
    }

    @Override
    public Iterator<T> iterate(String queryString, Object... values) {
        return (Iterator<T>) getHibernateTemplate().iterate(queryString, values);
    }

    /**
     * 加载一个实体
     *
     * @param entityClass
     * @param id
     */
    @Override
    public T load(Class<T> entityClass, Serializable id) {
        return (T) getHibernateTemplate().load(entityClass, id);
    }

    @Override
    public void persist(T entity) {
        getHibernateTemplate().persist(entity);
    }

    @Override
    public void refresh(T entity) {
        getHibernateTemplate().refresh(entity);
    }

    /**
     * 保存
     *
     * @param entity
     * @throws IllegalStateException if entity is null
     */
    @Override
    public Serializable save(T entity) {
        if (entity == null) {
            throw new IllegalStateException("entity is null");
        }
        return getHibernateTemplate().save(entity);
    }

    /**
     * 保存与修改
     *
     * @param entity
     */
    @Override
    public void saveOrUpdate(T entity) {
        getHibernateTemplate().saveOrUpdate(entity);
    }

    /**
     * 保存与修改全部
     *
     * @param entities
     */
    @Override
    public void saveOrUpdateAll(Collection<T> entities) {
        getHibernateTemplate().saveOrUpdateAll(entities);
    }

    /**
     * 修改
     *
     * @param entity
     */
    @Override
    public void update(T entity) {
        getHibernateTemplate().update(entity);
    }

    /**
     * 修改所有的实体
     *
     * @param entities
     * @throws IllegalStateException if entities is null
     */
    @Override
    public void updateAll(Collection<T> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            throw new IllegalStateException("entities is null");
        }
        int i = 0;
        for (Object obj : entities) {
            if (i % 30 == 0) {
                getHibernateTemplate().flush();
                getHibernateTemplate().clear();
            }
            getHibernateTemplate().update(obj);
            i++;
        }
    }

    /**
     * 保存所有的实体
     *
     * @param entities
     * @throws IllegalStateException if entities is null
     */
    @Override
    public void saveAll(Collection<T> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            throw new IllegalStateException("entities is null");
        }
        int i = 0;
        for (T obj : entities) {
            if (i % 30 == 0) {
                getHibernateTemplate().flush();
                getHibernateTemplate().clear();
            }
            save(obj);
            i++;
        }
    }

    /**
     * 多条件分页查询
     *
     * @param query    获取HQL与查询参数的接口
     * @param startRow 行数
     * @param pageSize 页数
     * @return 分页查询后的数据集合
     * @see #findByPage(String, Integer, Integer, Object...)
     */
    @Override
    public List<T> findByPage(IConditionQuery query, Integer startRow,
                              Integer pageSize) {

        Map<String, Object> maps = query.getQueryParams(Condition.getInstance());
        String hql = (String) maps.get(IConditionQuery.HQL);
        Object[] params = (Object[]) maps.get(IConditionQuery.PARAMS);
        if (ObjectUtils.isEmpty(params)) {
            return findByPage(hql, startRow, pageSize);
        } else {
            return findByPage(hql, startRow, pageSize, params);
        }
    }

    /**
     * 根据条件统计总条数
     *
     * @param query 接口
     * @return the count
     * @see #count(String)
     * @see #count(String, Object...)
     */
    @Override
    public Integer count(IConditionQuery query) {
        Map<String, Object> maps = query.getQueryParams(Condition.getInstance());
        String hql = (String) maps.get(IConditionQuery.HQL);
        Object[] params = (Object[]) maps.get(IConditionQuery.PARAMS);
        if (ObjectUtils.isEmpty(params)) {
            return count(hql);
        } else {
            return count(hql, params);
        }
    }

    public void setCacheQueries(boolean cacheQueries) {
        this.cacheQueries = cacheQueries;
    }


    /**
     * 创建一个session代理
     *
     * @param session
     * @return
     */
    @SuppressWarnings("unused")
    private Session createSessionProxy(Session session) {
        Class[] sessionIfcs = null;
        Class<? extends Session> mainIfc = (session instanceof org.hibernate.classic.Session ?
                org.hibernate.classic.Session.class : Session.class);
        if (session instanceof EventSource) {
            sessionIfcs = new Class[]{mainIfc, EventSource.class};
        } else if (session instanceof SessionImplementor) {
            sessionIfcs = new Class[]{mainIfc, SessionImplementor.class};
        } else {
            sessionIfcs = new Class[]{mainIfc};
        }
        return (Session) Proxy.newProxyInstance(
                session.getClass().getClassLoader(), sessionIfcs,
                new SessionInvocationHandler(session));
    }

    private class SessionInvocationHandler implements InvocationHandler {
        private final Session target;

        public SessionInvocationHandler(Session target) {
            this.target = target;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("equals")) {
                return (proxy == args[0] ? Boolean.TRUE : Boolean.FALSE);
            } else if (method.getName().equals("hashCode")) {
                return new Integer(System.identityHashCode(proxy));
            }
            try {
                Object retVal = method.invoke(this.target, args);
                if (retVal instanceof Query) {
                    ((Query) retVal).setCacheable(true);
                }
                if (retVal instanceof Criteria) {
                    ((Criteria) retVal).setCacheable(true);
                }
                return retVal;
            } catch (InvocationTargetException ex) {
                throw ex.getTargetException();
            }
        }
    }
}