package com.qingniao.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingniao.core.dao.product.SkuMapper;
import com.qingniao.core.pojo.product.Sku;

@Service
@Transactional
public class SkuServiceImpl implements SkuService {

	@Autowired
	SkuMapper skuMapper;
	
	@Override
	public void insertSelective(Sku sku) {
		skuMapper.insertSelective(sku);
		
	}

}
