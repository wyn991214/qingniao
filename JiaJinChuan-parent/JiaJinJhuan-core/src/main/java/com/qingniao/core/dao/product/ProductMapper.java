package com.qingniao.core.dao.product;

import com.qingniao.core.pojo.product.Product;
import com.qingniao.core.pojo.product.ProductCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    int countByExample(ProductCriteria example);

    int deleteByExample(ProductCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductCriteria example);

    Product selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductCriteria example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductCriteria example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}