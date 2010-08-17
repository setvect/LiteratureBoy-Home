<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.service.board.BoardManagerSearch"%>
<%@page import="com.setvect.literatureboy.vo.board.Board"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.board.BoardArticleController"%>
<%@page import="com.setvect.common.util.StringUtilAd"%>
<%@page import="com.setvect.literatureboy.service.board.BoardService"%>
<%@include file="/common/taglib.inc.jsp"%>
<jsp:include page="/common/script.inc.jsp"/>
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
			<input type="text" name="searchWord" value="${PAGE_SEARCH.word}">
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
	<display:table name="LIST.list" class="table" id="article" requestURI="${controller_url}" export="false" partialList="true" size="${LIST.total}" pagesize="${LIST.pagesize}"  style="margin-top:10px;">
    <display:column property="articleSeq" title="No." />
 		<display:column title="Title">
 			<span style="padding-left: ${(article.depthLevel -1) * 10}"><a href="javascript:BoardArticle.readForm(${article.articleSeq})">${article.title}</a></span>
 		</display:column>
		<display:column property="name" title="Name"/>
		<display:column property="hitCount" title="Hit Count"/>
		<display:column title="Date">
			<fmt:formatDate value="${article.regDate}" pattern="yyyy-MM-dd HH:mm"/>
		</display:column>
	</display:table>
</div>
<div>
	<input type="button" value="등록" onclick="BoardArticle.createFrom();">
</div>
<jsp:include page="board_article_form.inc.jsp"></jsp:include>