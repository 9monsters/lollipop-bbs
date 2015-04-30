package com.just.lollipop.bbs.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class BoardVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; /** 版块标识  */
	
	private String name; /** 版块名称  */
	
	private String remark; /** 版块描述  */
	
	private Date createTime; /** 创建时间  */
	
	private UserVo createPerson; /** 创建人 */
	
	private UserVo moderator; /** 版主 */
	
	private ColumnVo column; /** 该版块所属的栏目 */
	
	private Set<ArticleVo> articles; /** 该版块的所有文章  */
	
	private int todayOfTiezi; /** 今天帖子数 */
    
    private int totalOfTheme; /** 总主题 */
    
    private int totalOfTiezi; /** 总帖子数 */

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public UserVo getModerator() {
        return moderator;
    }

    public void setModerator(UserVo moderator) {
        this.moderator = moderator;
    }

    public ColumnVo getColumn() {
        return column;
    }

    public void setColumn(ColumnVo column) {
        this.column = column;
    }

    public Set<ArticleVo> getArticles() {
        return articles;
    }

    public void setArticles(Set<ArticleVo> articles) {
        this.articles = articles;
    }

    public int getTodayOfTiezi() {
        return todayOfTiezi;
    }

    public void setTodayOfTiezi(int todayOfTiezi) {
        this.todayOfTiezi = todayOfTiezi;
    }

    public int getTotalOfTheme() {
        return totalOfTheme;
    }

    public void setTotalOfTheme(int totalOfTheme) {
        this.totalOfTheme = totalOfTheme;
    }

    public int getTotalOfTiezi() {
        return totalOfTiezi;
    }

    public void setTotalOfTiezi(int totalOfTiezi) {
        this.totalOfTiezi = totalOfTiezi;
    }
	
}	
