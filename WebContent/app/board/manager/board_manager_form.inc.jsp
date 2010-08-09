<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.vo.board.Board"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.board.BoardManagerController"%>
<%@page import="com.setvect.common.util.PagingCondition"%>
<%@page import="com.setvect.common.util.StringUtilAd"%>
<%@page import="com.setvect.literatureboy.service.board.BoardService"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
	PagingCondition pageingVO = (PagingCondition)request.getAttribute(BoardManagerController.AttributeKey.PAGE_SEARCH.name());
%>
<form name="listForm" action="<%=request.getAttribute(ConstraintWeb.SERVLET_URL).toString()%>">
	<input type="hidden" name="mode" value="<%=BoardManagerController.Mode.LIST_FORM%>"/> 
	<input type="hidden" name="searchName" value="<%=StringUtilAd.toForm(pageingVO.getConditionString(BoardService.BOARD_SEARCH_ITEM.NAME))%>">
	<input type="hidden" name="searchCode" value="<%=StringUtilAd.toForm(pageingVO.getConditionString(BoardService.BOARD_SEARCH_ITEM.CODE))%>">
	<input type="hidden" name="currentPageNo" value="<%=pageingVO.getCurrentPageNo()%>">
</form>

<form name="readForm" action="<%=request.getAttribute(ConstraintWeb.SERVLET_URL).toString()%>">
	<input type="hidden" name="mode" value="<%=BoardManagerController.Mode.READ_FORM%>"/> 
	<input type="hidden" name="searchName" value="<%=StringUtilAd.toForm(pageingVO.getConditionString(BoardService.BOARD_SEARCH_ITEM.NAME))%>">
	<input type="hidden" name="searchCode" value="<%=StringUtilAd.toForm(pageingVO.getConditionString(BoardService.BOARD_SEARCH_ITEM.CODE))%>">
	<input type="hidden" name="currentPageNo" value="<%=pageingVO.getCurrentPageNo()%>">
	<input type="hidden" name="boardCode" value=""/> 
</form>

<form name="createForm" action="<%=request.getAttribute(ConstraintWeb.SERVLET_URL).toString()%>">
	<input type="hidden" name="mode" value="<%=BoardManagerController.Mode.CREATE_FORM%>"/> 
	<input type="hidden" name="searchName" value="<%=StringUtilAd.toForm(pageingVO.getConditionString(BoardService.BOARD_SEARCH_ITEM.NAME))%>">
	<input type="hidden" name="searchCode" value="<%=StringUtilAd.toForm(pageingVO.getConditionString(BoardService.BOARD_SEARCH_ITEM.CODE))%>">
	<input type="hidden" name="currentPageNo" value="<%=pageingVO.getCurrentPageNo()%>">
</form>

<form name="updateForm" action="<%=request.getAttribute(ConstraintWeb.SERVLET_URL).toString()%>">
	<input type="hidden" name="mode" value="<%=BoardManagerController.Mode.UPDATE_FORM%>"/> 
	<input type="hidden" name="searchName" value="<%=StringUtilAd.toForm(pageingVO.getConditionString(BoardService.BOARD_SEARCH_ITEM.NAME))%>">
	<input type="hidden" name="searchCode" value="<%=StringUtilAd.toForm(pageingVO.getConditionString(BoardService.BOARD_SEARCH_ITEM.CODE))%>">
	<input type="hidden" name="currentPageNo" value="<%=pageingVO.getCurrentPageNo()%>">
	<input type="hidden" name="boardCode" value=""/> 
</form>

<form name="deleteAction" action="<%=request.getAttribute(ConstraintWeb.SERVLET_URL).toString()%>">
	<input type="hidden" name="mode" value="<%=BoardManagerController.Mode.DELETE_ACTION%>"/> 
	<input type="hidden" name="searchName" value="<%=StringUtilAd.toForm(pageingVO.getConditionString(BoardService.BOARD_SEARCH_ITEM.NAME))%>">
	<input type="hidden" name="searchCode" value="<%=StringUtilAd.toForm(pageingVO.getConditionString(BoardService.BOARD_SEARCH_ITEM.CODE))%>">
	<input type="hidden" name="currentPageNo" value="<%=pageingVO.getCurrentPageNo()%>">
	<input type="hidden" name="boardCode" value=""/> 
</form>