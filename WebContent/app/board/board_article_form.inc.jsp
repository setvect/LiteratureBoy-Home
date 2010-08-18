<%@page import="com.setvect.literatureboy.web.board.BoardArticleController"%>
<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.service.board.BoardArticleSearch"%>
<%@page import="com.setvect.literatureboy.vo.board.Board"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.common.util.StringUtilAd"%>
<%@page import="com.setvect.literatureboy.service.board.BoardService"%>
<%@include file="/common/taglib.inc.jsp"%>
<%
	BoardArticleSearch searchVO = (BoardArticleSearch)request.getAttribute(BoardArticleController.AttributeKey.PAGE_SEARCH.name());
%>
<form:form commandName="PAGE_SEARCH" name="listForm" method="get" action="${controller_url}">
	<input type="hidden" name="mode" value="<%=BoardArticleController.Mode.LIST_FORM%>"/> 
	<form:hidden path="searchCode"/>
	<form:hidden path="searchName"/>
	<form:hidden path="searchTitle"/>
	<form:hidden path="searchContent"/>
	<form:hidden path="currentPage"/>	
</form:form>

<form:form commandName="PAGE_SEARCH" name="readForm" method="get" action="${controller_url}">
	<input type="hidden" name="mode" value="<%=BoardArticleController.Mode.READ_FORM%>"/> 
	<form:hidden path="searchCode"/>
	<form:hidden path="searchName"/>
	<form:hidden path="searchTitle"/>
	<form:hidden path="searchContent"/>
	<form:hidden path="currentPage"/>
	<input type="hidden" name="articleSeq"/>		
</form:form>

<form:form commandName="PAGE_SEARCH" name="createForm" method="get" action="${controller_url}">
	<input type="hidden" name="mode" value="<%=BoardArticleController.Mode.CREATE_FORM%>"/> 
	<form:hidden path="searchCode"/>
	<form:hidden path="searchName"/>
	<form:hidden path="searchTitle"/>
	<form:hidden path="searchContent"/>
	<form:hidden path="currentPage"/>		
</form:form>

<form:form commandName="PAGE_SEARCH" name="updateForm" method="get" action="${controller_url}">
	<input type="hidden" name="mode" value="<%=BoardArticleController.Mode.UPDATE_FORM%>"/> 
	<form:hidden path="searchCode"/>
	<form:hidden path="searchName"/>
	<form:hidden path="searchTitle"/>
	<form:hidden path="searchContent"/>
	<form:hidden path="currentPage"/>
	<input type="hidden" name="articleSeq"/>		
</form:form>
	
<form:form commandName="PAGE_SEARCH" name="removeAction" method="get" action="${controller_url}">
	<input type="hidden" name="mode" value="<%=BoardArticleController.Mode.REMOVE_ACTION%>"/> 
	<form:hidden path="searchCode"/>
	<form:hidden path="searchName"/>
	<form:hidden path="searchTitle"/>
	<form:hidden path="searchContent"/>
	<form:hidden path="currentPage"/>
	<input type="hidden" name="articleSeq"/>				
</form:form>