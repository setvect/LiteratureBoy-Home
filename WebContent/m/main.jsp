<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@include file="/common/taglib.inc.jsp"%>

<div data-role="content">
	<ul data-role="listview" data-divider-theme="e" data-inset="false">
		<c:forEach var="board" items="${BOARD_ITEMS}">
			<li data-theme="c"><a href="/m/bd.do?searchCode=${board.boardCode}">${board.name}</a></li>
		</c:forEach>
		<li data-theme="c"><a href="/m/emailget.do">문학소년 이메일 알기</a></li>
	</ul>
</div>