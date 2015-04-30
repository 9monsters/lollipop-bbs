package com.just.lollipop.bbs.web.action.admin;

import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.service.BoardService;
import com.just.lollipop.bbs.vo.BoardVo;
import com.just.lollipop.bbs.web.base.CommonAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;


public class BoardAction extends CommonAction {
	private static final long serialVersionUID = 1L;
	private final Log log = LogFactory.getLog(BoardAction.class);
	
	private BoardVo boardVo;
	private String ids; //版块IDs
	
	@Autowired
	private BoardService boardService;
	
	/**
	 * 显示论坛版块列表
	 */
	public String listBoards() {
		try {
			setLigerGrid(boardService.findBoardsByPage(
			        boardVo, getStart(), getPagesize()));
		} catch (PtException e) {
			setResult("服务器发生异常，请联系管理员.");
			log.error("论坛管理平台发生异常：", e);
		}
		return RESULT_JSON;
	}
	
	/**
	 * 添加版块
	 */
	public String addBoard() {
		try {
			boardService.addBoard(boardVo);
			setResult("ok");
		} catch (PtException e) {
			setResult("服务器发生异常，请联系管理员.");
			log.error("论坛管理平台发生异常：", e);
		}
		return RESULT_JSON;
	}
	
	/**
     * 获取下拉框论坛版块数据
     */
    public String listComboBoards(){
        JSONObject jsonObject = new JSONObject();
        try {
            String result = boardService.getComboBoards();
            jsonObject.put("status", "true");
            jsonObject.put("result", result);
            setResult("{status:'true',result:'" + result + "'}");
        } catch (JSONException e) {
            setResult("{status:'false',result:'服务器发生异常，请联系管理员.'}");
            log.error("论坛管理平台发生异常：", e);
        } catch (PtException e) {
            setResult("{status:'false',result:'服务器发生异常，请联系管理员.'}");
            log.error("论坛管理平台发生异常：", e);
        }
        return RESULT_JSON;
    }
	
	/**
	 * 进入修改版块页面
	 */
	public String forwardEditBoardPage(){
		try {
			boardVo = boardService.getBoard(boardVo.getId());
		} catch (PtException e) {
			log.error("论坛管理平台发生异常：", e);
		}
		setDefinedMethodTemp("editboard");
		return RESULT_PAGES;
	}
	
	/**
     * 修改版块
     */
    public String updateBoard(){
        try {
            boardService.updateBoard(boardVo);
            setResult("ok");
        } catch (PtException e) {
            setResult("服务器发生异常，请联系管理员.");
            log.error("论坛管理平台发生异常：", e);
        }
        return RESULT_JSON;
    }
    
    /**
     * 删除版块
     */
    public String deleteBoard(){
        try {
            boardService.deleteBoard(ids);
            setResult("ok");
        } catch (PtException e) {
            setResult("服务器发生异常，请联系管理员.");
            log.error("论坛管理平台发生异常：", e);
        }
        return RESULT_JSON;
    }

	public BoardVo getBoardVo() {
		return boardVo;
	}

	public void setBoardVo(BoardVo boardVo) {
		this.boardVo = boardVo;
	}

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
	
}
