<%@page import="com.setvect.literatureboy.web.board.BoardArticleController"%>
<%@page import="com.setvect.common.jsp.URLParameter"%>
<%@page import="com.setvect.literatureboy.web.MobileController"%>
<%@page import="com.setvect.literatureboy.web.MobileController.MobilePageStatus"%>
<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@include file="/common/taglib.inc.jsp"%>
<%
	MobilePageStatus pageStatus = (MobilePageStatus)request.getAttribute(MobileController.AttributeKey.STATUS.name());
	URLParameter urlParam = pageStatus.getUrlParam();
	urlParam.put("mode", BoardArticleController.Mode.READ_FORM.name()).clearParam("articleSeq").clearParam("encode");
%>
<script type="text/javascript" src="/app/board/board_article.js"></script>

<display:table name="LIST.list" class="list_table" id="articleList" requestURI="${SERVLET_URL}" export="false" partialList="true" size="${LIST.totalCount}" pagesize="${LIST.pagesize}">
	<display:column title="Title" class="align_left">
		<span style="padding-left: ${(articleList.depthLevel -1) * 10}">
			<c:if test="${STATUS.menu == 'ALL'}">
				<div style="width: 40px; float: left;">${BOARD_MAP[articleList.boardCode].name}</div>
			</c:if>   
			<a href="<%=urlParam.getParam()%>&amp;articleSeq=${articleList.articleSeq}">${articleList.title}</a>
			<c:if test="${articleList.encodeF}">
				<span class="se">(비공개)</span>
			</c:if>
		</span>
	</display:column>
	<display:column title="Date" class="align_right">
		<fmt:formatDate value="${articleList.regDate}" pattern="yyyy-MM-dd"/>
	</display:column>
</display:table>
 <script type="text/javascript">
	$(".list_table thead").css("display", "none");
</script>