package com.my.controller;


import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.my.beans.Book;
import com.my.service.BookService;

@RestController
public class BookController
{
	@Resource
	private BookService bookService;
	
	/**
	 * 保存方法1
	 * @url http://localhost:8080/save
	 * @return
	 */
	@GetMapping(value = "/save")
	public String save()
	{
		try{
			Book book = null;
			for(int i=0;i<100;i++) {	
				book = new Book();
				book.setAuthor("小明"+i);
				book.setBookName("从你在世界走过"+i);
				book.setPrice(i+"29.9");
				book.setFrom("清华出版"+i);
				bookService.Save(book);
			}
			
			return "success";
		}catch(Exception e){
			return "fail";
		}
	}
	
	/**
	 * 保存方法2
	 * @url http://localhost:8038/insert
	 * @return
	 */
	@GetMapping(value = "/insert")
	public String insert()
	{
		try{
			Book book = new Book();
			book.setAuthor("小明2");
			book.setBookName("从你在世界走过2");
			book.setPrice("29.9");
			book.setFrom("清华出版2");
			bookService.Save(book);
			return "success";
		}catch(Exception e){
			return "fail";
		}
	}
	
	
	/**
	 * 更新方法
	 * @url http://localhost:8038/update/5d174b38a8c079f248178182
	 * @return
	 */
	@GetMapping(value = "/update/{id}")
	public String update(@PathVariable("id") String id)
	{
		try{
			// 例如id  5d174b38a8c079f248178182 
			bookService.update(id);
			return "success";
		}catch(Exception e){
			return "fail";
		}
	}
	
	
	/**
	 * 根据id来删除文档
	 * @url http://localhost:8038/deleteByid/5d174b38a8c079f248178182
	 * @return
	 */
	@GetMapping(value = "/deleteByid/{id}")
	public String deleteByid(@PathVariable("id") String id)
	{
		try{
			bookService.deleteById(id);
			return "success";
		}catch(Exception e){
			return "fail";
		}
	}
	
	
	/**
	 * 根据实体文档来删除
	 * @url http://localhost:8038/deleteByentity/5d17a867a8c079ed247c54bd
	 * @return
	 */
	@GetMapping(value = "/deleteByentity/{id}")
	public String deleteByentity(@PathVariable("id") String id)
	{
		try{
			bookService.deleteByEntity(id);
			return "success";
		}catch(Exception e){
			return "fail";
		}
	}
	
	
	/**
	 * 用Example的方法来查询
	 * @uri http://localhost:8038/get/小明
	 * @param name
	 * @return
	 */
	@GetMapping(value = "/get/{name}")
	public Book getOne(@PathVariable("name") String name)
	{
		//可以把你要查询的字段任意设置为某一个字段的值，
		Book book = new Book();
		book.setBookName(name);
		
		return bookService.getByOne(book);
	}
	
	
	/**
	 * 根据jpa的命名方式来查询
	 * @param author
	 * @return
	 */
	@GetMapping(value = "/getByauthor/{author}")
	public Book getByAuthor(@PathVariable("author") String author)
	{
		return bookService.getByAuthor(author);
	}
	
	

}
