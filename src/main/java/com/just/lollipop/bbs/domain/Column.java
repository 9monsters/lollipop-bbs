package com.just.lollipop.bbs.domain;

import java.util.Date;
import java.util.Set;

/**
 * 栏目实体类
 */
public class Column {
    
	private Integer id; /** 栏目标识  */
	
	private String name; /** 栏目名称  */
	
	private String remark; /** 栏目描述  */
	
	private Date createTime = new Date(); /** 创建时间  */
	
	private User createPerson; /** 创建人 */
	
	private Set<Board> boards; /** 该栏目下的所有版块  */

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

    public User getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(User createPerson) {
        this.createPerson = createPerson;
    }

    public Set<Board> getBoards() {
        return boards;
    }

    public void setBoards(Set<Board> boards) {
        this.boards = boards;
    }

}	
