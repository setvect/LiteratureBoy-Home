<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.setvect.literatureboy.web.user.UserController"%>
<%@include file="/common/taglib.inc.jsp"%>


<script type="text/javascript" src="/app/user/manager/user.js"></script>
<div>
	<div>
		<form:form name="searchForm" method="get" action="${SERVLET_URL}">
			<input type="hidden" name="mode" value="<%=UserController.Mode.SEARCH_FORM%>"/>
			Search: 
			<select name="searchType">
				<option value="id" ${empty PAGE_SEARCH.searchId ? "" : "selected='selected'"}>id</option>
				<option value="name" ${empty PAGE_SEARCH.searchName ? "" : "selected='selected'"}>name</option>
			</select>
			<input type="text" name="searchWord" value="<c:out value="${PAGE_SEARCH.word}"/>">
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
	<display:table name="LIST.list" class="list_table" id="article" requestURI="${SERVLET_URL}" export="false" partialList="true" size="${LIST.total}" pagesize="${LIST.pagesize}"  style="margin-top:10px;">
    <display:column title="No." >
      ${LIST.rowNumDesc - article_rowNum + 1}
    </display:column>
		<display:column title="ID">
			<a href="javascript:User.readForm('${article.userId}')">${article.userId}</a>
		</display:column>
		<display:column property="name" title="Name"/>
		<display:column title="Auth">
			<input type="button" value="권한" onclick="User.authMapForm('${article.userId}')">
		</display:column>		
		<display:column title="Update">
			<input type="button" value="수정" onclick="User.updateForm('${article.userId}')">
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