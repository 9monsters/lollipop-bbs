package com.just.lollipop.bbs.web.interceptor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.just.lollipop.bbs.vo.UserVo;
import com.just.lollipop.bbs.web.base.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 1L;
    private final Log log = LogFactory.getLog(AuthorityInterceptor.class);
    private static final String REGEX_PATTERN = "^(.*)!forward(.*)Page$";
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
        String suffix = requestURI.substring(index + 1);
        int start = requestURI.lastIndexOf("/");
        String method = requestURI.substring(start + 1, index);
        if (needLoginValidateActionSet.contains(method) && user == null) {
            // 保存当前请求路径，以便登录后返回该页面
            HttpServletRequest request = ServletActionContext.getRequest();
            String url = "";
            if ("jspx".equals(suffix)){
                url = "/" + method + ".jspx?";
            }else if ("action".equals(suffix)){
                if (method.matches(REGEX_PATTERN)){
                    String nameSpace = invocation.getProxy().getNamespace();
                    url = nameSpace + "/" + method + ".action?";
                }
            }
            Map<String, String[]> zzMap = request.getParameterMap();
            if (zzMap != null) {
                for (String s : zzMap.keySet()) {
                    String[] value = zzMap.get(s);
                    for (String val : value) {
                        url = url + s + "=" + val + "&";
                    }
                    
                }
            }
            log.info("待转向URL:" + url);
            request.setAttribute("returnUrl", url);
            return Constants.LOGIN;
        }
        return invocation.invoke();
    }
    
    private void initNeedLogActionSet() {
        if (needLoginValidateActionSet == null) {
            needLoginValidateActionSet = new HashSet<String>();
        }
        needLoginValidateActionSet.add("addarticle_page");
        needLoginValidateActionSet.add("mypass_page");
        
        needLoginValidateActionSet.add("IndexAction!forwardMyInfoPage");
        needLoginValidateActionSet.add("IndexAction!forwardMyBlogPage");
        needLoginValidateActionSet.add("IndexAction!forwardEditArticlePage");
    }
    
}
