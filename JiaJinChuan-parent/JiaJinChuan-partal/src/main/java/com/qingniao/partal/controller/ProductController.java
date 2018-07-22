package com.qingniao.partal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qingniao.common.page.PageInfo;
import com.qingniao.core.pojo.Brand;
import com.qingniao.core.pojo.product.Img;
import com.qingniao.core.pojo.product.Product;
import com.qingniao.core.pojo.product.ProductCriteria;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Controller
public class ProductController {

	@Autowired
	SolrServer solrServer;
	@Autowired
	JedisPool jedisPool;

	@RequestMapping("/index.html")
	public String indext(Model model, String pname, Integer pageNo,Long brandId,String price) throws SolrServerException {
		//从缓存中加载品牌数据
		
		Jedis jedis = jedisPool.getResource();
		List<Brand> brands = new ArrayList<>();
		 Set<String> keys =jedis.keys("brand*");
		for (String key : keys) {
			Brand b = new Brand();
			b.setId(Long.parseLong(jedis.hget(key, "id")));
			b.setName(jedis.hget(key, "name"));
			brands.add(b);
		}
		model.addAttribute("brands",brands);
		
		ProductCriteria productCriteria = new ProductCriteria();
		productCriteria.setPageSize(4);

		// 制作分页工具栏
		StringBuilder stringBuilder = new StringBuilder();
		SolrQuery solrQuery = new SolrQuery();
		// 判断当前
		if (pageNo != null) {
			productCriteria.setPageNo(pageNo);
			model.addAttribute("pageNo", pageNo);
		} else {
			productCriteria.setPageNo(1);
		}

		if (pname != null && pname.trim().length() > 0) {
			// 通过名字查询
			solrQuery.set("q", "name_ik:" + pname);
			//高亮显示搜索关键字
			//开启高亮
			solrQuery.setHighlight(true);
			//需要高亮的字段
			solrQuery.addHighlightField("name_ik");
			//设置高亮前缀
			solrQuery.setHighlightSimplePre("<span style='color:red'>");
			//设置高亮后缀
			solrQuery.setHighlightSimplePost("</span>");
			
			
			model.addAttribute("pname", pname);
			stringBuilder.append("pname=" + pname);
		} else {
			// 查询所有
			solrQuery.set("q", "*:*");
		}
		
		//定义标记控制已选条件的显示
		Boolean faly = false;
		//定义存放已标记的map集合
		Map condition = new HashMap();
		
		//判断品牌
		if(brandId!=null){
			solrQuery.addFilterQuery("brandId:"+brandId);
			model.addAttribute("brandId",brandId);
			stringBuilder.append("&brandId=" + brandId);
			
			
			condition.put("品牌", jedis.hget("brand"+brandId,"name"));
			//改变标记
			faly = true;
		}
		//判断价格
		if(price != null){
			//价格区间
			String[] p = price.split("-");
			if(p.length == 2){
				Float start = new Float(p[0]);
				Float end = new Float(p[1]);
				solrQuery.addFilterQuery("price:[" +start+ " TO " +end+ "]");
				condition.put("价格", price);
			
			}else {
				Float start = new Float(6000);
				Float end = new Float(100000000f);
				solrQuery.addFilterQuery("price:[" +start+ " TO " +end+ "]");
				condition.put("价格", "6000以上");
			
			}
			model.addAttribute("price",price);
			stringBuilder.append("&price=" + price);
			
			//改变标记
			faly = true;
		}
		model.addAttribute("faly",faly);
		model.addAttribute("condition", condition);
		

		// 从solr服务中查询出商品信息
		solrQuery.setStart(productCriteria.getStartRow());// 起始页
		solrQuery.setRows(productCriteria.getPageSize());// 每页显示多少
		solrQuery.addSort("price",ORDER.asc);

		QueryResponse query = solrServer.query(solrQuery);
		SolrDocumentList docs = query.getResults();
		ArrayList<Product> products = new ArrayList<Product>();
		// 循环便利取出所有数据放到List集合中
		for (SolrDocument doc : docs) {
			Product product = new Product();
			// id
			String id = (String) doc.get("id");
			product.setId(Long.parseLong(id));
	
			 //判断 name
			if (pname != null && pname.trim().length() > 0) {
				//获取高亮数据
				Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();
				Map<String, List<String>> map = highlighting.get(id);
				List<String> list = map.get("name_ik");
				String name = list.get(0);
				product.setName(name);
			}else{
				String name = (String) doc.get("name_ik");
				product.setName(name);
			}
			// url
			String url = (String) doc.get("url");
			Img img = new Img();
			img.setUrl(url);
			product.setImg(img);
			// brandId
			Integer bid = (Integer) doc.get("brandId");
			product.setBrandId(Long.parseLong(bid.toString()));
			// price
			float p = (float) doc.get("price");
			product.setPrice(p);
			products.add(product);

		}
		PageInfo pageInfo = new PageInfo(productCriteria.getPageNo(), productCriteria.getPageSize(),
				(int) docs.getNumFound());
		pageInfo.setList(products);
		String url = "/index.html";
		pageInfo.pageView(url, stringBuilder.toString());
		model.addAttribute("pageInfo", pageInfo);
		return "product/product";
	}

}
