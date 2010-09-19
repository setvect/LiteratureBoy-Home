<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@include file="/common/taglib.inc.jsp"%>

<script type="text/javascript" src="/app/board/board_article.js"></script>
<div>
	<form:form commandName="PAGE_SEARCH" name="encodeForm" method="post" action="${SERVLET_URL}">
		Encode String: <input type="text" name="encode"/>
		<input type="hidden" name="mode" value="${MODE}"/> 
		<form:hidden path="searchCode"/>
		<form:hidden path="searchName"/>
		<form:hidden path="searchTitle"/>
		<form:hidden path="searchContent"/>
		<form:hidden path="currentPage"/>
		<input type="hidden" name="articleSeq" value="${ARTICLE.articleSeq}"/>
		<input type="button" value="확인" onclick="BoardArticle.encodeForm()"/>
	</form:form>
	
</div>
<jsp:include page="board_article_form.inc.jsp"></jsp:include>