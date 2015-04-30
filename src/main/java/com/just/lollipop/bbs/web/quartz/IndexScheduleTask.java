package com.just.lollipop.bbs.web.quartz;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import com.just.lollipop.bbs.service.ArticleService;
import com.just.lollipop.bbs.vo.ArticleVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;

import com.just.lollipop.bbs.exception.PtException;
import com.just.lollipop.bbs.util.date.DateUtil;


/**
 * 生成索引定时器
 */
public class IndexScheduleTask {
    private final Log log = LogFactory.getLog(IndexScheduleTask.class);
    
    private Directory dir;
    private Analyzer analyzer;
    
    @Autowired
    private ArticleService articleService;
    
    public void schedule(){
        log.info("starting create index...");
        IndexWriter writer = null;
        try {
            if (analyzer == null || dir == null){
                init();
            }
            
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36, analyzer);
            config.setOpenMode(OpenMode.CREATE);
            writer = new IndexWriter(dir, config);
            
            List<ArticleVo> articles = articleService.getArticles();
            for (ArticleVo article : articles){
                if (article.getIsThemeArticle() == 1){
                    Document doc = transformToDoc(article);
                    writer.addDocument(doc);
                }
            }
            writer.commit();
        } catch (Exception e) {
            log.error("运行定时任务发生异常：", e);
        } finally {
            if (writer != null){
                try {
                    writer.close();
                } catch (Exception e) {
                    log.error("关闭IndexWriter时候发生异常：", e);
                }
            }
        }
    }
    
    private void init(){
        try {
            InputStream in = getClass().getResourceAsStream("/system.properties");
            Properties prop = new Properties();
            prop.load(in);
            dir = new SimpleFSDirectory(new File(prop.getProperty("index_dir")));
        } catch (IOException e) {
            log.error("初始化定时任务发生异常：", e);
        }
        analyzer = new SmartChineseAnalyzer(Version.LUCENE_36);
    }
    
    /**
     * 把实体对象转换成Document对象
     * @param article News对象
     * @return 返回一个Document对象
     */
    private Document transformToDoc(ArticleVo article) throws PtException{
        Document doc = new Document();
        doc.add(new Field("id", article.getId().toString(), Store.YES, Index.NOT_ANALYZED));
        doc.add(new Field("title", article.getTitle(), Store.YES, Index.ANALYZED));
        doc.add(new Field("content", article.getContent(), Store.YES, Index.ANALYZED));
        doc.add(new Field("createPerson", article.getCreatePerson().getUserName(), Store.YES, Index.ANALYZED));
        doc.add(new Field("createTime", DateUtil.formatDate(article.getCreateTime()), Store.YES, Index.ANALYZED));
        int totalReplys = articleService.getTotalOfReplys(article.getId());
        doc.add(new Field("totalReply", totalReplys + "", Store.YES, Index.ANALYZED));
        doc.add(new Field("boardName", article.getDiscussionBoard().getName(), Store.YES, Index.ANALYZED));
        return doc; 
    }
}
