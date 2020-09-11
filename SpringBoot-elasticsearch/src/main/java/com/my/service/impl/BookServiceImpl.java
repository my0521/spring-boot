package com.my.service.impl;


import java.util.List;

import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zxp.esclientrhl.index.ElasticsearchIndex;
import org.zxp.esclientrhl.repository.ElasticsearchTemplate;
import org.zxp.esclientrhl.repository.HighLight;
import org.zxp.esclientrhl.repository.PageList;
import org.zxp.esclientrhl.repository.PageSortHighLight;
import org.zxp.esclientrhl.repository.Sort;

import com.my.entity.Book;
import com.my.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
    ElasticsearchTemplate<Book,String> elasticsearchTemplate;
	@Autowired
	ElasticsearchIndex<Book> elasticsearchIndex;
	
	@Override
	public void createIndex() throws Exception {
		elasticsearchIndex.createIndex(Book.class);
		
	}


	@Override
	public boolean save(Book book) throws Exception {		
		return elasticsearchTemplate.save(book);		
	}

	@Override
	public boolean save(List<Book> books) throws Exception {
		 elasticsearchTemplate.save(books);
		 return true;
	}

	@Override
	public boolean saveBatch(List<Book> books) throws Exception {
		elasticsearchTemplate.saveBatch(books);
		return false;
	}


	@Override
	public boolean update(Book book) throws Exception {
		 elasticsearchTemplate.update(book);
		 return true;
	}


	@Override
	public boolean updateCover(Book book) throws Exception {
		 elasticsearchTemplate.updateCover(book);
		 return true;
	}

	@Override
	public boolean batchUpdate(String key,String value, Book book, int limitcount, boolean asyn) throws Exception {
		elasticsearchTemplate.batchUpdate(QueryBuilders.matchQuery(key,value),book,Book.class,30, true);
		return true;
	}


	@Override
	public boolean deleteById(String id) throws Exception {
		elasticsearchTemplate.deleteById(id, Book.class);
		return true;
	}


	@Override
	public boolean deleteByCondition(String key, String value, Book book) throws Exception {
		elasticsearchTemplate.deleteByCondition(QueryBuilders.matchQuery(key,value),Book.class);
		return true;
	}


	@Override
	public boolean delete(Book book) throws Exception {
		elasticsearchTemplate.delete(book);
		return true;
	}


	@Override
	public List<Book> search(QueryBuilder queryBuilder) throws Exception {
		List<Book> books = elasticsearchTemplate.search(queryBuilder,Book.class);
		return books;
	}


	@Override
	public List<Book> searchMore(QueryBuilder queryBuilder, int limitSize) throws Exception {
		List<Book> books = elasticsearchTemplate.searchMore(queryBuilder,limitSize,Book.class);
		return books;
	}


	@Override
	public PageList<Book> search(QueryBuilder queryBuilder, PageSortHighLight pageSortHighLight) throws Exception {
		//定制分页信息 
		int currentPage = 1; 
		int pageSize = 10; 
		//分页 
		PageSortHighLight psh = new PageSortHighLight(currentPage,pageSize); 
		//排序字段，注意如果proposal_no是text类型会默认带有keyword性质，需要拼接.keyword 
		String sorter = "id.keyword"; 
		Sort.Order order = new Sort.Order(SortOrder.ASC,sorter); 
		psh.setSort(new Sort(order)); 
		//定制高亮，如果定制了高亮，返回结果会自动替换字段值为高亮内容
		psh.setHighLight(new HighLight().field("risk_code")); 
		//可以单独定义高亮的格式 
		//new HighLight().setPreTag("<em>"); 
		//new HighLight().setPostTag("</em>");
		PageList<Book> pageList = new PageList<>(); 
		pageList = elasticsearchTemplate.search(queryBuilder, psh, Book.class); 
		
		return pageList;
	}


	@Override
	public PageList<Book> searchAll() throws Exception {
		//定制分页信息 
				int currentPage = 1; 
				int pageSize = 10; 
				//分页 
				PageSortHighLight psh = new PageSortHighLight(currentPage,pageSize); 
				//排序字段，注意如果proposal_no是text类型会默认带有keyword性质，需要拼接.keyword 
				String sorter = "id.keyword"; 
				Sort.Order order = new Sort.Order(SortOrder.ASC,sorter); 
				psh.setSort(new Sort(order)); 
				//定制高亮，如果定制了高亮，返回结果会自动替换字段值为高亮内容
				psh.setHighLight(new HighLight().field("title")); 
				//可以单独定义高亮的格式 
				//new HighLight().setPreTag("<em>"); 
				//new HighLight().setPostTag("</em>");
				PageList<Book> pageList = new PageList<>(); 
				pageList = elasticsearchTemplate.search(new MatchAllQueryBuilder(), psh, Book.class); 
				return pageList;
	}


	@Override
	public List<Book> mgetById(String[] ids) throws Exception {
		
		return elasticsearchTemplate.mgetById(ids, Book.class);
	}
	
}
