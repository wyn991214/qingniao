package com;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qingniao.core.dao.product.SkuMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-*.xml" })
public class solrTest {
	// solr添加
	@Test
	public void cs1() throws SolrServerException, IOException {
		// 创建HttpSolrServer
		HttpSolrServer httpSolrServer = new HttpSolrServer("http://192.168.0.1:8080/solr");
		// 创建solrInputDocument对象 封装数据
		SolrInputDocument solrInputDocument = new SolrInputDocument();
		solrInputDocument.setField("id", 2);
		solrInputDocument.setField("name", "张三");
		// 把要封装的数据放到httpSolrServer
		httpSolrServer.add(solrInputDocument);
		httpSolrServer.commit();
	}

	// 查询
	@Test
	public void cs2() throws SolrServerException {
		// 创建HttpSolrServer
		HttpSolrServer httpSolrServer = new HttpSolrServer("http://192.168.0.1:8080/solr");
		// 创建solrInputDocument对象 封装数据
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.set("q", "*:*"); //查询所有
		QueryResponse query = httpSolrServer.query(solrQuery);
		SolrDocumentList results = query.getResults();
		for(SolrDocument doc : results){
			 String a = (String) doc.get("id");
			 String b = (String) doc.get("name");
			 System.out.println(a+"             "+b);
		}
	}
	//删除
	@Autowired
	SolrServer solrServer;
		@Test
		public void cs3() throws Exception {
			//删除数据
			solrServer.deleteById("change.me");
			solrServer.commit();
		}
		
		
		@Autowired
		SkuMapper skuMapper;
		@Test
		public void cs4(){
			float id = skuMapper.selectPriceByProductId(1l);
			System.out.println(id);
		}

}
