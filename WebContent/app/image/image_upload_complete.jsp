<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@include file="/common/taglib.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>Literature Boy</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/common/css/main.css"/>
<jsp:include page="/common/script.inc.jsp"/>
<script type="text/javascript">
	var ImageUpload = new Object();
	ImageUpload.apply = function(){
		var sHTML = "<img src='${IMAGE_URL}'/>";


		// 하드 코딩..원래는 이미지 팝업창 오픈 할때 이미지를 받을 객체의 파라미터를 넣어야 됨.
		opener.window.oEditors.getById["content"].exec("PASTE_HTML", [sHTML]);
		window.close();
	};
</script>
</head>
<body>
<div>
	<input type="button" value="확인" onclick="ImageUpload.apply()"/>
	<img src='${IMAGE_URL}' onclick="ImageUpload.apply()"/>
</div>
</body>
</html>