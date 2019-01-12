package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TreeNode;
import com.taotao.service.ItemService;

@Controller
@RequestMapping(value="/item/cat")
public class ItemCatController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value="/list")
	@ResponseBody
	public List<TreeNode> getItemCatList(@RequestParam(value="id",defaultValue="0") long parentId) {
		List<TreeNode> result= itemService.getCatList(parentId);
		return result;
	}
}
