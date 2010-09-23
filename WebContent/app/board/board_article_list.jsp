<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.setvect.literatureboy.web.board.BoardArticleController"%>
<%@include file="/common/taglib.inc.jsp"%>

<script type="text/javascript" src="/app/board/board_article.js"></script>
<div>
	<div>
		<form:form name="searchForm" method="get" action="${SERVLET_URL}">
			<input type="hidden" name="mode" value="<%=BoardArticleController.Mode.SEARCH_FORM%>"/>
			<input type="hidden" name="searchCode" value="${PAGE_SEARCH.searchCode}"/>
			Search: 
			<select name="searchType">
				<option value="title" ${empty PAGE_SEARCH.searchTitle ? "" : "selected='selected'"}>Title</option>
				<option value="name" ${empty PAGE_SEARCH.searchName ? "" : "selected='selected'"}>Name</option>
				<option value="content" ${empty PAGE_SEARCH.searchContent ? "" : "selected='selected'"}>Content</option>
			</select>
			<input type="text" name="searchWord" value="<c:out value="${PAGE_SEARCH.word}"/>">
			<input type="button" value="Search" onclick="BoardArticle.searchForm()">
			<c:if test="${! empty PAGE_SEARCH.word}">
				<input type="button" value="Search Stop"  onclick="BoardArticle.searchStopForm()">
			</c:if>
		</form:form>
	</div>
	<div>
		Total: ${LIST.total},  Page: ${LIST.currentPage }/${LIST.maxPage}
	</div>
</div>
<div>
	<display:table name="LIST.list" class="list_table" id="articleList" requestURI="${SERVLET_URL}" export="false" partialList="true" size="${LIST.total}" pagesize="${LIST.pagesize}">
    <display:column property="articleSeq" title="No." />
 		<display:column title="Title" class="align_left">
 			<span style="padding-left: ${(articleList.depthLevel -1) * 10}">
 				<a href="javascript:BoardArticle.readForm(${articleList.articleSeq})">${articleList.title}</a>
 				<c:if test="${articleList.encodeF}">
 					(비공개)
 				</c:if>
 			</span>
 		</display:column>
		<display:column property="name" title="Name"/>
		<display:column property="hitCount" title="Hit Count"/>
		<display:column title="Date">
			<fmt:formatDate value="${articleList.regDate}" pattern="yyyy-MM-dd HH:mm"/>
		</display:column>
	</display:table>
</div>
<div>
	<c:if test="${AUTH_WRITE}">
		<input type="button" value="등록" onclick="BoardArticle.createFrom();">
	</c:if>
</div>
<jsp:include page="board_article_form.inc.jsp"></jsp:include>