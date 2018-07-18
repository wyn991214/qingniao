package com.qingniao.core.service;

import java.util.List;

import com.qingniao.core.pojo.product.Feature;
import com.qingniao.core.pojo.product.FeatureCriteria;

public interface FeatureService {
	public List<Feature> selectFeatureByFeatureCriteria(FeatureCriteria featureCriteria);

}
