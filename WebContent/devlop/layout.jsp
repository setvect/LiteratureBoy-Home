<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@include file="/common/taglib.inc.jsp"%>
<!--${INCLUDE_PAGE}-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>Devlop</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/common/css/main.css"/>
<jsp:include page="/common/script.inc.jsp"/>
</head>
<body>
<div class="warp">
	<c:if test="${_user_session_key != null}">
		<div class="header">
			로그인 아이디:	${ _user_session_key.userId}
			<a href="/user/logout.do">로그아웃</a>
		</div>
	</c:if>
	<div class="container" >
		<div class="left">
			<div class="left_area">
				<div class="main_image"><a href="/"><img src="/upload/image_reg/main_08.jpg" alt="야구장에서..."/></a></div>
				<ul>
					<li><a href="/literatureboy/bd.do?searchCode=BDBBAA01">지식</a></li>
				</ul>
				<hr/>
			</div>
		</div>
		<div class="content">
			<jsp:include page="${INCLUDE_PAGE}"></jsp:include>
		</div>
	</div>
</div> 
</body>
</html>