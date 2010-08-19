<%@page import="com.setvect.literatureboy.vo.board.BoardArticle"%>
<%@page import="com.setvect.literatureboy.web.board.BoardArticleController"%>
<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.service.board.BoardManagerSearch"%>
<%@page import="com.setvect.literatureboy.vo.board.Board"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.board.BoardManagerController"%>
<%@page import="com.setvect.common.util.StringUtilAd"%>
<%@page import="com.setvect.literatureboy.service.board.BoardService"%>
<%@include file="/common/taglib.inc.jsp"%>
<%
	BoardArticle board = (BoardArticle) request.getAttribute(BoardArticleController.AttributeKey.ARTICLE.name());
	if(board == null){
		board = new BoardArticle();
	}
	request.setAttribute("createForm", board);
%>
<jsp:include page="/common/script.inc.jsp"/>
<script type="text/javascript" src="/app/board/board_article.js"></script>
<script type="text/javascript" src="/app/webedit/fckeditor.js"></script>
<script type="text/javascript">
  window.onload = function()
  {
    var oFCKeditor = new FCKeditor( 'content' ) ;
    oFCKeditor.ToolbarSet = 'Default';
    oFCKeditor.BasePath = "/app/webedit/" ;
    oFCKeditor.ReplaceTextarea() ;
  }
</script>
<div>
	<form:form commandName="createForm" name="createAction" id="createAction" method="post" enctype="multipart/form-data" action="${controller_url}">
		<input type="hidden" name="mode" value="${MODE}"/>
		<input type="hidden" name="searchCode" value="${PAGE_SEARCH.searchCode}"/>
		<input type="hidden" name="searchName" value="${PAGE_SEARCH.searchName}"/>
		<input type="hidden" name="searchTitle" value="${PAGE_SEARCH.searchTitle}"/>
		<input type="hidden" name="searchContent" value="${PAGE_SEARCH.searchContent}"/>
		<input type="hidden" name="currentPage" value="${PAGE_SEARCH.currentPage}"/>
			
		<input type="hidden" name="boardCode" value="${PAGE_SEARCH.searchCode}"/>	
		<form:hidden path="articleSeq"/>	
		<table>
			<tr>
				<th>Title</th>
				<td><form:input id="title" path="title" size="50" maxlength="50"/></td>
			</tr>
			<tr>
				<th>Name</th>
				<td><form:input id="name" path="name" size="10" maxlength="10"/></td>
			</tr>
			<tr>
				<th>Email</th>
				<td><form:input id="email" path="email" size="30" maxlength="50"/></td>
			</tr>
			<tr>
				<th>Content</th>
				<td><form:textarea id="content" path="content"/></td>
			</tr>
			<tr>
				<th>Attach</th>
				<td>
					<span>이전 첨부파일 삭제</span>
					<ul>
						<c:forEach var="file" items="${ATTACH}">
							<li><input type="checkbox" name="deleteAttach" value="${file.fileSeq}"/> ${file.originalName} </li>
						</c:forEach>
					</ul>
					<ul>
         		<c:set var="attachIndex" value="<%=new int[] {1,2,3}%>" />
						<c:forEach var="id" items="${attachIndex}">
							<li>Attach #${id}: <input type="file" name="attachFile"/></li>
						</c:forEach>
					</ul>
				</td>
			</tr>			
		</table>
	</form:form>
</div>
<div>
	<input type="button" value="확인" onclick="BoardArticle.createOrUpdate()">
	<input type="button" value="취소" onclick="history.back();">
</div>
<jsp:include page="board_article_form.inc.jsp"></jsp:include>