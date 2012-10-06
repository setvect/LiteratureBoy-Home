<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.setvect.literatureboy.vo.user.User"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="java.util.Date"%>
<%@page import="com.setvect.literatureboy.util.DateDiff"%>
<%@page import="com.setvect.common.util.StringUtilAd"%>
<%@page import="com.setvect.literatureboy.vo.Comment"%>
<%@page import="java.util.List"%>
<%@page import="com.setvect.literatureboy.web.comment.CommentController"%>
<%
	User loginUser = (User)request.getAttribute(ConstraintWeb.USER_SESSION_KEY);
%>
<style>
	#commentList{
		padding: 5px; 
	} 
	#commentList li{
		padding-top: 10px;
		padding-bottom: 10px;
	}
	#commentList .diff_date{
		font-size: 0.7em;
	}
	#commentList input{
		height:20px;
	}
</style>
<script type='text/javascript' src='/dwr/interface/commentService.js'></script>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/util.js'></script>
<script type='text/javascript' src='/app/comment/comment.js'></script>

<script type='text/javascript'>
	// 아래 구문이 없으면 DWR util.js과 충돌 되어 jquery를 사용 할 수 없다.
	var $ = jQuery.noConflict();
	$(function(){
		Comment.moduleName = "<%=request.getParameter("moduleName")%>";
		Comment.moduleId = "<%=request.getParameter("moduleId")%>"; 
		Comment.loginId = "${ _user_session_key.userId}"; 
		Comment.curretPage = 1;
		Comment.append();
	});
</script>

<%
	if(loginUser != null ){
%>
<form action="${SERVLET_URL}" method="post" name="commentCreateAction" style="clear: both;">
	<table style="width: 100%">
		<tr>
			<td class="left">
				<textarea name="content" style="width: 100%;height: 50px;" onkeyup="$.FORM.inputLimitSize(this, 1000, 'commentContentSize');"></textarea>
				<span class="button blue " >
					<input type="button" value="등록" onclick="Comment.createAction()">
				</span><br/>
				제한: <span id="commentContentSize"></span>/1000글자
			</td>
		</tr>		
	</table>
</form>
<%
	}
%>

<ul id="commentList">	
</ul>
<div id="commentListNext"><a href="javascript:Comment.append()">더보기</a></div>