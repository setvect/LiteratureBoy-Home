
var BoardArticle = new Object();

BoardArticle.searchForm = function(){
	if($.FORM.isEmptyRtnMsg(searchForm.searchWord, "검색단어를 입력해 주세요")){
		return;
	}
	searchForm.submit();
};

BoardArticle.searchStopForm = function(){
	listForm.searchName.value="";
	listForm.searchCode.value="";
	listForm.submit();
};


BoardArticle.listForm = function(){
	listForm.submit();
};

BoardArticle.readForm = function(code){
	readForm.boardCode.value = code;
	readForm.submit();
};

BoardArticle.createFrom = function(){
	createForm.submit();
};

BoardArticle.createOrUpdate = function(){
	if($.FORM.isEmptyRtnMsg(createAction.boardCode, "코드를 입력해 주세요")){
		return;
	}
	if($.FORM.isEmptyRtnMsg(createAction.name, "이름을 입력해 주세요")){
		return;
	}	
	
	if(!$.STR.isNumber(createAction.uploadLimit.value)){
		$.FORM.selectMsg(createAction.uploadLimit, "숫자로 입력해 주세요");
		return;
	}
	createAction.submit();
};

BoardArticle.updateFrom = function(code){
	updateForm.boardCode.value = code;
	updateForm.submit();
};

BoardArticle.deleteAction = function(code){
	if(confirm("삭제 하시겠습니까?")){
		deleteAction.boardCode.value = code;
		deleteAction.submit();
	}
};
