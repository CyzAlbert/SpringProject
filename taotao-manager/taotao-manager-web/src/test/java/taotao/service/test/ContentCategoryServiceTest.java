package taotao.service.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.taotao.service.ContentCategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-dao.xml",
		"classpath:spring/applicationContext-service.xml",
		"classpath:spring/applicationContext-trans.xml",
		"classpath:spring/springmvc.xml"})
public class ContentCategoryServiceTest {
	@Autowired
	private ContentCategoryService service;
	
	@Test
	public void testService() {
		service.deleteContentCategory(88,98);
	}
}
