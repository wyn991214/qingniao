package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qingniao.core.dao.BrandMapper;
import com.qingniao.core.dao.product.ProductMapper;
import com.qingniao.core.pojo.product.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-*.xml"})
public class T {
	
	@Autowired
	ProductMapper productMapper;
	//添加数据
	@Test
	public void te1(){
		Product product = new Product();
		product.setId(1002l);
		product.setName("安踏");
	    product.setDescription("就那样");
		productMapper.insertSelective(product);
		
	}
	//查询
	@Test
	public void te2(){
		
	 Product p=	productMapper.selectByPrimaryKey(1001l);
		System.out.println(p);
	}
	//修改
	@Test
	public void te3(){
		Product p = new Product();
		p.setId(1001l);
		p.setName("安踏22222");
		productMapper.updateByPrimaryKeySelective(p);
	}
	//删除
	@Test
	public void te4(){
		
	  productMapper.deleteByPrimaryKey(1002l);
		
	}
	

}
