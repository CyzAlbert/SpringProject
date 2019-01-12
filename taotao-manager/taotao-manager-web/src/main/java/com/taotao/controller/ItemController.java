package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUIDataGrideResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value="/item/{itemId}",method=RequestMethod.GET)
	@ResponseBody
	public TbItem getTbItemById(@PathVariable Long itemId) {	
		TbItem item=itemService.getItemById(itemId);
		
		return item;	
	}
	
	@RequestMapping(value="/item/list")
	@ResponseBody
	public EUIDataGrideResult getItemList(Integer page,Integer rows) {
		EUIDataGrideResult dataGrideResult=itemService.getItemList(page, rows);
		
		return dataGrideResult;
	}
	
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	private TaotaoResult createItem(TbItem item,String desc,String itemParams) throws Exception{
		TaotaoResult result=itemService.createItem(item,desc,itemParams);
		return result;
	}
}
