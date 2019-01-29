package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;

@Service
public class CartServiceImpl implements CartService {
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${ITEM_INFOURL}")
	private String ITEM_INFOURL;

	@Override
	public TaotaoResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		CartItem cartItem=null;
		List<CartItem> itemList=getCartItemList(request);
		for(CartItem item:itemList) {
			if (item.getId()==itemId) {
				item.setNum(item.getNum()+num);
				cartItem=item;
				break;
			}
		}
		if(cartItem==null) {
			cartItem=new CartItem();
			String json=HttpClientUtil.doGet(REST_BASE_URL+ITEM_INFOURL+itemId);
			TaotaoResult result=TaotaoResult.formatToPojo(json,TbItem.class);
			if(result.getStatus()==200) {
				TbItem item =(TbItem) result.getData();
				cartItem.setId(item.getId());
				cartItem.setTitle(item.getTitle());
				cartItem.setImage(item.getImage() == null ? "":item.getImage().split(",")[0]);
				cartItem.setNum(num);
				cartItem.setPrice(item.getPrice());
			}
			itemList.add(cartItem);
		}
		
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList),true);
		
		return TaotaoResult.ok();
	}
	
	
	private List<CartItem> getCartItemList(HttpServletRequest request){
		
		String CartJson=CookieUtils.getCookieValue(request,"TT_CART",true);
		if(CartJson==null) {
			return new ArrayList<>();
		}
		try {
			List<CartItem> list=JsonUtils.jsonToList(CartJson,CartItem.class);
			return list;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ArrayList<>();
		
	}


	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<CartItem> itemList = getCartItemList(request);
		return itemList;
	}


	@Override
	public TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<CartItem> itemList = getCartItemList(request);
		//从列表中找到此商品
		for (CartItem cartItem : itemList) {
			if (cartItem.getId() == itemId) {
				itemList.remove(cartItem);
				break;
			}
				 
		}
		//把购物车列表重新写入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
		
		return TaotaoResult.ok();
	}

}
