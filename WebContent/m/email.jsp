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
			<h3>이메일 알기</h3>
			<a data-role="button" data-inline="true" data-transition="fade" href="page1" data-icon="home" data-iconpos="left">홈</a>
			<div data-role="navbar" data-iconpos="top">
				<ul>
					<li><a href="#page1" data-theme="" data-icon=""	class="ui-btn-active">메뉴들..</a></li>
					<li><a href="#page1" data-theme="" data-icon="">전체 글 보기</a></li>
				</ul>
			</div>			
		</div>
		<div data-role="content">
			<div>
				입력값: <span style="font-weight: bold; color:red">1234</span> <br>(요즘 하도 스팸이 많아서리...)
			</div>
			<form action="">
				<div data-role="fieldcontain">
					<fieldset data-role="controlgroup">
						<label for="textinput1">번호 입력</label> 
						<input id="textinput1" placeholder="" value="" type="text" />
					</fieldset>
				</div>
				<input type="submit" value="이메일 주소 알기" />
			</form>
		</div>
	</div>
</body>
</html>