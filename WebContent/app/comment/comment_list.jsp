<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="java.util.Date"%>
<%@page import="com.setvect.literatureboy.util.DateDiff"%>
<%@page import="com.setvect.common.util.StringUtilAd"%>
<%@page import="com.setvect.literatureboy.vo.Comment"%>
<%@page import="java.util.List"%>
<%@page import="com.setvect.literatureboy.web.comment.CommentController"%>
<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%
	List<Comment> list = (List<Comment>)request.getAttribute(CommentController.AttributeKey.LIST.name());
	String loginId = (String)request.getAttribute(ConstraintWeb.USER_SESSION_KEY);
%>
<ul>	
<%
	Date now = new Date();
	for(Comment comment : list){
%>
	<li>
		<%=StringUtilAd.toBr(comment.getContent())%>
		<%=comment.getUser().getName() %>
		<%=DateDiff.diff(now, comment.getRegDate()) %>
<%
		if(comment.getUserId().equals(loginId) ){
%>
		<span class='button blue small'><input type='button' value='삭제' onclick='Comment.removeAction(<%=comment.getCommentSeq()%>)'></span>
<%
		}
%>		
	</li>
<%		
	}
%>
</ul>
