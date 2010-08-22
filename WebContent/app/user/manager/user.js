
var User = new Object();

User.searchForm = function(){
	if($.FORM.isEmptyRtnMsg(searchForm.searchWord, "검색단어를 입력해 주세요")){
		return;
	}
	searchForm.submit();
};

User.searchStopForm = function(){
	listForm.searchName.value="";
	listForm.searchId.value="";
	listForm.submit();
};


User.listForm = function(){
	listForm.submit();
};

User.readForm = function(code){
	readForm.userId.value = code;
	readForm.submit();
};

User.createFrom = function(){
	createForm.submit();
};

User.createOrUpdate = function(){
	if($.FORM.isEmptyRtnMsg(createAction.userId, "아이디를 주세요")){
		return;
	}
	if($.FORM.isEmptyRtnMsg(createAction.name, "이름을 입력해 주세요")){
		return;
	}	
	if(createAction.email.value != ""){
		if(!$.FORM.isValidEmail(createAction.email)){
			return;
		}
	}
	createAction.submit();
};

User.updateFrom = function(code){
	updateForm.userId.value = code;
	updateForm.submit();
};

User.removeAction = function(code){
	if(confirm("삭제 하시겠습니까?")){
		removeAction.userId.value = code;
		removeAction.submit();
	}
};
