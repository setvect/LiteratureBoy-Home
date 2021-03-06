<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.setvect.literatureboy.web.board.BoardManagerController"%>
<%@include file="/common/taglib.inc.jsp"%>
<form:form commandName="PAGE_SEARCH" name="listForm" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=BoardManagerController.Mode.LIST_FORM%>"/> 
	<form:hidden path="searchCode"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
</form:form>

<form:form commandName="PAGE_SEARCH" name="readForm" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=BoardManagerController.Mode.READ_FORM%>"/> 
	<form:hidden path="searchCode"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
	<input type="hidden" name="boardCode" value=""/> 
</form:form>

<form:form commandName="PAGE_SEARCH" name="createForm" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=BoardManagerController.Mode.CREATE_FORM%>"/> 
	<form:hidden path="searchCode"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
</form:form>

<form:form commandName="PAGE_SEARCH" name="updateForm" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=BoardManagerController.Mode.UPDATE_FORM%>"/> 
	<form:hidden path="searchCode"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
	<input type="hidden" name="boardCode" value=""/> 
</form:form>
	
<form:form commandName="PAGE_SEARCH" name="removeAction" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=BoardManagerController.Mode.REMOVE_ACTION%>"/> 
	<form:hidden path="searchCode"/>
	<form:hidden path="searchName"/>
	<form:hidden path="currentPage"/>
	<input type="hidden" name="boardCode" value=""/> 
</form:form>