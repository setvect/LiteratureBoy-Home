function createFrom(){
	createForm.submit();
}

function createOrUpdate(){
	var form = $("#createForm")[0];
	if($.FORM.isEmptyRtnMsg(form.boardCode, "코드를 입력해 주세요")){
		return;
	}
	if($.FORM.isEmptyRtnMsg(form.name, "이름을 입력해 주세요")){
		return;
	}	
	
	if(!$.STR.isNumber(form.uploadLimit.value)){
		$.FORM.selectMsg(form.uploadLimit, "숫자로 입력해 주세요");
		return;
	}
	form.submit();
}
