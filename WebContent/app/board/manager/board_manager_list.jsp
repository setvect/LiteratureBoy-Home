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
<jsp:include page="/common/script.inc.jsp"/>
<script type="text/javascript" src="/app/board/manager/board_manager.js"></script>
<div>
	<div>
		<form:form name="searchForm" method="get" action="${controller_url}">
			<input type="hidden" name="mode" value="<%=BoardManagerController.Mode.SEARCH_FORM%>"/>
			Search: 
			<select name="searchType">
				<option value="code" ${empty PAGE_SEARCH.searchCode ? "" : "selected='selected'"}>code</option>
				<option value="name" ${empty PAGE_SEARCH.searchName ? "" : "selected='selected'"}>name</option>
			</select>
			<input type="text" name="searchWord" value="${PAGE_SEARCH.word}">
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
<jsp:include page="board_manager_form.inc.jsp"></jsp:include>