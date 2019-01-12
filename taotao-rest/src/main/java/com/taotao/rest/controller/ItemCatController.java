package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import com.taotao.utils.JsonUtils;

@Controller
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	/*@RequestMapping(value="/itemcat/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		CatResult result=itemCatService.getItemCatList();
		
		String json=JsonUtils.objectToJson(result);
		String retunRes=callback+"("+json+");";
		return retunRes;
	}*/
	
	@RequestMapping(value="/itemcat/all",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public Object getItemCatList(String callback) {
		CatResult result=itemCatService.getItemCatList();
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(result);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
	
}
