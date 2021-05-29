package com.my;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.apache.lucene.queryparser.classic.QueryParser;


@SpringBootTest
class ApplicationTests {
	
	String path_ = "src/main/resources/Lucene/Document";
	String index_ = "src/main/resources/Lucene/index";

	@Test
	void contextLoads() {
	}	
	
	//创建索引
    @Test
    public void luceneCreateIndex() throws Exception{
    	
    	System.out.println(path_);
    	System.out.println(index_);
        //指定索引存放的位置
        //E:\Lucene_index
        Directory directory = FSDirectory.open(Paths.get(new File(index_).getPath()));
        System.out.println("pathname"+Paths.get(new File(index_).getPath()));
       //创建一个分词器
//        StandardAnalyzer analyzer = new StandardAnalyzer();
//        CJKAnalyzer cjkAnalyzer = new CJKAnalyzer();
        Analyzer analyzer =new IKAnalyzer();
        //创建indexwriterConfig(参数分词器)
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        //创建indexwrite 对象(文件对象，索引配置对象)
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
        //原始文件
        File file = new File(path_);

        for (File f: file.listFiles()){
            //文件名
            String fileName = f.getName();
            //文件内容
            String fileContent = FileUtils.readFileToString(f,"utf8");
            System.out.println(fileContent);
            //文件路径
            String path = f.getPath();
            //文件大小
            long fileSize = FileUtils.sizeOf(f);

            //创建文件域名
            //域的名称 域的内容 是否存储
            Field fileNameField = new TextField("fileName", fileName, Field.Store.YES);
            Field fileContentField = new TextField("fileContent", fileContent, Field.Store.YES);
            Field filePathField = new TextField("filePath", path, Field.Store.YES);
            Field fileSizeField = new TextField("fileSize", fileSize+"", Field.Store.YES);

            //创建Document 对象
            Document indexableFields = new Document();
            indexableFields.add(fileNameField);
            indexableFields.add(fileContentField);
            indexableFields.add(filePathField);
            indexableFields.add(fileSizeField);
            //创建索引，并写入索引库
            indexWriter.addDocument(indexableFields);

        }

        //关闭indexWriter
        indexWriter.close();
    }
    
    @Test
    public void searchIndex() throws IOException {
    	String index_ = "src/main/resources/Lucene/index";
    	System.out.println(index_);
        //指定索引库存放路径
        //E:\Lucene_index
        Directory directory = FSDirectory.open(Paths.get(new File(index_).getPath()));
        //创建indexReader对象
        IndexReader indexReader = DirectoryReader.open(directory);
        //创建indexSearcher对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //创建查询
        Query query = new TermQuery(new Term("fileContent", "中文"));
        //执行查询
        //参数一  查询对象    参数二  查询结果返回的最大值
        TopDocs topDocs = indexSearcher.search(query, 10);
        System.out.println("查询结果的总数"+topDocs.totalHits);
        //遍历查询结果
        for (ScoreDoc scoreDoc: topDocs.scoreDocs){
            //scoreDoc.doc 属性就是doucumnet对象的id
            Document doc = indexSearcher.doc(scoreDoc.doc);
            System.out.println(doc.getField("fileName"));
//            System.out.println(doc.getField("fileContent"));
            System.out.println(doc.getField("filePath"));
            System.out.println(doc.getField("fileSize"));
        }
        indexReader.close();
    }

    /**
     * 增删改公用的获取IndexWriter对象
     * @return
     * @throws Exception
     */
    public IndexWriter getIndexWriter() throws Exception{
        //获得索引存放的位置
    	 Directory directory = FSDirectory.open(Paths.get(new File(index_).getPath()));
    	 Analyzer analyzer =new IKAnalyzer();
         //创建indexwriterConfig(参数分词器)
         IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
         //创建indexwrite 对象(文件对象，索引配置对象)
         IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
        return  indexWriter;
    }
    
    /**
     * 删除索引——全部删除
     * @throws Exception
     */
    @Test
    public  void deleteAll() throws  Exception{
        //获得indexWeiter对象
        IndexWriter indexWriter= this.getIndexWriter();
        //删除所有的索引
        indexWriter.deleteAll();
        indexWriter.close();
    }
    /**
     * 删除索引-按条件删除
     */
    @Test
    public void delete() throws Exception{
        //获得indexWeiter对象
        IndexWriter indexWriter= this.getIndexWriter();

        Term t=new Term("fileName","全文");
        TermQuery query=new TermQuery(t);
        //删除指定条件
        indexWriter.deleteDocuments(query);
        indexWriter.close();
    }

    /**
     * 更新索引
     * @throws Exception
     */
    @Test
    public  void  update() throws  Exception{
        //获得indexWeiter对象
        IndexWriter indexWriter= this.getIndexWriter();
        Document document =new Document();
        document.add(new TextField("filen","测试文件名", Field.Store.YES));
        document.add(new TextField("fileC","测试文件内容", Field.Store.YES));

        indexWriter.updateDocument(new Term("fileName","全文"),document);
        indexWriter.close();
    }
    
    /**
     * 公用的查询方法获取indexSearcher
     * @return
     * @throws Exception
     */
    public IndexSearcher getIndexSearcher() throws  Exception{

        //1：创建一个Directory对象，也就是索引库存放的位置
    	Directory directory = FSDirectory.open(Paths.get(new File(index_).getPath()));
        //2:创建一个indexReader对象，需要制定Directory对象。
        IndexReader indexReader =DirectoryReader.open(directory);
        //3:创建一个indexSearcher对象，需要制定IndexReader对象
        return new IndexSearcher(indexReader);
    }

    /**
     * 公用的返回结果方法
     * @param indexSearcher
     * @param query
     * @throws Exception
     */
    public  void printResult(IndexSearcher indexSearcher, Query query) throws  Exception{
        //5：执行查询
        TopDocs topDocs =indexSearcher.search(query,10);
        //6:返回查询结果，便利查询结果且输出
        ScoreDoc[] scoreDocs=topDocs.scoreDocs;
        for(ScoreDoc scoreDoc: scoreDocs){
            int doc=scoreDoc.doc;
            Document document =indexSearcher.doc(doc);
            //文件名
            String fileName=document.get("fileName");
            System.out.println("fileName:"+fileName);
            // 文件大小
            String fileSize = document.get("fileSize");
            System.out.println("fileSize:::"+fileSize);
            //文件路径
            String filePath = document.get("filePath");
            System.out.println("filePath:::"+filePath);
            //文件内容
            // fileContent=document.get("fileContent");
            //System.out.println(fileContent);
            System.out.println("===============================");
        }
    }
    
    
    /**
     * 查询所有
     * @throws Exception
     */
    @Test
    public void searchAll() throws  Exception{
        IndexSearcher indexSearcher =getIndexSearcher();

        Query query=new MatchAllDocsQuery();
        System.out.println("query::::"+query);
        printResult(indexSearcher,query);
        indexSearcher.getIndexReader().close();
    }
    
    /**
     *解析查询
     */
    @Test
    public void QueryParser() throws Exception{
        IndexSearcher indexSearcher=getIndexSearcher();
        //参数1：默认查询的域，参数2：采用的分析器
        QueryParser queryParser=new QueryParser("fileName",new IKAnalyzer());
        Query query=queryParser.parse("fielName:aaabbb OR fileContent:what");
        printResult(indexSearcher,query);
        indexSearcher.getIndexReader().close();
    }
}
