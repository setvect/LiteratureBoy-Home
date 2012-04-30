<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@include file="/common/taglib.inc.jsp"%>

<div data-role="content">
	<ul data-role="listview" data-divider-theme="e" data-inset="false">
		<c:forEach var="board" items="${BOARD_ITEMS}">
			<li data-theme="c"><a href="/m/board_list.do?searchCode=${board.boardCode}" data-transition="slide">${board.name}</a></li>
		</c:forEach>
	</ul>
</div>
