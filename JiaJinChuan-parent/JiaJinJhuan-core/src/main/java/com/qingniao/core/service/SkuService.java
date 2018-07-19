package com.qingniao.core.service;

import java.util.List;

import com.qingniao.core.pojo.product.Sku;
import com.qingniao.core.pojo.product.SkuCriteria;

public interface SkuService {

	public void insertSelective(Sku sku);

	public List<Sku> selectByExample(SkuCriteria example);

	public void updateByPrimaryKeySelective(Sku sku);

}
