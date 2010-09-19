<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.vo.board.Board"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.board.BoardManagerController"%>
<%@page import="com.setvect.common.util.StringUtilAd"%>
<%@page import="com.setvect.literatureboy.service.board.BoardService"%>
<%@page import="com.setvect.literatureboy.web.board.BoardArticleController"%>

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