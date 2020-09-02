package com.my.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.my.beans.Book;

public interface BookRepository extends MongoRepository<Book, String> {

	public Book findByAuthor(String author);

}
