package com.itheima.test.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrTest {
	@Test
	public void testAddAndUpdateIndex() throws Exception{
		// 1.创建HttpSolrServer对象，用于连接solr服务
		HttpSolrServer server = new HttpSolrServer("http://127.0.0.1:8081/solr");
		// 2.创建文档对象（SolrInuptDocument）
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "9527");
		document.addField("name","solr123 solrj mybatis springmvc");
		// 3.使用HttpSolrServer对象，执行添加（或者更新）
		server.add(document);
		// 4.提交
		server.commit();
	}
	
	@Test
	public void deleteIndexById() throws Exception{
		//创建HttpSolrServer对象
		HttpSolrServer server = new HttpSolrServer("http://127.0.0.1:8081/solr");
		//使用server对象删除
		server.deleteById("9527");
		//提交
		server.commit();
		
	}
	
	/**
	 * 查询索引
	 */
	@Test
	public void queryIndex() throws Exception{
		//创建HttpSolrServer对象
		HttpSolrServer server = new HttpSolrServer("http://127.0.0.1:8081/solr");
		//创建查询对象
		SolrQuery query = new SolrQuery("*:*");
		//使用sever对象执行查询
		QueryResponse queryResponse = server.query(query);
		//使用queryResponse对象，获取查询的结果集SolrDocumentList
		SolrDocumentList results = queryResponse.getResults();
		//遍历结果集
		System.out.println("结果数量："+results.getNumFound());
		for (SolrDocument doc : results) {
			System.out.println("-----------------------------------");
			//打印id域和name域
			System.out.println("id:"+doc.get("id"));
			System.out.println("name:"+doc.get("name"));
		}
		
	}
}
