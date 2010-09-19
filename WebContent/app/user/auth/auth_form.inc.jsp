<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.service.user.AuthSearch"%>
<%@page import="com.setvect.literatureboy.vo.user.Auth"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.user.AuthController"%>
<%@page import="com.setvect.common.util.StringUtilAd"%>
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