package com.just.lollipop.bbs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.just.lollipop.bbs.common.PaginationSupport;
import com.just.lollipop.bbs.dao.ArticleDao;
import com.just.lollipop.bbs.dao.ColumnDao;
import com.just.lollipop.bbs.dao.UserDao;
import com.just.lollipop.bbs.domain.Article;
import com.just.lollipop.bbs.domain.Board;
import com.just.lollipop.bbs.domain.Column;
import com.just.lollipop.bbs.domain.User;
import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.service.ColumnService;
import com.just.lollipop.bbs.util.date.DateUtil;
import com.just.lollipop.bbs.vo.ColumnVo;
import com.just.lollipop.bbs.vo.UserVo;
import com.just.lollipop.bbs.web.base.Constants;

/**
 * 栏目业务实现类
 */
@Service("columnService")
public class ColumnServiceImpl implements ColumnService {
	
	@Autowired
	private ColumnDao columnDao;
	
	@Autowired
    private UserDao userDao;
	
	@Autowired
    private ArticleDao articleDao;

    @Override
    public PaginationSupport<ColumnVo> findColumnsByPage(ColumnVo ColumnVo,
            int start, int pagesize) throws PtException {
        List<Column> columns = columnDao.findColumns(ColumnVo, start, pagesize);
        List<ColumnVo> voList = new ArrayList<ColumnVo>();
        for (Column col : columns){
            ColumnVo vo = new ColumnVo();
            vo.setId(col.getId());
            vo.setName(col.getName());
            vo.setRemark(col.getRemark());
            voList.add(vo);
        }
        int total = columnDao.getTotalOfColumns(ColumnVo);
        PaginationSupport<ColumnVo> pager = new PaginationSupport<ColumnVo>(
                voList, start, total, pagesize);
        return pager;
    }

    @Override
    public void addColumn(ColumnVo columnVo) throws PtException {
        Column column = new Column();
        column.setName(columnVo.getName());
        column.setRemark(columnVo.getRemark());
        
        HttpSession session = ServletActionContext.getRequest().getSession();
        UserVo userVo = (UserVo) session.getAttribute(Constants.USER);
        User createPerson = userDao.getUser(userVo.getId());
        column.setCreatePerson(createPerson);
        
        columnDao.addColumn(column);
    }

    @Override
    public void updateColumn(ColumnVo ColumnVo) throws PtException {
        Column column = columnDao.getColumn(ColumnVo.getId());
        column.setName(ColumnVo.getName());
        column.setRemark(ColumnVo.getRemark());
        columnDao.updateColumn(column);
    }

    @Override
    public void deleteColumn(String ids) throws PtException {
        String[] idArray = ids.split(",");
        Board board = null;
        List<Board> boards = null;
        for (String id : idArray){
            if (id != null && !"".equals(id)){
                columnDao.deleteColumn(Integer.parseInt(id));
            }
        }
    }

    @Override
    public ColumnVo getColumn(int columnId) throws PtException {
        Column column = columnDao.getColumn(columnId);
        ColumnVo vo = new ColumnVo();
        vo.setId(column.getId());
        vo.setName(column.getName());
        vo.setRemark(column.getRemark());
        return vo;
    }

    @Override
    public String listComboColumns() throws JSONException, PtException {
        List<Column> cols = columnDao.getColumns();
        JSONArray result = new JSONArray();
        for (Column col : cols){
            JSONObject json = new JSONObject();
            json.put("id", col.getId());
            json.put("name", col.getName());
            result.put(json);
        }
        return result.toString();
    }

    @Override
    public List<ColumnVo> getColumns() throws PtException {
        List<Column> columns = columnDao.getColumns();
        List<ColumnVo> columnVos = new ArrayList<ColumnVo>();
        ColumnVo columnVo = null;
        for (Column col : columns){
            columnVo = new ColumnVo();
            columnVo.setId(col.getId());
            columnVo.setName(col.getName());
            columnVo.setRemark(col.getRemark());
            
            int todayOfTiezi = 0; //今天帖子数=今天主题数+今天回复数
            int totalOfTheme = 0; //总主题数
            int totalOfTiezi = 0; //总帖子数=总的主题数+总的回复数
            for (Board bo : col.getBoards()){
                Set<Article> articles = bo.getArticles();
                for (Article article : articles){
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
            }
            
            columnVo.setTodayOfTiezi(todayOfTiezi);
            columnVo.setTotalOfTheme(totalOfTheme);
            columnVo.setTotalOfTiezi(totalOfTiezi);
            columnVos.add(columnVo);
        }
        return columnVos;
    }
    
}
