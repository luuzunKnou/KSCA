function submitCheck(){
	var name = $(".input.name");
	var manager = $(".input.manager");
	var tel = $(".input.tel");
	
	//기관 이름
	if(!check(/^[가-힣0-9a-zA-Z-_,.ㆍ ]{2,50}$/, name, "기관 이름이 올바르지 않습니다.")){
		return false;
	}
    
    //담당자
   	if(manager.val().length!=0 && !check(/^[가-힣]{2,10}$/, manager, "담당자 이름이 올바르지 않습니다.")){
   		return false;
   	}
   	
    //전화번호
   	if(tel.val().length!=0 && !check(/^(010|011|017|018|02|031|032|033|041|042|043|044|051||052|053|054|055|061|062|063|064)-\d{3,4}-\d{4}$/,
   			tel, "전화번호가 올바르지 않습니다.")){
   		return false;
   	}
   	
    return true;
}