package com.qingniao.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import com.qingniao.common.page.PageInfo;
import com.qingniao.core.dao.BrandMapper;
import com.qingniao.core.pojo.Brand;
import com.qingniao.core.pojo.BrandExample;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service("brandService")
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandMapper brandMapper;
	@Autowired
	JedisPool jedisPool;

	@Override
	public void insertBrand(Brand brand) { 
		brandMapper.insertBrand(brand);
		//把数据缓存到redis中；
		Jedis jedis = jedisPool.getResource();
		//保存id
		jedis.hset("brand"+brand.getId(),"id",brand.getId().toString());
		//保存name
		jedis.hset("brand"+brand.getId(),"name",brand.getName());
		jedis.close();
	

	}

	@Override
	public PageInfo selectByExample(BrandExample brandExample) {
		//使用分页查询
		PageInfo pageInfo = new PageInfo(brandExample.getPageNo(),brandExample.getPageSize(),brandMapper.selectCount(brandExample));
		brandExample.setPageNo(pageInfo.getPageNo());//矫正当前页；
		List<Brand> brands = brandMapper.selectByExample(brandExample);	
		pageInfo.setList(brands);
		return pageInfo;
	}
	
	public void batchDelect(Long[] ids){
		Jedis jedis = jedisPool.getResource();
		for (Long id : ids) {
			jedis.del("brand"+id);
		}
		jedis.close();
		brandMapper.batchDelect(ids);
	}
	public void updateBrand(Brand brand){
		brandMapper.updateBrand(brand);
		Jedis jedis = jedisPool.getResource();
		
		jedis.hset("brand"+brand.getId(), "name", brand.getName());
		jedis.close();
	}
	public Brand selectBrand (Integer id){
		return brandMapper.selectBrand(id);
	}

	@Override
	public List<Brand> selectAllByExample(BrandExample brandExample) {
		List<Brand> selectAllByExample = brandMapper.selectAllByExample(brandExample);
		return selectAllByExample;
	}

}
