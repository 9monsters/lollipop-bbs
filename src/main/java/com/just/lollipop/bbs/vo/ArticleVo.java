package com.just.lollipop.bbs.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class ArticleVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; /** 文章标识  */
	
	private String title; /** 文章标题  */
	
	private String content; /** 文章内容  */
	
	private Integer isThemeArticle; /** 是否主题帖子,1代表主题帖子， 2代表回复帖子  */
	
	private Integer isTop = 0; /** 是否置顶， 0代表不置顶 1代表置顶 */
	
	private Integer totalReply; /** 回复数  */
	
	private Date createTime; /** 发表时间  */
	
	private UserVo createPerson; /** 发表人  */
	
	private UserVo lastUpdatePerson; /** 最后更新人  */
	
	private Date lastUpdateTime; /** 最后更新时间  */
	
	private BoardVo discussionBoard; /** 版块  */
	
	private ArticleVo themeArticle; /** 主题帖子 */
	
	private Set<ArticleReplyVo> articleReplys;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public Integer getTotalReply() {
		return totalReply;
	}

	public void setTotalReply(Integer totalReply) {
		this.totalReply = totalReply;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public UserVo getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(UserVo createPerson) {
		this.createPerson = createPerson;
	}

	public UserVo getLastUpdatePerson() {
		return lastUpdatePerson;
	}

	public void setLastUpdatePerson(UserVo lastUpdatePerson) {
		this.lastUpdatePerson = lastUpdatePerson;
	}

	public BoardVo getDiscussionBoard() {
		return discussionBoard;
	}

	public void setDiscussionBoard(BoardVo discussionBoard) {
		this.discussionBoard = discussionBoard;
	}

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Set<ArticleReplyVo> getArticleReplys() {
        return articleReplys;
    }

    public void setArticleReplys(Set<ArticleReplyVo> articleReplys) {
        this.articleReplys = articleReplys;
    }

    public ArticleVo getThemeArticle() {
        return themeArticle;
    }

    public void setThemeArticle(ArticleVo themeArticle) {
        this.themeArticle = themeArticle;
    }

    public Integer getIsThemeArticle() {
        return isThemeArticle;
    }

    public void setIsThemeArticle(Integer isThemeArticle) {
        this.isThemeArticle = isThemeArticle;
    }
	
}	
