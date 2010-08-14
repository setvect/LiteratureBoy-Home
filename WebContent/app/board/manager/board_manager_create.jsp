<%@page import="com.setvect.common.util.PagingCondition"%>
<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.vo.board.Board"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.board.BoardManagerController"%>
<%@page import="com.setvect.common.util.StringUtilAd"%>
<%@page import="com.setvect.literatureboy.service.board.BoardService"%>
<%@include file="/common/taglib.inc.jsp"%>
<%
	BoardManagerController.Mode mode = (BoardManagerController.Mode)request.getAttribute(BoardManagerController.AttributeKey.MODE.name());
	PagingCondition pageingVO = (PagingCondition)request.getAttribute(BoardManagerController.AttributeKey.PAGE_SEARCH.name());
	Board board = (Board) request.getAttribute(BoardManagerController.AttributeKey.BOARD_ITEM.name());
	if(board == null){
		board = new Board();
	}
	request.setAttribute("createForm", board);
%>
<jsp:include page="/common/script.inc.jsp"/>
<script type="text/javascript" src="/app/board/manager/board_manager.js"></script>
<div>
	<form:form commandName="createForm" name="createAction" id="createAction" method="post" action="<%=request.getAttribute(ConstraintWeb.SERVLET_URL).toString() %>">
		<input type="hidden" name="mode" value="${MODE}"/>
		<input type="hidden" name="searchName" value="<%=StringUtilAd.toForm(pageingVO.getConditionString(BoardService.BOARD_SEARCH_ITEM.NAME))%>">
		<input type="hidden" name="searchCode" value="<%=StringUtilAd.toForm(pageingVO.getConditionString(BoardService.BOARD_SEARCH_ITEM.CODE))%>">
		<input type="hidden" name="currentPage" value="${PAGE_SEARCH.currentPage}">	
		<table>
			<tr>
				<td>코드</td>
				<td>
<%
	if(mode == BoardManagerController.Mode.CREATE_ACTION){
%>				
					<form:input id="boardCode" path="boardCode" size="15" maxlength="8"/>
<%
	}
	// 수정 처리시 
	else{
%>				${createForm.boardCode}
					<form:hidden id="boardCode" path="boardCode"/>
<%
	}
%>					
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><form:input id="name" path="name" size="15" maxlength="8"/></td>
			</tr>
			<tr>
				<td>업로드용량 제한</td>
				<td><form:input id="uploadLimit" path="uploadLimit" size="15" maxlength="8"/></td>
			</tr>
			<tr>
				<td>답글 사용</td>
				<td>
					<form:radiobutton path="replyF" value="true"/>예
					<form:radiobutton path="replyF" value="false"/>아니오
				</td>
			</tr>
			<tr>
				<td>짧은 리플 사용</td>
				<td>
					<form:radiobutton path="commentF" value="true"/>예
					<form:radiobutton path="commentF" value="false"/>아니오
				</td>
			</tr>
			<tr>
				<td>파일 업로드</td>
				<td>
					<form:radiobutton path="attachF" value="true"/>예
					<form:radiobutton path="attachF" value="false"/>아니오
				</td>
			</tr>
			<tr>
				<td>암호화글 등록</td>
				<td>
					<form:radiobutton path="encodeF" value="true"/>예
					<form:radiobutton path="encodeF" value="false"/>아니오
				</td>
			</tr>
		</table>
	</form:form>
</div>
<div>
	<input type="button" value="확인" onclick="BoardManager.createOrUpdate()">
	<input type="button" value="취소" onclick="history.back();">
</div>
<jsp:include page="board_manager_form.inc.jsp"></jsp:include>