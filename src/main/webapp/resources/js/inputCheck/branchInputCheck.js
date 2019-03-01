function submitCheck(){
	var name = $(".input.branch");
	var code = $(".input.branch_code");
	
	//분회 이름
   	if(!check(/^[가-힣|0-9]{2,50}$/, name, "분회 이름이 올바르지 않습니다.")){
   		return false;
   	};
    
    //분회 코드
   	if(!check(/^[0-9]{2}$/, code, "분회 코드가 올바르지 않습니다.")){
		return false;
	};
	
    return true;
}