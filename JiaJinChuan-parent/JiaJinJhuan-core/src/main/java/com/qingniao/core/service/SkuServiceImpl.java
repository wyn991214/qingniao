package com.qingniao.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingniao.core.dao.product.ColorMapper;
import com.qingniao.core.dao.product.SkuMapper;
import com.qingniao.core.pojo.product.Color;
import com.qingniao.core.pojo.product.ColorCriteria;
import com.qingniao.core.pojo.product.Sku;
import com.qingniao.core.pojo.product.SkuCriteria;

@Service
@Transactional
public class SkuServiceImpl implements SkuService {

	@Autowired
	SkuMapper skuMapper;
	@Autowired
	ColorMapper colerMapper;

	@Override
	public void insertSelective(Sku sku) {
		skuMapper.insertSelective(sku);

	}

	@Override
	public List<Sku> selectByExample(SkuCriteria example) {
		List<Sku> skuList = skuMapper.selectByExample(example);
		// 遍历sku
		for (Sku sku : skuList) {
			ColorCriteria colorCriteria = new ColorCriteria();
			colorCriteria.createCriteria().andIdEqualTo(sku.getColorId());
			List<Color> colors = colerMapper.selectByExample(colorCriteria);
			sku.setColor(colors.get(0));
		}

		return skuList;

	}

	public void updateByPrimaryKeySelective(Sku sku) {
		skuMapper.updateByPrimaryKeySelective(sku);
	}

}
