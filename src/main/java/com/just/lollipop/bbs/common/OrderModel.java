package com.just.lollipop.bbs.common;

import java.util.LinkedList;
import java.util.List;

public class OrderModel implements java.io.Serializable{
	
	private static final long serialVersionUID = 1437849056453455155L;
	/** 定义降序常量属性 */
	public static final String DESC = "desc";
	/** 定义升序常量属性 */
	public static final String ASC = "asc";
	private List<String> keys = new LinkedList<String>();
	private List<String> values = new LinkedList<String>();
	private String model;
	
	public OrderModel(){
	}
	/**
	 * 添加排序
	 * @param field bean field
	 * @param type desc or asc
	 * @return this
	 * @see #DESC
	 * @see #ASC
	 */
	public OrderModel put(String field, String type){
		keys.add(field);
		values.add(type);
		return this;
	}
	
	@Override
	public String toString() {
		if (keys.size() == 0 || values.size() == 0){
			return "";
		} 
		model = (model == null) ? "" : model;
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < keys.size(); i++){
			str.append(("".equals(model) ? keys.get(i) : model + "." + keys.get(i)))
			   .append(" " + values.get(i));
			if (i + 1 < keys.size()){
				str.append(",");
			}
		}
		return str.toString();
	}

	public OrderModel setModel(String model){
		this.model = model;
		return this;
	}
}
