
var Auth = new Object();

Auth.searchForm = function(){
	if($.FORM.isEmptyRtnMsg(document.searchForm.searchWord, "검색단어를 입력해 주세요")){
		return;
	}
	document.searchForm.submit();
};

Auth.searchStopForm = function(){
	document.listForm.searchName.value="";
	document.listForm.searchUrl.value="";
	document.listForm.submit();
};


Auth.listForm = function(){
	document.listForm.submit();
};

Auth.readForm = function(seq){
	document.readForm.authSeq.value = seq;
	document.readForm.submit();
};

Auth.createFrom = function(){
	document.createForm.submit();
};

Auth.createOrUpdate = function(){

	if($.FORM.isEmptyRtnMsg(document.createAction.name, "이름을 입력해 주세요")){
		return;
	}	
	if($.FORM.isEmptyRtnMsg(document.createAction.url, "URL를 입력해 주세요")){
		return;
	}	
	if($.FORM.isEmptyRtnMsg(document.createAction.parameter, "파라미터를 입력해 주세요")){
		return;
	}	

	document.createAction.submit();
};

Auth.updateForm = function(seq){
	document.updateForm.authSeq.value = seq;
	document.updateForm.submit();
};

Auth.removeAction = function(seq){
	if(confirm("삭제 하시겠습니까?")){
		document.removeAction.authSeq.value = seq;
		document.removeAction.submit();
	}
};
