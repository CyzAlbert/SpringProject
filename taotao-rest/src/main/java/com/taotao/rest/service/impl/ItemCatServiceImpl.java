package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public CatResult getItemCatList() {
		// TODO Auto-generated method stub
		CatResult result=new CatResult();
		
		result.setData(getCatList(0));
		
		return result;
	}
	
 	private List<?> getCatList(long parentId){
 		TbItemCatExample example=new TbItemCatExample();
 		Criteria criteria=example.createCriteria();
 		criteria.andParentIdEqualTo(parentId);
 		
 		List<TbItemCat> list=itemCatMapper.selectByExample(example);
		List resultList=new ArrayList<>();
 		
		int count=0;
 		for(TbItemCat item:list) {
 			if(item.getIsParent()) {
	 			CatNode catNode=new CatNode();
	 			if(parentId==0) {
	 				catNode.setName("<a href='/products/"+item.getId()+".html'>"+item.getName()+"</a>");
	 			}
	 			else {
	 				catNode.setName(item.getName());
	 			}
	 			catNode.setUrl("/products/"+item.getId()+".html");
	 			catNode.setItem(getCatList(item.getId()));
	 			resultList.add(catNode);
	 			count++;
	 			if(parentId==0&&count>=14)
	 				break;
 			}
 			else {
 				resultList.add("/products/"+item.getId()+".html|"+item.getName());
 			}
 		}
 		
 		return resultList;
 	}

}
