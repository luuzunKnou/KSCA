function submitCheck(){
	var name= $(".input.name");
	var cat1= $(".input.cat1");
	var cat2= $(".input.cat2");	
	var agency= $(".input.agency"); 
	
	//이름
	if(!check(/^[가-힣0-9a-zA-Z-_,.ㆍ ]{2,50}$/, name, "프로그램 이름이 올바르지 않습니다.")){
		return false;
	}
	
	//Cat1
	if(cat1.val()==""){
		alert("상위 카테고리를 선택해주세요.");
		return false;
	}
    
	//Cat2
	if(cat2.val()==""){
		alert("하위 카테고리를 선택해주세요.");
		return false;
	}
	
	//Agency
	if(agency.val()==""){
		alert("제공 기관을 선택해주세요.");
		return false;
	}
	
    return true;
}