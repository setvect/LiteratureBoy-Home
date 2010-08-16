<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.web.board.BoardArticleController"%>
<%@page import="com.setvect.literatureboy.service.board.BoardArticleSearch"%>
<%@page import="com.setvect.literatureboy.service.board.BoardManagerSearch"%>
<%@page import="com.setvect.literatureboy.vo.board.Board"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.board.BoardManagerController"%>
<%@page import="com.setvect.common.util.StringUtilAd"%>
<%@page import="com.setvect.literatureboy.service.board.BoardService"%>
<%@include file="/common/taglib.inc.jsp"%>
<jsp:include page="/common/script.inc.jsp"/>
<%
	BoardArticleSearch searchVo = (BoardArticleSearch)request.getAttribute(BoardArticleController.AttributeKey.PAGE_SEARCH.name()); 
%>
<script type="text/javascript" src="/app/board/board_article.js"></script>
<div>
	<div>
		<form:form name="searchForm" method="get" action="${controller_url}">
			<input type="hidden" name="mode" value="<%=BoardManagerController.Mode.SEARCH_FORM%>"/>
			Search: 
			<select name="searchType">
				<option value="title" <%=StringUtilAd.isEmpty(searchVo.getSearchName()) ? "" : "selected='selected'"%>>name</option>
				<option value="content" <%=StringUtilAd.isEmpty(searchVo.getSearchName()) ? "" : "selected='selected'"%>>name</option>
				<option value="name" <%=StringUtilAd.isEmpty(searchVo.getSearchName()) ? "" : "selected='selected'"%>>name</option>
			</select>
			<input type="text" name="searchWord" value="<%=StringUtilAd.toForm(searchVo.getWord())%>">
			<input type="button" value="Search" onclick="BoardManager.searchForm()">
			<c:if test="${! empty PAGE_SEARCH.word}">
				<input type="button" value="Search Stop"  onclick="BoardManager.searchStopForm()">
			</c:if>
		</form:form>
	</div>
	<div>
		Total: ${pageList.total},  Page: ${pageList.currentPage }/${pageList.maxPage}
	</div>
</div>
<div>
	<display:table name="pageList.list" class="table" id="boardList" requestURI="${controller_url}" export="false" partialList="true" size="${pageList.total}" pagesize="${pageList.pagesize}"  style="margin-top:10px;">
    <display:column title="No." >
      ${pageList.rowNumDesc - boardList_rowNum + 1}
    </display:column>
		<display:column property="boardCode" href="javascript:BoardManager.readForm('${boardList.boardCode}')" title="Code"/>
		<display:column property="name" title="Name"/>
		<display:column title="Update">
			<input type="button" value="수정" onclick="BoardManager.updateFrom('${boardList.boardCode}')">
		</display:column>
		<display:column title="Delete">
			<input type="button" value="삭제" onclick="BoardManager.deleteAction('${boardList.boardCode}')">
		</display:column>
	</display:table>
</div>
<div>
	<input type="button" value="생성" onclick="BoardManager.createFrom();">
</div>
<jsp:include page="board_article_form.inc.jsp"></jsp:include>