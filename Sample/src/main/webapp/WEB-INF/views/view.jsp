<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
번호 : ${map.ID}<br>
제목 : ${map.TITLE}<br>
파일1 : ${map.FILE1}<br>
<img src="../resources/${map.FILE1}"><br>
파일2 : ${map.FILE2}<br>
<img src="../resources/${map.FILE2}"><br>

<button type="button" onclick="moveEdit()">수정</button>
<button type="button" onclick="moveDelete()">삭제</button>
<script>

// 	localhost:8080/sample.show.329
//							!
//				/sample
	function moveEdit(){
		location = "../edit/${map.ID}";
		//location = "/sample/edit/${map.ID}";
	}
	
	function moveDelete(){
		location = "../delete/${map.ID}";
		//location = "/sample/edit/${map.ID}";
	}
</script>