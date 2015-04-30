package com.just.lollipop.bbs.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.just.lollipop.bbs.vo.UserVo;
import com.just.lollipop.bbs.web.base.Constants;

public class OnlineUserListener implements HttpSessionListener {
    private final Log log = LogFactory.getLog(OnlineUserListener.class);
    private static List<UserVo> onlineUserList = null;
    
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        if (onlineUserList == null) {
            onlineUserList = new ArrayList<UserVo>();
            
            HttpSession session = event.getSession();
            ServletContext application = session.getServletContext();
            application.setAttribute("onlineUserList", onlineUserList);
        }
    }
    
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        
        // 取得登录的用户
        UserVo userVo = (UserVo) session.getAttribute(Constants.USER);
        
        // 从在线列表中删除用户名
        onlineUserList = (List<UserVo>) application.getAttribute("onlineUserList");
        if (onlineUserList.contains(userVo)) {
            onlineUserList.remove(userVo);
        }
        application.setAttribute("onlineUserList", onlineUserList);
        
        System.out.print("当前在线人数（" + onlineUserList.size() + "人）：");
        for (UserVo vo : onlineUserList) {
            System.out.print(vo.getUserName() + ",");
        }
        System.out.println("");
    }
    
}
