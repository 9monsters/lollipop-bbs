package com.just.lollipop.bbs.common;

import java.util.List;


/**
 * 分页查询结果基类
 */
public class PaginationSupport<T> {
    private List<T> items;
    private int startOfPage;    // 当前页第一条记录的开始位置
    private int totalCount; // 总记录条数
    private int pageSize;    // 每页显示数
    private int page; //当前第几页
    private int totalPage; //总页数

    public PaginationSupport(List<T> items, int startOfPage, int totalCount, int pageSize) {
        this.items = items;
        this.startOfPage = startOfPage;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.page = startOfPage / pageSize + 1;
        if (totalCount % pageSize == 0) {
            this.totalPage = totalCount / pageSize;
        } else {
            this.totalPage = totalCount / pageSize + 1;
        }
        if (page > totalPage) {
            this.totalCount = 0;
            this.page = 1;
            this.totalPage = 1;
        }
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getStartOfPage() {
        return startOfPage;
    }

    public void setStartOfPage(int startOfPage) {
        this.startOfPage = startOfPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}