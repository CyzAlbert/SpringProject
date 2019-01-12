package com.taotao.service;

import java.util.List;


import com.taotao.common.pojo.TreeNode;
import com.taotao.common.pojo.EUIDataGrideResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(long itemId);
	EUIDataGrideResult getItemList(int page,int rows);
	List<TreeNode> getCatList(long parentId);
	TaotaoResult createItem(TbItem item,String desc,String itemParamData) throws Exception;
}
