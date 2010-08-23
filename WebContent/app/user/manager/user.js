
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

User.readForm = function(userId){
	readForm.userId.value = userId;
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
	if(createAction.passwd.value != createAction.passwdRe.value){
		$.FORM.selectMsg(createAction.passwd, "비밀번호 입력이 서로 다릅니다.");
		return;
	}
	
	if(createAction.email.value != ""){
		if(!$.FORM.isValidEmail(createAction.email)){
			return;
		}
	}
	createAction.submit();
};

User.updateForm = function(userId){
	updateForm.userId.value = userId;
	updateForm.submit();
};

User.removeAction = function(userId){
	if(confirm("삭제 하시겠습니까?")){
		removeAction.userId.value = userId;
		removeAction.submit();
	}
};

User.authMapForm = function(userId){
	authMapForm.userId.value = userId;
	authMapForm.submit();
};

User.authMapAction = function(){
	authMapAction.submit();
}
