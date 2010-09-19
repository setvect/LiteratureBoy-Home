<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.setvect.literatureboy.web.board.BoardArticleController"%>
<%@include file="/common/taglib.inc.jsp"%>
<script type="text/javascript" src="/app/board/board_article.js"></script>
<style type="text/css">
	.list_table thead{display: none};
</style>
<div>
	<display:table name="LIST.list" class="list_table" id="articleList" requestURI="${SERVLET_URL}" export="false" partialList="true" size="${LIST.total}" pagesize="${LIST.pagesize}"  style="margin-top:10px;">
 		<display:column title="Title" class="align_left">
 			<span style="padding-left: ${(articleList.depthLevel -1) * 10}">
 				${articleList.title}
 			</span>
 			-
 			<fmt:formatDate value="${articleList.regDate}" pattern="yyyy-MM-dd"/>
			<c:forEach var="file" items="${articleList.attach}">
				<img src="${file.url}" alt="${file.originalName}" align="left"/>
			</c:forEach>
 			<p>
 				${articleList.content}
 			</p>
		
 			
 		</display:column>
	</display:table>
</div>
<jsp:include page="../board_article_form.inc.jsp"></jsp:include>