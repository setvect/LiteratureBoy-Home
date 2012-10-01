<%@page import="com.setvect.literatureboy.vo.user.User"%>
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
	User loginUser = (User)request.getAttribute(ConstraintWeb.USER_SESSION_KEY);
%>
<ul>	
<%
	Date now = new Date();
	for(Comment comment : list){
%>
	<li>
		<%=StringUtilAd.toBr(comment.getContent().trim())%>
		<span style="float: right"><%=DateDiff.diff(now, comment.getRegDate()) %></span>
<%
		if(loginUser != null && comment.getUserId().equals(loginUser.getUserId()) ){
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
