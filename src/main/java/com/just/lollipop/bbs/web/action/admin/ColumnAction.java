package com.just.lollipop.bbs.web.action.admin;

import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.service.ColumnService;
import com.just.lollipop.bbs.vo.ColumnVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.just.lollipop.bbs.web.base.CommonAction;


public class ColumnAction extends CommonAction {
	private static final long serialVersionUID = 1L;
	private final Log log = LogFactory.getLog(ColumnAction.class);
	
	private ColumnVo columnVo;
	private String ids; //栏目IDs
	
	@Autowired
	private ColumnService columnService;
	
	/**
	 * 显示论坛栏目列表
	 */
	public String listColumns() {
		try {
			setLigerGrid(columnService.findColumnsByPage(columnVo, getStart(), getPagesize()));
		} catch (PtException e) {
			setResult("服务器发生异常，请联系管理员.");
			log.error("论坛管理平台发生异常：", e);
		}
		return RESULT_JSON;
	}
	
	/**
	 * 添加栏目
	 */
	public String addColumn() {
		try {
		    columnService.addColumn(columnVo);
			setResult("ok");
		} catch (PtException e) {
			setResult("服务器发生异常，请联系管理员.");
			log.error("论坛管理平台发生异常：", e);
		}
		return RESULT_JSON;
	}
	
	/**
	 * 进入修改栏目页面
	 */
	public String forwardEditColumnPage(){
		try {
			columnVo = columnService.getColumn(columnVo.getId());
		} catch (PtException e) {
			log.error("论坛管理平台发生异常：", e);
		}
		setDefinedMethodTemp("editcolumn");
		return RESULT_PAGES;
	}
	
	/**
     * 修改栏目
     */
    public String updateColumn(){
        try {
            columnService.updateColumn(columnVo);
            setResult("ok");
        } catch (PtException e) {
            setResult("服务器发生异常，请联系管理员.");
            log.error("论坛管理平台发生异常：", e);
        }
        return RESULT_JSON;
    }
    
    /**
     * 删除栏目
     */
    public String deleteColumn(){
        try {
            columnService.deleteColumn(ids);
            setResult("ok");
        } catch (PtException e) {
            setResult("服务器发生异常，请联系管理员.");
            log.error("论坛管理平台发生异常：", e);
        }
        return RESULT_JSON;
    }
    
    /**
     * 获取下拉框栏目数据
     */
    public String listComboColumns(){
        JSONObject jsonObject = new JSONObject();
        try {
            String result = columnService.listComboColumns();
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

    public ColumnVo getColumnVo() {
        return columnVo;
    }

    public void setColumnVo(ColumnVo columnVo) {
        this.columnVo = columnVo;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

}
