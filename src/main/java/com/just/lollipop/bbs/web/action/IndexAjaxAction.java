package com.just.lollipop.bbs.web.action;

import java.util.List;

import javax.servlet.ServletContext;

import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.service.ArticleService;
import com.just.lollipop.bbs.vo.ArticleVo;
import com.just.lollipop.bbs.web.base.AbstractAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.just.lollipop.bbs.service.UserService;
import com.just.lollipop.bbs.vo.UserVo;
import com.just.lollipop.bbs.web.base.Constants;


public class IndexAjaxAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private final Log log = LogFactory.getLog(IndexAjaxAction.class);
    private UserVo userVo;
    private ArticleVo articleVo;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ArticleService articleService;
    
    /**
     * 网站登录
     */
    @SuppressWarnings("unchecked")
    public String login(){
        try {
            String username = userVo.getUserName();
            String userpass = userVo.getUserPass();
            UserVo user = userService.getUser(username, userpass);
            if (user != null){
                if (user.getStatus() == 1){
                    ServletContext application = getSession().getServletContext();
                    List<UserVo> onlineUserList = (List<UserVo>) application.getAttribute("onlineUserList");
                    if (!onlineUserList.contains(user)){
                        onlineUserList.add(user);
                        application.setAttribute("onlineUserList", onlineUserList);
                    }
                    getSession().setAttribute(Constants.USER, user);
                    setTip("ok");
                }else{
                    setTip("该用户已经不存在.");
                }
            }else{
                setTip("用户名或密码不正确.");
            }
        } catch (Exception e) {
            setTip("服务器发生异常，请联系管理员.");
            log.error("论坛管理平台发生异常：", e);
        }
        return RESULT_JSON;
    }
    
    /**
     * 退出网站
     */
    public String logout(){
        getSession().invalidate();
        setTip("ok");
        return RESULT_JSON;
    }
    
    /**
     * 发表帖子
     */
    public String addArticle(){
        try {
            articleService.addArticle(articleVo);
            setTip("ok");
        } catch (Exception e) {
            setTip("服务器发生异常，请联系管理员.");
            log.error("论坛管理平台发生异常：", e);
        }
        return RESULT_JSON;
    }
    
    /**
     * 回复帖子
     */
    public String addReply(){
        try {
            articleService.addReply(articleVo);
            setTip("ok");
        } catch (Exception e) {
            setTip("服务器发生异常，请联系管理员.");
            log.error("论坛管理平台发生异常：", e);
        }
        return RESULT_JSON;
    }
    
    /**
     * 删除帖子
     */
    public String deleteArticle(){
        try {
            articleService.delete(articleVo);
            setTip("ok");
        } catch (PtException e) {
            setTip("服务器发生异常，请联系管理员.");
            log.error("论坛管理平台发生异常：", e);
        }
        return RESULT_JSON;
    }
    
    /**
     * 修改帖子
     */
    public String updateArticle(){
        try {
            articleService.updateArticle(articleVo);
            setTip("ok");
        } catch (Exception e) {
            setTip("服务器发生异常，请联系管理员.");
            log.error("论坛管理平台发生异常：", e);
        }
        return RESULT_JSON;
    }
    
    /**
     * 更新用户信息
     */
    public String updateUser(){
        try {
            userService.updateUser(userVo);
            setTip("ok");
        } catch (Exception e) {
            setTip("服务器发生异常，请联系管理员.");
            log.error("论坛管理平台发生异常：", e);
        }
        return RESULT_JSON;
    }

    /**
     * 修改密码
     */
    public String changePass(){
        try {
            setTip(userService.updatePass(userVo));
        } catch (PtException e) {
            setTip("服务器发生异常，请联系管理员.");
            log.error("程序发生异常：", e);
        }
        return RESULT_JSON;
    }
    
    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    public ArticleVo getArticleVo() {
        return articleVo;
    }

    public void setArticleVo(ArticleVo articleVo) {
        this.articleVo = articleVo;
    }
    
}
