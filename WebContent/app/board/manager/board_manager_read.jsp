<%@page import="com.setvect.common.util.PagingCondition"%>
<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.vo.board.Board"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.board.BoardManagerController"%>
<%@page import="com.setvect.common.util.StringUtilAd"%>
<%@page import="com.setvect.literatureboy.service.board.BoardService"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
	Board board = (Board) request.getAttribute(BoardManagerController.AttributeKey.BOARD_ITEM.name());
%>
<jsp:include page="/common/script.inc.jsp"/>
<script type="text/javascript" src="/app/board/manager/board_manager.js"></script>
<div>
	<table>
		<tr>
			<td>코드</td>
			<td><%=board.getBoardCode()%></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><%=board.getName()%></td>
		</tr>
		<tr>
			<td>업로드용량 제한</td>
			<td><%=board.getUploadLimit()%></td>
		</tr>
		<tr>
			<td>답글 사용</td>
			<td><%=board.isReplyF()%></td>
		</tr>
		<tr>
			<td>짧은 리플 사용</td>
			<td><%=board.isCommentF()%></td>
		</tr>
		<tr>
			<td>파일 업로드</td>
			<td><%=board.isAttachF()%></td>
		</tr>
		<tr>
			<td>암호화글 등록</td>
			<td><%=board.isEncodeF()%></td>
		</tr>
	</table>
</div>
<div>
	<input type="button" value="확인" onclick="BoardManager.listForm()">
</div>
<jsp:include page="board_manager_form.inc.jsp"></jsp:include>