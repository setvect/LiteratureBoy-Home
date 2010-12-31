<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.setvect.literatureboy.vo.board.BoardArticle"%>
<%@page import="com.setvect.literatureboy.web.board.BoardArticleController"%>
<%@include file="/common/taglib.inc.jsp"%>
<script type="text/javascript" src="/app/board/board_article.js"></script>
<script type="text/javascript" src="/app/smart_edit/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	var oEditors = [];
	window.onload = function()
  {
  	nhn.husky.EZCreator.createInIFrame({
  		oAppRef: oEditors,
  		elPlaceHolder: "content",
  		sSkinURI: "/app/smart_edit/SEditorSkin.html",
  		fCreator: "createSEditorInIFrame"
  	});
  };
</script>
<div>
	<form:form commandName="ARTICLE" name="createAction" id="createAction" method="post" enctype="multipart/form-data" action="${SERVLET_URL}">
		<input type="hidden" name="mode" value="${MODE}"/>
		<input type="hidden" name="searchCode" value="${PAGE_SEARCH.searchCode}"/>
		<input type="hidden" name="searchName" value="${PAGE_SEARCH.searchName}"/>
		<input type="hidden" name="searchTitle" value="${PAGE_SEARCH.searchTitle}"/>
		<input type="hidden" name="searchContent" value="${PAGE_SEARCH.searchContent}"/>
		<input type="hidden" name="currentPage" value="${PAGE_SEARCH.currentPage}"/>
			
		<input type="hidden" name="boardCode" value="${PAGE_SEARCH.searchCode}"/>	
		<form:hidden path="articleSeq"/>	
		<table>
			<tr>
				<th>Title</th>
				<td><form:input id="title" path="title" size="50" maxlength="50"/></td>
			</tr>
			<tr>
				<th>Name</th>
				<td><form:input id="name" path="name" size="10" maxlength="10"/></td>
			</tr>
			<tr>
				<th>Email</th>
				<td><form:input id="email" path="email" size="30" maxlength="50"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<form:textarea id="content" path="content" cssStyle="width:590px; height:300px"/><br>
					<input onclick="$.APP.openImageUpload();" type="button" value="이미지 첨부"/>
				</td>
			</tr>
			<c:if test="${BOARD.encodeF}">
				<tr>
					<th>암호코드</th>
					<td><input type="text" name="encode"/></td>
				</tr>
			</c:if> 
			<c:if test="${BOARD.attachF}">
				<tr>
					<th>Attach</th>
					<td>
						<span>이전 첨부파일 삭제</span>
						<ul>
							<c:forEach var="file" items="${ARTICLE.attach}">
								<li><input type="checkbox" name="deleteAttach" value="${file.fileSeq}"/> ${file.originalName} </li>
							</c:forEach>
						</ul>
						<ul>
	         		<c:set var="attachIndex" value="<%=new int[] {1,2,3}%>" />
							<c:forEach var="id" items="${attachIndex}">
								<li>Attach #${id}: <input type="file" name="attachFile"/></li>
							</c:forEach>
						</ul>
						업로드 제한: 
						<c:if test="${BOARD.uploadLimit == 0}">
							없음
						</c:if>
						<c:if test="${BOARD.uploadLimit != 0}">
							${BOARD.uploadLimit}
						</c:if>
					</td>
				</tr>			
			</c:if>
		</table>
	</form:form>
</div>
<div>
	<input type="button" value="확인" onclick="BoardArticle.createOrUpdate()">
	<input type="button" value="취소" onclick="history.back();">
</div>
<jsp:include page="board_article_form.inc.jsp"></jsp:include>
