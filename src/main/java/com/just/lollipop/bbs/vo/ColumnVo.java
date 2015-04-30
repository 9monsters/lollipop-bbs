package com.just.lollipop.bbs.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class ColumnVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id; /** 栏目标识  */
	
	private String name; /** 栏目名称  */
	
	private String remark; /** 栏目描述  */
	
	private Date createTime; /** 创建时间  */
	
	private UserVo createPerson; /** 创建人 */
	
	private Set<BoardVo> boards; /** 该栏目下的所有版块  */
	
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

    public Set<BoardVo> getBoards() {
        return boards;
    }

    public void setBoards(Set<BoardVo> boards) {
        this.boards = boards;
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
