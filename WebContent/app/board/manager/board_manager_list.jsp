<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.board.BoardManagerController"%>
<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%
	String incPage = (String) request
			.getAttribute(ConstraintWeb.INCLUDE_PAGE);
%>
<div>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>코드</th>
				<th>이름</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
		</thead>
	</table>
</div>