package edu.spring.sample;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.spring.sample.dao.WriteDao;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

public class WriteDaoTest {
	@Autowired
	WriteDao dao;
	
	@Test            
	public void insert() {
		Map<String, String> map = new HashMap<String, String>();
		
		for(int i=0;i<150 ; i++) {
			
			map.put("title", "데이터" + i);
			dao.save(map);
		}
	}

}
