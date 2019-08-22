package edu.spring.sample.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao2 {
	@Autowired
	SqlSessionTemplate ss;
	
	public void selectMember() {
		List<Map<String, Object>> map = ss.selectList("member.selectAll", null);
		System.out.println(map);
	}
	
	public void selectById() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id","a");
		ss.selectList("member.selectById",map);
	}

}
