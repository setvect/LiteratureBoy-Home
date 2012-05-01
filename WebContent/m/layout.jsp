<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Literature Boy</title>
<jsp:include page="/m/common/script.inc.jsp"/>
<link rel="stylesheet" href="my.css" />
<style></style>
<!--
	${INCLUDE_PAGE} 
 -->
</head>
<body>
	<div data-role="page" id="page1">
		<div data-theme="a" data-role="header">
			<h3>${STATUS.title}</h3>
			<div data-role="navbar" data-iconpos="top">
				<ul>
					<li><a href="#page1" class="${STATUS.menu == 'MAIN' ? 'ui-btn-active' : ''}">메뉴들..</a></li>
					<li><a href="#page1" class="${STATUS.menu == 'ALL' ? 'ui-btn-active' : ''}">전체 글 모아 보기</a></li>
				</ul>
			</div>			
		</div>
		<!-- 내용 시작 -->
		<jsp:include page="${INCLUDE_PAGE}"></jsp:include>
		<!-- 내용 종료 -->
		<div data-theme="a" data-role="footer" style="text-align: center; margin-top: 20px;">
			<a href="/literatureboy/main.do?pc=true" data-role="button" data-inline="true" data-transition="none" href="page1" data-icon="star" data-iconpos="left">PC 화면</a>
		</div>		
	</div>
</body>
</html> 