<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.setvect.literatureboy.web.board.BoardArticleController"%>

<%@include file="/common/taglib.inc.jsp"%>

<script type="text/javascript" src="/app/board/board_article.js"></script>
<div>
	<table>
		<tr>
			<th>Title</th>
			<td>${ARTICLE.title}</td>
		</tr>
		<tr>
			<th>Name</th>
			<td>${ARTICLE.name}</td>
		</tr>
		<tr>
			<th>Date</th>
			<td><fmt:formatDate value="${ARTICLE.regDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr>
			<th>Content</th>
			<td>${ARTICLE.content}</td>
		</tr>
		<c:if test="${fn:length(ARTICLE.attach) > 0}" >
			<tr>
				<th>Attach</th>
				<td>
					<ul>
						<c:forEach var="file" items="${ARTICLE.attach}">
							<li>File: <a href="/download.do?s=${file.savePathEncode}&amp;d=${file.originalNameEncode}">${file.originalName}</a></li>
						</c:forEach>
					</ul>			
				</td>
			</tr>
		</c:if>
	</table>
</div>

<div>
	<input type="button" value="수정" onclick="BoardArticle.updateForm('${ARTICLE.articleSeq}')">
	<input type="button" value="삭제" onclick="BoardArticle.removeAction('${ARTICLE.articleSeq}')">
	<input type="button" value="목록" onclick="BoardArticle.listForm()">
</div>

<div>
	<form:form commandName="PAGE_SEARCH" name="commentCreateAction" method="get" action="${SERVLET_URL}">
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
						<textarea name="content"></textarea>
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

<form:form commandName="PAGE_SEARCH" name="commentRemoveAction" method="get" action="${SERVLET_URL}">
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