package com.qingniao.console.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qingniao.common.page.PageInfo;
import com.qingniao.core.pojo.Brand;
import com.qingniao.core.pojo.BrandExample;

import com.qingniao.core.pojo.product.Color;
import com.qingniao.core.pojo.product.ColorCriteria;
import com.qingniao.core.pojo.product.Feature;
import com.qingniao.core.pojo.product.FeatureCriteria;
import com.qingniao.core.pojo.product.Img;
import com.qingniao.core.pojo.product.Product;
import com.qingniao.core.pojo.product.ProductCriteria;
import com.qingniao.core.pojo.product.ProductCriteria.Criteria;
import com.qingniao.core.pojo.product.Sku;
import com.qingniao.core.pojo.product.Type;
import com.qingniao.core.pojo.product.TypeCriteria;
import com.qingniao.core.service.BrandService;
import com.qingniao.core.service.ColorService;
import com.qingniao.core.service.FeatureService;
import com.qingniao.core.service.ImgService;
import com.qingniao.core.service.ProductServiceImpl;
import com.qingniao.core.service.SkuService;
import com.qingniao.core.service.TypeService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.qingniao.core.service.ProductService;

@Controller
public class ProductController {
	/**
	 * 商品管理
	 * 
	 * @return
	 */
	@Autowired
	TypeService typeService;
	@Autowired
	BrandService brandService;
	@Autowired
	FeatureService  featureService;
	@Autowired
	ColorService colorService;
	@Autowired
	ProductService  productService;
	@Autowired
	SkuService skuService;
	@Autowired
	ImgService imgService;
	@Autowired
	JedisPool  jedisPool;

	@RequestMapping("/product/list.do")
	public String list(String name,Long brandId,Boolean isShow,Integer pageNo,Model model) {
		//加载品牌数据
		BrandExample brandExample = new BrandExample();
		brandExample.setStatus(1);
		List<Brand> brands = brandService.selectAllByExample(brandExample);
		model.addAttribute("brands",brands);
		
		
	ProductCriteria productCriteria = new ProductCriteria();
		Criteria createCriteria = productCriteria.createCriteria();
		
		//制作分页工具
				StringBuilder stringBuilder= new StringBuilder();
		
		if(pageNo !=null){
			productCriteria.setPageNo(pageNo);
			model.addAttribute("pageNo",pageNo);
		}else {
			productCriteria.setPageNo(1);
		}
		
		//判断上下架状态
		if(isShow != null){
			createCriteria.andIsShowEqualTo(isShow);
			stringBuilder.append("isShow="+isShow);
			model.addAttribute("isShow",isShow);
		}else{
			createCriteria.andIsShowEqualTo(false);//默认查询下架的商品
			stringBuilder.append("isShow=false");
		}
		//判断名称like模糊查询
		if(name !=null && name.trim().length()!=0){
			createCriteria.andNameLike("%"+name+"%");
			stringBuilder.append("&name="+name);
			model.addAttribute("name",name);
		}
		//判断通过品牌id查询
		if(brandId !=null){
			createCriteria.andBrandIdEqualTo(brandId);
			stringBuilder.append("&brandId="+brandId);
			model.addAttribute("brandId",brandId);
		}
		
		//查询
		PageInfo pageInfo = productService.selectByExample(productCriteria);
		//制作分页
		String url = "/product/list.do";
		pageInfo.pageView(url, stringBuilder.toString());
		model.addAttribute("pageInfo",pageInfo);
		
		
		return "product/list";
		
	
	}

	/**
	 * 去添加页面 初始化数据
	 * 
	 * @return
	 */
	@RequestMapping("/product/add.do")
	public String add(Model model) {
		// 商品类型
		TypeCriteria typeCriteria = new TypeCriteria();
		typeCriteria.createCriteria().andParentIdNotEqualTo(0l);
		List<Type> types = typeService.selectTypeByTypeCriteria(typeCriteria);
		model.addAttribute("types",types);
		// 商品品牌
		BrandExample brandExample = new BrandExample();
		brandExample.setStatus(1);
		 List<Brand> brands = brandService.selectAllByExample(brandExample);
		model.addAttribute("brands",brands);
		// 商品材质
		FeatureCriteria featureCriteria = new FeatureCriteria();
		featureCriteria.createCriteria().andIsDelEqualTo(true);
		List<Feature> features = featureService.selectFeatureByFeatureCriteria(featureCriteria);
		model.addAttribute("features",features);
		// 商品颜色
		ColorCriteria colorCriteria = new ColorCriteria();
		colorCriteria.createCriteria().andParentIdNotEqualTo(0l);
		List<Color> colors = colorService.selectByExampleByColorCriteria(colorCriteria);
		model.addAttribute("colors",colors);
		
		return "product/add";
	}
	@RequestMapping("/product/save.do")
	public String save (Product product ){
		//保存商品数据
		Jedis jedis = jedisPool.getResource();
		Long productId = jedis.incr("productId");
			
		product.setId(productId);
		product.setIsShow(false);  //默认下架
		product.setIsDel(false);  //默认没删除
		product.setCreateTime(new Date());
		productService.insertSelectiveByProduct(product); //插入数据并返回id
	
		
		//保存图片数据
		Img  img= product.getImg();
		img.setIsDef(false);
		img.setProductId(productId);
		imgService.insertSelective(img);
		
		
		
		String colors = product.getColors();
		String sizes = product.getSizes();
		for(String color :colors.split(",")){
			Sku sku = new Sku();
			sku.setColorId(Long.parseLong(color));//颜色id
			sku.setProductId(productId);//商品id
			sku.setCreateTime(new Date());//创建时间
			for(String size :sizes.split(",")){
				sku.setSize(size);//尺码
				//运费
				sku.setDeliveFee(10f);
				//市场价格
				sku.setMarketPrice(100f);
				//售价
				sku.setPrice(260f);
				//库存
				sku.setStock(100);
				//购买限制
				sku.setUpperLimit(100);
				skuService.insertSelective(sku);
			}
		}
		
		
		return "redirect:/product/list.do";
		

		
		
		}
	

}
