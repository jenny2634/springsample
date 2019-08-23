package edu.spring.sample.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import edu.spring.sample.service.HomeService;
import edu.spring.sample.service.WriteService;

@Controller
public class HomeController {
	@Autowired
	HomeService homeService;
	@Autowired
	WriteService writeService;

	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") int id) {
		int result = writeService.delete(id);
		return "" + result;
	}

	// 1 - 2
	// 양식화면 - 실제 데이터 처리
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editNum(@PathVariable("id") int id, Model model) {
		Map<String, Object> map = writeService.findById(id);
		model.addAttribute("map", map);
		return "edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editNumPost(@PathVariable("id") int id, Model model,
			@RequestParam Map<String, Object> map) {
		// TODO todo TODO
		// request param으로 id값을 넘겨받는게 없기 때문에
		map.put("id", id);
		int result = writeService.update(map);
		return map; // json응답
					// 스프링 외부조립기에 mvc:annotation-driven
					// message-converter
		// return "redirect:/show/" + id;
		// return result + "";
		// return String.valueOf(result);
	}

	// pathVariable
	@RequestMapping(value = "/show/{id}")
	public String showNum(@PathVariable("id") int id, Model model) {
		Map<String, Object> map = writeService.findById(id);
		model.addAttribute("map", map);
		return "view";
	}

	@RequestMapping(value = "/show")
	public String show(Model model, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "search", defaultValue = "") String search) {
		if (page < 1) {
			page = 1;
		}

		int endRow = page * 10;
		int startRow = endRow - 9;
		model.addAttribute("list", writeService.findAll(startRow, search));

		int totalCount = writeService.getTotalCount();
		model.addAttribute("totalCount", totalCount);
		// 1번
		int totalPage = (totalCount - 1) / 10 + 1;
		// 2번
//		if(totalCount % 10 == 0) {
//			totalPage -= 1;
//		}
		model.addAttribute("totalPage", totalPage);

		int startPage = page / 10 * 10 + 1;
		int endPage = startPage + 9;
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		return "show";// show.jsp
	}

	@RequestMapping(value = "/download2", method = RequestMethod.GET)
	public void download2(HttpServletResponse response) {
		response.setHeader("Content-Disposition", "attachment; filename=\"html.jpg\";charset=\"UTF-8\"");

		InputStream in;
		try {
			in = new FileInputStream("/dev/html.jpg");
			OutputStream out = response.getOutputStream();
			FileCopyUtils.copy(in, out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String download() {
		// <= filedownloadview의 id(bean name viewresolver 동작)
		return "download";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	@ResponseBody
	public String writePost(@RequestParam Map<String, String> map, MultipartHttpServletRequest mReq) {

		String path = this.getClass().getClassLoader().getResource("").getPath();
		// System.out.println(path);
		path = path.substring(0, path.indexOf("WEB-INF"));

		File dir = new File(path + "/resources");
		// 디렉토리가 없다면 생성
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}

		MultipartFile mFile1 = mReq.getFile("file1");
		MultipartFile mFile2 = mReq.getFile("file2");
		String file1 = mFile1.getOriginalFilename();
		String file2 = mFile2.getOriginalFilename();
		// file명 => xxxx.jpg
		// => xxxx.jpg.23409823908209(x)
		// => xxxx_23409823908209.jpg (o)
		long unixTime = 0;
		String fileName = "";
		String fileExt = "";
		if (!file1.equals("")) {
			unixTime = System.currentTimeMillis();
			fileName = file1.substring(0, file1.indexOf("."));
			fileExt = file1.substring(file1.indexOf("."));

			file1 = fileName + "_" + unixTime + fileExt;

			try {
				// 1.NAS 2.프로젝트내부
				// 보안(프라이버시) 쉬운접근성
				mFile1.transferTo(new File(path + "/resources/" + file1));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// 1970년 1월 1일 00시~ 현재까지 흘러온 밀리초
		if (!file2.equals("")) {

			unixTime = System.currentTimeMillis();
			fileName = file2.substring(0, file2.indexOf("."));
			fileExt = file2.substring(file2.indexOf("."));

			file2 = fileName + "_" + unixTime + fileExt;

			try {
				// 1.NAS 2.프로젝트내부
				// 보안(프라이버시) 쉬운접근성
				mFile2.transferTo(new File(path + "/resources/" + file2));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		map.put("file1", file1);
		map.put("file2", file2);

		writeService.insert(map);
		return "write";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String uploadPost(MultipartHttpServletRequest mReq) {
		// c:/dev/...../.../Sample/.../WEB-INF/classes/.../HomeController
		String path = this.getClass().getClassLoader().getResource("").getPath();
		// System.out.println(path);
		path = path.substring(0, path.indexOf("WEB-INF"));
		System.out.println(path);
		MultipartFile mFile = mReq.getFile("file");
		String oName = mFile.getOriginalFilename();
		long size = mFile.getSize();
		// 저장
		try {
			mFile.transferTo(new File(path + "/resources/" + oName));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MultipartFile mFile2 = mReq.getFile("file2");
		String oName2 = mFile2.getOriginalFilename();
		long size2 = mFile2.getSize();
		// 저장
		try {
			mFile2.transferTo(new File(path + "/resources/" + oName2));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<MultipartFile> files = mReq.getFiles("file3");
		for (int i = 0; i < files.size(); i++) {
			MultipartFile f = files.get(i);
			String n = f.getOriginalFilename();
			try {
				f.transferTo(new File(path + "/resources/" + n));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return oName + "//" + size;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload() {
		// c:/dev/...../.../Sample/.../WEB-INF/classes/.../HomeController
		String path = this.getClass().getClassLoader().getResource("").getPath();
		// System.out.println(path);
		path = path.substring(0, path.indexOf("WEB-INF"));
		System.out.println(path);
		return "upload";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest req) {
		String sql = "select * from member";
		List<Map<String, Object>> list = homeService.getMemberList();
		// request.setAttribute("list",list);
		model.addAttribute("list", list);
		return "home";
	}

}
