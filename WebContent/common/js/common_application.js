// 현재 웹 어플에 의존적인 함수 정의
{
	$.APP = new Object();

	// 이미지 업로드 창 
	$.APP.openImageUpload = function(){
		$.POPUP.popupWindowCenter("/image/upload.do", "imageupload", 500, 600, 
				false, false, false);
	};
}