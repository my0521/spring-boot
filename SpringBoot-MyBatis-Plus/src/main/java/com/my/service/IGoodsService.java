package com.my.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.my.entity.Goods;


public interface IGoodsService extends IService<Goods> {

	@Override
	boolean save(Goods entity);

	List<Goods> getUserList();
}

