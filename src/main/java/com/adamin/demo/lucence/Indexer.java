package com.adamin.demo.lucence;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @Classname Indexer
 * @Description TODO
 * @Date 2022/4/11 18:21
 * @Created by Adam(https://www.lixiaopeng.top)
 */
public class Indexer {

    private static final Logger logger = LoggerFactory.getLogger(Indexer.class);

    private IndexWriter indexWriter;

    public Indexer(String indexDir) throws IOException {
        FSDirectory open = FSDirectory.open(Paths.get(indexDir));
        Analyzer analyzer=new StandardAnalyzer();
        IndexWriterConfig config=new IndexWriterConfig(analyzer);
        indexWriter=new IndexWriter(open,config);
    }
    /**
     * 关闭写索引
     * @throws Exception
     */
    public void close() throws Exception {
        indexWriter.close();
    }

    /**
     * 索引目录下所有文件
     * @param dataDir
     * @return
     */
    public int indexAll(String dataDir) throws IOException {
        File[] files = new File(dataDir).listFiles();
        if(null!=files){
            for (File file : files) {
                indexFile(file);
            }
        }
      return  indexWriter.numRamDocs();
    }

    /**
     * 索引指定的文件
     * @param file
     * @throws IOException
     */
    private void indexFile(File file) throws IOException {
        Document document=getDocument(file);
        indexWriter.addDocument(document);

    }

    /**
     * 获取文档
     * @param file
     * @return
     */
    private Document getDocument(File file) throws IOException {
       Document document=new Document();
       document.add(new TextField("contents",new FileReader(file)));
       document.add(new TextField("fileName", file.getName(), Field.Store.YES));
       document.add(new TextField("fullPath", file.getCanonicalPath(), Field.Store.YES));
       return  document;
    }

    public static void main(String[] args) {
        String indexDir="E:\\springboot\\demo\\lucence";
        String dataDir= indexDir+"\\data";
        Indexer indexer=null;
        int indexNum=0;
        long startTime=System.currentTimeMillis();
        try {
            indexer=new Indexer(indexDir);
            indexNum=indexer.indexAll(dataDir);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null!=indexer){
                try {
                    indexer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        long endTime=System.currentTimeMillis();
        logger.info("--索引耗时{}毫秒--",endTime-startTime);
        logger.info("----索引行数{}--",indexNum);


    }
}
