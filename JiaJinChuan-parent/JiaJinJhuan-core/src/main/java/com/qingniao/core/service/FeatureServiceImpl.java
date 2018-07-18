package com.qingniao.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingniao.core.dao.product.FeatureMapper;
import com.qingniao.core.pojo.product.Feature;
import com.qingniao.core.pojo.product.FeatureCriteria;

@Service
@Transactional
public class FeatureServiceImpl implements FeatureService {

	@Autowired
	FeatureMapper featureMapper;
	
	public List<Feature> selectFeatureByFeatureCriteria(FeatureCriteria featureCriteria){
		
		return featureMapper.selectByExample(featureCriteria);
	}
}
