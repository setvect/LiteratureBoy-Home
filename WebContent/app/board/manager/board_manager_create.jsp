<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.vo.board.Board"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.board.BoardManagerController"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
	String mode = (String)request.getAttribute(BoardManagerController.Mode.name());
	request.setAttribute("userForm", userBean);
%>

<form:form commandName="userForm" id="userForm" method="post" action="<%=request.getAttribute(PresentationConstants.CONTROLLER_URL).toString() %>">
	<input type="hidden" name="mode" value="<%=mode%>"/>
	
</form:form>