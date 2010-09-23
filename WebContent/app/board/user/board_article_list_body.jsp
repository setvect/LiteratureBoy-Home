<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.setvect.literatureboy.web.board.BoardArticleController"%>
<%@include file="/common/taglib.inc.jsp"%>
<script type="text/javascript" src="/app/board/board_article.js"></script>
<div>
	<display:table name="LIST.list" class="list_table" id="articleList" requestURI="${SERVLET_URL}" export="false" partialList="true" size="${LIST.total}" pagesize="${LIST.pagesize}" >
 		<display:column title="Title" class="body_list_td align_left">
			<div>
				<span class="title">
					${articleList.title}
				</span>
	 			(<fmt:formatDate value="${articleList.regDate}" pattern="yyyy-MM-dd"/>)
 			</div><br/>
			<c:forEach var="file" items="${articleList.attach}">
				<img src="${file.url}" alt="${file.originalName}" width="290" class="list_image"/>
			</c:forEach>
			${articleList.content}
 		</display:column>
	</display:table>
</div>
<jsp:include page="../board_article_form.inc.jsp"></jsp:include>
<script type="text/javascript">
	$(".list_table thead").css("display", "none");
</script>