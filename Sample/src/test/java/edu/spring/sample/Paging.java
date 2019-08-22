package edu.spring.sample;

public class Paging {
	public static void main(String[] args) {
		int page = 3;
		//===============================
		//한 페이지에 보여줄 게시물
		//===============================
		int 한페이지에보여주는개수 = 10;
		int endRow = page*한페이지에보여주는개수; //끝번호
		int startRow = endRow - (한페이지에보여주는개수-1); // 시작번호
		System.out.println(startRow);
		System.out.println(endRow);
	}

}
