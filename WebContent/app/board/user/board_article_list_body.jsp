<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.setvect.literatureboy.web.board.BoardArticleController"%>
<%@include file="/common/taglib.inc.jsp"%>
<script type="text/javascript" src="/app/board/board_article.js"></script>
<h2>${BOARD.name}</h2>
<display:table name="LIST.list" class="list_table" id="articleList" requestURI="${SERVLET_URL}" export="false" partialList="true" size="${LIST.total}" pagesize="${LIST.pagesize}" >
	<display:column title="Title" class="body_list_td align_left">
		<div>
			<h3>
				${articleList.title}
			</h3>
			</div><br/>
		<c:forEach var="file" items="${articleList.attach}">
			<img src="/servlet/Thumbnail?i=${file.url}&w=290&h=450" alt="${file.originalName}" class="list_image"/>
		</c:forEach>
		${articleList.content}<br/>
		<span class="tail">
			(<fmt:formatDate value="${articleList.regDate}" pattern="yyyy-MM-dd"/>)
		</span>
	</display:column>
</display:table>
<jsp:include page="../board_article_form.inc.jsp"></jsp:include>
<script type="text/javascript">
	$(".list_table thead").css("display", "none");
</script>