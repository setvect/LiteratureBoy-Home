<%@page import="com.setvect.literatureboy.service.board.BoardArticleSearch"%>
<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.service.board.BoardManagerSearch"%>
<%@page import="com.setvect.literatureboy.web.board.BoardArticleController.AttributeKey"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.setvect.literatureboy.vo.board.Board"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.board.BoardArticleController"%>
<%@page import="com.setvect.common.util.StringUtilAd"%>
<%@page import="com.setvect.literatureboy.service.board.BoardService"%>
<%@include file="/common/taglib.inc.jsp"%>

<script type="text/javascript" src="/app/board/board_article.js"></script>
<div>
	<div>
		<form:form name="searchForm" method="get" action="${controller_url}">
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
	<display:table name="LIST.list" class="list_table" id="articleList" requestURI="${controller_url}" export="false" partialList="true" size="${LIST.total}" pagesize="${LIST.pagesize}"  style="margin-top:10px;">
    <display:column property="articleSeq" title="No." />
 		<display:column title="Title" class="left">
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