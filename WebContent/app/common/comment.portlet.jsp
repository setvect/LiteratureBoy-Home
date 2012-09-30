<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@include file="/common/taglib.inc.jsp"%>
<script type='text/javascript' src='/dwr/interface/commentService.js'></script>
<script type="text/javascript" src="/common/js/jquery.dateFormat-1.0.js"></script>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/util.js'></script>
<%
	boolean writerForm = Boolean.getBoolean(request.getParameter("writerForm"));	 
%>

<script type='text/javascript'>
	// 아래 구문이 없으면 DWR util.js과 충돌 되어 jquery를 사용 할 수 없다.
	var $ = jQuery.noConflict();
	var moduleName = "<%=request.getParameter("moduleName")%>";
	var moduleId = "<%=request.getParameter("moduleId")%>"; 
	var loginId = "${ _user_session_key.userId}"; 
	
	$(function(){
		Comment.display();
	});
	
	var Comment = new Object();
	Comment.createAction = function(){
		if($.FORM.isEmptyRtnMsg(document.commentCreateAction.content, "내용을 입력해 주세요")){
			return;
		}
		var comment = new Object();
		comment.content = document.commentCreateAction.content.value;
		comment.moduleName = moduleName;
		comment.moduleId = moduleId;
		commentService.createComment(comment, function(){
			document.commentCreateAction.content.value ="";
			Comment.display();
		});
	};

	Comment.removeAction = function(issueSeq){
		if(confirm("삭제 하시겠습니까?")){
			commentService.removeComment(issueSeq, function(){
				Comment.display();
			});
		}
	};	
	
	Comment.display = function(){
		commentService.getCommentList(moduleName, moduleId, function(commentList){
			var commentLength = commentList.length;
			var html="";
			html += "<ul>";
			for(var i=0; i< commentLength; i++){
				html += "<li>";
				html += commentList[i].content.replace("\n","<br/>");
				html += commentList[i].user.name;
	
				html += $.format.date(commentList[i].regDate, "yyyy/MM/dd");
				
				if(loginId == commentList[i].userId){
					html += "<span class='button blue small'><input type='button' value='삭제' onclick='Comment.removeAction("+commentList[i].commentSeq+")'></span>";
				}
				else{
					html.innerHTML = "&nbsp;";
				}
				html += "</li>";
			}
			html += "</ul>";
			
			$("#commentList").html(html);
		});
	};
</script>
<%
	if(writerForm){
%>
<form action="${SERVLET_URL}" method="post" name="commentCreateAction" style="clear: both;">
	<table class="table_list1">
		<tr>
			<td class="left">
				<textarea name="content" style="width: 80%;height: 30px;" onkeyup="$.FORM.inputLimitSize(this, 1000, 'commentContentSize');"></textarea>
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
<div id="commentList">
</div>