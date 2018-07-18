package com.qingniao.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingniao.common.page.PageInfo;
import com.qingniao.core.dao.product.ImgMapper;
import com.qingniao.core.dao.product.ProductMapper;
import com.qingniao.core.pojo.product.Img;
import com.qingniao.core.pojo.product.ImgCriteria;
import com.qingniao.core.pojo.product.Product;
import com.qingniao.core.pojo.product.ProductCriteria;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductMapper productMapper;
	@Autowired
	ImgMapper imgMapper;
	
	@Override
	public void insertSelectiveByProduct(Product product) {
		productMapper.insertSelective(product);
	}

	@Override
	public PageInfo selectByExample(ProductCriteria productCriteria) {
		PageInfo info = new  PageInfo(productCriteria.getPageNo(),productCriteria.getPageSize(),productMapper.countByExample(productCriteria));
		//使用pageInfo 的pageNo来矫正productCriteria的pageNo
		productCriteria.setPageNo(info.getPageNo());
		List<Product> selectByExample = productMapper.selectByExample(productCriteria);
		for(Product product :selectByExample){
			ImgCriteria imgCriteria = new ImgCriteria();
			imgCriteria.createCriteria().andProductIdEqualTo(product.getId()).andIsDefEqualTo(false);
			List<Img> imgs = imgMapper.selectByExample(imgCriteria);
		    product.setImg(imgs.get(0));
		}
		info.setList(selectByExample);
		return info;
	}

}
