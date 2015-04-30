package com.just.lollipop.bbs.service.impl;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import com.just.lollipop.bbs.dao.UserDao;
import com.just.lollipop.bbs.service.ArticleService;
import com.just.lollipop.bbs.vo.ArticleVo;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.KeywordAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.just.lollipop.bbs.common.PaginationSupport;
import com.just.lollipop.bbs.dao.ArticleDao;
import com.just.lollipop.bbs.dao.BoardDao;
import com.just.lollipop.bbs.domain.Article;
import com.just.lollipop.bbs.domain.Board;
import com.just.lollipop.bbs.domain.User;
import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.util.date.DateUtil;
import com.just.lollipop.bbs.vo.BoardVo;
import com.just.lollipop.bbs.vo.ColumnVo;
import com.just.lollipop.bbs.vo.UserVo;
import com.just.lollipop.bbs.web.base.Constants;

/**
 * 文章业务实现类
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
    private UserDao userDao;
	
	@Autowired
    private BoardDao boardDao;
	
	@Override
	public PaginationSupport<ArticleVo> findArticlesByPage(ArticleVo articleVo,
			int start, int pagesize) throws PtException {
	    HttpSession session = ServletActionContext.getRequest().getSession();
        UserVo u = (UserVo) session.getAttribute(Constants.USER);
        if (articleVo == null){
            articleVo = new ArticleVo();
        }
        BoardVo board = new BoardVo();
        board.setModerator(u);
        articleVo.setDiscussionBoard(board);
		List<Article> articles = articleDao.findArticles(articleVo, start, pagesize);
		List<ArticleVo> voList = new ArrayList<ArticleVo>();
		UserVo userVo = null;
		BoardVo boardVo = null;
		for (Article article : articles){
			ArticleVo vo = new ArticleVo();
			vo.setId(article.getId());
			vo.setTitle(article.getTitle());
			vo.setContent(article.getContent());
			vo.setCreateTime(article.getCreateTime());
			vo.setIsTop(article.getIsTop());
			
			userVo = new UserVo();
			userVo.setId(article.getCreatePerson().getId());
			userVo.setUserName(article.getCreatePerson().getUserName());
			vo.setCreatePerson(userVo);
			
			boardVo = new BoardVo();
			boardVo.setId(article.getDiscussionBoard().getId());
			boardVo.setName(article.getDiscussionBoard().getName());
			vo.setDiscussionBoard(boardVo);
			
			voList.add(vo);
		}
		int total = articleDao.getTotalOfArticles(articleVo);
		PaginationSupport<ArticleVo> pager = new PaginationSupport<ArticleVo>(voList
				, start, total, pagesize);
		return pager;
	}

    @Override
    public void addArticle(ArticleVo articleVo) throws PtException {
        Article article = new Article();
        
        article.setTitle(articleVo.getTitle());
        
        try {
            article.setContent(java.net.URLDecoder.decode(articleVo.getContent(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        article.setIsTop(articleVo.getIsTop());
        
        HttpSession session = ServletActionContext.getRequest().getSession();
        UserVo userVo = (UserVo) session.getAttribute(Constants.USER);
        User createPerson = userDao.getUser(userVo.getId());
        article.setCreatePerson(createPerson);
        
        Board parentBoard = boardDao.getBoard(articleVo.getDiscussionBoard().getId());
        article.setDiscussionBoard(parentBoard);
        
        article.setIsThemeArticle(Constants.THEME_ARTICLE);
        articleDao.addArticle(article);
    }

    @Override
    public ArticleVo getArticle(int articleId) throws PtException {
        Article article = articleDao.getArticle(articleId);
        ArticleVo articleVo = new ArticleVo();
        articleVo.setId(article.getId());
        articleVo.setTitle(article.getTitle());
        articleVo.setContent(article.getContent());
        articleVo.setIsTop(article.getIsTop());
        
        UserVo user = new UserVo();
        user.setId(article.getCreatePerson().getId());
        user.setUserName(article.getCreatePerson().getUserName());
        user.setSexy(article.getCreatePerson().getSexy());
        user.setRegistTime(article.getCreatePerson().getRegistTime());
        articleVo.setCreatePerson(user);
        
        BoardVo boardVo = new BoardVo();
        boardVo.setId(article.getDiscussionBoard().getId());
        boardVo.setName(article.getDiscussionBoard().getName());
        
        ColumnVo columnVo = new ColumnVo();
        columnVo.setId(article.getDiscussionBoard().getColumn().getId());
        columnVo.setName(article.getDiscussionBoard().getColumn().getName());
        boardVo.setColumn(columnVo);
        
        articleVo.setDiscussionBoard(boardVo);
        articleVo.setCreateTime(article.getCreateTime());
        articleVo.setIsThemeArticle(article.getIsThemeArticle());
        return articleVo;
    }

    @Override
    public void updateArticle(ArticleVo articleVo) throws PtException {
        Article article = articleDao.getArticle(articleVo.getId());
        if (articleVo.getTitle() != null && !"".equals(articleVo.getTitle())){
            article.setTitle(articleVo.getTitle());
        }
        
        try {
            article.setContent(java.net.URLDecoder.decode(articleVo.getContent(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        if (articleVo.getIsTop() != null){
            article.setIsTop(articleVo.getIsTop());
        }
        
        if (articleVo.getDiscussionBoard() != null){
            Board board = boardDao.getBoard(articleVo.getDiscussionBoard().getId());
            article.setDiscussionBoard(board);
        }
        
        HttpSession session = ServletActionContext.getRequest().getSession();
        UserVo userVo = (UserVo) session.getAttribute(Constants.USER);
        User user = userDao.getUser(userVo.getId());
        article.setLastUpdatePerson(user);
        article.setLastUpdateTime(new java.util.Date());
        
        articleDao.updateArticle(article);
    }

    @Override
    public void deleteArticle(String ids) throws PtException {
        String[] idArray = ids.split(",");
        for (String id : idArray){
            if (id != null && !"".equals(id)){
                articleDao.deleteArticle(Integer.parseInt(id));
            }
        }
    }

    @Override
    public PaginationSupport<ArticleVo> findReplysByPage(
            ArticleVo articleVo, int start, int pagesize) throws PtException {
        List<Article> replys = articleDao.findArticleReplys(articleVo, start, pagesize);
        List<ArticleVo> voList = new ArrayList<ArticleVo>();
        for (Article article : replys){
            ArticleVo vo = new ArticleVo();
            vo.setId(article.getId());
            vo.setContent(article.getContent());
            vo.setCreateTime(article.getCreateTime());
            
            User createPerson = article.getCreatePerson();
            UserVo userVo = new UserVo();
            userVo.setId(createPerson.getId());
            userVo.setUserName(createPerson.getUserName());
            vo.setCreatePerson(userVo);
            
            Article themeArticle = article.getThemeArticle();
            articleVo = new ArticleVo();
            articleVo.setId(themeArticle.getId());
            articleVo.setTitle(themeArticle.getTitle());
            vo.setThemeArticle(articleVo);
            
            voList.add(vo);
        }
        int total = articleDao.getTotalOfArticleReplys(articleVo.getId());
        PaginationSupport<ArticleVo> pager = new PaginationSupport<ArticleVo>(voList
                , start, total, pagesize);
        return pager;
    }

    @Override
    public void addReply(ArticleVo articleVo) throws PtException {
        Article article = new Article();
        article.setIsThemeArticle(Constants.REPLY_ARTICLE);
        article.setContent(articleVo.getContent());
        
        HttpSession session = ServletActionContext.getRequest().getSession();
        UserVo userVo = (UserVo) session.getAttribute(Constants.USER);
        User createPerson = userDao.getUser(userVo.getId());
        article.setCreatePerson(createPerson);
        
        Article themeArticle = articleDao.getArticle(articleVo.getThemeArticle().getId());
        article.setDiscussionBoard(themeArticle.getDiscussionBoard());
        article.setThemeArticle(themeArticle);
        
        articleDao.addArticle(article);
    }

    @Override
    public ArticleVo getArticleReply(int replyId) throws PtException {
        Article article = articleDao.getArticleReply(replyId);
        ArticleVo articleVo = new ArticleVo();
        articleVo.setId(article.getId());
        articleVo.setContent(article.getContent());
        
        ArticleVo themeArticle = new ArticleVo();
        themeArticle.setId(article.getThemeArticle().getId());
        themeArticle.setTitle(article.getThemeArticle().getTitle());
        articleVo.setThemeArticle(themeArticle);
        return articleVo;
    }

    @Override
    public void updateReply(ArticleVo articleVo) throws PtException {
        Article article = articleDao.getArticleReply(articleVo.getId());
        
        try {
            article.setContent(java.net.URLDecoder.decode(articleVo.getContent(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        HttpSession session = ServletActionContext.getRequest().getSession();
        UserVo userVo = (UserVo) session.getAttribute(Constants.USER);
        User user = userDao.getUser(userVo.getId());
        article.setLastUpdatePerson(user);
        article.setLastUpdateTime(new java.util.Date());
        articleDao.updateArticle(article);
    }

    @Override
    public void deleteReply(String ids) throws PtException {
        String[] idArray = ids.split(",");
        for (String id : idArray){
            if (id != null && !"".equals(id)){
                articleDao.deleteArticle(Integer.parseInt(id));
            }
        }
    }

    @Override
    public List<ArticleVo> getArticles() throws PtException {
        List<Article> articles = articleDao.getAll();
        List<ArticleVo> voList = new ArrayList<ArticleVo>();
        for (Article article : articles){
            ArticleVo vo = new ArticleVo();
            vo.setId(article.getId());
            vo.setTitle(article.getTitle());
            vo.setContent(article.getContent());
            vo.setCreateTime(article.getCreateTime());
            
            UserVo user = new UserVo();
            user.setId(article.getCreatePerson().getId());
            user.setUserName(article.getCreatePerson().getUserName());
            vo.setCreatePerson(user);
            
            BoardVo board = new BoardVo();
            board.setId(article.getDiscussionBoard().getId());
            board.setName(article.getDiscussionBoard().getName());
            vo.setDiscussionBoard(board);
            vo.setIsThemeArticle(article.getIsThemeArticle());
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public PaginationSupport<ArticleVo> search(String keyword,
            int currentPage, int pageSize) throws Exception {
        if (currentPage <= 0) {
            currentPage = 1;
        }
        if (pageSize == 0) {
            pageSize = Constants.PAGESIZE;
        }
        int start = (currentPage - 1) * pageSize;
        
        InputStream in = getClass().getResourceAsStream("/system.properties");
        Properties prop = new Properties();
        prop.load(in);
        Directory dir = new SimpleFSDirectory(new File(prop.getProperty("index_dir")));
        IndexReader reader = IndexReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        BooleanQuery bQuery = new BooleanQuery();  //组合查询
        if (!"".equals(keyword)) { //包含全部关键词
            KeywordAnalyzer analyzer = new KeywordAnalyzer();
            BooleanClause.Occur[] flags = {BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD};
            Query query = MultiFieldQueryParser.parse(Version.LUCENE_36, keyword
                    , new String[]{"title", "content"}, flags, analyzer);
            bQuery.add(query, BooleanClause.Occur.MUST);
        }
        
        int hitNum = start + pageSize; //返回前N条结果数
        TopScoreDocCollector res = TopScoreDocCollector.create(hitNum, false);
        searcher.search(bQuery, res);
        
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter(
                "<span style='color:#ff6600;'>", "</span>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(bQuery));
        
        int total = res.getTotalHits();
        
        TopDocs tds = res.topDocs(start, pageSize);
        ScoreDoc[] sd = tds.scoreDocs;
        Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_36);
        
        List<ArticleVo> voList = new ArrayList<ArticleVo>();
        for (int i = 0; i < sd.length; i++){
            Document hitDoc = reader.document(sd[i].doc);
            ArticleVo vo = new ArticleVo();
            vo.setId(Integer.parseInt(hitDoc.get("id")));
            String title = highlighter.getBestFragment(analyzer, "title", hitDoc.get("title"));
            if (title == null){
                vo.setTitle(hitDoc.get("title"));
            }else{
                vo.setTitle(title);
            }
            String content = hitDoc.get("content");
            if (content.length() > 100){
                content = content.substring(0, 150) + "...";
            }
            content = highlighter.getBestFragment(analyzer, "content", content);
            if (content == null){
                vo.setContent(hitDoc.get("content"));
            }else{
                vo.setContent(content);
            }
            
            UserVo user = new UserVo();
            user.setUserName(hitDoc.get("createPerson"));
            vo.setCreatePerson(user);
            vo.setCreateTime(DateUtil.parseDate(hitDoc.get("createTime")));
            vo.setTotalReply(Integer.parseInt(hitDoc.get("totalReply")));
            BoardVo board = new BoardVo();
            board.setName(hitDoc.get("boardName"));
            vo.setDiscussionBoard(board);
            voList.add(vo);
        }
        searcher.close();
        return new PaginationSupport<ArticleVo>(voList, start, total, pageSize);
    }

    @Override
    public PaginationSupport<ArticleVo> getArticleList(int boardId
            , int currentPage, int pageSize) throws PtException {
        if (currentPage <= 0) {
            currentPage = 1;
        }
        if (pageSize == 0) {
            pageSize = Constants.PAGESIZE;
        }
        int start = (currentPage - 1) * pageSize;
        List<Article> articles = articleDao.getArticles(boardId, start, pageSize);
        List<ArticleVo> voList = new ArrayList<ArticleVo>();
        ArticleVo articleVo = null;
        UserVo user = null;
        BoardVo board = null;
        ColumnVo column = null;
        for (Article article : articles){
            articleVo = new ArticleVo();
            articleVo.setId(article.getId());
            articleVo.setTitle(article.getTitle());
            articleVo.setContent(article.getContent());
            articleVo.setCreateTime(article.getCreateTime());
            
            user = new UserVo();
            user.setId(article.getCreatePerson().getId());
            user.setUserName(article.getCreatePerson().getUserName());
            articleVo.setCreatePerson(user);
            
            int totalReply = articleDao.getTotalOfArticleReplys(article.getId());
            articleVo.setTotalReply(totalReply);
            
            if (article.getLastUpdateTime() != null){
                user = new UserVo();
                user.setId(article.getLastUpdatePerson().getId());
                user.setUserName(article.getLastUpdatePerson().getUserName());
                articleVo.setLastUpdatePerson(user);
                
                articleVo.setLastUpdateTime(article.getLastUpdateTime());
            }else{
                articleVo.setLastUpdatePerson(user);
                articleVo.setLastUpdateTime(article.getCreateTime());
            }
            
            board = new BoardVo();
            board.setId(article.getDiscussionBoard().getId());
            board.setName(article.getDiscussionBoard().getName());
            
            column = new ColumnVo();
            column.setId(article.getDiscussionBoard().getColumn().getId());
            column.setName(article.getDiscussionBoard().getColumn().getName());
            board.setColumn(column);
            articleVo.setDiscussionBoard(board);
            voList.add(articleVo);
        }
        int total = articleDao.getTotalOfThemeArticles(boardId);
        PaginationSupport<ArticleVo> pager = new PaginationSupport<ArticleVo>(voList
                , start, total, pageSize);
        return pager;
    }

    @Override
    public PaginationSupport<ArticleVo> getArticles(Integer themeArtileId, int currentPage,
            int pageSize) throws PtException {
        if (currentPage <= 0) {
            currentPage = 1;
        }
        if (pageSize == 0) {
            pageSize = Constants.PAGESIZE;
        }
        int start = (currentPage - 1) * pageSize;
        List<Article> articles = articleDao.findByPage(themeArtileId, start, pageSize);
        List<ArticleVo> voList = new ArrayList<ArticleVo>();
        ArticleVo articleVo = null;
        UserVo user = null;
        BoardVo board = null;
        ColumnVo column = null;
        for (Article article : articles){
            articleVo = new ArticleVo();
            articleVo.setId(article.getId());
            articleVo.setTitle(article.getTitle());
            articleVo.setContent(article.getContent());
            articleVo.setCreateTime(article.getCreateTime());
            
            user = new UserVo();
            user.setId(article.getCreatePerson().getId());
            user.setUserName(article.getCreatePerson().getUserName());
            articleVo.setCreatePerson(user);
            
            board = new BoardVo();
            board.setId(article.getDiscussionBoard().getId());
            board.setName(article.getDiscussionBoard().getName());
            
            column = new ColumnVo();
            column.setId(article.getDiscussionBoard().getColumn().getId());
            column.setName(article.getDiscussionBoard().getColumn().getName());
            board.setColumn(column);
            articleVo.setDiscussionBoard(board);
            
            articleVo.setIsThemeArticle(article.getIsThemeArticle());
            voList.add(articleVo);
        }
        int total = articleDao.getTotalOfArticles(themeArtileId);
        PaginationSupport<ArticleVo> pager = new PaginationSupport<ArticleVo>(voList
                , start, total, pageSize);
        return pager;
    }

    @Override
    public int getTotalOfReplys(int themeArtileId) throws PtException {
        return articleDao.getTotalOfArticleReplys(themeArtileId);
    }

    @Override
    public ArticleVo getNextArticle(int themeArticleId) throws PtException {
        Article article = articleDao.getArticle(themeArticleId);
        article = articleDao.getNextArticle(article.getDiscussionBoard().getId(), themeArticleId);
        if (article != null){
            
            ArticleVo articleVo = new ArticleVo();
            articleVo.setId(article.getId());
            articleVo.setTitle(article.getTitle());
            articleVo.setContent(article.getContent());
            articleVo.setIsTop(article.getIsTop());
            
            UserVo user = new UserVo();
            user.setId(article.getCreatePerson().getId());
            user.setUserName(article.getCreatePerson().getUserName());
            user.setSexy(article.getCreatePerson().getSexy());
            user.setRegistTime(article.getCreatePerson().getRegistTime());
            articleVo.setCreatePerson(user);
            
            BoardVo boardVo = new BoardVo();
            boardVo.setId(article.getDiscussionBoard().getId());
            boardVo.setName(article.getDiscussionBoard().getName());
            
            ColumnVo columnVo = new ColumnVo();
            columnVo.setId(article.getDiscussionBoard().getColumn().getId());
            columnVo.setName(article.getDiscussionBoard().getColumn().getName());
            boardVo.setColumn(columnVo);
            
            articleVo.setDiscussionBoard(boardVo);
            articleVo.setCreateTime(article.getCreateTime());
            
            return articleVo;
        }
        
        return null;
    }

    @Override
    public ArticleVo getPrevArticle(int themeArticleId) throws PtException {
        Article article = articleDao.getArticle(themeArticleId);
        article = articleDao.getPrevArticle(article.getDiscussionBoard().getId(), themeArticleId);
        if (article != null){
            ArticleVo articleVo = new ArticleVo();
            articleVo.setId(article.getId());
            articleVo.setTitle(article.getTitle());
            articleVo.setContent(article.getContent());
            articleVo.setIsTop(article.getIsTop());
            
            UserVo user = new UserVo();
            user.setId(article.getCreatePerson().getId());
            user.setUserName(article.getCreatePerson().getUserName());
            user.setSexy(article.getCreatePerson().getSexy());
            user.setRegistTime(article.getCreatePerson().getRegistTime());
            articleVo.setCreatePerson(user);
            
            BoardVo boardVo = new BoardVo();
            boardVo.setId(article.getDiscussionBoard().getId());
            boardVo.setName(article.getDiscussionBoard().getName());
            
            ColumnVo columnVo = new ColumnVo();
            columnVo.setId(article.getDiscussionBoard().getColumn().getId());
            columnVo.setName(article.getDiscussionBoard().getColumn().getName());
            boardVo.setColumn(columnVo);
            
            articleVo.setDiscussionBoard(boardVo);
            articleVo.setCreateTime(article.getCreateTime());
            return articleVo;
        }
        
        return null;
    }

    @Override
    public void delete(ArticleVo articleVo) throws PtException {
        articleDao.deleteArticle(articleVo.getId());
    }

    @Override
    public PaginationSupport<ArticleVo> getMyArticles(Integer userId,
            int currentPage, int pageSize) throws PtException {
        if (currentPage <= 0) {
            currentPage = 1;
        }
        if (pageSize == 0) {
            pageSize = Constants.PAGESIZE;
        }
        int start = (currentPage - 1) * pageSize;
        List<Article> articles = articleDao.findMyArticlesByPage(userId, start, pageSize);
        List<ArticleVo> voList = new ArrayList<ArticleVo>();
        ArticleVo articleVo = null;
        UserVo user = null;
        BoardVo board = null;
        ColumnVo column = null;
        for (Article article : articles){
            articleVo = new ArticleVo();
            articleVo.setId(article.getId());
            articleVo.setTitle(article.getTitle());
            articleVo.setContent(article.getContent());
            articleVo.setCreateTime(article.getCreateTime());
            
            user = new UserVo();
            user.setId(article.getCreatePerson().getId());
            user.setUserName(article.getCreatePerson().getUserName());
            articleVo.setCreatePerson(user);
            
            board = new BoardVo();
            board.setId(article.getDiscussionBoard().getId());
            board.setName(article.getDiscussionBoard().getName());
            
            column = new ColumnVo();
            column.setId(article.getDiscussionBoard().getColumn().getId());
            column.setName(article.getDiscussionBoard().getColumn().getName());
            board.setColumn(column);
            articleVo.setDiscussionBoard(board);
            
            int totalReply = articleDao.getTotalOfArticleReplys(articleVo.getId());
            articleVo.setTotalReply(totalReply);
            voList.add(articleVo);
        }
        int total = articleDao.getTotalOfMyArticles(userId);
        PaginationSupport<ArticleVo> pager = new PaginationSupport<ArticleVo>(voList
                , start, total, pageSize);
        return pager;
    }

    @Override
    public int getTotalOfArticle(int userId) throws PtException {
        return articleDao.getTotalOfMyArticles(userId);
    }
    
}
