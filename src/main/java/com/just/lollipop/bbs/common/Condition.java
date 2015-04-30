package com.just.lollipop.bbs.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.just.lollipop.bbs.dao.base.IConditionQuery;

public class Condition implements java.io.Serializable{
	
	private static final long serialVersionUID = 1478803287687671425L;
	private static Condition condition;
	private Condition(){}
	
	public static Condition getInstance(){
		if (condition == null){
			condition = new Condition();
		}
		return condition;
	}
	/**
	 * toMap
	 * @param hql HQL语句
	 * @param params HQL参数集合
	 * @return the map
	 */
	public Map<String, Object> toMap(String hql, List<Object> params){
		Map<String, Object> maps = new HashMap<String,Object>();
		maps.put(IConditionQuery.HQL, hql.toString());
		if (params == null || params.size() == 0){
			maps.put(IConditionQuery.PARAMS, new Object[0]);
		}else{
			maps.put(IConditionQuery.PARAMS, params.toArray());
		}
		return maps;
	}
}
