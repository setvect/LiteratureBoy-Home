<%@page import="com.setvect.literatureboy.web.board.BoardArticleController"%>
<%@page import="com.setvect.common.jsp.URLParameter"%>
<%@page import="com.setvect.literatureboy.web.MobileController"%>
<%@page import="com.setvect.literatureboy.web.MobileController.MobilePageStatus"%>
<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@include file="/common/taglib.inc.jsp"%>
<%
	MobilePageStatus pageStatus = (MobilePageStatus)request.getAttribute(MobileController.AttributeKey.STATUS.name());
	URLParameter urlParam = pageStatus.getUrlParam();
	urlParam.put("mode", BoardArticleController.Mode.LIST_FORM.name());
%>
<style type="text/css">
	.ui-content{padding: 0px;}
</style>
<script type="text/javascript" src="/app/board/board_article.js"></script>
<div>
	<p class="title">${ARTICLE.title}</p>
	<div style="text-align: center">
		<c:forEach var="file" items="${ARTICLE.attach}">
			<c:if test="${file.image}"> 
				<img src="/servlet/Thumbnail?i=${file.url}&w=290&h=450" alt="${file.originalName}" class="list_image" onclick=""/><br/>
			</c:if>
		</c:forEach>
	</div>
	${ARTICLE.content}<br/>
	<p class="date"><fmt:formatDate value="${ARTICLE.regDate}" pattern="yyyy년MM월dd일"/></p>
	<br/>
</div>
<div style="text-align: center; clear: both;">
<a href="<%=urlParam.getParam()%>" data-role="button" data-inline="true" data-transition="fade"
	href="page1" data-icon="back" data-iconpos="left">목록</a>
</div>	

