package com.just.lollipop.bbs.dao;

import com.just.lollipop.bbs.domain.Column;
import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.vo.ColumnVo;

import java.util.List;

/**
 * 栏目数据访问接口
 */
public interface ColumnDao {

    /**
     * 根据查询条件，分页获取栏目对象
     *
     * @param columnVo
     * @param start
     * @param pagesize
     * @return
     * @throws PtException
     */
    public List<Column> findColumns(ColumnVo columnVo, int start, int pagesize) throws PtException;

    /**
     * 根据查询条件获取查询的结果总数
     *
     * @param columnVo
     * @return
     * @throws PtException
     */
    public int getTotalOfColumns(ColumnVo columnVo) throws PtException;

    /**
     * 添加栏目
     *
     * @param column
     * @throws PtException
     */
    public void addColumn(Column column) throws PtException;

    /**
     * 更新栏目
     *
     * @param column
     * @throws PtException
     */
    public void updateColumn(Column column) throws PtException;

    /**
     * 删除栏目
     *
     * @param columnId
     * @throws PtException
     */
    public void deleteColumn(int columnId) throws PtException;

    /**
     * 根据ID获取栏目
     *
     * @param columnId
     * @return
     * @throws PtException
     */
    public Column getColumn(int columnId) throws PtException;

    /**
     * 获取栏目
     *
     * @return
     * @throws PtException
     */
    public List<Column> getColumns() throws PtException;

}
