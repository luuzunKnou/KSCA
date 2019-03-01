function submitCheckCat1(){
	var c1Name= $(".m1.input.cat1.name");
	var c1Code= $(".m1.input.cat1.code");
	
	//Cat1 이름
	if(!check(/^[가-힣0-9a-zA-Z-_,.ㆍ ]{2,50}$/, c1Name, "카테고리 이름이 올바르지 않습니다.")){
		return false;
	}
    
    //Cat1 코드
   	if(!check(/^[0-9]{4}$/, c1Code, "카테고리 코드가 올바르지 않습니다. 4자리 숫자로 입력해 주세요.")){
   		return false;
   	}
   	
    return true;
}

function submitCheckCat2(){
	
	var c1Name=$(".m2.input.cat1.name");
	var c1Code=$(".m2.input.cat1.code");	
	var c2Name= $(".m2.input.cat2.name");
	var c2Code= $(".m2.input.cat2.code");
	
	//Cat1 이름
	if(c1Name.val()==""){
		alert("상위 카테고리를 선택해주세요.");
		return false;
	}
    
    //Cat1 코드
	if(c1Code.val()==""){
		alert("상위 카테고리를 선택해주세요.");
		return false;
	}
   	
	//Cat2 이름
	if(!check(/^[가-힣0-9a-zA-Z-_,.ㆍ ]{2,50}$/, c2Name, "카테고리 이름이 올바르지 않습니다.")){
		return false;
	}
    
    //Cat2 코드
   	if(!check(/^[0-9]{3}$/, c2Code, "카테고리 코드가 올바르지 않습니다. 4자리 숫자로 입력해 주세요.")){
   		return false;
   	}
   	
    return true;
}