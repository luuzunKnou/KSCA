function submitCheck(){
	var branch 	= $(".input.branch");
	var scc		= $(".input.scc_code");
	var dong	= $(".input.dong");
	var sccName = $(".input.name");
	var address = $(".input.address");
	var regDate = $(".input.reg_date");
	var site 	= $(".input.site");
	var building= $(".input.building");
	var member 	= $(".input.member");
	var male	= $(".input.male");
	var female 	= $(".input.female");
	var own 	= $(".input.own");
	var tel 	= $(".input.tel");
	var president = $(".input.president");
	var phone 	= $(".input.phone");
	
	//분회 선택
	if(branch.val()=="default"){
		alert("분회를 선택해주세요.");
		return false;
	}
    
	//경로당 코드
	if(!check(/^[0-9]{1,3}$/, scc, "경로당 코드가 올바르지 않습니다.")){
		return false;
	}
	
	//동
	if(tel.val().length!=0 && 
			!check(/^[가-힣0-9-_,.ㆍ ]{2,50}$/, dong, "동 이름이 올바르지 않습니다.")){
		return false;
	}
	
	//경로당 이름
	if(!check(/^[가-힣0-9a-zA-Z-_,.ㆍ ]{2,50}$/, sccName, "경로당 이름이 올바르지 않습니다.")){
		return false;
	}
	
	//소재지(주소)
	if(tel.val().length!=0 && 
			!check(/^[가-힣0-9a-zA-Z-_,.ㆍ() ]{2,255}$/, address, "주소가 올바르지 않습니다.")){
		return false;
	}
	
	//등록일자
	if(tel.val().length!=0 && 
			!check(/^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/, regDate, "등록일자가 올바르지 않습니다.")){
		return false;
	}
	
	//규모-대지
	if(tel.val().length!=0 && 
			!check(/^\d+(?:[.]?[\d]?[\d])?$/, site, "규모(대지)가 올바르지 않습니다.")){
		return false;
	}
	
	//규모-건물
	if(tel.val().length!=0 && 
			!check(/^\d+(?:[.]?[\d]?[\d])?$/, building, "규모(건물)가 올바르지 않습니다.")){
		return false;
	}
	
	//회원 수
	if(tel.val().length!=0 && !check(/^[0-9]{1,3}$/, member, "회원 수가 올바르지 않습니다.")){
		if(member!=male+female){
			alert("회원 수가 남/여 회원수의 합과 일치하지 않습니다.");
		}
		return false;
	}
	
	//회원 수 - 남
	if(tel.val().length!=0 && !check(/^[0-9]{1,3}$/, male, "회원 수(남)가 올바르지 않습니다.")){
		return false;
	}
	
	//회원 수 - 여
	if(tel.val().length!=0 && !check(/^[0-9]{1,3}$/, female, "회원 수(여)가 올바르지 않습니다.")){
		return false;
	}
	
	//소유
	if(own.val()==""){
		alert("소유를 선택해주세요.");
		return false;
	}
	
	//전화번호
   	if(tel.val().length!=0 && !check(/^(010|011|017|018|02|031|032|033|041|042|043|044|051||052|053|054|055|061|062|063|064)-\d{3,4}-\d{4}$/,
   			tel, "전화번호가 올바르지 않습니다.")){
   		return false;
   	}
   	
	//회장 이름
   	if(tel.val().length!=0 && !check(/^[가-힣]{2,10}$/, president, "회장 이름이 올바르지 않습니다.")){
   		return false;
   	}
   	
	//회장 전화번호
   	if(tel.val().length!=0 && !check(/^(010|011|017|018|02|031|032|033|041|042|043|044|051||052|053|054|055|061|062|063|064)-\d{3,4}-\d{4}$/,
   			phone, "회장 연락처가 올바르지 않습니다.")){
   		return false;
   	}

    return true;
}