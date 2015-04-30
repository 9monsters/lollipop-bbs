package com.just.lollipop.bbs.dao.base;

public interface IGeneratorDao {
	
	String generatorCode(String prefix, Class<?> bean, String fieldName);
}
