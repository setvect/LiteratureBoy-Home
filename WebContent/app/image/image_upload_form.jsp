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
	var ImageUpload = new Object();
	ImageUpload.upload = function(){
		if($.FORM.isEmptyRtnMsg(document.uploadAction.imageFile, "이미지를 선택해 줘~")){
			return;
		}	

		var imageName = document.uploadAction.imageFile.value;
		
		if(!$.STR.isImage(imageName)){
			$.FORM.selectMsg(document.uploadAction.imageFile, "이미지 파일만 업로드 하럼.");
			return;			
		}		
		
		document.uploadAction.submit();
	};
</script>
</head>
<body>
<div>
	<form:form name="uploadAction" id="uploadAction" method="post" 
			enctype="multipart/form-data" action="${controller_url}">
		<input type="hidden" name="mode" value="${MODE}"/>
		<table>
			<tr>
				<th>이미지</th>
				<td><input type="file" name="imageFile" style="width:100%"/></td>
			</tr>
		</table>
	</form:form>
</div>
<div>
	<input type="button" value="확인" onclick="ImageUpload.upload()"/>
	<input type="button" value="취소" onclick="window.close();"/>
</div>
</body>
</html>