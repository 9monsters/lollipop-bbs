package com.just.lollipop.bbs.dao;

import com.just.lollipop.bbs.domain.Article;
import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.vo.ArticleVo;

import java.util.List;

/**
 * 文章数据访问接口
 */
public interface ArticleDao {

    /**
     * 分页获取文章
     * @param articleVo
     * @param start
     * @param pagesize
     * @return
     * @throws PtException
     */
    public List<Article> findArticles(ArticleVo articleVo, int start, int pagesize) throws PtException;

    /**
     * 获取查询结果总数
     * @param articleVo
     * @return
     * @throws PtException
     */
    public int getTotalOfArticles(ArticleVo articleVo) throws PtException;

    /**
     * 添加文章
     * @param article
     * @throws PtException
     */
    public void addArticle(Article article) throws PtException;

    /**
     * 根据ID获取Article对象
     * @param articleId
     * @return
     * @throws PtException
     */
    public Article getArticle(Integer articleId) throws PtException;

    /**
     * 修改文章
     * @param article
     * @throws PtException
     */
    public void updateArticle(Article article) throws PtException;

    /**
     * 删除文章
     * @param articleId
     * @throws PtException
     */
    public void deleteArticle(int articleId) throws PtException;

    /**
     * 获取所有文章
     * @return
     * @throws PtException
     */
    public List<Article> getAll() throws PtException;

    /**
     * 根据版块获取文章
     * @param boardId 版块ID
     * @param start 从第几行开始查询
     * @param pageSize 每页显示结果数
     * @return
     * @throws PtException
     */
    public List<Article> getArticles(int boardId, int start, int pageSize) throws PtException;
    
    /**
     * 分页获取回复
     * @param articleVo
     * @param start
     * @param pageSize
     * @return
     * @throws PtException
     */
    public List<Article> findArticleReplys(ArticleVo articleVo, int start,
                                           int pageSize) throws PtException;
    
    /**
     * 获取回复结果总数
     * @param themeArticleId
     * @return
     * @throws PtException
     */
    public int getTotalOfArticleReplys(int themeArticleId) throws PtException;
    
    /**
     * 获取回复内容
     * @param replyId
     * @return
     * @throws PtException
     */
    public Article getArticleReply(int replyId) throws PtException;

    /**
     * 获取回复帖子
     * @param themeArticleId 主题帖子ID
     * @return
     * @throws PtException
     */
    public List<Article> getReplyArticles(int themeArticleId) throws PtException;

    /**
     * 分页获取所有帖子
     * @param themeArtileId 主题帖子ID
     * @param start
     * @param pageSize
     * @return
     * @throws PtException
     */
    public List<Article> findByPage(int themeArtileId, int start,
                                    int pageSize) throws PtException;

    /**
     * 获取版块主题文章总数
     * @param boardId
     * @return
     * @throws PtException
     */
    public int getTotalOfThemeArticles(int boardId) throws PtException;

    /**
     * 获取帖子总数，总帖子数=主题帖子+回复帖子
     * @param themeArtileId
     * @return
     * @throws PtException
     */
    public int getTotalOfArticles(int themeArtileId) throws PtException;

    /**
     * 获取下一遍主题文章
     * @param boardId 版块ID
     * @param themeArticleId 主题文章ID
     * @return
     * @throws PtException
     */
    public Article getNextArticle(int boardId, int themeArticleId) throws PtException;

    /**
     * 获取上一遍主题文章
     * @param boardId 版块ID
     * @param themeArticleId 主题文章ID
     * @return
     * @throws PtException
     */
    public Article getPrevArticle(int boardId, int themeArticleId) throws PtException;

    /**
     * 分页获取我的所有文章
     * @param userId
     * @param start
     * @param pageSize
     * @return
     * @throws PtException
     */
    public List<Article> findMyArticlesByPage(int userId, int start,
                                              int pageSize) throws PtException;

    /**
     * 获取我的文章的总数
     * @param userId
     * @return
     * @throws PtException
     */
    public int getTotalOfMyArticles(int userId) throws PtException;

}
