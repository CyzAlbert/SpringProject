package com.taotao.search.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchDao;

@Repository
public class SearchDaoImpl implements SearchDao {
	
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public SearchResult search(SolrQuery query) throws Exception {
		// TODO Auto-generated method stub
		SearchResult result=new SearchResult();
		QueryResponse response=solrServer.query(query);
		SolrDocumentList solrDocumentList=response.getResults();
		result.setPageCount(solrDocumentList.getNumFound());
		List<Item> itemList=new ArrayList<>();
		Map<String, Map<String,List<String>>> highLight=response.getHighlighting();
		
		for(SolrDocument document:solrDocumentList) {
			Item item=new Item();
			item.setId((String)document.get("id"));
			List<String> list=highLight.get(document.get("id")).get("item_title");
			String title = "";
			if (list != null && list.size()>0) {
				title = list.get(0);
			} else {
				title = (String) document.get("item_title");
			}
			item.setTitle(title);
			item.setImage((String) document.get("item_image"));
			item.setPrice((long) document.get("item_price"));
			item.setSell_point((String) document.get("item_sell_point"));
			item.setCategory_name((String) document.get("item_category_name"));
			//添加的商品列表
			itemList.add(item);
		}
		result.setItemList(itemList);
		return result;
	}

}
