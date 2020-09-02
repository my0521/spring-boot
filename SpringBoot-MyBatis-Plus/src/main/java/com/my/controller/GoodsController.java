package com.my.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.entity.Goods;
import com.my.service.IGoodsService;
@RequestMapping("goods")
@RestController
public class GoodsController {
	@Autowired
	private IGoodsService goodsService;
	
	@GetMapping("/select")
	public List<Goods> select() {
		
		return goodsService.getUserList();
	}
	
	@GetMapping("/insert")
	public boolean insert(Goods goods){
		
		return goodsService.save(goods);
	}	
}
