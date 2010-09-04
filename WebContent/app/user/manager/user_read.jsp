<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.vo.user.User"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.user.UserController"%>
<%@page import="com.setvect.common.util.StringUtilAd"%>
<%@include file="/common/taglib.inc.jsp"%>

<script type="text/javascript" src="/app/user/manager/user.js"></script>
<div>
	<table>
		<tr>
			<th>아이디</th>
			<td>${ITEM.userId}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${ITEM.name}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${ITEM.email}</td>
		</tr>
	</table>
</div>
<div>
	<input type="button" value="수정" onclick="User.updateForm('${ITEM.userId}')">
	<input type="button" value="삭제" onclick="User.removeAction('${ITEM.userId}')">
	<input type="button" value="목록" onclick="User.listForm()">
</div>
<jsp:include page="user_form.inc.jsp"></jsp:include>