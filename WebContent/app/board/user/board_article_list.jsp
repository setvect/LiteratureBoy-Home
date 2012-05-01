<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.setvect.literatureboy.web.board.BoardArticleController"%>
<%@include file="/common/taglib.inc.jsp"%>
<script type="text/javascript" src="/app/board/board_article.js"></script>
<h2>${BOARD.name}</h2>
<display:table name="LIST.list" class="list_table" id="articleList" requestURI="${SERVLET_URL}" export="false" partialList="true" size="${LIST.totalCount}" pagesize="${LIST.pagesize}">
	<display:column title="Title" class="align_left">
		<span style="padding-left: ${(articleList.depthLevel -1) * 10}">
			<a href="javascript:BoardArticle.readForm(${articleList.articleSeq})">${articleList.title}</a>
			<c:if test="${articleList.encodeF}">
				<span class="se">(비공개)</span>
			</c:if>
		</span>
	</display:column>
	<display:column title="Date" class="align_right">
		<fmt:formatDate value="${articleList.regDate}" pattern="yyyy-MM-dd"/>
	</display:column>
</display:table>
<jsp:include page="../board_article_form.inc.jsp"></jsp:include>
<script type="text/javascript">
	$(".list_table thead").css("display", "none");
</script>