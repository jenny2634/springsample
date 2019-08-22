package edu.spring.sample.view;


import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDownloadView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition",
				 "attachment; filename=\"html.jpg\";charset=\"UTF-8\"");
		
		InputStream in = new FileInputStream("/dev/html.jpg");
		OutputStream out = response.getOutputStream();
		FileCopyUtils.copy(in,out);
		while(true) {
			int data = in.read();
			if(data == -1) break;
			out.write(data);
		}
		
	}
	

	
	

}
