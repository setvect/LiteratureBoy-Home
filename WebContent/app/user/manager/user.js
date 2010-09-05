
var User = new Object();

User.searchForm = function(){
	if($.FORM.isEmptyRtnMsg(document.searchForm.searchWord, "검색단어를 입력해 주세요")){
		return;
	}
	document.searchForm.submit();
};

User.searchStopForm = function(){
	document.listForm.searchName.value="";
	document.listForm.searchId.value="";
	document.listForm.submit();
};


User.listForm = function(){
	document.listForm.submit();
};

User.readForm = function(userId){
	document.readForm.userId.value = userId;
	document.readForm.submit();
};

User.createFrom = function(){
	document.createForm.submit();
};

User.createOrUpdate = function(){
	if($.FORM.isEmptyRtnMsg(document.createAction.userId, "아이디를 주세요")){
		return;
	}
	if($.FORM.isEmptyRtnMsg(document.createAction.name, "이름을 입력해 주세요")){
		return;
	}	
	if(document.createAction.passwd.value != document.createAction.passwdRe.value){
		$.FORM.selectMsg(document.createAction.passwd, "비밀번호 입력이 서로 다릅니다.");
		return;
	}
	
	if(document.createAction.email.value != ""){
		if(!$.FORM.isValidEmail(document.createAction.email)){
			return;
		}
	}
	document.createAction.submit();
};

User.updateForm = function(userId){
	document.updateForm.userId.value = userId;
	document.updateForm.submit();
};

User.removeAction = function(userId){
	if(confirm("삭제 하시겠습니까?")){
		document.removeAction.userId.value = userId;
		document.removeAction.submit();
	}
};

User.authMapForm = function(userId){
	document.authMapForm.userId.value = userId;
	document.authMapForm.submit();
};

User.authMapAction = function(){
	document.authMapAction.submit();
};
