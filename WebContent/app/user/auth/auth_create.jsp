<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.setvect.literatureboy.vo.user.Auth"%>
<%@page import="com.setvect.literatureboy.web.user.AuthController"%>
<%@include file="/common/taglib.inc.jsp"%>
<%
	Auth auth = (Auth) request.getAttribute(AuthController.AttributeKey.ITEM.name());
	if(auth == null){
		auth = new Auth();
	}
	request.setAttribute("createForm", auth);
%>

<script type="text/javascript" src="/app/user/auth/auth.js"></script>
<div>
	<form:form commandName="createForm" name="createAction" id="createAction" method="post" action="${SERVLET_URL}">
		<input type="hidden" name="mode" value="${MODE}"/>
		<input type="hidden" name="searchUrl" value="${PAGE_SEARCH.searchUrl}"/>
		<input type="hidden" name="searchName" value="${PAGE_SEARCH.searchName}"/>
		<input type="hidden" name="currentPage" value="${PAGE_SEARCH.currentPage}"/>	
		<form:hidden path="authSeq"/>
		<table>
			<tr>
				<th>이름</th>
				<td><form:input path="name" size="15" maxlength="15"/></td>
			</tr>
			<tr>
				<th>URL</th>
				<td><form:input path="url" size="30" maxlength="50"/></td>
			</tr>
			<tr>
				<th>파라미터</th>
				<td><form:input path="parameter" size="30" maxlength="50"/></td>
			</tr>

		</table>
	</form:form>
</div>
<div>
	<input type="button" value="확인" onclick="Auth.createOrUpdate()">
	<input type="button" value="취소" onclick="history.back();">
</div>
<jsp:include page="auth_form.inc.jsp"></jsp:include>