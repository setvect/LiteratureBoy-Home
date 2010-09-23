<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@include file="/common/taglib.inc.jsp"%>
<div>
	<c:forEach var="file" items="${MAIN_ARTICLE.attach}">
		<img src="${file.url}" alt="${file.originalName}" class="list_image"/>
	</c:forEach>
	${MAIN_ARTICLE.content}
</div>
<br/>
<a href="/literatureboy/bd.do?searchCode=${MAIN_ARTICLE.boardCode}">이전 메인화면</a>