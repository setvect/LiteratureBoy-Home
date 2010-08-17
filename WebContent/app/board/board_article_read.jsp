<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.vo.board.Board"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.board.BoardManagerController"%>
<%@page import="com.setvect.common.util.StringUtilAd"%>
<%@page import="com.setvect.literatureboy.service.board.BoardService"%>
<%@include file="/common/taglib.inc.jsp"%>
<jsp:include page="/common/script.inc.jsp"/>
<script type="text/javascript" src="/app/board/board_article.js"></script>
<div>
	<table>
		<tr>
			<th>제목</th>
			<td>${ARTICLE.title}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${ARTICLE.name}</td>
		</tr>
		<tr>
			<th>날짜</th>
			<td><fmt:formatDate value="${ARTICLE.regDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr>
			<th>본문</th>
			<td>${ARTICLE.content}</td>
		</tr>

	</table>
</div>
<div>
	<input type="button" value="수정" onclick="BoardArticle.updateFrom('${ARTICLE.articleSeq}')">
	<input type="button" value="삭제" onclick="BoardArticle.deleteAction('${ARTICLE.articleSeq}')">
	<input type="button" value="목록" onclick="BoardArticle.listForm()">
</div>
<jsp:include page="board_article_form.inc.jsp"></jsp:include>