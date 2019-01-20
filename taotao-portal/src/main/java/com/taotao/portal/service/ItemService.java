package com.taotao.portal.service;

import com.taotao.portal.pojo.Item;

public interface ItemService {
	Item getItemById(Long itemId);
	String getItemDescById(Long itemId);
	String getItemParam(Long itemId);
}
