<%@page import="com.setvect.literatureboy.web.CommonUtil"%>
<%@page import="com.setvect.literatureboy.vo.user.User"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.board.BoardManagerController"%>
<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@include file="/common/taglib.inc.jsp"%>
<%
	String incPage = (String) request
			.getAttribute(ConstraintWeb.INCLUDE_PAGE);

	if(incPage==null){
		out.println("nothing...");
		return;
	}
%>

<!-- <%=incPage%> -->
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
<div class="warp">
	<div class="header">
		<c:if test="${_user_session_key != null}">
			로그인 아이디:	${ _user_session_key.userId}
			<a href="/user/logout.do">로그아웃</a>
		</c:if>
	</div>
	<div class="container" >
		<div class="left">
			menu2a
						
		</div>
		<div class="content">
			<jsp:include page="<%=incPage%>"></jsp:include>
		</div>
	</div>
</div> 
</body>
</html>