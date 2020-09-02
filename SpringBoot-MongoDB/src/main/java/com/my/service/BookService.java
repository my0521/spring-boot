package com.my.service;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.my.beans.Book;
import com.my.repository.BookRepository;

@Service
public class BookService {

	@Resource
	private  BookRepository bookRepository;
	
	
	public void Save(Book book) {
		bookRepository.save(book);
	}
	
	public void insert(Book book) {
		bookRepository.insert(book);
	}
	
	public void update(String id)
	{
		Book book = bookRepository.findById(id).get();
		book.setAuthor("小黄");
		bookRepository.save(book);
	}
	
	public void deleteById(String id)
	{
		bookRepository.deleteById(id);
	}
	
	
	/**
	 * 其实也是根据id来删除的
	 * @param id
	 */
	public void deleteByEntity(String id)
	{
		Book book = bookRepository.findById(id).get();
		bookRepository.delete(book);
	}
	
	
	
	/**
	  * 利用ExampleMatcher 自定义条件来获取书本
	 * @param book
	 * @return
	 */
	public Book getByOne(Book book)
	{
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("bookName", "author");
		Example<Book> example = Example.of(book, matcher);
		return bookRepository.findOne(example).get();

	}
	
	public Book getByAuthor(String author)
	{		
		return bookRepository.findByAuthor(author);
	}
}
