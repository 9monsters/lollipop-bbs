package com.just.lollipop.bbs.domain;

import java.util.Date;
import java.util.Set;

/**
 * 版块实体类
 */
public class Board {
    
	private Integer id; /** 版块标识  */
	
	private String name; /** 版块名称  */
	
	private Date createTime = new Date(); /** 创建时间  */
	
	private User createPerson; /** 创建人 */
	
	private User moderator; /** 版主 */
	
	private Column column; /** 该版块所属的栏目 */
	
	private Set<Article> articles; /** 该版块的文章  */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public User getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(User createPerson) {
		this.createPerson = createPerson;
	}

	public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

    public User getModerator() {
        return moderator;
    }

    public void setModerator(User moderator) {
        this.moderator = moderator;
    }

}	
