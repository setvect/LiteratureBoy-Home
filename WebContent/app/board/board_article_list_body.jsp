<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.setvect.literatureboy.web.board.BoardArticleController"%>
<%@include file="/common/taglib.inc.jsp"%>
<script type="text/javascript" src="/app/board/board_article.js"></script>
<div>
	<div>
		<form:form id="" name="searchForm" method="get" action="${SERVLET_URL}">
			<input type="hidden" name="mode" value="<%=BoardArticleController.Mode.SEARCH_FORM%>"/>
			<input type="hidden" name="searchCode" value="${PAGE_SEARCH.searchCode}"/>
			Search: 
			<select name="searchType">
				<option value="content" ${empty PAGE_SEARCH.searchContent ? "" : "selected='selected'"}>Content</option>
				<option value="title" ${empty PAGE_SEARCH.searchTitle ? "" : "selected='selected'"}>Title</option>
				<option value="name" ${empty PAGE_SEARCH.searchName ? "" : "selected='selected'"}>Name</option>
			</select>
			<input type="text" name="searchWord" value="<c:out value="${PAGE_SEARCH.word}"/>"/>
			<input type="button" value="Search" onclick="BoardArticle.searchForm()"/>
			<c:if test="${! empty PAGE_SEARCH.word}">
				<input type="button" value="Search Stop"  onclick="BoardArticle.searchStopForm()">
			</c:if>
		</form:form>
	</div>
	<div>
		Total: ${LIST.totalCount},  Page: ${LIST.currentPage }/${LIST.maxPage}
	</div>
</div>
<div>
	<c:if test="${AUTH_WRITE}">
		<input type="button" value="등록" onclick="BoardArticle.createFrom();"/>
	</c:if>
</div>
<div>
	<display:table name="LIST.list" class="list_table" id="articleList" requestURI="${SERVLET_URL}" export="false" partialList="true" size="${LIST.totalCount}" pagesize="${LIST.pagesize}" >
 		<display:column title="Title" class="body_list_td align_left">
			<div>
				<span class="title">
					${articleList.title}
				</span>
	 			(<fmt:formatDate value="${articleList.regDate}" pattern="yyyy-MM-dd"/>)
 			</div><br/>
			<c:forEach var="file" items="${articleList.attach}">
				<img src="/servlet/Thumbnail?i=${file.url}&w=290&h=450" alt="${file.originalName}" class="list_image"/>
			</c:forEach>
			${articleList.content}
			<c:if test="${AUTH_WRITE}">			
				<input type="button" value="수정" onclick="BoardArticle.updateForm('${articleList.articleSeq}')">
				<input type="button" value="삭제" onclick="BoardArticle.removeAction('${articleList.articleSeq}')">
			</c:if>			
 		</display:column>
	</display:table>
</div>
<div>
	<c:if test="${AUTH_WRITE}">
		<input type="button" value="등록" onclick="BoardArticle.createFrom();"/>
	</c:if>
</div>
<script type="text/javascript">
	$(".list_table thead").css("display", "none");
</script>
<jsp:include page="board_article_form.inc.jsp"></jsp:include>