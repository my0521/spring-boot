package com.my;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zxp.esclientrhl.index.ElasticsearchIndex;
import org.zxp.esclientrhl.repository.ElasticsearchTemplate;
import org.zxp.esclientrhl.repository.PageList;

import com.my.entity.Book;
import com.my.service.BookService;

@SpringBootTest
class SpringBootAppTests {
	
	@Autowired
	private BookService bookService;	
	
	@Test
	void contextLoads() throws Exception {
		
		PageList<Book> list = bookService.search(QueryBuilders.termQuery("title", "三国演义"), null);
		System.out.println(list.getList().toString());
	}
}
