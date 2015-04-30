package com.just.lollipop.bbs.dao.impl;

import com.just.lollipop.bbs.dao.ArticleDao;
import com.just.lollipop.bbs.dao.base.impl.HibernateDaoImpl;
import com.just.lollipop.bbs.domain.Article;
import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.util.date.DateUtil;
import com.just.lollipop.bbs.vo.ArticleVo;
import com.just.lollipop.bbs.vo.UserVo;
import com.just.lollipop.bbs.web.base.Constants;

import java.util.ArrayList;
import java.util.List;


/**
 * 文章数据访问实现类
 */
public class ArticleDaoImpl extends HibernateDaoImpl<Article>
        implements ArticleDao {

    @Override
    public List<Article> findArticles(ArticleVo articleVo, int start,
                                      int pagesize) throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT a ");
        sb.append("FROM Article a ");
        sb.append("WHERE 1=1 ");
        List<Object> params = new ArrayList<Object>();
        if (articleVo.getTitle() != null && !"".equals(articleVo.getTitle())) {
            sb.append("AND a.title like ? ");
            params.add("%" + articleVo.getTitle() + "%");
        }
        if (articleVo.getCreateTime() != null) {
            sb.append("AND YEAR(a.createTime) = ? ");
            sb.append("AND MONTH(a.createTime) = ? ");
            sb.append("AND DAY(a.createTime) = ? ");
            params.add(DateUtil.getYear(articleVo.getCreateTime()));
            params.add(DateUtil.getMonth(articleVo.getCreateTime()));
            params.add(DateUtil.getDay(articleVo.getCreateTime()));
        }
        // 主题帖子
        sb.append("AND a.isThemeArticle = 1 ");
        // 如果当前用户角色为版主，只获取他所管理版块中的文章。
        UserVo u = articleVo.getDiscussionBoard().getModerator();
        if (u.getRole() == Constants.Role_MODERATOR) {
            sb.append("AND a.discussionBoard.moderator.id = ? ");
            params.add(u.getId());
        }
        sb.append("ORDER BY createTime DESC ");
        return this.findByPage(sb.toString(), start, pagesize, params.toArray());
    }

    @Override
    public int getTotalOfArticles(ArticleVo articleVo) throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(a.id) ");
        sb.append("FROM Article a ");
        sb.append("WHERE 1=1 ");
        List<Object> params = new ArrayList<Object>();
        if (articleVo.getTitle() != null && !"".equals(articleVo.getTitle())) {
            sb.append("AND a.title like ? ");
            params.add("%" + articleVo.getTitle() + "%");
        }
        if (articleVo.getCreateTime() != null) {
            sb.append("AND YEAR(a.createTime) = ? ");
            sb.append("AND MONTH(a.createTime) = ? ");
            sb.append("AND DAY(a.createTime) = ? ");
            params.add(DateUtil.getYear(articleVo.getCreateTime()));
            params.add(DateUtil.getMonth(articleVo.getCreateTime()));
            params.add(DateUtil.getDay(articleVo.getCreateTime()));
        }
        //如果当前用户角色为版主，只获取他所管理版块中的文章。
        UserVo u = articleVo.getDiscussionBoard().getModerator();
        if (u.getRole() == Constants.Role_MODERATOR) {
            sb.append("AND a.discussionBoard.moderator.id = ? ");
            params.add(u.getId());
        }
        return this.count(sb.toString(), params.toArray());
    }

    @Override
    public void addArticle(Article article) throws PtException {
        this.save(article);
    }

    @Override
    public Article getArticle(Integer articleId) throws PtException {
        return this.get(Article.class, articleId);
    }

    @Override
    public void updateArticle(Article article) throws PtException {
        this.saveOrUpdate(article);
    }

    @Override
    public void deleteArticle(int articleId) throws PtException {
        this.delete(getArticle(articleId));
    }

    @Override
    public List<Article> getAll() throws PtException {
        return this.find(Article.class);
    }

    @Override
    public List<Article> getArticles(int boardId, int start, int pageSize) throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("FROM Article ");
        sb.append("WHERE isThemeArticle = 1 AND discussionBoard.id = ? ");
        sb.append("ORDER BY isTop DESC, createTime DESC ");
        return this.findByPage(sb.toString(), start, pageSize, new Object[]{boardId});
    }

    @Override
    public List<Article> findArticleReplys(ArticleVo articleVo, int start,
                                           int pagesize) throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("FROM Article ar ");
        sb.append("WHERE ar.isThemeArticle = 2 AND ar.themeArticle.id = ? ");
        return this.findByPage(sb.toString(), start, pagesize
                , new Object[]{articleVo.getId()});
    }

    @Override
    public int getTotalOfArticleReplys(int themeArticleId) throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(ar.id) ");
        sb.append("FROM Article ar ");
        sb.append("WHERE ar.isThemeArticle = 2 AND ar.themeArticle.id = ? ");
        return this.count(sb.toString(), new Object[]{themeArticleId});
    }

    @Override
    public Article getArticleReply(int replyId) throws PtException {
        return this.get(Article.class, replyId);
    }

    @Override
    public List<Article> getReplyArticles(int themeArticleId) throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("FROM Article ar ");
        sb.append("WHERE ar.isThemeArticle = 2 AND ar.themeArticle.id = ? ");
        return this.find(sb.toString(), new Object[]{themeArticleId});
    }

    @Override
    public List<Article> findByPage(int themeArtileId, int start,
                                    int pageSize) throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("FROM Article ar ");
        sb.append("WHERE ar.id = ? OR ar.themeArticle.id = ? ");
        sb.append("ORDER BY ar.isThemeArticle ASC, ar.createTime DESC ");
        return this.findByPage(sb.toString(), start, pageSize
                , new Object[]{themeArtileId, themeArtileId});
    }

    @Override
    public int getTotalOfThemeArticles(int boardId) throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(id) ");
        sb.append("FROM Article ");
        sb.append("WHERE discussionBoard.id = ? ");
        return this.count(sb.toString(), new Object[]{boardId});
    }

    @Override
    public int getTotalOfArticles(int themeArtileId) throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(ar.id) ");
        sb.append("FROM Article ar ");
        sb.append("WHERE ar.id = ? OR ar.themeArticle.id = ? ");
        return this.count(sb.toString(), new Object[]{themeArtileId, themeArtileId});
    }

    @Override
    public Article getNextArticle(int boardId, int themeArticleId) throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("FROM Article ar ");
        sb.append("WHERE ar.isThemeArticle = 1 AND ar.discussionBoard.id = ? AND ar.id < ? ");
        sb.append("ORDER BY ar.id DESC ");
        List<Article> list = this.find(sb.toString(), new Object[]{boardId, themeArticleId});
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public Article getPrevArticle(int boardId, int themeArticleId) throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("FROM Article ar ");
        sb.append("WHERE ar.isThemeArticle = 1 AND ar.discussionBoard.id = ? AND ar.id > ? ");
        sb.append("ORDER BY ar.id ASC ");
        List<Article> list = this.find(sb.toString(), new Object[]{boardId, themeArticleId});
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<Article> findMyArticlesByPage(int userId, int start,
                                              int pageSize) throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("FROM Article ar ");
        sb.append("WHERE ar.isThemeArticle = 1 AND ar.createPerson.id = ? ");
        sb.append("ORDER BY ar.createTime DESC ");
        return this.findByPage(sb.toString(), start, pageSize
                , new Object[]{userId});
    }

    @Override
    public int getTotalOfMyArticles(int userId) throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(ar.id) ");
        sb.append("FROM Article ar ");
        sb.append("WHERE ar.isThemeArticle = 1 AND ar.createPerson.id = ? ");
        return this.count(sb.toString(), new Object[]{userId});
    }

}
