package com.qingniao.core.service;

import java.util.List;

import com.qingniao.common.page.PageInfo;
import com.qingniao.core.pojo.Brand;
import com.qingniao.core.pojo.BrandExample;

public interface BrandService {
	public void insertBrand(Brand brand);
	 public  PageInfo selectByExample(BrandExample brandExample);
	 public void batchDelect(Long[] ids);
	 public void updateBrand(Brand brand);
	 public Brand selectBrand (Integer id);
	 public  List<Brand> selectAllByExample(BrandExample brandExample);

}
