
var BoardArticle = new Object();

BoardArticle.searchForm = function(){
	if($.FORM.isEmptyRtnMsg(document.searchForm.searchWord, "검색단어를 입력해 주세요")){
		return;
	}
	document.searchForm.submit();
};

BoardArticle.encodeForm = function(){
	if($.FORM.isEmptyRtnMsg(document.encodeForm.encode, "단어를 입력하세요")){
		return;
	}
	document.encodeForm.submit();
};

BoardArticle.searchStopForm = function(){
	document.listForm.searchName.value="";
	document.listForm.searchTitle.value="";
	document.listForm.searchContent.value="";
	document.listForm.submit();
};

BoardArticle.listForm = function(){
	document.listForm.submit();
};

BoardArticle.readForm = function(seq){
	document.readForm.articleSeq.value = seq;
	document.readForm.submit();
};

BoardArticle.createFrom = function(){
	document.createForm.submit();
};

BoardArticle.createOrUpdate = function(){
	if($.FORM.isEmptyRtnMsg(document.createAction.title, "제목을 입력해 주세요")){
		return;
	}
	if($.FORM.isEmptyRtnMsg(document.createAction.name, "이름을 입력해 주세요")){
		return;
	}	
	if(document.createAction.email.value !=""){
		if(!$.FORM.isValidEmail(document.createAction.email)){
			return;
		}
	}
	if($.FORM.isEmptyRtnMsg(document.createAction.content, "내용을 입력해 주세요")){
		return;
	}		
	document.createAction.submit();
};

BoardArticle.updateForm = function(seq){
	document.updateForm.articleSeq.value = seq;
	document.updateForm.submit();
};

BoardArticle.removeAction = function(seq){
	if(confirm("삭제 하시겠습니까?")){
		document.removeAction.articleSeq.value = seq;
		document.removeAction.submit();
	}
};

BoardArticle.commentCreateAction = function(seq){
	if($.FORM.isEmptyRtnMsg(document.commentCreateAction.name, "이름을 입력해 주세요")){
		return;
	}
	if($.FORM.isEmptyRtnMsg(document.commentCreateAction.passwd, "비밀번호를 입력해 주세요")){
		return;
	}	
	if($.FORM.isEmptyRtnMsg(document.commentCreateAction.content, "내용을 입력해 주세요")){
		return;
	}	
	document.commentCreateAction.submit();
};

BoardArticle.commentRemoveAction = function(commentSeq){
	if(confirm("삭제 하시겠습니까?")){
		document.commentRemoveAction.commentSeq.value = commentSeq;
		document.commentRemoveAction.submit();
	}
};