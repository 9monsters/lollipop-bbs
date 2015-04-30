package com.just.lollipop.bbs.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.json.JSONException;

import com.just.lollipop.bbs.common.PaginationSupport;
import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.vo.ColumnVo;


/**
 * 栏目业务接口
 */
public interface ColumnService {

	/**
	 * 分页获取栏目数据
	 * @param ColumnVo
	 * @param start
	 * @param pagesize
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws PtException
	 */
	PaginationSupport<ColumnVo> findColumnsByPage(ColumnVo ColumnVo, int start,
                                                  int pagesize) throws PtException;

	/**
	 * 添加栏目
	 * @param ColumnVo
	 * @throws PtException
	 */
	public void addColumn(ColumnVo ColumnVo) throws PtException;

	/**
	 * 修改栏目
	 * @param ColumnVo
	 * @throws PtException
	 */
    public void updateColumn(ColumnVo ColumnVo) throws PtException;

    /**
     * 删除栏目
     * @param ids
     * @throws PtException
     */
    public void deleteColumn(String ids) throws PtException;

    /**
     * 根据ID获取栏目
     * @param columnId
     * @return
     * @throws PtException
     */
    public ColumnVo getColumn(int columnId) throws PtException;

    /**
     * 获取下拉框栏目数据
     * @return
     * @throws PtException
     */
    public String listComboColumns() throws JSONException, PtException;

    /**
     * 网站首页获取所有栏目
     * @return
     * @throws PtException
     */
    public List<ColumnVo> getColumns() throws PtException;

}
