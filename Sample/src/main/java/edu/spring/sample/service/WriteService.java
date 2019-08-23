package edu.spring.sample.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.sample.dao.WriteDao;

@Service
public class WriteService {
	@Autowired
	WriteDao writeDao;
	
	public int delete(int id) {
		return writeDao.delete(id);
	}
	
	public int update(Map<String,Object> map) {
		return writeDao.update(map);
	}
	
	public Map<String, Object> findById(int id) {
		Map<String, Object> map = writeDao.findById(id);
		return map;
	}
	
	public int getTotalCount() {
		return writeDao.getTotalCount();
	}
	
	public List<Map<String, Object>> findAll(int startRow, String search) {
		return writeDao.findAll(startRow, search);
	}
	public void insert(Map<String, String> map) {
		writeDao.save(map);
	}
}
