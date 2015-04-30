package com.just.lollipop.bbs.dao.base.impl;

import java.util.List;

import com.just.lollipop.bbs.common.Generator;
import com.just.lollipop.bbs.dao.base.IGeneratorDao;

public class GeneratorDaoImpl<T> extends HibernateDaoImpl<T> implements IGeneratorDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public String generatorCode(String prefix, Class<?> bean, String fieldName) {
		int length = 4;
		if (prefix != null && !"".equals(prefix)){
			length = prefix.length() + 4;
		}
		StringBuffer hql = new StringBuffer();
		hql.append("select " + fieldName + " from " + bean.getSimpleName() + " where ");
		hql.append("length(" + fieldName + ") = " + length);
		List<String> lists;
		if (prefix != null && prefix.length() >= 4) {
			hql.append(" and " + fieldName + " like ?");
			lists = (List<String>)find(hql.toString(), prefix + "%");
		} else {
			lists = (List<String>)find(hql.toString());
		}
		try {
			return Generator.getInstance().generatedId(lists, prefix, length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
