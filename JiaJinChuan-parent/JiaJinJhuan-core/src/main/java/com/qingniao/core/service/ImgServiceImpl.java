package com.qingniao.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingniao.core.dao.product.ImgMapper;
import com.qingniao.core.pojo.product.Img;

@Service
@Transactional
public class ImgServiceImpl implements ImgService {

	@Autowired
	ImgMapper imgMapper;
	@Override
	public void insertSelective(Img img) {
	imgMapper.insertSelective(img);
		
	}
	

}
