
var Auth = new Object();

Auth.searchForm = function(){
	if($.FORM.isEmptyRtnMsg(searchForm.searchWord, "검색단어를 입력해 주세요")){
		return;
	}
	searchForm.submit();
};

Auth.searchStopForm = function(){
	listForm.searchName.value="";
	listForm.searchUrl.value="";
	listForm.submit();
};


Auth.listForm = function(){
	listForm.submit();
};

Auth.readForm = function(seq){
	readForm.authSeq.value = seq;
	readForm.submit();
};

Auth.createFrom = function(){
	createForm.submit();
};

Auth.createOrUpdate = function(){

	if($.FORM.isEmptyRtnMsg(createAction.name, "이름을 입력해 주세요")){
		return;
	}	
	if($.FORM.isEmptyRtnMsg(createAction.url, "URL를 입력해 주세요")){
		return;
	}	
	if($.FORM.isEmptyRtnMsg(createAction.parameter, "파라미터를 입력해 주세요")){
		return;
	}	

	createAction.submit();
};

Auth.updateFrom = function(seq){
	updateForm.authSeq.value = seq;
	updateForm.submit();
};

Auth.removeAction = function(seq){
	if(confirm("삭제 하시겠습니까?")){
		removeAction.authSeq.value = seq;
		removeAction.submit();
	}
};
