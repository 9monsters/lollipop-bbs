package com.just.lollipop.bbs.dao.base;

import java.util.Map;

import com.just.lollipop.bbs.common.Condition;

public interface IConditionQuery {
	
	public static final String HQL = "hql";
	public static final String PARAMS = "params";
	
	/**
	 * 获取HQL与查询参数数组
	 * @return the map
	 */
	Map<String,Object> getQueryParams(Condition handler);
}
