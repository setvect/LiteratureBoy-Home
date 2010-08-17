
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

BoardArticle.deleteAction = function(code){
	if(confirm("삭제 하시겠습니까?")){
		deleteAction.articleSeq.value = seq;
		deleteAction.submit();
	}
};
