package com.just.lollipop.bbs.web.action;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletContext;

import com.just.lollipop.bbs.service.ArticleService;
import com.just.lollipop.bbs.vo.ArticleVo;
import com.just.lollipop.bbs.web.base.AbstractAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.just.lollipop.bbs.common.PaginationSupport;
import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.service.BoardService;
import com.just.lollipop.bbs.service.ColumnService;
import com.just.lollipop.bbs.service.UserService;
import com.just.lollipop.bbs.vo.ArticleReplyVo;
import com.just.lollipop.bbs.vo.BoardVo;
import com.just.lollipop.bbs.vo.ColumnVo;
import com.just.lollipop.bbs.vo.UserVo;
import com.just.lollipop.bbs.web.base.Constants;


public class IndexAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private final Log log = LogFactory.getLog(IndexAction.class);
    private List<ColumnVo> columns;
    private List<BoardVo> boards;
    private UserVo userVo;
    private ColumnVo columnVo;
    private BoardVo boardVo;
    private ArticleVo articleVo;
    private ArticleReplyVo articleReplyVo;
    private String keyword; //搜索关键字
    private PaginationSupport<ArticleVo> result;
    private double totalTime = 0.0; //搜索花费的时间
    private String oper; //切换主题
    private String returnUrl; //登陆后返回的页面地址
    private int totalOfArticle; //文章数
    
    @Autowired
    private UserService userService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private ArticleService articleService;
    
    /**
     * 网站首页 
     */
    public String index(){
        try {
            columns = columnService.getColumns();
        } catch (Exception e) {
            setTip("服务器发生异常，请联系管理员.");
            log.error("程序发生异常：", e);
        }
        setDefinedMethodTemp("index");
        return RESULT_PAGES;
    }
    
    /**
     * 网站登录
     */
    @SuppressWarnings("unchecked")
    public String login(){
        try {
            String username = userVo.getUserName();
            String userpass = userVo.getUserPass();
            UserVo user = userService.getUser(username, userpass);
            if (user != null){
                if (user.getStatus() == 1){
                    ServletContext application = getSession().getServletContext();
                    List<UserVo> onlineUserList = (List<UserVo>) application.getAttribute("onlineUserList");
                    if (!onlineUserList.contains(user)){
                        onlineUserList.add(user);
                        application.setAttribute("onlineUserList", onlineUserList);
                    }
                    getSession().setAttribute(Constants.USER, user);
                    setTip("ok");
                }else{
                    setTip("该用户已经不存在.");
                }
            }else{
                setTip("用户名或密码不正确.");
            }
        } catch (Exception e) {
            setTip("服务器发生异常，请联系管理员.");
            log.error("论坛管理平台发生异常：", e);
        }
        if (!"ok".equals(getTip())){
            setDefinedMethodTemp("login");
            return RESULT_PAGES;
        }else{
            if (returnUrl != null && !"".equals(returnUrl)){
                setDefinedMethodTemp(returnUrl);
            }else{
                setDefinedMethodTemp("IndexAction!index.action");
            }
            return RESULT_REDIRECT_ACTION;
        }
    }
    
    /**
     * 退出网站
     */
    public String logout(){
        getSession().invalidate();
        setDefinedMethodTemp("IndexAction!index.action");
        return RESULT_REDIRECT_ACTION;
    }
    
    /**
     * 用户注册
     */
    public String regist(){
        try {
            setTip(userService.addUser(userVo));
        } catch (PtException e) {
            setTip("服务器发生异常，请联系管理员.");
            log.error("论坛管理平台发生异常：", e);
        }
        setDefinedMethodTemp("regist");
        return RESULT_PAGES;
    }
    
    /**
     * 进入版块页面
     */
    public String forwardBoardPage(){
        try {
            boards = boardService.getBoards(columnVo.getId());
        } catch (PtException e) {
            setTip("服务器发生异常，请联系管理员.");
            log.error("程序发生异常：", e);
        }
        setDefinedMethodTemp("boards");
        return RESULT_PAGES;
    }
    
    /**
     * 进入文章详细列表页面
     */
    public String forwardDetailPage(){
        try {
            boardVo = boardService.getBoard(boardVo.getId());
            result = articleService.getArticleList(
                    boardVo.getId(), currentPage, Constants.PAGESIZE);
        } catch (PtException e) {
            setTip("服务器发生异常，请联系管理员.");
            log.error("程序发生异常：", e);
        }
        setDefinedMethodTemp("details");
        return RESULT_PAGES;
    }
    
    /**
     * 搜索文章
     */
    public String search(){
        long start = System.currentTimeMillis();
        try {
            result = articleService.search(keyword, currentPage, pageSize);
        } catch (Exception e) {
            log.error("执行搜索操作时候发生异常：", e);
        }
        long end = System.currentTimeMillis();
        double total = (end - start) / 1000f;
        DecimalFormat dec = new DecimalFormat("###.000");
        setTotalTime(Double.parseDouble(dec.format(total)));
        setDefinedMethodTemp("searchresult");
        return RESULT_PAGES;
    }
    
    /**
     * 进入文章页面
     */
    public String forwardArticlePage(){
        try {
            if ("next".equals(oper)){
                int articleId = articleVo.getId();
                articleVo = articleService.getNextArticle(articleId);
                if (articleVo == null){
                    articleVo = articleService.getArticle(articleId);
                    result = articleService.getArticles(articleId
                            , currentPage, Constants.PAGESIZE);
                }else{
                    result = articleService.getArticles(articleVo.getId()
                            , currentPage, Constants.PAGESIZE);
                }
            }else if ("prev".equals(oper)){
                int articleId = articleVo.getId();
                articleVo = articleService.getPrevArticle(articleId);
                if (articleVo == null){
                    articleVo = articleService.getArticle(articleId);
                    result = articleService.getArticles(articleId
                            , currentPage, Constants.PAGESIZE);
                }else{
                    result = articleService.getArticles(articleVo.getId()
                            , currentPage, Constants.PAGESIZE);
                }
            }else{
                articleVo = articleService.getArticle(articleVo.getId());
                result = articleService.getArticles(articleVo.getId()
                        , currentPage, Constants.PAGESIZE);
            }
        } catch (PtException e) {
            setTip("服务器发生异常，请联系管理员.");
            log.error("程序发生异常：", e);
        }
        setDefinedMethodTemp("article");
        return RESULT_PAGES;
    }
    
    /**
     * 我的信息
     */
    public String forwardMyInfoPage(){
        UserVo user = (UserVo) getSession().getAttribute(Constants.USER);
        try {
            userVo = userService.getUser(user.getId());
        } catch (PtException e) {
            setTip("服务器发生异常，请联系管理员.");
            log.error("程序发生异常：", e);
        }
        setDefinedMethodTemp("myinfo");
        return RESULT_PAGES;
    }
    
    /**
     * 我的信息
     */
    public String forwardMyBlogPage(){
        UserVo user = (UserVo) getSession().getAttribute(Constants.USER);
        try {
            userVo = userService.getUser(user.getId());
            setResult(articleService.getMyArticles(userVo.getId(), currentPage, pageSize));
        } catch (PtException e) {
            setTip("服务器发生异常，请联系管理员.");
            log.error("程序发生异常：", e);
        }
        setDefinedMethodTemp("myblog");
        return RESULT_PAGES;
    }
    
    /**
     * 进入编辑文章页面
     */
    public String forwardEditArticlePage(){
        try {
            articleVo = articleService.getArticle(articleVo.getId());
        } catch (PtException e) {
            setTip("服务器发生异常，请联系管理员.");
            log.error("程序发生异常：", e);
        }
        setDefinedMethodTemp("editarticle");
        return RESULT_PAGES;
    }
    
    /**
     * 进入个人页面
     */
    public String forwardUserPage(){
        try {
            userVo = userService.getUser(userVo.getId());
            totalOfArticle = articleService.getTotalOfArticle(userVo.getId());
        } catch (PtException e) {
            setTip("服务器发生异常，请联系管理员.");
            log.error("程序发生异常：", e);
        }
        setDefinedMethodTemp("userinfo");
        return RESULT_PAGES;
    }
    
    /**
     * 进入用户博客页面
     */
    public String forwardUserBlogPage(){
        try {
            result = articleService.getMyArticles(
                    userVo.getId(), currentPage, pageSize);
            userVo = userService.getUser(userVo.getId());
        } catch (PtException e) {
            setTip("服务器发生异常，请联系管理员.");
            log.error("程序发生异常：", e);
        }
        setDefinedMethodTemp("userblog");
        return RESULT_PAGES;
    }
    
    public List<ColumnVo> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnVo> columns) {
        this.columns = columns;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    public List<BoardVo> getBoards() {
        return boards;
    }
    
    public ArticleVo getArticleVo() {
        return articleVo;
    }

    public void setArticleVo(ArticleVo articleVo) {
        this.articleVo = articleVo;
    }

    public void setBoards(List<BoardVo> boards) {
        this.boards = boards;
    }

    public ColumnVo getColumnVo() {
        return columnVo;
    }

    public void setColumnVo(ColumnVo columnVo) {
        this.columnVo = columnVo;
    }

    public BoardVo getBoardVo() {
        return boardVo;
    }

    public void setBoardVo(BoardVo boardVo) {
        this.boardVo = boardVo;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public PaginationSupport<ArticleVo> getResult() {
        return result;
    }

    public void setResult(PaginationSupport<ArticleVo> result) {
        this.result = result;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public ArticleReplyVo getArticleReplyVo() {
        return articleReplyVo;
    }

    public void setArticleReplyVo(ArticleReplyVo articleReplyVo) {
        this.articleReplyVo = articleReplyVo;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public int getTotalOfArticle() {
        return totalOfArticle;
    }

    public void setTotalOfArticle(int totalOfArticle) {
        this.totalOfArticle = totalOfArticle;
    }
    
}
