
var BoardArticle = new Object();

BoardArticle.searchForm = function(){
	if($.FORM.isEmptyRtnMsg(searchForm.searchWord, "검색단어를 입력해 주세요")){
		return;
	}
	searchForm.submit();
};

BoardArticle.searchStopForm = function(){
	listForm.searchName.value="";
	listForm.searchTitle.value="";
	listForm.searchContent.value="";
	listForm.submit();
};

BoardArticle.listForm = function(){
	listForm.submit();
};

BoardArticle.readForm = function(seq){
	readForm.articleSeq.value = seq;
	readForm.submit();
};

BoardArticle.createFrom = function(){
	createForm.submit();
};

BoardArticle.createOrUpdate = function(){
	if($.FORM.isEmptyRtnMsg(createAction.title, "제목을 입력해 주세요")){
		return;
	}
	if($.FORM.isEmptyRtnMsg(createAction.name, "이름을 입력해 주세요")){
		return;
	}	
	if(createAction.email.value !=""){
		if(!$.FORM.isValidEmail(createAction.email)){
			return;
		}
	}
	if($.FORM.isEmptyRtnMsg(createAction.content, "내용을  입력해 주세요")){
		return;
	}	
	createAction.submit();
};

BoardArticle.updateFrom = function(seq){
	updateForm.articleSeq.value = seq;
	updateForm.submit();
};

BoardArticle.removeAction = function(seq){
	if(confirm("삭제 하시겠습니까?")){
		removeAction.articleSeq.value = seq;
		removeAction.submit();
	}
};

BoardArticle.commentCreateAction = function(seq){
	if($.FORM.isEmptyRtnMsg(commentCreateAction.name, "이름을 입력해 주세요")){
		return;
	}
	if($.FORM.isEmptyRtnMsg(commentCreateAction.passwd, "비밀번호를 입력해 주세요")){
		return;
	}	
	if($.FORM.isEmptyRtnMsg(commentCreateAction.content, "내용을 입력해 주세요")){
		return;
	}	
	commentCreateAction.submit();
};

BoardArticle.commentRemoveAction = function(commentSeq){
	if(confirm("삭제 하시겠습니까?")){
		commentRemoveAction.commentSeq.value = commentSeq;
		commentRemoveAction.submit();
	}
};