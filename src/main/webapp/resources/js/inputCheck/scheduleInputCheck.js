function submitCheckSch(){
	var date 	= $(".m1.input_date");
	var scc 	= $(".m1.input.scc.select");
	var program = $(".m1.input.program.select");
	
	var week = $(".m1.checkbox.week");
	
	//날짜, 주간반복
	if(week.is(":checked")){//주간반복일 때
		//요일 중 하나 이상 선택
		var checkFlag = false;
	    $("input[name='day']").each( function () {
	        if (this.checked) { 
	            checkFlag=true;
	            return false; 
	        }
	    });
	    
	    if(!checkFlag){
	    	alert("요일을 하나 이상 선택해 주세요");
	    	return false;
	    }
	    
	} else { //주간반복이 아닐 때만 날짜 체크
		if(!check(/^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/, date, "날짜가 올바르지 않습니다.")){
			return false;
		}
	}
	
    
    //경로당
	if(scc.val()==""){
		alert("경로당을 선택해주세요.");
		return false;
	}
   	
    //프로그램
	if(program.val()==""){
		alert("프로그램을 선택해주세요.");
		return false;
	}
   	
    return true;
}

function submitCheckPro(){
	var name  = $(".m2.input.pname");
	var begin = $(".m2.input.begin");
	var end   = $(".m2.input.end");
	
	//프로그램 이름
	if(name.val()==""){
		alert("프로그램을 선택해주세요.");
		return false;
	}
    
    //시작 날짜
	if(!check(/^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/, begin, "시작 날짜가 올바르지 않습니다.")){
		return false;
	}
	
    //종료 날짜
	if(!check(/^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/, end, "종료 날짜가 올바르지 않습니다.")){
		return false;
	}
	
    return true;
}

function submitCheckLoad(){
	var load = $(".m3.input.year");
	
	if(load.val()==""){
		alert("불러올 항목을 선택해주세요.");
		return false;
	}
   	
    return true;
}