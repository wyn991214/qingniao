package com.qingniao.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingniao.core.dao.product.TypeMapper;
import com.qingniao.core.pojo.product.Type;
import com.qingniao.core.pojo.product.TypeCriteria;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {

	@Autowired
	TypeMapper typeMapper;

	public List<Type> selectTypeByTypeCriteria(TypeCriteria typeCriteria) {
		return typeMapper.selectByExample(typeCriteria);
	}

}
