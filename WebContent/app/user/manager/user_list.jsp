<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.service.user.UserSearch"%>
<%@page import="com.setvect.literatureboy.vo.user.User"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.user.UserController"%>
<%@page import="com.setvect.common.util.StringUtilAd"%>
<%@page import="com.setvect.literatureboy.service.user.UserService"%>
<%@include file="/common/taglib.inc.jsp"%>
<jsp:include page="/common/script.inc.jsp"/>

<script type="text/javascript" src="/app/user/manager/user.js"></script>
<div>
	<div>
		<form:form name="searchForm" method="get" action="${controller_url}">
			<input type="hidden" name="mode" value="<%=UserController.Mode.SEARCH_FORM%>"/>
			Search: 
			<select name="searchType">
				<option value="id" ${empty PAGE_SEARCH.searchId ? "" : "selected='selected'"}>id</option>
				<option value="name" ${empty PAGE_SEARCH.searchName ? "" : "selected='selected'"}>name</option>
			</select>
			<input type="text" name="searchWord" value="${PAGE_SEARCH.word}">
			<input type="button" value="Search" onclick="User.searchForm()">
			<c:if test="${! empty PAGE_SEARCH.word}">
				<input type="button" value="Search Stop"  onclick="User.searchStopForm()">
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
		<display:column property="userId" href="javascript:User.readForm('${article.userId}')"  title="ID"/>
		<display:column property="name" title="Name"/>
		<display:column title="Update">
			<input type="button" value="수정" onclick="User.updateFrom('${article.userId}')">
		</display:column>
		<display:column title="Delete">
			<input type="button" value="삭제" onclick="User.removeAction('${article.userId}')">
		</display:column>
	</display:table>
</div>
<div>
	<input type="button" value="생성" onclick="User.createFrom();">
</div>
<jsp:include page="user_form.inc.jsp"></jsp:include>