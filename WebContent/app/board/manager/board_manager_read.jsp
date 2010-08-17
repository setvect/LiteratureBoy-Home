<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
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
	<table>
		<tr>
			<td>코드</td>
			<td>${ITEM.boardCode}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${ITEM.name}</td>
		</tr>
		<tr>
			<td>업로드용량 제한</td>
			<td>${ITEM.uploadLimit}</td>
		</tr>
		<tr>
			<td>답글 사용</td>
			<td>${ITEM.replyF}</td>
		</tr>
		<tr>
			<td>짧은 리플 사용</td>
			<td>${ITEM.commentF}</td>
		</tr>
		<tr>
			<td>파일 업로드</td>
			<td>${ITEM.attachF}</td>
		</tr>
		<tr>
			<td>암호화글 등록</td>
			<td>${ITEM.encodeF}</td>
		</tr>
	</table>
</div>
<div>
	<input type="button" value="수정" onclick="BoardManager.updateFrom('${ITEM.boardCode}')">
	<input type="button" value="삭제" onclick="BoardManager.deleteAction('${ITEM.boardCode}')">
	<input type="button" value="목록" onclick="BoardManager.listForm()">

</div>
<jsp:include page="board_manager_form.inc.jsp"></jsp:include>