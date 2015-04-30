package com.just.lollipop.bbs.vo;

import java.io.Serializable;
import java.util.Date;

public class ArticleReplyVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; /** 回复标识  */
	
	private String content; /** 回复内容  */
	
	private Date replyTime; /** 回复时间  */
	
	private Date lastUpdateTime; /** 最后更新时间  */
	
	private UserVo replyPerson; /** 回复人  */
	
	private UserVo lastUpdatePerson; /** 最后更新人  */
	
	private ArticleVo article; /** 文章  */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public UserVo getReplyPerson() {
		return replyPerson;
	}

	public void setReplyPerson(UserVo replyPerson) {
		this.replyPerson = replyPerson;
	}

	public UserVo getLastUpdatePerson() {
		return lastUpdatePerson;
	}

	public void setLastUpdatePerson(UserVo lastUpdatePerson) {
		this.lastUpdatePerson = lastUpdatePerson;
	}

	public ArticleVo getArticle() {
		return article;
	}

	public void setArticle(ArticleVo article) {
		this.article = article;
	}

}	
