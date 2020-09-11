package com.my.entity;



import org.zxp.esclientrhl.annotation.ESID;
import org.zxp.esclientrhl.annotation.ESMapping;
import org.zxp.esclientrhl.annotation.ESMetaData;
import org.zxp.esclientrhl.enums.DataType;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ESMetaData(indexName = "book", number_of_shards = 5,number_of_replicas = 0)
public class Book {
	//标识es主键（自动对应es索引数据_id字段），注意：主键的类型需要与ElasticsearchTemplate的第二泛型一致
	@ESID
    private String id;
	@ESMapping(datatype = DataType.keyword_type) 
    private String title;
	@ESMapping(datatype = DataType.text_type) 
    private String author;
    private String postDate;

    public Book(){}

    public Book(String id, String title, String author, String postDate){
        this.id=id;
        this.title=title;
        this.author=author;
        this.postDate=postDate;
   }
}
