<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@include file="/common/taglib.inc.jsp"%>
<div data-role="content">
	<form:form name="getAction" id="uploadAction" method="post" action="${SERVLET_URL}">
		<input type="hidden" name="mode" value="${MODE}"/>
		<div>
			입력값: <span style="font-weight: bold; color:red">${EMAIL_NUMBER}</span> <br>(요즘 하도 스팸이 많아서리...)
		</div>	
		<div data-role="fieldcontain">
			<fieldset data-role="controlgroup">
				<label for="textinput1">위 숫자 입력</label> 
				<input id="inputNumber" name="inputNumber" maxlength="4" type="number" />
			</fieldset>
		</div>
		<input type="submit" value="이메일 주소 알기" />
	</form:form>
</div>

