package com.just.lollipop.bbs.common.base;

public class ListEntry {

	final private Object key;
	final private Object value;

	public ListEntry(Object key, Object value) {
		this.key = key;
		this.value = value;
	}

	public Object getKey() {
		return key;
	}

	public Object getValue() {
		return value;
	}
}