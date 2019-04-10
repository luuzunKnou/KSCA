$(document).on("click",".sign_up_btn.btn_submit, .sign_up_btn.modify",function() {
	var id 		= $("#inputID");
	var mail 	= $("#inputEmail");
	var pwd 	= $("#inputPassword");
	var pwd2 	= $("#inputPasswordCheck");
	var name 	= $("#inputName");
	var tel 	= $("#inputNumber");
	var city 	= $("#input_city");
	var cityCode= $("#input_city_code");
	var gu 		= $("#input_gu");
	var guCode 	= $("#input_gu_code");
	
	//아이디
	if(!check(/^[a-z|A-Z|0-9]{2,50}$/, id, "아이디가 올바르지 않습니다.")){
		return false;
	}
	
	//이메일
	if(!check(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i,
			mail, "이메일 주소가 올바르지 않습니다.")){
		return false;
	}
    
	//비밀번호
	if(!check(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,20}$/, 
			pwd, "영어, 숫자, 특수문자를 조합한 8~20자리 비밀번호를 사용해 주세요.")){
		return false;
	}
	
	//비밀번호 확인
	if(pwd.val()!=pwd2.val()){
		alert("확인을 위한 비밀번호가 일치하지 않습니다.");
		pwd2.val("");
		pwd2.focus(); 
		return false;
	}
	
	//이름
	if(!check(/^[가-힣]{2,12}$/, name, "이름이 올바르지 않습니다.")){
		return false;
	}
	
	//휴대폰 번호
	if(!check(/^(010|011|017|018|02|031|032|033|041|042|043|044|051||052|053|054|055|061|062|063|064)-\d{3,4}-\d{4}$/,
   			tel, "휴대폰 번호가 올바르지 않습니다.")){
		return false;
	}
	
	//담당 연합회
	if(!check(/^[가-힣|0-9]{2,20}$/, city, "연합회 이름이 올바르지 않습니다.")){
		return false;
	}
	
	//연합회 코드
	if(!check(/^[0-9]{2}$/, cityCode, "연합회 코드가 올바르지 않습니다.")){
		return false;
	}
	
	//담당 지회
	if(!check(/^[가-힣|0-9]{2,20}$/, gu, "지회 이름이 올바르지 않습니다.")){
		return false;
	}
	
	//지회 코드
	if(!check(/^[0-9]{2}$/, guCode, "지회 코드가 올바르지 않습니다.")){
		return false;
	}
});