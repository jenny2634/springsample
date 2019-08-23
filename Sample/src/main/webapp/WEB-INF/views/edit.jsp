<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form method="post">
	번호 : ${map.ID}<br> 
	제목 : <input type="text" name ="title" value="${map.TITLE}"><br> 
	파일1 : ${map.FILE1}<br>
	<img src="../resources/${map.FILE1}"><br> 
	파일2 : ${map.FILE2}<br>
	<img src="../resources/${map.FILE2}"><br>
	<input type="submit" value="수정완료">

</form>