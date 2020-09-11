package com.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zxp.esclientrhl.annotation.EnableESTools;

import com.spring4all.swagger.EnableSwagger2Doc;


@EnableSwagger2Doc
@EnableESTools(entityPath = {"com.my.entity"})
@SpringBootApplication
public class SpringBootApp {

	public static void main(String[] args) {
		// Factory method 'elasticsearchTemplate' threw exception
		//System.setProperty("es.set.netty.runtime.available.processors","false");
		SpringApplication.run(SpringBootApp.class, args);
	}
}
