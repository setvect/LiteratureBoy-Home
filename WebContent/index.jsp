<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.board.BoardManagerController"%>
<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
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
</head>
<body>
<div class="warp">
	<div class="header">
		menu1
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