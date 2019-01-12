package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;


/*
 * 商品规格参数模板的操作
 * 
 */


@Controller
@RequestMapping(value="/item/param")
public class ItemParamController {
	@Autowired
	private ItemParamService service;
	
	@RequestMapping(value="/query/itemcatid/{itemcatId}")
	@ResponseBody
	public TaotaoResult getItemParamByCid(@PathVariable Long itemcatId) {
		TaotaoResult result=service.getItemParamByCid(itemcatId);
		return result;
	}
	
	/*
	 *添加商品规格参数模板
	 */
	
	@RequestMapping(value="/save/{cid}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable(value="cid") Long cid,String paramData) {
		TbItemParam itemParam=new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		TaotaoResult result=service.inserItemParam(itemParam);
		return result;
	}
}
