<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.setvect.literatureboy.web.user.UserController"%>
<%@include file="/common/taglib.inc.jsp"%>
<form:form commandName="PAGE_SEARCH" name="listForm" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=UserController.Mode.LIST_FORM%>"/> 
	<form:hidden path="searchId"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
</form:form>

<form:form commandName="PAGE_SEARCH" name="readForm" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=UserController.Mode.READ_FORM%>"/> 
	<form:hidden path="searchId"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
	<input type="hidden" name="userId" value=""/> 
</form:form>

<form:form commandName="PAGE_SEARCH" name="createForm" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=UserController.Mode.CREATE_FORM%>"/> 
	<form:hidden path="searchId"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
</form:form>

<form:form commandName="PAGE_SEARCH" name="updateForm" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=UserController.Mode.UPDATE_FORM%>"/> 
	<form:hidden path="searchId"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
	<input type="hidden" name="userId" value=""/> 
</form:form>
	
<form:form commandName="PAGE_SEARCH" name="removeAction" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=UserController.Mode.REMOVE_ACTION%>"/> 
	<form:hidden path="searchId"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
	<input type="hidden" name="userId" value=""/> 
</form:form>

<form:form commandName="PAGE_SEARCH" name="authMapForm" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=UserController.Mode.AUTHMAP_FORM%>"/> 
	<form:hidden path="searchId"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
	<input type="hidden" name="userId" value=""/> 
</form:form>