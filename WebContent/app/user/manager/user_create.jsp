<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.service.user.UserSearch"%>
<%@page import="com.setvect.literatureboy.vo.user.User"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.user.UserController"%>
<%@page import="com.setvect.common.util.StringUtilAd"%>
<%@include file="/common/taglib.inc.jsp"%>
<%
	User user = (User) request.getAttribute(UserController.AttributeKey.ITEM.name());
	if(user == null){
		user = new User();
	}
	user.setPasswd("");
	request.setAttribute("createForm", user);
%>

<script type="text/javascript" src="/app/user/manager/user.js"></script>
<div>
	<form:form commandName="createForm" name="createAction" id="createAction" method="post" action="${controller_url}">
		<input type="hidden" name="mode" value="${MODE}"/>
		<input type="hidden" name="searchName" value="${PAGE_SEARCH.searchName}"/>
		<input type="hidden" name="searchId" value="${PAGE_SEARCH.searchId}"/>
		<input type="hidden" name="currentPage" value="${PAGE_SEARCH.currentPage}"/>	
		<table>
			<tr>
				<th>아이디</th>
				<td>
					<c:choose>
						<c:when test="${MODE == 'CREATE_ACTION'}">
							<form:input id="userId" path="userId" size="15" maxlength="8"/>
						</c:when>
						<c:otherwise>
							${createForm.userId}
							<form:hidden id="userId" path="userId"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td><form:input id="name" path="name" size="15" maxlength="8"/></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<form:password id="passwd" path="passwd" size="15" maxlength="12"/><br/>
					다시입력:<input type="password" name="passwdRe" size="15" maxlength="12"/>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><form:input id="email" path="email" size="30" maxlength="50"/></td>
			</tr>
			<tr>
				<th>관리자</th>
				<td>
					<form:radiobutton path="adminF" value="true"/>예
					<form:radiobutton path="adminF" value="false"/>아니오
				</td>
			</tr>
		</table>
	</form:form>
</div>
<div>
	<input type="button" value="확인" onclick="User.createOrUpdate()">
	<input type="button" value="취소" onclick="history.back();">
</div>
<jsp:include page="user_form.inc.jsp"></jsp:include>