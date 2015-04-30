package com.just.lollipop.bbs.service;

import java.util.List;

import com.just.lollipop.bbs.common.PaginationSupport;
import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.vo.ArticleVo;

/**
 * 文章业务接口
 */
public interface ArticleService {

	/**
	 * 分页获取文章
	 * @param articleVo
	 * @param start
	 * @param pagesize
	 * @return
	 * @throws PtException
	 */
	public PaginationSupport<ArticleVo> findArticlesByPage(ArticleVo articleVo
            , int start, int pagesize) throws PtException;

	/**
	 * 添加文章
	 * @param articleVo
	 * @throws PtException
	 */
    public void addArticle(ArticleVo articleVo) throws PtException;

    /**
     * 根据ID获取ArticleVo对象
     * @param articleId
     * @return
     * @throws PtException
     */
    public ArticleVo getArticle(int articleId) throws PtException;

    /**
     * 修改文章
     * @param articleVo
     * @throws PtException
     */
    public void updateArticle(ArticleVo articleVo) throws PtException;

    /**
     * 删除文章
     * @param ids
     * @throws PtException
     */
    public void deleteArticle(String ids) throws PtException;

    /**
     * 分页获取回复
     * @param articleVo
     * @param start
     * @param pagesize
     * @return
     * @throws PtException
     */
    public PaginationSupport<ArticleVo> findReplysByPage(ArticleVo articleVo,
                                                         int start, int pagesize) throws PtException;

    /**
     * 添加回复
     * @param articleVo
     * @throws PtException
     */
    public void addReply(ArticleVo articleVo) throws PtException;

    /**
     * 获取回复
     * @param replyId
     * @return
     * @throws PtException
     */
    public ArticleVo getArticleReply(int replyId) throws PtException;

    /**
     * 修改回复
     * @param articleVo
     * @throws PtException
     */
    public void updateReply(ArticleVo articleVo) throws PtException;

    /**
     * 删除回复
     * @param ids
     * @throws PtException
     */
    public void deleteReply(String ids) throws PtException;

    /**
     * 获取文章
     * @return
     * @throws PtException
     */
    public List<ArticleVo> getArticles() throws PtException;

    /**
     * 搜索文章
     * @param keyword 搜索关键字
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    public PaginationSupport<ArticleVo> search(String keyword, int currentPage, int pageSize) throws Exception;

    /**
     * 根据版块ID获取文章
     * @param boardId
     * @param page 第几页
     * @param pageSize 每页显示结果数
     * @return
     * @throws PtException
     */
    public PaginationSupport<ArticleVo> getArticleList(int boardId, int currentPage, int pageSize) throws PtException;

    /**
     * 分页获取所有文章（包含主题帖子和回复帖子）
     * @param themeArtileId
     * @param currentPage
     * @param pagesize
     * @return
     * @throws PtException
     */
    public PaginationSupport<ArticleVo> getArticles(Integer themeArtileId, int currentPage, int pageSize) throws PtException;
    
    /**
     * 分页获取所有文章
     * @param themeArtileId
     * @return
     * @throws PtException
     */
    public int getTotalOfReplys(int themeArtileId) throws PtException;

    /**
     * 获取下一遍主题文章
     * @param themeArticleId 当前主题文章ID
     * @return
     * @throws PtException
     */
    public ArticleVo getNextArticle(int themeArticleId) throws PtException;

    /**
     * 获取上一遍主题文章
     * @param themeArticleId 当前主题文章ID
     * @return
     * @throws PtException
     */
    public ArticleVo getPrevArticle(int themeArticleId) throws PtException;

    /**
     * 删除帖子
     * @param articleVo
     * @throws PtException
     */
    public void delete(ArticleVo articleVo) throws PtException;

    /**
     * 获取我发表的文章
     * @param userId
     * @param currentPage
     * @param pageSize
     * @return
     * @throws PtException
     */
    public PaginationSupport<ArticleVo> getMyArticles(Integer userId,
                                                      int currentPage, int pageSize) throws PtException;

    /**
     * 获取用户发表的文章数量
     * @param userId
     * @return
     * @throws PtException
     */
    public int getTotalOfArticle(int userId) throws PtException;
    
}
