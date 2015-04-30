package com.just.lollipop.bbs.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.just.lollipop.bbs.common.PaginationSupport;
import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.vo.BoardVo;
import org.json.JSONException;


/**
 * 版块业务接口
 */
public interface BoardService {

	/**
	 * 分页获取版块数据
	 * @param boardVo
	 * @param start
	 * @param pagesize
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws PtException
	 */
	PaginationSupport<BoardVo> findBoardsByPage(BoardVo boardVo, int start,
												int pagesize) throws PtException;

	/**
	 * 添加版块
	 * @param boardVo
	 * @throws PtException
	 */
	public void addBoard(BoardVo boardVo) throws PtException;

	/**
     * 获取下拉框论坛版块
     * @return
     * @throws PtException
     */
    public String getComboBoards() throws JSONException, PtException;

	/**
	 * 根据ID获取Board对象
	 * @param boardId 版块ID
	 * @return
	 * @throws PtException
	 */
	public BoardVo getBoard(int boardId) throws PtException;

	/**
	 * 修改版块
	 * @param boardVo
	 * @throws PtException
	 */
    public void updateBoard(BoardVo boardVo) throws PtException;

    /**
     * 删除版块
     * @param ids
     * @throws PtException
     */
    public void deleteBoard(String ids) throws PtException;

    /**
     * 根据栏目获取所有版块
     * @param columnId
     * @return
     * @throws PtException
     */
    public List<BoardVo> getBoards(Integer columnId) throws PtException;

}
