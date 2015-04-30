package com.just.lollipop.bbs.web.interceptor.admin;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.just.lollipop.bbs.vo.UserVo;
import com.just.lollipop.bbs.web.base.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	private final Log log = LogFactory.getLog(AuthorityInterceptor.class);
	
	private static Set<String> notLoginValidateActionSet; //无需登录校验的Action
	private static Set<String> hasAuthActionSetForModerator; //版主有权访问的Action
	
	@Override
	public void init() {
		initNotNeedLogActionSet();
		initAuthActionSetForModerator();
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		log.info("authority interceptor starting...");
		ActionContext actionContext = invocation.getInvocationContext();
		Map<String, Object> session = actionContext.getSession();
		UserVo user = (UserVo)session.get(Constants.USER);
		
		String requestURI = ServletActionContext.getRequest().getRequestURI();
		int index = requestURI.lastIndexOf(".");
		String suffix = requestURI.substring(index + 1);
		boolean isNeedLoginValidation = true;
		String method = "";
		int start = 0;
		if ("jspx".equals(suffix) || "action".equals(suffix)){
			start = requestURI.lastIndexOf("/");
			method = requestURI.substring(start + 1, index);
			if (notNeedLoginValidateAction(method)){
			    isNeedLoginValidation = false;
			}
		}
		if (isNeedLoginValidation){
		    if (user == null){
		        return Constants.NO_LOGIN;
		    }
		    if (user.getRole() == 2 && !hasAuth(method)){
	            return Constants.NO_AUTH; 
	        }
		}
		return invocation.invoke();
	}
	
	private void initNotNeedLogActionSet() {
		if (notLoginValidateActionSet == null) {
			notLoginValidateActionSet = new HashSet<String>();
		}
		notLoginValidateActionSet.add("login_page");
		notLoginValidateActionSet.add("index_page");
		notLoginValidateActionSet.add("IndexAction!login");
		notLoginValidateActionSet.add("IndexAction!logout");
	}
	
	private void initAuthActionSetForModerator() {
        if (hasAuthActionSetForModerator == null) {
            hasAuthActionSetForModerator = new HashSet<String>();
        }
        hasAuthActionSetForModerator.add("login_page");
        hasAuthActionSetForModerator.add("index_page");
        hasAuthActionSetForModerator.add("articles_page");
        hasAuthActionSetForModerator.add("articlereplys_page");
        hasAuthActionSetForModerator.add("addarticle_page");
        hasAuthActionSetForModerator.add("addreply_page");
        
        hasAuthActionSetForModerator.add("IndexAction!login");
        hasAuthActionSetForModerator.add("IndexAction!logout");
        hasAuthActionSetForModerator.add("ArticleAction!listArticles");
        hasAuthActionSetForModerator.add("ArticleAction!addArticle");
        hasAuthActionSetForModerator.add("ArticleAction!forwardEditArticlePage");
        hasAuthActionSetForModerator.add("ArticleAction!updateArticle");
        hasAuthActionSetForModerator.add("ArticleAction!deleteArticle");
        hasAuthActionSetForModerator.add("ArticleAction!listArticleReplys");
        hasAuthActionSetForModerator.add("ArticleAction!addReply");
        hasAuthActionSetForModerator.add("ArticleAction!forwardEditReplyPage");
        hasAuthActionSetForModerator.add("ArticleAction!updateReply");
        hasAuthActionSetForModerator.add("ArticleAction!deleteReply");
        hasAuthActionSetForModerator.add("BoardAction!listComboBoards");
    }
	
	//判断该Action是否需要登录验证，true代表不需要， false代表需要。
	private boolean notNeedLoginValidateAction(String action) {
		if (notLoginValidateActionSet == null) {
		    initNotNeedLogActionSet();
		}
		return notLoginValidateActionSet.contains(action);
	}
	
	//判断该角色是否有权限访问，true代表有权访问，false代表无权访问。
    private boolean hasAuth(String action) {
        if (hasAuthActionSetForModerator == null) {
            initAuthActionSetForModerator();
        }
        return hasAuthActionSetForModerator.contains(action);
    }
}
