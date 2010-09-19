<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.setvect.literatureboy.web.board.BoardManagerController"%>
<%@include file="/common/taglib.inc.jsp"%>

<script type="text/javascript" src="/app/board/manager/board_manager.js"></script>
<div>
	<table>
		<tr>
			<th>코드</th>
			<td>${ITEM.boardCode}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${ITEM.name}</td>
		</tr>
		<tr>
			<th>업로드용량 제한</th>
			<td>${ITEM.uploadLimit}</td>
		</tr>
		<tr>
			<th>답글 사용</th>
			<td>${ITEM.replyF}</td>
		</tr>
		<tr>
			<th>짧은 리플 사용</th>
			<td>${ITEM.commentF}</td>
		</tr>
		<tr>
			<th>파일 업로드</th>
			<td>${ITEM.attachF}</td>
		</tr>
		<tr>
			<th>암호화글 등록</th>
			<td>${ITEM.encodeF}</td>
		</tr>
	</table>
</div>
<div>
	<input type="button" value="수정" onclick="BoardManager.updateForm('${ITEM.boardCode}')">
	<input type="button" value="삭제" onclick="BoardManager.removeAction('${ITEM.boardCode}')">
	<input type="button" value="목록" onclick="BoardManager.listForm()">

</div>
<jsp:include page="board_manager_form.inc.jsp"></jsp:include>