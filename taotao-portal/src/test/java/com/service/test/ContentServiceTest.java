package com.service.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tatao.portal.service.ContentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-service.xml",
		"classpath:spring/springmvc.xml"})
public class ContentServiceTest {
	
	@Autowired
	private ContentService service;
	
	@Test
	public void testService() {
		String res=service.getContentList();
		System.out.println("+++++"+res);
	}
	
}
