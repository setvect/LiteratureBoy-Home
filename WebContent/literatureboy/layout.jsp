<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@include file="/common/taglib.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>Literature Boy</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/common/css/style.css" media="screen" />
<jsp:include page="/common/script.inc.jsp"/>
</head>
<body>

<div id="wrap">
	<div id="header">
		<h1><a href="/literatureboy">Literature Boy</a></h1>
		<h2>글이란 옛 기억을 여는 열쇠</h2>
	</div>
	<div id="top"> </div>
	<div id="content_area">
		<div class="left"> 
			<jsp:include page="${INCLUDE_PAGE}"></jsp:include>
		</div>
		<div class="right"> 
			<div class="main_image"><a href="/"><img src="/upload/image_reg/main_08.jpg" alt="야구장에서..."/></a></div>
			<c:if test="${_user_session_key != null}">
				${ _user_session_key.userId}
				<a href="/user/logout.do">로그아웃</a>
			</c:if>		
			<ul> 
				<li><a href="/literatureboy/bd.do?searchCode=BDAAAA01">글</a></li>
				<li><a href="/literatureboy/bd.do?searchCode=BDAAAA09">꿈</a></li>
				<li><a href="/literatureboy/bd.do?searchCode=BDAAAA02">책</a></li>
				<li><a href="/literatureboy/bd.do?searchCode=BDAAAA03">음악</a></li>
				<li><a href="/literatureboy/bd.do?searchCode=BDAAAA04">영화</a></li>
				<li><a href="/literatureboy/bd.do?searchCode=BDAAAA05">사진</a></li>
				<li><a href="/literatureboy/bd.do?searchCode=BDAAAA08">잡담</a></li>
				<li><a href="/literatureboy/bd.do?searchCode=BDAAAA06">기억</a></li>
				<li><a href="/literatureboy/bd.do?searchCode=BDAAAA07">인연</a></li>
				<li><a href="/literatureboy/bd.do?searchCode=BDAAAA11">소설</a></li>
				<li><a href="/literatureboy/bd.do?searchCode=BDAAAA10">기술사</a></li>
				<li><a href="#" onclick="$.POPUP.popupWindowCenter('/emailget.do', 'emailInput', 400, 250, false, false, false)">email</a></li>			
			</ul>
			<c:if test="${fn:length(BOARD_ITEMS) > 0}" >
				<h2>Manager</h2>
				<ul>
					<c:forEach var="board" items="${BOARD_ITEMS}">
						<li><a href="/literatureboy/bdm.do?searchCode=${board.boardCode}">${board.name}</a></li>		
					</c:forEach>
					<li><a href="/board/manager.do">게시판</a></li>		
				</ul>
			</c:if>
		</div>
		<div style="clear: both;"> </div>
	</div>
	<div id="bottom"> </div>
	<div id="footer">
		
	</div>
</div>
</body>
</html>