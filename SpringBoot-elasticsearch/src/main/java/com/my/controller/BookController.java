package com.my.controller;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zxp.esclientrhl.repository.PageList;

import com.my.entity.Book;
import com.my.service.BookService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;	
	

	@PostMapping("/createIndex")
	public String createIndex() throws Exception {
		bookService.createIndex();
		return "OK";
	}
	
	@RequestMapping(value = "/init", method = RequestMethod.POST)
	public String init() throws Exception {
		List<Book> list = new ArrayList<>();
		list.add(new Book("1", "大卫-科波菲尔", "json", "1986-12-25"));
		list.add(new Book("2", "巴黎圣母院", "雨果", "1867-12-25"));
		list.add(new Book("3", "在你的世界等你", "没有", "2020-12-25"));
		list.add(new Book("4", "三国演义", "罗贯中", "1645-12-25"));
		bookService.save(list);
		return "OK";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@RequestBody Book book) throws Exception {
		bookService.save(book);
		return "OK";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestBody Book book) throws Exception {
		bookService.update(book);
		return "OK";
	}
	
	@RequestMapping(value = "/updateCover", method = RequestMethod.POST)
	public String updateCover(@RequestBody Book book) throws Exception {
		bookService.updateCover(book);
		return "OK";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestBody Book book) throws Exception {
		bookService.delete(book);
		return "OK";
	}
	
	@RequestMapping(value = "/deleteById/{id}", method = RequestMethod.POST)
	public String deleteById(@PathVariable String id) throws Exception {
		bookService.deleteById(id);
		return "OK";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public List<Book> search(@RequestParam(value="key",required = true) String key,@RequestParam(value="value",required = true)  String  value) throws Exception {
		List<Book> books = bookService.search(QueryBuilders.termQuery(key, value));
		return books;
	}
	
	@RequestMapping(value = "/searchall", method = RequestMethod.POST)
	public List<Book> searchAll() throws Exception {
		PageList<Book> books = bookService.searchAll();
		return books.getList();
	}
	
	@RequestMapping(value = "/mgetById", method = RequestMethod.POST)
	public List<Book> mgetByIds(@RequestBody String[] ids) throws Exception {
		List<Book> books = bookService.mgetById(ids);
		return books;
	}
}