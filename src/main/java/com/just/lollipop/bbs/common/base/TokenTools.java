package com.just.lollipop.bbs.common.base;

import javax.servlet.http.HttpServletRequest;

public class TokenTools {
	public static final String WEB_TOKEN = "web_token";
	
	public static boolean validateToken(HttpServletRequest request, String token_value){
		String value = (String)request.getSession().getAttribute(WEB_TOKEN);
		if (value.equals(token_value)){
			request.getSession().removeAttribute(WEB_TOKEN);
			return true;
		}
		return false;
	}
}
