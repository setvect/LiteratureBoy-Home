
var BoardManager = new Object();

BoardManager.searchForm = function(){
	if($.FORM.isEmptyRtnMsg(document.searchForm.searchWord, "검색단어를 입력해 주세요")){
		return;
	}
	document.searchForm.submit();
};

BoardManager.searchStopForm = function(){
	document.listForm.searchName.value="";
	document.listForm.searchCode.value="";
	document.listForm.submit();
};


BoardManager.listForm = function(){
	document.listForm.submit();
};

BoardManager.readForm = function(code){
	document.readForm.boardCode.value = code;
	document.readForm.submit();
};

BoardManager.createFrom = function(){
	document.createForm.submit();
};

BoardManager.createOrUpdate = function(){
	if($.FORM.isEmptyRtnMsg(document.createAction.boardCode, "코드를 입력해 주세요")){
		return;
	}
	if($.FORM.isEmptyRtnMsg(document.createAction.name, "이름을 입력해 주세요")){
		return;
	}	
	
	if(!$.STR.isNumber(document.createAction.uploadLimit.value)){
		$.FORM.selectMsg(document.createAction.uploadLimit, "숫자로 입력해 주세요");
		return;
	}
	document.createAction.submit();
};

BoardManager.updateForm = function(code){
	document.updateForm.boardCode.value = code;
	document.updateForm.submit();
};

BoardManager.removeAction = function(code){
	if(confirm("삭제 하시겠습니까?")){
		document.removeAction.boardCode.value = code;
		document.removeAction.submit();
	}
};
