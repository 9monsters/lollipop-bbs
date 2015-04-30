package com.just.lollipop.bbs.common;

import java.util.Collections;
import java.util.List;

public class Generator {
	
	private static Generator generator;
	public static Generator getInstance(){
		if (generator == null){
			generator = new Generator();
		}
		return generator;
	}
	
	public static String generatedName(String code, String prefix, String name){
		String str = "";
		if (code != null && !"".equals(code.trim())){
			for (int i = 0; i < code.length() - 4; i++){
				str += prefix;
			}
		}
		return str + name;
	}
	
	/**
	 * @param idList 编号生成器
	 * @param prefix 已存在的编号集合
	 * @param length 生成编号的长度(包含前缀)
	 * @return 生成的编号
	 * @throws Exception
	 * @author
	 */
	public String generatedId(List<String> idList, String prefix,
										int length) throws Exception {
		prefix = prefix == null ? "" : prefix;
		if (length >= prefix.length()) {
			length = length - prefix.length();
		}
		/** 保存生成的编号 **/
		String generatedId = "";
		/** 保存最大编号的数字部分 **/
		int id = 0;
		/** 没有已存在的编号时自动从000...1开始 **/
		if (idList == null || idList.isEmpty()) {
			for (int i = 1; i < length; i++) {
				generatedId += "0";
			}
			generatedId += 1;
		} else {
			/** 对已存在的编号进行排序 **/
			Collections.sort(idList);
			/** 对排序后的编号倒序(使得最大的编号排到第一位) **/
			Collections.reverse(idList);
			try {
				if (length >= prefix.length()) {
					id = Integer.parseInt(idList.get(0).substring(
							prefix.length()));
				} else {
					id = Integer.parseInt(idList.get(0).substring(
							prefix.length()));
				}
			} catch (NumberFormatException nex) {
				throw new Exception("只能生成格式为:\"[prefix][0-9][0-9]...\"");
			}
			int temp1 = String.valueOf(id).length();
			int temp2 = String.valueOf(id + 1).length();
			/** 长度不够的在前面加0 **/
			if (temp1 < length) {
				for (int j = 0; j < length - temp2; j++) {
					generatedId += "0";
				}
			}
			generatedId += (id + 1);
		}
		if (generatedId.length() > length) {
			throw new Exception("在指定长度范围内的编号已全部占用...");
		}
		return prefix + generatedId;
	}
}