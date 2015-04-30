package com.just.lollipop.bbs.domain;

import java.util.Date;
import java.util.Set;

/**
 * 博客文章实体类
 */
public class Article {
	private Integer id; /* 文章标识  */

	private String title; /* 文章标题  */

	private String content; /* 文章内容  */

	private Integer isTop = 0; /* 是否置顶, 默认0代表不置顶 ，1代表置顶   */

	private Integer isThemeArticle; /* 是否主题帖子,1代表主题帖子， 2代表回复帖子  */

	private Integer totalReply = 0; /* 回复数  */

	private Date createTime = new Date(); /* 发表时间  */

	private Date lastUpdateTime; /* 最后更新时间  */

	private User createPerson; /* 发表人  */

	private User lastUpdatePerson; /* 最后更新人  */

	private Board discussionBoard; /* 板块  */

	private Article themeArticle; /* 主题帖子 */

	private Set<Article> childArticles;

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

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public User getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(User createPerson) {
		this.createPerson = createPerson;
	}

	public User getLastUpdatePerson() {
		return lastUpdatePerson;
	}

	public void setLastUpdatePerson(User lastUpdatePerson) {
		this.lastUpdatePerson = lastUpdatePerson;
	}

	public Board getDiscussionBoard() {
		return discussionBoard;
	}

	public void setDiscussionBoard(Board discussionBoard) {
		this.discussionBoard = discussionBoard;
	}

    public Article getThemeArticle() {
        return themeArticle;
    }

    public void setThemeArticle(Article themeArticle) {
        this.themeArticle = themeArticle;
    }

    public Integer getIsThemeArticle() {
        return isThemeArticle;
    }

    public void setIsThemeArticle(Integer isThemeArticle) {
        this.isThemeArticle = isThemeArticle;
    }

    public Set<Article> getChildArticles() {
        return childArticles;
    }

    public void setChildArticles(Set<Article> childArticles) {
        this.childArticles = childArticles;
    }

}	
