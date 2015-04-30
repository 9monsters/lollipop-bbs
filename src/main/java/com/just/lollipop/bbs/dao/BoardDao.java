package com.just.lollipop.bbs.dao;

import com.just.lollipop.bbs.domain.Board;
import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.vo.BoardVo;

import java.util.List;

/**
 * 版块数据访问接口
 */
public interface BoardDao {

    /**
     * 根据查询条件，分页获取Board对象
     *
     * @param boardVo
     * @param start
     * @param pagesize
     * @return
     * @throws PtException
     */
    public List<Board> findBoards(BoardVo boardVo, int start, int pagesize) throws PtException;

    /**
     * 根据查询条件获取查询的结果总数
     *
     * @param boardVo
     * @return
     * @throws PtException
     */
    public int getTotalOfBoards(BoardVo boardVo) throws PtException;

    /**
     * 添加版块
     *
     * @param board
     * @throws PtException
     */
    public void addBoard(Board board) throws PtException;

    /**
     * 根据栏目获取版块
     *
     * @param columnId
     * @return
     * @throws PtException
     */
    public List<Board> getBoards(int columnId) throws PtException;

    /**
     * 获取父版块
     *
     * @return
     * @throws PtException
     */
    public List<Board> getParentBoards() throws PtException;

    /**
     * 根据ID获取Board对象
     *
     * @param boardId
     * @return
     * @throws PtException
     */
    public Board getBoard(int boardId) throws PtException;

    /**
     * 更新版块
     *
     * @param board
     * @throws PtException
     */
    public void updateBoard(Board board) throws PtException;

    /**
     * 删除版块
     *
     * @param boardId
     * @throws PtException
     */
    public void deleteBoard(int boardId) throws PtException;

    /**
     * 获取指定版主所管理的版块
     *
     * @param moderatorId
     * @return
     * @throws PtException
     */
    public List<Board> getBoardsByModerator(int moderatorId) throws PtException;

}
