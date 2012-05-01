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
<script type="text/javascript" src="/app/board/board_article.js"></script>
<script type="text/javascript">
	$(function() {
	  $("form").attr("data-ajax", "false");
	});
</script>

<div>
	<span class="title">${ARTICLE.title}</span>
	${ARTICLE.content}
	<span class="date"><fmt:formatDate value="${ARTICLE.regDate}" pattern="yyyy년MM월dd일"/></span>
</div>
<a href="<%=urlParam.getParam()%>" data-role="button" data-inline="true" data-transition="fade"
	href="page1" data-icon="back" data-iconpos="left">목록</a>

