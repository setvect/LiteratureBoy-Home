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
</head>
<body>
<div class="popup">
	<c:if test="${empty EMAIL}">
		음~ 뭔가 잘 못 입력 하셨군요.
	</c:if>
	<c:if test="${!empty EMAIL}">
		정호 이메일은 <a href="mailto:${EMAIL}">${EMAIL}</a> 이네요.	
	</c:if>
</div>
</body>
</html>