<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.setvect.literatureboy.web.user.LoginController"%>
<%@include file="/common/taglib.inc.jsp"%>

<script type="text/javascript">
	function login(){
		if($.FORM.isEmptyRtnMsg(document.logineAction.userId, "아이디를 입력하세요.")){
			return;
		}
		if($.FORM.isEmptyRtnMsg(document.logineAction.passwd, "비밀번호 입력하세요.")){
			return;
		}
		document.logineAction.submit();
	}
</script>
<div>
	<form:form id="" name="logineAction" method="post" action="${SERVLET_URL}">
		<input type="hidden" name="mode" value="<%=LoginController.Mode.LOGIN_ACTION%>"/>
		<input type="hidden" name="<%=ConstraintWeb.RETURN_URL %>" value="<c:out value="${returnUrl}"/>"/>
		<c:if test="${LOGIN_FAIL}">
			아이디 또는 비밀번호가 틀렸습니다.
		</c:if>
		<table>
			<tr>
				<th>아이디</th>
				<td><input name="userId" type="text" size="15" maxlength="12"/></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input name="passwd" type="password" size="15" maxlength="12"/></td>
			</tr>
			<tr>
				<th>상태 유지</th>
				<td><input name="statusSave" type="checkbox" value="Y"/></td>
			</tr>			
		</table>
	</form:form>
</div>
<div>
	<input type="button" value="확인" onclick="login()"/>
</div>  
<script type="text/javascript">
	$(function(){
		document.logineAction.userId.focus();
	});
</script>