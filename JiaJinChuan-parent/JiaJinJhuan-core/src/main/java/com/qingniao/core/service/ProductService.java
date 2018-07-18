package com.qingniao.core.service;

import com.qingniao.common.page.PageInfo;
import com.qingniao.core.pojo.product.Product;
import com.qingniao.core.pojo.product.ProductCriteria;

public interface ProductService {
	public  void insertSelectiveByProduct(Product record);

	public PageInfo selectByExample(ProductCriteria productCriteria);

}
