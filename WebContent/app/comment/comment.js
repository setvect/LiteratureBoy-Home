var Comment = new Object();

Comment.createAction = function(){
	if($.FORM.isEmptyRtnMsg(document.commentCreateAction.content, "내용을 입력해 주세요")){
		return;
	}
	var formValue = new Object();
	formValue.content = document.commentCreateAction.content.value;
	formValue.moduleName = Comment.moduleName;
	formValue.moduleId = Comment.moduleId;
	commentService.createComment(formValue, function(){
		document.commentCreateAction.content.value ="";
		Comment.reload();
	});
};

Comment.removeAction = function(issueSeq){
	if(confirm("삭제 하시겠습니까?")){
		commentService.removeComment(issueSeq, function(){
			Comment.reload();
		});
	}
};

Comment.reload = function(){
	$("#commentList").html("");
	Comment.curretPage = 1;
	Comment.append();		
};

Comment.append = function(){
	commentService.getCommentList(Comment.moduleName, Comment.moduleId, Comment.curretPage, function(commentList){
		var commentLength = commentList.length;
		if(commentLength == 0){
			$("#commentListNext").html("더 이상 없음");
		}
		
		var html="";
		for(var i=0; i< commentLength; i++){
			html += "<li>";
			html += $.STR.trim(commentList[i].content).replace("\n","<br/>");
			html += "&nbsp;&nbsp;";
			html += "<span class='diff_date'>" + commentList[i].regDateDiff + "</span>";
			
			if(Comment.loginId == commentList[i].userId){
				html += "<span style='float:right' class='button blue small'><input type='button' value='삭제' onclick='Comment.removeAction("+commentList[i].commentSeq+")'></span>";
			}
			else{
				html.innerHTML = "&nbsp;";
			}
			html += "</li>";
		}
		$("#commentList").append(html);
		Comment.curretPage = Comment.curretPage + 1; 
	});
};