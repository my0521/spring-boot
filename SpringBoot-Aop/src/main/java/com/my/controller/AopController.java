package com.my.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.annotation.LogFilter;


@RestController
public class AopController {

	//http://localhost:8080/saveRequestLog?name=Request
    @LogFilter("保存请求日志")
    @RequestMapping("/saveRequestLog")
    public String saveRequestLog(@RequestParam("name") String name) {
        return "success：" + name;
    }
	//http://localhost:8080/saveExceptionLog?name=Exception
    @LogFilter("保存异常日志")
    @RequestMapping("/saveExceptionLog")
    public String saveExceptionLog(@RequestParam("name") String name) {
        int error = 100 / 0;
        System.out.println(error);
        return "success：" + name;
    }
}
