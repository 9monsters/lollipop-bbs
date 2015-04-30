package com.just.lollipop.bbs.dao.impl;

import com.just.lollipop.bbs.dao.BoardDao;
import com.just.lollipop.bbs.dao.base.impl.HibernateDaoImpl;
import com.just.lollipop.bbs.domain.Board;
import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.vo.BoardVo;

import java.util.ArrayList;
import java.util.List;


/**
 * 版块数据访问实现类
 */
public class BoardDaoImpl extends HibernateDaoImpl<Board>
        implements BoardDao {

    @Override
    public List<Board> findBoards(BoardVo boardVo, int start, int pagesize)
            throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT b ");
        sb.append("FROM Board b ");
        sb.append("WHERE 1=1 ");
        List<Object> params = new ArrayList<Object>();
        if (boardVo != null) {
            if (boardVo.getName() != null && !"".equals(boardVo.getName())) {
                sb.append("AND b.name like ? ");
                params.add("%" + boardVo.getName() + "%");
            }
            if (boardVo.getColumn().getId() != null) {
                sb.append("AND b.column.id = ? ");
                params.add(boardVo.getColumn().getId());
            }
        }
        return this.findByPage(sb.toString(), start, pagesize, params.toArray());
    }

    @Override
    public int getTotalOfBoards(BoardVo boardVo) throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(b.id) ");
        sb.append("FROM Board b ");
        sb.append("WHERE 1=1 ");
        List<Object> params = new ArrayList<Object>();
        if (boardVo != null) {
            if (boardVo.getName() != null && !"".equals(boardVo.getName())) {
                sb.append("AND b.name like ? ");
                params.add("%" + boardVo.getName() + "%");
            }
            if (boardVo.getColumn().getId() != null) {
                sb.append("AND b.column.id = ? ");
                params.add(boardVo.getColumn().getId());
            }
        }
        return this.count(sb.toString(), params.toArray());
    }

    @Override
    public void addBoard(Board board) throws PtException {
        this.save(board);
    }

    @Override
    public List<Board> getBoards(int columnId) throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT b ");
        sb.append("FROM Board b ");
        sb.append("WHERE column.id = ? ");
        return this.find(sb.toString(), new Object[]{columnId});
    }

    @Override
    public List<Board> getParentBoards() throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT b ");
        sb.append("FROM Board b ");
        sb.append("WHERE parentBoard = null ");
        return this.find(sb.toString());
    }

    @Override
    public Board getBoard(int boardId) throws PtException {
        return this.get(Board.class, boardId);
    }

    @Override
    public void updateBoard(Board board) throws PtException {
        this.update(board);
    }

    @Override
    public void deleteBoard(int boardId) throws PtException {
        this.delete(getBoard(boardId));
    }

    @Override
    public List<Board> getBoardsByModerator(int moderatorId) throws PtException {
        StringBuilder sb = new StringBuilder();
        sb.append("FROM Board ");
        sb.append("WHERE moderator.id = ? ");
        return this.find(sb.toString(), new Object[]{moderatorId});
    }

}
