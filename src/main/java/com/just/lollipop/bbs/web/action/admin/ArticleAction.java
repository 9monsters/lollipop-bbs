package com.just.lollipop.bbs.web.action.admin;

import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.service.ArticleService;
import com.just.lollipop.bbs.vo.ArticleReplyVo;
import com.just.lollipop.bbs.vo.ArticleVo;
import com.just.lollipop.bbs.web.base.CommonAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class ArticleAction extends CommonAction {
	private static final long serialVersionUID = 1L;
	private final Log log = LogFactory.getLog(ArticleAction.class);
	
	private ArticleVo articleVo;
	private ArticleReplyVo articleReplyVo;
	private String ids; //文章IDs
	
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 显示所有文章
	 */
	public String listArticles() {
		try {
			setLigerGrid(articleService.findArticlesByPage(
					articleVo, getStart(), getPagesize()));
		} catch (PtException e) {
			setResult("服务器发生异常，请联系管理员.");
			log.error("论坛管理平台发生异常：", e);
		}
		return RESULT_JSON;
	}
	
	/**
     * 添加文章
     */
    public String addArticle() {
        try {
            articleService.addArticle(articleVo);
            setResult("ok");
        } catch (PtException e) {
            setResult("服务器发生异常，请联系管理员.");
            log.error("论坛管理平台发生异常：", e);
        }
        return RESULT_JSON;
    }
    
    /**
     * 进入修改文章页面
     */
    public String forwardEditArticlePage(){
        try {
            articleVo = articleService.getArticle(articleVo.getId());
        } catch (PtException e) {
            log.error("论坛管理平台发生异常：", e);
        }
        setDefinedMethodTemp("editarticle");
        return RESULT_PAGES;
    }
    
    /**
     * 修改文章
     */
    public String updateArticle(){
        try {
            articleService.updateArticle(articleVo);
            setResult("ok");
        } catch (PtException e) {
            setResult("服务器发生异常，请联系管理员.");
            log.error("论坛管理平台发生异常：", e);
        }
        return RESULT_JSON;
    }
    
    /**
     * 删除文章
     */
    public String deleteArticle(){
        try {
            articleService.deleteArticle(ids);
            setResult("ok");
        } catch (PtException e) {
            setResult("服务器发生异常，请联系管理员.");
            log.error("论坛管理平台发生异常：", e);
        }
        return RESULT_JSON;
    }
    
    /**
     * 显示所有文章
     */
    public String listArticleReplys() {
        try {
            setLigerGrid(articleService.findReplysByPage(
                    articleVo, getStart(), getPagesize()));
        } catch (PtException e) {
            setResult("服务器发生异常，请联系管理员.");
            log.error("论坛管理平台发生异常：", e);
        }
        return RESULT_JSON;
    }
    
    /**
     * 添加文章
     */
    public String addReply() {
        try {
            articleService.addReply(articleVo);
            setResult("ok");
        } catch (PtException e) {
            setResult("服务器发生异常，请联系管理员.");
            log.error("论坛管理平台发生异常：", e);
        }
        return RESULT_JSON;
    }
    
    /**
     * 进入修改回复页面
     */
    public String forwardEditReplyPage(){
        try {
            articleVo = articleService.getArticleReply(articleVo.getId());
        } catch (PtException e) {
            log.error("论坛管理平台发生异常：", e);
        }
        setDefinedMethodTemp("editreply");
        return RESULT_PAGES;
    }
    
    /**
     * 修改回复
     */
    public String updateReply(){
        try {
            articleService.updateReply(articleVo);
            setResult("ok");
        } catch (PtException e) {
            setResult("服务器发生异常，请联系管理员.");
            log.error("论坛管理平台发生异常：", e);
        }
        return RESULT_JSON;
    }
    
    /**
     * 删除回复
     */
    public String deleteReply(){
        try {
            articleService.deleteReply(ids);
            setResult("ok");
        } catch (PtException e) {
            setResult("服务器发生异常，请联系管理员.");
            log.error("论坛管理平台发生异常：", e);
        }
        return RESULT_JSON;
    }
    
	public ArticleVo getArticleVo() {
		return articleVo;
	}

	public void setArticleVo(ArticleVo articleVo) {
		this.articleVo = articleVo;
	}

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public ArticleReplyVo getArticleReplyVo() {
        return articleReplyVo;
    }

    public void setArticleReplyVo(ArticleReplyVo articleReplyVo) {
        this.articleReplyVo = articleReplyVo;
    }
	
}
