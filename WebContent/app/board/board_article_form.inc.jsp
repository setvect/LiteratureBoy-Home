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
<form:form name="listForm" method="get" action="${controller_url}">
	<input type="hidden" name="mode" value="<%=BoardArticleController.Mode.LIST_FORM%>"/> 
	<input type="hidden" name="searchCode" value="${PAGE_SEARCH.searchCode}">
	<input type="hidden" name="searchName" value="${PAGE_SEARCH.searchName}">
	<input type="hidden" name="searchTitle" value="${PAGE_SEARCH.searchTitle}">
	<input type="hidden" name="searchContent" value="${PAGE_SEARCH.searchContent}">
	<input type="hidden" name="currentPage" value="${PAGE_SEARCH.currentPage}">	
</form:form>

<form:form name="readForm" method="get" action="${controller_url}">
	<input type="hidden" name="mode" value="<%=BoardArticleController.Mode.READ_FORM%>"/> 
	<input type="hidden" name="searchCode" value="${PAGE_SEARCH.searchCode}">
	<input type="hidden" name="searchName" value="${PAGE_SEARCH.searchName}">
	<input type="hidden" name="searchTitle" value="${PAGE_SEARCH.searchTitle}">
	<input type="hidden" name="searchContent" value="${PAGE_SEARCH.searchContent}">
	<input type="hidden" name="currentPage" value="${PAGE_SEARCH.currentPage}">	
</form:form>

<form:form name="createForm" method="get" action="${controller_url}">
	<input type="hidden" name="mode" value="<%=BoardArticleController.Mode.CREATE_FORM%>"/> 
	<input type="hidden" name="searchCode" value="${PAGE_SEARCH.searchCode}">
	<input type="hidden" name="searchName" value="${PAGE_SEARCH.searchName}">
	<input type="hidden" name="searchTitle" value="${PAGE_SEARCH.searchTitle}">
	<input type="hidden" name="searchContent" value="${PAGE_SEARCH.searchContent}">
	<input type="hidden" name="currentPage" value="${PAGE_SEARCH.currentPage}">	
</form:form>

<form:form name="updateForm" method="get" action="${controller_url}">
	<input type="hidden" name="mode" value="<%=BoardArticleController.Mode.UPDATE_FORM%>"/> 
	<input type="hidden" name="searchCode" value="${PAGE_SEARCH.searchCode}">
	<input type="hidden" name="searchName" value="${PAGE_SEARCH.searchName}">
	<input type="hidden" name="searchTitle" value="${PAGE_SEARCH.searchTitle}">
	<input type="hidden" name="searchContent" value="${PAGE_SEARCH.searchContent}">
	<input type="hidden" name="currentPage" value="${PAGE_SEARCH.currentPage}">	
</form:form>
	
<form:form name="deleteAction" method="get" action="${controller_url}">
	<input type="hidden" name="mode" value="<%=BoardArticleController.Mode.DELETE_ACTION%>"/> 
	<input type="hidden" name="searchCode" value="${PAGE_SEARCH.searchCode}">
	<input type="hidden" name="searchName" value="${PAGE_SEARCH.searchName}">
	<input type="hidden" name="searchTitle" value="${PAGE_SEARCH.searchTitle}">
	<input type="hidden" name="searchContent" value="${PAGE_SEARCH.searchContent}">
	<input type="hidden" name="currentPage" value="${PAGE_SEARCH.currentPage}">	
</form:form>