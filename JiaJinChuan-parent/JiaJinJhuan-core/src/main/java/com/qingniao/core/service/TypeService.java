package com.qingniao.core.service;

import java.util.List;

import com.qingniao.core.pojo.product.Type;
import com.qingniao.core.pojo.product.TypeCriteria;

public interface TypeService {
	public List<Type> selectTypeByTypeCriteria(TypeCriteria typeCriteria );
}
