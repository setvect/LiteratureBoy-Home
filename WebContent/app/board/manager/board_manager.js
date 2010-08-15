
var BoardManager = new Object();

BoardManager.searchForm = function(){
	if($.FORM.isEmptyRtnMsg(searchForm.searchWord, "검색단어를 입력해 주세요")){
		return;
	}
	searchForm.submit();
};

BoardManager.searchStopForm = function(){
	listForm.searchName.value="";
	listForm.searchCode.value="";
	listForm.submit();
};


BoardManager.listForm = function(){
	listForm.submit();
};

BoardManager.readForm = function(code){
	readForm.boardCode.value = code;
	readForm.submit();
};

BoardManager.createFrom = function(){
	createForm.submit();
};

BoardManager.createOrUpdate = function(){
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

BoardManager.updateFrom = function(code){
	updateForm.boardCode.value = code;
	updateForm.submit();
};

BoardManager.deleteAction = function(code){
	if(confirm("삭제 하시겠습니까?")){
		deleteAction.boardCode.value = code;
		deleteAction.submit();
	}
};
