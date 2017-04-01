<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="chooseRoom.action" method="post">
		价格：<input name="price" type="text"><br>
		朝向：<input name="direction" type="text"><br>
		入住：<input name="roomStatus" type="text"><br>
		类型：<input name="RoomType" type="text"><br>
		<input value="提交" type="submit">
	</form>
</body>
</html>