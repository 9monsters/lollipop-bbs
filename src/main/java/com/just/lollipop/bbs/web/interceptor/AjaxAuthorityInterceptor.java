package com.just.lollipop.bbs.web.interceptor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.just.lollipop.bbs.vo.UserVo;
import com.just.lollipop.bbs.web.base.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AjaxAuthorityInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 1L;
    private final Log log = LogFactory.getLog(AjaxAuthorityInterceptor.class);
    private static Set<String> needLoginValidateActionSet; // 无需登录校验的Action
    
    @Override
    public void init() {
        initNeedLogActionSet();
    }
    
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        ActionContext actionContext = invocation.getInvocationContext();
        Map<String, Object> session = actionContext.getSession();
        UserVo user = (UserVo) session.get(Constants.USER);
        
        String requestURI = ServletActionContext.getRequest().getRequestURI();
        int index = requestURI.lastIndexOf(".");
        int start = requestURI.lastIndexOf("/");
        String method = requestURI.substring(start + 1, index);
        if (needLoginValidateActionSet.contains(method) && user == null) {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("tip", "请先登录.");
            return Constants.JSON;
        }
        return invocation.invoke();
    }
    
    private void initNeedLogActionSet() {
        if (needLoginValidateActionSet == null) {
            needLoginValidateActionSet = new HashSet<String>();
        }
        needLoginValidateActionSet.add("IndexAjaxAction!addArticle");
        needLoginValidateActionSet.add("IndexAjaxAction!updateArticle");
        needLoginValidateActionSet.add("IndexAjaxAction!addReply");
        needLoginValidateActionSet.add("IndexAjaxAction!deleteArticle");
        needLoginValidateActionSet.add("IndexAjaxAction!updateUser");
        needLoginValidateActionSet.add("IndexAjaxAction!changePass");
    }
    
}
