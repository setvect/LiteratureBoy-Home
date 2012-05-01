<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@include file="/common/taglib.inc.jsp"%>
<div data-role="content">
	<div>
		<c:if test="${empty EMAIL}">
			음~ 뭔가 잘 못 입력 하셨군요.<br>
			<input type="button" value="다시 찾기" onclick="location.href='/m/emailget.do'" />
		</c:if>
		<c:if test="${!empty EMAIL}">
			빙고! 정호 이메일은 <a href="mailto:${EMAIL}">${EMAIL}</a> 입니당. ^^	
		</c:if>
	</div>
</div>

