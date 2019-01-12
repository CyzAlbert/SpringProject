package com.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.service.PictureService;
import com.taotao.utils.JsonUtils;

@Controller
public class PictureController {
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping(value="/pic/upload")
	@ResponseBody
	public String pictureUpload(MultipartFile uploadFile){	
		try {
			Map result;
			result= pictureService.uploadFile(uploadFile);
			//java Object转JSON 格式字符串
			String json=JsonUtils.objectToJson(result);
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
