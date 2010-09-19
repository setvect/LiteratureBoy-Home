<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.setvect.literatureboy.web.user.AuthController"%>
<%@include file="/common/taglib.inc.jsp"%>
<form:form commandName="PAGE_SEARCH" name="listForm" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=AuthController.Mode.LIST_FORM%>"/> 
	<form:hidden path="searchUrl"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
</form:form>

<form:form commandName="PAGE_SEARCH" name="readForm" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=AuthController.Mode.READ_FORM%>"/> 
	<form:hidden path="searchUrl"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
	<input type="hidden" name="authSeq" value=""/> 
</form:form>

<form:form commandName="PAGE_SEARCH" name="createForm" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=AuthController.Mode.CREATE_FORM%>"/> 
	<form:hidden path="searchUrl"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
</form:form>

<form:form commandName="PAGE_SEARCH" name="updateForm" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=AuthController.Mode.UPDATE_FORM%>"/> 
	<form:hidden path="searchUrl"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
	<input type="hidden" name="authSeq" value=""/> 
</form:form>
	
<form:form commandName="PAGE_SEARCH" name="removeAction" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=AuthController.Mode.REMOVE_ACTION%>"/> 
	<form:hidden path="searchUrl"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
	<input type="hidden" name="authSeq" value=""/> 
</form:form>