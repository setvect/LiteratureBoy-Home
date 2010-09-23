<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@include file="/common/taglib.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>Literature Boy</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/common/css/main.css"/>
<jsp:include page="/common/script.inc.jsp"/>
<script type="text/javascript">
	var EmailGet = new Object();
	EmailGet.get = function(){
		if($.FORM.isEmptyRtnMsg(document.getAction.inputNumber, "숫자 입력!!")){
			return;
		}	

		document.getAction.submit();
	};
</script>
</head>
<body>
<div class="popup">
	<form:form name="getAction" id="uploadAction" method="post" 
			action="${SERVLET_URL}">
		<input type="hidden" name="mode" value="${MODE}"/>
		<table>
			<tr>
				<th>입력값</th>
				<td>${EMAIL_NUMBER}</td>
			</tr>
			<tr>
				<td colspan="2">
					위 숫자를 입력하세요.(요즘 스펨이 많아서리.)
					<input type="text" name="inputNumber" size="4" maxlength="4"/>
				</td>
			</tr>
		</table>
	</form:form>
	<div>
		<input type="button" value="확인" onclick="EmailGet.get()"/>
		<input type="button" value="취소" onclick="window.close();"/>
	</div>
</div>
</body>
</html>