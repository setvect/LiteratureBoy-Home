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
<form:form commandName="PAGE_SEARCH" name="listForm" method="get" action="${controller_url}">
	<input type="hidden" name="mode" value="<%=UserController.Mode.LIST_FORM%>"/> 
	<form:hidden path="searchId"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
</form:form>

<form:form commandName="PAGE_SEARCH" name="readForm" method="get" action="${controller_url}">
	<input type="hidden" name="mode" value="<%=UserController.Mode.READ_FORM%>"/> 
	<form:hidden path="searchId"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
	<input type="hidden" name="userId" value=""/> 
</form:form>

<form:form commandName="PAGE_SEARCH" name="createForm" method="get" action="${controller_url}">
	<input type="hidden" name="mode" value="<%=UserController.Mode.CREATE_FORM%>"/> 
	<form:hidden path="searchId"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
</form:form>

<form:form commandName="PAGE_SEARCH" name="updateForm" method="get" action="${controller_url}">
	<input type="hidden" name="mode" value="<%=UserController.Mode.UPDATE_FORM%>"/> 
	<form:hidden path="searchId"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
	<input type="hidden" name="userId" value=""/> 
</form:form>
	
<form:form commandName="PAGE_SEARCH" name="removeAction" method="get" action="${controller_url}">
	<input type="hidden" name="mode" value="<%=UserController.Mode.REMOVE_ACTION%>"/> 
	<form:hidden path="searchId"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
	<input type="hidden" name="userId" value=""/> 
</form:form>