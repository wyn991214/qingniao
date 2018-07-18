package com.qingniao.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qingniao.core.pojo.Brand;
import com.qingniao.core.pojo.BrandExample;

/**
 * 品牌dao
 * 
 * @author Wang.YN
 *
 */

public interface BrandMapper {
	//添加
	public void insertBrand(Brand Brand);
	//根据条件查询结果
	public List<Brand> selectByExample(BrandExample brandExample);
	//根据条件查询满足条件个数
	public Integer selectCount(BrandExample brandExample);
	//删除
	public void batchDelect(Long[] ids);
	//修改
	public void updateBrand(Brand brand);
	//单个brand
	public Brand selectBrand (Integer id);
	//根据条件查询---不使用分页
	public List<Brand> selectAllByExample(BrandExample brandExample);
}
