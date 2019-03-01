function submitCheck(){
	var name = $(".input.name");
	var manager = $(".input.manager");
	var tel = $(".input.tel");
	
	//분회 선택
	if(cat1.val()==""){
		alert("분회를 선택해주세요.");
		return false;
	}
    
	//경로당 코드
	if(!check(/^[0-9]{3}$/, name, "기관 이름이 올바르지 않습니다.")){
		return false;
	}
	
	//동
	if(!check(/^[가-힣0-9-_,.ㆍ ]{2,50}$/, name, "기관 이름이 올바르지 않습니다.")){
		return false;
	}
	
	//경로당 이름
	if(!check(/^[가-힣0-9a-zA-Z-_,.ㆍ ]{2,50}$/, name, "기관 이름이 올바르지 않습니다.")){
		return false;
	}
	
	//소재지(주소)
	
	//등록일자
	
	//규모-대지
	if(!check(/^\d+(?:[.]?[\d]?[\d])?$/, name, "기관 이름이 올바르지 않습니다.")){
		return false;
	}
	
	//규모-건물
	if(!check(/^\d+(?:[.]?[\d]?[\d])?$/, name, "기관 이름이 올바르지 않습니다.")){
		return false;
	}
	
	//회원 수
	if(!check(/^[0-9]{1,3}$/, name, "기관 이름이 올바르지 않습니다.")){
		if(c!=a+b){
			alert("회원 수가 남/여 회원수의 합과 일치하지 않습니다.");
		}
		return false;
	}
	
	//회원 수 - 남
	if(!check(/^[0-9]{1,3}$/, name, "기관 이름이 올바르지 않습니다.")){
		return false;
	}
	
	//회원 수 - 여
	if(!check(/^[0-9]{1,3}$/, name, "기관 이름이 올바르지 않습니다.")){
		return false;
	}
	
	//소유
	if(cat1.val()==""){
		alert("상위 카테고리를 선택해주세요.");
		return false;
	}
	
	//전화번호
   	if(tel.val().length!=0 && !check(/^(010|011|017|018|02|031|032|033|041|042|043|044|051||052|053|054|055|061|062|063|064)-\d{3,4}-\d{4}$/,
   			tel, "전화번호가 올바르지 않습니다.")){
   		return false;
   	}
   	
	//회장 이름
   	if(manager.val().length!=0 && !check(/^[가-힣]{2,10}$/, manager, "담당자 이름이 올바르지 않습니다.")){
   		return false;
   	}
   	
	//회장 전화번호
   	if(tel.val().length!=0 && !check(/^(010|011|017|018|02|031|032|033|041|042|043|044|051||052|053|054|055|061|062|063|064)-\d{3,4}-\d{4}$/,
   			tel, "전화번호가 올바르지 않습니다.")){
   		return false;
   	}
   	
    //담당자
   	if(manager.val().length!=0 && !check(/^[가-힣]{2,10}$/, manager, "담당자 이름이 올바르지 않습니다.")){
   		return false;
   	}


    return true;
}