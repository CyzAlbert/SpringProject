package com.taotao.sso.controller.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.taotao.sso.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/applicationContext-dao.xml",
		"classpath:spring/applicationContext-jedis.xml",
		"classpath:spring/applicationContext-service.xml",
		"classpath:spring/applicationContext-trans.xml",
		"classpath:spring/springmvc.xml"})
public class UserControllerTest {

	@Test
	private void testUsetController(){
		
	}
	
}
