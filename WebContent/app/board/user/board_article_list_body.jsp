<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.setvect.literatureboy.web.board.BoardArticleController"%>
<%@include file="/common/taglib.inc.jsp"%>
<link rel="stylesheet" type="text/css" href="/common/css/jquery-ui-1.7.3.custom.css" />
<script type="text/javascript" src="/app/board/board_article.js"></script>

<script type="text/javascript">
	$(function(){
		// Dialog			
		$('#dialog').dialog({
			autoOpen: false,
			width: 600,
			buttons: {
				"닫음": function() { 
					$(this).dialog("close"); 
				} 
			}
		});
	});
</script>

<h2>${BOARD.name}</h2>
<display:table name="LIST.list" class="list_table" id="articleList" requestURI="${SERVLET_URL}" export="false" partialList="true" size="${LIST.totalCount}" pagesize="${LIST.pagesize}" >
	<display:column title="Title" class="body_list_td align_left">
		<div>
			<h3>
				${articleList.title}
			</h3>
		</div><br/>
		<c:forEach var="file" items="${articleList.attach}">
			<img src="/servlet/Thumbnail?i=${file.url}&w=290&h=450" alt="${file.originalName}" class="list_image" onclick=""/>
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

<div id="dialog" title="이미지보기">
	<img alt="" src=""/>
</div>
