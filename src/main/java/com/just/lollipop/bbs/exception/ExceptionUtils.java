package com.just.lollipop.bbs.exception;

public final class ExceptionUtils {
	
	public static String getCause(Throwable ex){
		StackTraceElement[] stes = ex.getStackTrace();
		if (stes == null){
			return ex.toString();
		}
		StringBuilder str = new StringBuilder();
		str.append(ex + "\r\n");
		for (StackTraceElement stackTraceElement : stes){
			str.append(stackTraceElement.toString() + "\r\n");
		}
		return str.toString();
	}
}
