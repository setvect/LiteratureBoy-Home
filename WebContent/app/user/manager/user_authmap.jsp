<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.setvect.literatureboy.web.user.UserController"%>
<%@include file="/common/taglib.inc.jsp"%>


<script type="text/javascript" src="/app/user/manager/user.js"></script>
<div>
	<form:form name="authMapAction" id="authMapAction" method="post" action="${SERVLET_URL}">
		<input type="hidden" name="mode" value="<%=UserController.Mode.AUTHMAP_ACTION.name()%>"/>
		<input type="hidden" name="searchName" value="${PAGE_SEARCH.searchName}"/>
		<input type="hidden" name="searchId" value="${PAGE_SEARCH.searchId}"/>
		<input type="hidden" name="currentPage" value="${PAGE_SEARCH.currentPage}"/>	
		<input type="hidden" name="userId" value="${ITEM.userId}"/>	
		<display:table name="AUTH_LIST.list" class="list_table" id="article" requestURI="${SERVLET_URL}" export="false"  style="margin-top:10px;">
	    <display:column title="No." >
	      ${article_rowNum }
	    </display:column>
			<display:column property="name" title="Name" class="align_left"/>
			<display:column property="url" title="Url" class="align_left"/>
			<display:column property="parameter" title="Parameter" class="align_left"/>		
			<display:column title="Select">
				<input type="checkbox" name="authSeq" value="${article.authSeq}" ${article.authHave ? "checked='checked'" : ""}>
			</display:column>
		</display:table>
	</form:form>
</div>
<div>
	<input type="button" value="등록" onclick="User.authMapAction();">
</div>
<jsp:include page="user_form.inc.jsp"></jsp:include>