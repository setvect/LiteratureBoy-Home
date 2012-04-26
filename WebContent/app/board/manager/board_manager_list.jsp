<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.setvect.literatureboy.web.board.BoardManagerController"%>
<%@include file="/common/taglib.inc.jsp"%>

<script type="text/javascript" src="/app/board/manager/board_manager.js"></script>
<div>
	<div>
		<form:form name="searchForm" method="get" action="${SERVLET_URL}">
			<input type="hidden" name="mode" value="<%=BoardManagerController.Mode.SEARCH_FORM%>"/>
			Search: 
			<select name="searchType">
				<option value="code" ${empty PAGE_SEARCH.searchCode ? "" : "selected='selected'"}>code</option>
				<option value="name" ${empty PAGE_SEARCH.searchName ? "" : "selected='selected'"}>name</option>
			</select>
			<input type="text" name="searchWord" value="<c:out value="${PAGE_SEARCH.word}"/>">
			<input type="button" value="Search" onclick="BoardManager.searchForm()">
			<c:if test="${! empty PAGE_SEARCH.word}">
				<input type="button" value="Search Stop"  onclick="BoardManager.searchStopForm()">
			</c:if>
		</form:form>
	</div>
	<div>
		Total: ${LIST.totalCount},  Page: ${LIST.currentPage }/${LIST.maxPage}
	</div>
</div>
<div>
	<display:table name="LIST.list" class="list_table" id="article" requestURI="${SERVLET_URL}" export="false" partialList="true" size="${LIST.totalCount}" pagesize="${LIST.pagesize}"  style="margin-top:10px;">
    <display:column title="No." >
      ${LIST.rowNumDesc - article_rowNum + 1}
    </display:column>
		<display:column property="boardCode" href="/board/article.do" paramId="searchCode" paramProperty="boardCode"  title="Code"/>
		<display:column title="Name">
			<a href="javascript:BoardManager.readForm('${article.boardCode}')">${article.name}</a>
		</display:column>
		<display:column title="Update">
			<input type="button" value="수정" onclick="BoardManager.updateForm('${article.boardCode}')">
		</display:column>
		<display:column title="Delete">
			<input type="button" value="삭제" onclick="BoardManager.removeAction('${article.boardCode}')">
		</display:column>
	</display:table>
</div>
<div>
	<input type="button" value="생성" onclick="BoardManager.createFrom();">
</div>
<jsp:include page="board_manager_form.inc.jsp"></jsp:include>