package com.qingniao.core.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingniao.core.dao.product.ColorMapper;
import com.qingniao.core.pojo.product.Color;
import com.qingniao.core.pojo.product.ColorCriteria;
import com.qingniao.core.pojo.product.Product;

@Service
@Transactional
public class ColorServiceImpl implements ColorService {

	@Autowired
	ColorMapper colorMapper;
	@Override
	public List<Color> selectByExampleByColorCriteria(ColorCriteria colorCriteria) {
	
		return colorMapper.selectByExample(colorCriteria) ;
	}

}
