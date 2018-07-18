package com.qingniao.core.service;

import java.util.List;

import com.qingniao.core.pojo.product.Color;
import com.qingniao.core.pojo.product.ColorCriteria;

public interface ColorService {
	
	public List<Color> selectByExampleByColorCriteria(ColorCriteria colorCriteria);

}
