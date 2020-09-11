package com.my.exception;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

@Configuration
public class MySimpleMappingExceptionResolver {

	@Bean
	public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver(){
		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
		
		Properties mappings = new Properties();
		
		/**
		 * 参数一：异常类型，注意必要要全名
		 * 参数二:视图的名称
		 */
		mappings.put("java.lang.ArithmeticException", "error");
		mappings.put("java.lang.NullPointerException", "error");
		mappings.put("java.lang.ArithmeticException", "error");
		
		//设置异常映射信息
		//哪些异常要交给哪个错误页面显示
		resolver.setExceptionMappings(mappings);
		
		return resolver;
	}
}
