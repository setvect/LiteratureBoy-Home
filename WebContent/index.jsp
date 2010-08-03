<%@page import="com.setvect.literatureboy.web.board.BoardManagerController"%>
<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>Literature Boy</title>
</head>
<body>
<div class="warp">
	<div class="header">
		menu1
	</div>
	<div class="container" >
		<div class="left">
			menu2
		</div>
		<div class="content">
			content1
			<%=request.getAttribute(BoardManagerController.SendKey.BOARD_LIST.name()) %>
		</div>
	</div>
</div>
</body>
</html>