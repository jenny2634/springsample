package edu.spring.sample.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//component-scan의 스캔대상
@Repository
public class WriteDao {

	@Autowired
	JdbcTemplate jt;
	
	@Autowired
	SqlSessionTemplate ss;
	
	public int delete(int id) {
		int result = ss.delete("write.delete", id);
		return result;
	}
	
	public int update(Map<String,Object> map) {
		int result = ss.update("write.update",map);
		return result;
	}
	
	//tdd(test driven development) - 테스트 주도 개발 방법(방식)
	public Map<String, Object> findById(int id) {
		//			parameterType="_int"
		Map<String, Object> map = ss.selectOne("write.select",id);
		return map;	
	}
	
	public int getTotalCount(){
		return ss.selectOne("write.totalCount");
	}
	
	//shift + ctrl + r : 파일 (리소스) 찾기
	public List<Map<String, Object>> findAll(int startRow, String search) {
		//마이바티스는 여러개의 자료를 넘겨받지 못함
		//반드시 하나의 자료형으로만 넘겨줘야 됨
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startRow", startRow -1);
		map.put("search", search);
		List<Map<String, Object>> list = ss.selectList("write.selectAll", map);
		return list;
	}
	public void save(Map<String, String> map) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO `WRITE` (ID,TITLE,FILE1,FILE2)");
		sql.append(" VALUES (NULL,?,?,?)");
		//insert
		jt.update(sql.toString(), map.get("title"), map.get("file1"), map.get("file2"));
	}
}
