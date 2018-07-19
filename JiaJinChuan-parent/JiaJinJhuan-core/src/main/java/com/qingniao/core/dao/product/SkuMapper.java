package com.qingniao.core.dao.product;

import com.qingniao.core.pojo.product.Sku;
import com.qingniao.core.pojo.product.SkuCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SkuMapper {
	int countByExample(SkuCriteria example);

	int deleteByExample(SkuCriteria example);

	int deleteByPrimaryKey(Long id);

	int insert(Sku record);

	int insertSelective(Sku record);

	List<Sku> selectByExample(SkuCriteria example);

	Sku selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") Sku record, @Param("example") SkuCriteria example);

	int updateByExample(@Param("record") Sku record, @Param("example") SkuCriteria example);

	int updateByPrimaryKeySelective(Sku record);

	int updateByPrimaryKey(Sku record);

	float selectPriceByProductId(Long pid);
}