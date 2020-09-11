package com.my.service;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.zxp.esclientrhl.repository.PageList;
import org.zxp.esclientrhl.repository.PageSortHighLight;

import com.my.entity.Book;

public interface BookService {

	void createIndex() throws Exception;	

	//新增文档
	boolean save(Book book) throws Exception;
	//批量新增
	boolean save(List<Book> book) throws Exception;
	//批量新增，分批次，批次上限5000
	boolean saveBatch(List<Book> books) throws Exception;
	
	//部分更新
	boolean update(Book book) throws Exception;
	//覆盖更新
	boolean updateCover(Book book) throws Exception;
	
	boolean deleteById(String id) throws Exception;
	
	boolean delete(Book book) throws Exception;
	
	boolean deleteByCondition(String key,String value, Book book) throws Exception;
	/** 根据queryBuilder所查结果，按照有值字段更新索引 
	 * @param queryBuilder 
	 * @param t   满足queryBuilder查询出条件的结果集更新为t的有值字段值 
	 * @param clazz 
	 * @param limitcount 更新字段不能超出limitcount
	 * @param asyn true异步处理  否则同步处理 
	 * @return 	@throws Exception 
	 * */ 
	boolean batchUpdate(String key,String value, Book book, int limitcount, boolean asyn) throws Exception;
	
	
	/** * 非分页查询 
	 *  目前暂时传入类类型 
	 *   @param queryBuilder 查询条件
	 *   @param clazz 泛型对应类类型
	 *   @return 泛型定义好的es索引结构实体类查询结果集合 
	 *   @throws Exception 
	 *   */
	public List<Book> search(QueryBuilder queryBuilder) throws Exception;
	/** * 非分页查询，指定大返回条数 
	 * * 目前暂时传入类类型 
	 * * @param queryBuilder 
	 * * @param limitSize 大返回条数 
	 * * @param clazz 
	 * * @return 
	 * * @throws Exception 
	 * */ 
	public List<Book> searchMore(QueryBuilder queryBuilder,int limitSize) throws Exception;
	/** * 支持分页、高亮、排序的查询 
	 * * @param queryBuilder 查询条件
	 * @param pageSortHighLight 封装了分页、排序、高亮的规格信息 
	 * * @param clazz 泛型对应类类型 
	 * * @return 泛型定义好的es索引结构实体类查询结果集合 
	 * * @throws Exception 
	 * */ 
	public PageList<Book> search(QueryBuilder queryBuilder, PageSortHighLight pageSortHighLight) throws Exception;

	public PageList<Book> searchAll() throws Exception;
	
	List<Book> mgetById(String[] ids) throws Exception;

}
