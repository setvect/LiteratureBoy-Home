<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.service.user.UserSearch"%>
<%@page import="com.setvect.literatureboy.vo.user.User"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.user.UserController"%>
<%@page import="com.setvect.common.util.StringUtilAd"%>
<%@include file="/common/taglib.inc.jsp"%>
<jsp:include page="/common/script.inc.jsp"/>

<script type="text/javascript" src="/app/user/manager/user.js"></script>
<div>
	<form:form name="authMapAction" id="authMapAction" method="post" action="${controller_url}">
		<input type="hidden" name="mode" value="<%=UserController.Mode.AUTHMAP_ACTION.name()%>"/>
		<input type="hidden" name="searchName" value="${PAGE_SEARCH.searchName}"/>
		<input type="hidden" name="searchId" value="${PAGE_SEARCH.searchId}"/>
		<input type="hidden" name="currentPage" value="${PAGE_SEARCH.currentPage}"/>	
		<input type="hidden" name="userId" value="${ITEM.userId}"/>	
		<display:table name="AUTH_LIST.list" class="table" id="article" requestURI="${controller_url}" export="false"  style="margin-top:10px;">
	    <display:column title="No." >
	      ${article_rowNum }
	    </display:column>
			<display:column property="name" title="Name"/>
			<display:column property="url" title="Url"/>
			<display:column property="parameter" title="Parameter"/>		
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