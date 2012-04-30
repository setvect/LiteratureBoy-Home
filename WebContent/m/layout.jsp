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
</head>
<body>
	<div data-role="page" id="page1">
		<div data-theme="a" data-role="header">
			<h3>${TITLE}</h3>
			<div data-role="navbar" data-iconpos="top">
				<ul>
					<li><a href="#page1" class="ui-btn-active">메뉴들..</a></li>
					<li><a href="#page1">전체 글 보기</a></li>
				</ul>
			</div>			
		</div>
		<!-- 내용 시작 -->
		<jsp:include page="${INCLUDE_PAGE}"></jsp:include>
		<!-- 내용 종료 -->
	</div>
</body>
</html>