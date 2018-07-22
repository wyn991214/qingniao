package com.qingniao.core.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingniao.common.page.PageInfo;
import com.qingniao.core.dao.product.ImgMapper;
import com.qingniao.core.dao.product.ProductMapper;
import com.qingniao.core.dao.product.SkuMapper;
import com.qingniao.core.pojo.product.Img;
import com.qingniao.core.pojo.product.ImgCriteria;
import com.qingniao.core.pojo.product.Product;
import com.qingniao.core.pojo.product.ProductCriteria;
import com.qingniao.core.pojo.product.Sku;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductMapper productMapper;
	@Autowired
	ImgMapper imgMapper;
	@Autowired
	SkuMapper skuMapper;
	@Autowired
	SolrServer solrServer;

	@Override
	public void insertSelectiveByProduct(Product product) {
		productMapper.insertSelective(product);
	}

	@Override
	public PageInfo selectByExample(ProductCriteria productCriteria) {
		PageInfo info = new PageInfo(productCriteria.getPageNo(), productCriteria.getPageSize(),
				productMapper.countByExample(productCriteria));
		// 使用pageInfo 的pageNo来矫正productCriteria的pageNo
		productCriteria.setPageNo(info.getPageNo());
		List<Product> selectByExample = productMapper.selectByExample(productCriteria);
		for (Product product : selectByExample) {
			ImgCriteria imgCriteria = new ImgCriteria();
			imgCriteria.createCriteria().andProductIdEqualTo(product.getId()).andIsDefEqualTo(false);
			List<Img> imgs = imgMapper.selectByExample(imgCriteria);
			product.setImg(imgs.get(0));
		}
		info.setList(selectByExample);
		return info;
	}

	// 商品上架
	@Override
	public void onSale(Long[] ids) {
		for (Long id : ids) {
			Product product = new Product();
			product.setId(id);
			product.setIsShow(true);// 设置商品为上架
			productMapper.updateByPrimaryKeySelective(product);
			Product p = productMapper.selectByPrimaryKey(id);

			// 保存商品数据到solr服务器
			SolrInputDocument solrInputDocument = new SolrInputDocument();
			// id
			solrInputDocument.setField("id", id);
			// name
			solrInputDocument.setField("name_ik", p.getName());
			// url
			ImgCriteria imgCriteria = new ImgCriteria();
			imgCriteria.createCriteria().andProductIdEqualTo(id).andIsDefEqualTo(false);
			List<Img> imgs = imgMapper.selectByExample(imgCriteria);
			solrInputDocument.setField("url", imgs.get(0).getUrl());
			// price
			float price = skuMapper.selectPriceByProductId(id);
			solrInputDocument.setField("price", price);
			// 设置brandId
			solrInputDocument.setField("brandId", p.getBrandId());

			try {
				solrServer.add(solrInputDocument);
				solrServer.commit();

			} catch (SolrServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// 商品下架
	@Override
	public void sellOut(Long[] ids) {
		for (Long id : ids) {
			Product product = new Product();
			product.setId(id);
			product.setIsShow(false);// 设置商品为上架
			productMapper.updateByPrimaryKeySelective(product);

		}
	}

}
