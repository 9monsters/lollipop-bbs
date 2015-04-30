package com.just.lollipop.bbs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.just.lollipop.bbs.common.PaginationSupport;
import com.just.lollipop.bbs.dao.ArticleDao;
import com.just.lollipop.bbs.dao.BoardDao;
import com.just.lollipop.bbs.dao.ColumnDao;
import com.just.lollipop.bbs.dao.UserDao;
import com.just.lollipop.bbs.domain.Article;
import com.just.lollipop.bbs.domain.Board;
import com.just.lollipop.bbs.domain.Column;
import com.just.lollipop.bbs.domain.User;
import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.service.BoardService;
import com.just.lollipop.bbs.util.date.DateUtil;
import com.just.lollipop.bbs.vo.BoardVo;
import com.just.lollipop.bbs.vo.ColumnVo;
import com.just.lollipop.bbs.vo.UserVo;
import com.just.lollipop.bbs.web.base.Constants;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 版块业务实现类
 */
@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
    private ColumnDao columnDao;
	
	@Autowired
    private ArticleDao articleDao;

	@Override
	public PaginationSupport<BoardVo> findBoardsByPage(BoardVo boardVo,
			int start, int pagesize) throws PtException {
		List<Board> boards = boardDao.findBoards(boardVo, start, pagesize);
		List<BoardVo> voList = new ArrayList<BoardVo>();
		ColumnVo columnVo = null;
		UserVo userVo = null;
		for (Board bo : boards){
			BoardVo vo = new BoardVo();
			vo.setId(bo.getId());
			vo.setName(bo.getName());
			
			userVo = new UserVo();
			userVo.setId(bo.getModerator().getId());
			userVo.setUserName(bo.getModerator().getUserName());
			vo.setModerator(userVo);
			
			columnVo = new ColumnVo();
			columnVo.setId(bo.getColumn().getId());
			columnVo.setName(bo.getColumn().getName());
			vo.setColumn(columnVo);
			
			voList.add(vo);
		}
		int total = boardDao.getTotalOfBoards(boardVo);
		PaginationSupport<BoardVo> pager = new PaginationSupport<BoardVo>(
				voList, start, total, pagesize);
		return pager;
	}

	@Override
	public void addBoard(BoardVo boardVo) throws PtException {
		Board board = new Board();
		board.setName(boardVo.getName());
		
		User moderator = userDao.getUser(boardVo.getModerator().getId());
		if (moderator.getRole() == Constants.ROLE_MEMBER){
		    moderator.setRole(Constants.Role_MODERATOR);
	        userDao.updateUser(moderator);
		}
		
		board.setModerator(moderator);
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		UserVo userVo = (UserVo) session.getAttribute(Constants.USER);
		User createPerson = userDao.getUser(userVo.getId());
		board.setCreatePerson(createPerson);
		
	    Column column = columnDao.getColumn(boardVo.getColumn().getId());
	    board.setColumn(column);
	    
		boardDao.addBoard(board);
	}
	
	@Override
    public String getComboBoards() throws JSONException, PtException {
	    HttpSession session = ServletActionContext.getRequest().getSession();
        UserVo userVo = (UserVo) session.getAttribute(Constants.USER);
        List<Column> columns = columnDao.getColumns();
        JSONArray result = new JSONArray();
        List<Board> boards = null;
        JSONObject json = null;
        if (userVo.getRole() == Constants.ROLE_ADMIN){
            for (Column col : columns){
                json = new JSONObject();
                json.put("id", "0");
                json.put("name", col.getName());
                result.put(json);
                //根据栏目获取所有版块
                boards = boardDao.getBoards(col.getId());
                for (Board bo : boards){
                    json = new JSONObject();
                    json.put("id", bo.getId());
                    json.put("name", "&nbsp;&nbsp;|-" + bo.getName());
                    result.put(json);
                }
            }
        }else if (userVo.getRole() == Constants.Role_MODERATOR){
            boards = boardDao.getBoardsByModerator(userVo.getId());
            if (!boards.isEmpty()){
                json = new JSONObject();
                json.put("id", "0");
                json.put("name", boards.get(0).getColumn().getName());
                result.put(json);
                for (Board bo : boards){
                    json = new JSONObject();
                    json.put("id", bo.getId());
                    json.put("name", "&nbsp;&nbsp;|-" + bo.getName());
                    result.put(json);
                }
            }
        }
        return result.toString();
    }

	@Override
	public BoardVo getBoard(int boardId) throws PtException {
		Board bo = boardDao.getBoard(boardId);
		BoardVo vo = new BoardVo();
		vo.setId(bo.getId());
		vo.setName(bo.getName());
		
		UserVo userVo = new UserVo();
		userVo.setId(bo.getModerator().getId());
		userVo.setUserName(bo.getModerator().getUserName());
		vo.setModerator(userVo);
		
		ColumnVo column = new ColumnVo();
		column.setId(bo.getColumn().getId());
		column.setName(bo.getColumn().getName());
		vo.setColumn(column);

		return vo;
	}

    @Override
    public void updateBoard(BoardVo boardVo) throws PtException {
        Board board = boardDao.getBoard(boardVo.getId());
        board.setName(boardVo.getName());
        
        User oldModerator = board.getModerator();
        User newModerator = userDao.getUser(boardVo.getModerator().getId());
        if (oldModerator.getId() != newModerator.getId()){
            if (newModerator.getRole() != Constants.Role_MODERATOR){
                newModerator.setRole(Constants.Role_MODERATOR);
                userDao.updateUser(newModerator);
            }
            board.setModerator(newModerator);
            
            //判断旧版主是否还是他们版块的版主，如果不是，修改他的角色为普通会员。
            List<Board> boardList = boardDao.getBoardsByModerator(oldModerator.getId());
            if (boardList == null || boardList.size() == 0){
                oldModerator.setRole(Constants.ROLE_MEMBER);
                userDao.updateUser(newModerator);
            }
        }
        
        HttpSession session = ServletActionContext.getRequest().getSession();
        UserVo userVo = (UserVo) session.getAttribute(Constants.USER);
        User createPerson = userDao.getUser(userVo.getId());
        board.setCreatePerson(createPerson);
        
        Column column = columnDao.getColumn(boardVo.getColumn().getId());
        board.setColumn(column);
        boardDao.updateBoard(board);
    }

    @Override
    public void deleteBoard(String ids) throws PtException {
        String[] idArray = ids.split(",");
        for (String id : idArray){
            if (id != null && !"".equals(id)){
                boardDao.deleteBoard(Integer.parseInt(id));
            }
        }
    }

    @Override
    public List<BoardVo> getBoards(Integer columnId) throws PtException {
        List<Board> boards = boardDao.getBoards(columnId);
        List<BoardVo> boardVos = new ArrayList<BoardVo>();
        BoardVo boardVo = null;
        UserVo user = null;
        for (Board bo : boards){
            boardVo = new BoardVo();
            boardVo.setId(bo.getId());
            boardVo.setName(bo.getName());
            
            user = new UserVo();
            user.setId(bo.getModerator().getId());
            user.setUserName(bo.getModerator().getUserName());
            boardVo.setModerator(user);
            
            int todayOfTiezi = 0; //今天帖子数=今天主题数+今天回复数
            int totalOfTheme = 0; //总主题数
            int totalOfTiezi = 0; //总帖子数=总的主题数+总的回复数
            for (Article article : bo.getArticles()){
                if (article.getIsThemeArticle() == 1){
                    totalOfTheme++;
                    totalOfTiezi++;
                    if (DateUtil.isToday(article.getCreateTime())){
                        todayOfTiezi += 1;
                    }
                    //获取回复帖子
                    List<Article> replyArticles = articleDao.getReplyArticles(article.getId());
                    totalOfTiezi += replyArticles.size();
                    for (Article replyArticle : replyArticles){
                        if (DateUtil.isToday(replyArticle.getCreateTime())){
                            todayOfTiezi += 1;
                        }
                    }
                }
            }
            
            boardVo.setTodayOfTiezi(todayOfTiezi);
            boardVo.setTotalOfTheme(totalOfTheme);
            boardVo.setTotalOfTiezi(totalOfTiezi);
            boardVos.add(boardVo);
        }
        
        return boardVos;
    }
    
}
