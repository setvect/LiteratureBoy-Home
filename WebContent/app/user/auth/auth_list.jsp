<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.service.user.AuthSearch"%>
<%@page import="com.setvect.literatureboy.vo.user.Auth"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.user.AuthController"%>
<%@page import="com.setvect.common.util.StringUtilAd"%>
<%@include file="/common/taglib.inc.jsp"%>


<script type="text/javascript" src="/app/user/auth/auth.js"></script>
<div>
	<div>
		<form:form name="searchForm" method="get" action="${controller_url}">
			<input type="hidden" name="mode" value="<%=AuthController.Mode.SEARCH_FORM%>"/>
			Search: 
			<select name="searchType">
				<option value="url" ${empty PAGE_SEARCH.searchUrl ? "" : "selected='selected'"}>url</option>
				<option value="name" ${empty PAGE_SEARCH.searchName ? "" : "selected='selected'"}>name</option>
			</select>
			<input type="text" name="searchWord" value="<c:out value="${PAGE_SEARCH.word}"/>">
			<input type="button" value="Search" onclick="Auth.searchForm()">
			<c:if test="${! empty PAGE_SEARCH.word}">
				<input type="button" value="Search Stop"  onclick="Auth.searchStopForm()">
			</c:if>
		</form:form>
	</div>
	<div>
		Total: ${LIST.total},  Page: ${LIST.currentPage }/${LIST.maxPage}
	</div>
</div>
<div>
	<display:table name="LIST.list" class="table" id="article" requestURI="${controller_url}" export="false" partialList="true" size="${LIST.total}" pagesize="${LIST.pagesize}"  style="margin-top:10px;">
    <display:column title="No." >
      ${LIST.rowNumDesc - article_rowNum + 1}
    </display:column>
		<display:column property="name" href="javascript:Auth.readForm('${article.authSeq}')"  title="Name"/>
		<display:column property="url" title="Url"/>
		<display:column property="parameter" title="Parameter"/>		
		<display:column title="Update">
			<input type="button" value="수정" onclick="Auth.updateForm('${article.authSeq}')">
		</display:column>
		<display:column title="Delete">
			<input type="button" value="삭제" onclick="Auth.removeAction('${article.authSeq}')">
		</display:column>
	</display:table>
</div>
<div>
	<input type="button" value="생성" onclick="Auth.createFrom();">
</div>
<jsp:include page="auth_form.inc.jsp"></jsp:include>