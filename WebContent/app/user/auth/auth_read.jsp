<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@include file="/common/taglib.inc.jsp"%>

<script type="text/javascript" src="/app/user/auth/auth.js"></script>
<div>
	<table>
		<tr>
			<th>이름</th>
			<td>${ITEM.name}</td>
		</tr>
		<tr>
			<th>Url</th>
			<td>${ITEM.url}</td>
		</tr>
		<tr>
			<th>Parameter</th>
			<td>${ITEM.parameter}</td>
		</tr>
	</table>
</div>
<div>
	<input type="button" value="수정" onclick="Auth.updateForm('${ITEM.authSeq}')">
	<input type="button" value="삭제" onclick="Auth.removeAction('${ITEM.authSeq}')">
	<input type="button" value="목록" onclick="Auth.listForm()">
</div>
<jsp:include page="auth_form.inc.jsp"></jsp:include>