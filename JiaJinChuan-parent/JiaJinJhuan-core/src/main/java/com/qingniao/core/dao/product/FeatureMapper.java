package com.qingniao.core.dao.product;

import com.qingniao.core.pojo.product.Feature;
import com.qingniao.core.pojo.product.FeatureCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FeatureMapper {
    int countByExample(FeatureCriteria example);

    int deleteByExample(FeatureCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Feature record);

    int insertSelective(Feature record);

    List<Feature> selectByExample(FeatureCriteria example);

    Feature selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Feature record, @Param("example") FeatureCriteria example);

    int updateByExample(@Param("record") Feature record, @Param("example") FeatureCriteria example);

    int updateByPrimaryKeySelective(Feature record);

    int updateByPrimaryKey(Feature record);
}