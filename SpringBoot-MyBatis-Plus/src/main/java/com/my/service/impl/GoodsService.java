package com.my.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.dao.GoodsMapper;
import com.my.entity.Goods;
import com.my.service.IGoodsService;

@Service
public class GoodsService extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

	@Override
	public boolean save(Goods entity) {		
		return super.save(entity);
	}

	@Override
	public List<Goods> getUserList() {		
		return baseMapper.selectList(Wrappers.<Goods>lambdaQuery());
	}
}
