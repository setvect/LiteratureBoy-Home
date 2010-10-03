<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.setvect.literatureboy.web.board.BoardArticleController"%>
<%@page import="com.setvect.literatureboy.service.board.BoardArticleSearch"%>
<%@include file="/common/taglib.inc.jsp"%>
<%
	BoardArticleSearch searchVO = (BoardArticleSearch)request.getAttribute(BoardArticleController.AttributeKey.PAGE_SEARCH.name());
%>
<form:form commandName="PAGE_SEARCH" id="" name="listForm" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=BoardArticleController.Mode.LIST_FORM%>"/> 
	<form:hidden id="" path="searchCode"/>
	<form:hidden id="" path="searchName"/>
	<form:hidden id="" path="searchTitle"/>
	<form:hidden id="" path="searchContent"/>
	<form:hidden id="" path="currentPage"/>	
</form:form>

<form:form commandName="PAGE_SEARCH" id="" name="readForm" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=BoardArticleController.Mode.READ_FORM%>"/> 
	<form:hidden id="" path="searchCode"/>
	<form:hidden id="" path="searchName"/>
	<form:hidden id="" path="searchTitle"/>
	<form:hidden id="" path="searchContent"/>
	<form:hidden id="" path="currentPage"/>
	<input type="hidden" name="articleSeq"/>		
</form:form>

<form:form commandName="PAGE_SEARCH" id="" name="createForm" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=BoardArticleController.Mode.CREATE_FORM%>"/> 
	<form:hidden id="" path="searchCode"/>
	<form:hidden id="" path="searchName"/>
	<form:hidden id="" path="searchTitle"/>
	<form:hidden id="" path="searchContent"/>
	<form:hidden id="" path="currentPage"/>		
</form:form>

<form:form commandName="PAGE_SEARCH" id="" name="updateForm" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=BoardArticleController.Mode.UPDATE_FORM%>"/> 
	<form:hidden id="" path="searchCode"/>
	<form:hidden id="" path="searchName"/>
	<form:hidden id="" path="searchTitle"/>
	<form:hidden id="" path="searchContent"/>
	<form:hidden id="" path="currentPage"/>
	<input type="hidden" name="articleSeq"/>		
</form:form>
	
<form:form commandName="PAGE_SEARCH" id="" name="removeAction" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=BoardArticleController.Mode.REMOVE_ACTION%>"/> 
	<form:hidden id="" path="searchCode"/>
	<form:hidden id="" path="searchName"/>
	<form:hidden id="" path="searchTitle"/>
	<form:hidden id="" path="searchContent"/>
	<form:hidden id="" path="currentPage"/>
	<input type="hidden" name="articleSeq"/>				
</form:form>

<form:form commandName="PAGE_SEARCH" id="" name="trackbackRemoveAction" method="get" action="${SERVLET_URL}">
	<input type="hidden" name="mode" value="<%=BoardArticleController.Mode.TRACKBACK_REMOVE_ACTION%>"/> 
	<form:hidden id="" path="searchCode"/>
	<form:hidden id="" path="searchName"/>
	<form:hidden id="" path="searchTitle"/>
	<form:hidden id="" path="searchContent"/>
	<form:hidden id="" path="currentPage"/>
	<input type="hidden" name="relationSeq" value="" />
	<input type="hidden" name="articleSeq" value="" />
				
</form:form>
