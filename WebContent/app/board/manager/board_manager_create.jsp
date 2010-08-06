<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.vo.board.Board"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.board.BoardManagerController"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
	BoardManagerController.Mode mode = (BoardManagerController.Mode)request.getAttribute(BoardManagerController.AttributeKey.MODE.name());
	Board board = (Board) request.getAttribute(BoardManagerController.AttributeKey.BOARD_ITEM.name());
	if(board == null){
		board = new Board();
	}
	request.setAttribute("createForm", board);
%>
<div>
<form:form commandName="createForm" id="createForm" method="post" action="<%=request.getAttribute(ConstraintWeb.SERVLET_URL).toString() %>">
	<input type="hidden" name="mode" value="<%=mode%>"/>
	<table>
		<tr>
			<td>코드</td>
			<td><form:input id="boardCode" path="boardCode" size="15" maxlength="8"/></td>
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
	<input type="button" value="확인" onclick="createOrUpdate()">
</div>