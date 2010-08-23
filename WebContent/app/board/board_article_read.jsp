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
		<tr>
			<th>첨부파일</th>
			<td>
				<ul>
					<c:forEach var="file" items="${ATTACH}">
						<li>Attach: <a href="/download.do?s=${file.savePathEncode}&amp;d=${file.originalNameEncode}">${file.originalName}</a></li>
					</c:forEach>
				</ul>			
			</td>
		</tr>
	</table>
</div>
<div>
	<form:form commandName="PAGE_SEARCH" name="commentCreateAction" method="get" action="${controller_url}">
		<input type="hidden" name="mode" value="<%=BoardArticleController.Mode.COMMENT_CREATE_ACTION%>"/> 
		<form:hidden path="searchCode"/>
		<form:hidden path="searchName"/>
		<form:hidden path="searchTitle"/>
		<form:hidden path="searchContent"/>
		<form:hidden path="currentPage"/>
		<input type="hidden" name="articleSeq" value="${ARTICLE.articleSeq}"/>
		<div>
			<table>
				<tr>
					<th>이름</th>
					<td><input type="text" name="name" maxlength="10"></td>
					<th>비밀번호</th>
					<td><input type="password" name="passwd" maxlength="10"></td>
				</tr>
				<tr>
					<td colspan="4">
						<textarea rows="" cols="" name="content"></textarea>
					</td>					
				</tr>
			</table>
		</div>	
		<div>
			<input type="button" value="확인" onclick="BoardArticle.commentCreateAction()">
		</div>
	</form:form>
	<div>
		<c:forEach var="comment" items="${COMMENT}">
			<table>
				<tr>
					<td>
						${comment.name} - <fmt:formatDate value="${comment.regDate}" pattern="yyyy-MM-dd HH:mm:ss"/>	
						<input type="button" value="삭제" onclick="BoardArticle.commentRemoveAction('${comment.commentSeq}')">
					</td>
				</tr>
				<tr>
					<td>${comment.content}</td>
				</tr>
			</table>
		</c:forEach>
	</div>
</div>
<div>
	<input type="button" value="수정" onclick="BoardArticle.updateForm('${ARTICLE.articleSeq}')">
	<input type="button" value="삭제" onclick="BoardArticle.removeAction('${ARTICLE.articleSeq}')">
	<input type="button" value="목록" onclick="BoardArticle.listForm()">
</div>

<form:form commandName="PAGE_SEARCH" name="commentRemoveAction" method="get" action="${controller_url}">
	<input type="hidden" name="mode" value="<%=BoardArticleController.Mode.COMMENT_REMOVE_ACTION%>"/> 
	<form:hidden path="searchCode"/>
	<form:hidden path="searchName"/>
	<form:hidden path="searchTitle"/>
	<form:hidden path="searchContent"/>
	<form:hidden path="currentPage"/>
	<input type="hidden" name="articleSeq" value="${ARTICLE.articleSeq}"/>
	<input type="hidden" name="commentSeq"/>
</form:form>

<jsp:include page="board_article_form.inc.jsp"></jsp:include>