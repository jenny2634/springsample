package edu.spring.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.spring.sample.dao.MemberDao2;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

public class MemberDao2Test {
	@Autowired
	MemberDao2 dao;
	
	@Test                                                                                                                                                                               
	public void test() {
		dao.selectMember();
	}
	
	@Test
	public void test2() {
		dao.selectById();
	}

}
