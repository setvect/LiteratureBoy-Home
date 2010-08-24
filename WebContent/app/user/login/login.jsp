<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.service.user.AuthSearch"%>
<%@page import="com.setvect.literatureboy.vo.user.Auth"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.user.LoginController"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@include file="/common/taglib.inc.jsp"%>
<jsp:include page="/common/script.inc.jsp"/>
<script type="text/javascript">
	function login(){
		if($.FORM.isEmptyRtnMsg(logineAction.userId, "아이디를 입력하세요.")){
			return;
		}
		if($.FORM.isEmptyRtnMsg(logineAction.passwd, "비밀번호 입력하세요.")){
			return;
		}
		logineAction.submit();
	}
</script>
<div>
	<form:form name="logineAction" method="post" action="${controller_url}">
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
		</table>
	</form:form>
</div>
<div>
	<input type="button" value="확인" onclick="login()">
</div>