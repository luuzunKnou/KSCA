//On Save Button Click
var lastDateList = [31,28,31,30,31,30,31,31,30,31,30,31];

$(document).ready(function() {
	//날짜, 년월일 변수 생성
	var date  = new Date();
	var year  = date.getFullYear();
	var month = date.getMonth()+1;
	var date  = date.getDate();	
	
	drawCal(year, month, date, "5");
	setSchedule(); //Set Schedule List
	setDefaultValue(); //Set modal default value
});

//달력 생성 함수
function drawCal(year, month, date, type) { //type: 휴일 표시 유무(5,7)
	
	//1일의 요일
	var firstDate = new Date(year, month, 1);
	var firstDay  = firstDate.getDay();
	
	//1~12월달의 마지막 날
	
	//당월 마지막 날
	var lastDate = lastDateList[month-1];
	
	//윤년 2월달 처리(2월 29일)
	if(year%4==0 && year%100!=0 || year%400==0) {
		lastDate=29;
	}
	
	//Row Count //firstDay: 1일의 요일=빈칸 수
	var rowCnt = Math.ceil( (firstDay+lastDate)/7 );
	
	$(".cal.year").text(year);
	$(".cal.month").text(pad(month,2));
	
	var cal=""; //Calender 코드
	
	//요일 th 입력
	cal +=	'<tr class="cal day tr">';
	
	if(type==7){ 
		cal += 	'<td class="cal day th">SUNDAY</th>';}
	
	cal += 		'<td class="cal day th">MONDAY</th>';
	cal += 		'<td class="cal day th">TUESDAY</th>';
	cal += 		'<td class="cal day th">WEDNESDAY</th>';
	cal += 		'<td class="cal day th">THURSDAY</th>';
	cal += 		'<td class="cal day th">FRIDAY</th>';
	
	if(type==7){
		cal += 	'<td class="cal day th">SATURDAY</th>';}
	
	cal +=	'</tr>';
	
	//월~일 달력
	if(type=="7"){
		var calDate=1;
		for(var i=1; i<=rowCnt; i++){
			cal += '<tr class="cal date tr">';
			
			for(var j=1; j<=7; j++){
				if(i==1 && j<=firstDay || calDate>lastDate ){ //앞,뒤 공백
					cal += '<td class="cal date td space"></td>';
				} else { //날짜 입력
					cal += createTdCode(calDate);
					calDate++;
				}
			}
			cal += '</tr>';
		}
		$(".cal_table").append(cal);
	} 
	
	//월~금 달력
	if(type=="5"){
		var calDate=1;
		for(var i=1; i<=rowCnt; i++){
			cal += '<tr class="cal date tr">';
			
			for(var j=1; j<=7; j++){
				if(i==1 && j<=firstDay || calDate>lastDate ){ //앞,뒤 공백
					if(j%7==1 || j%7==0){ //토,일요일에 해당하는 날짜는 표시하지 않음
						continue;
					}
					cal += '<td class="cal date td space"></td>';
				} else { //날짜 입력
					if(j%7==1 || j%7==0){ //토,일요일에 해당하는 날짜는 표시하지 않음
						calDate++;
						continue;
					}
					cal += createTdCode(calDate);
					calDate++;
				}
			}
			cal += '</tr>';
		}
		$(".cal_table").append(cal);
	}

	var dateDivHeight = 700/rowCnt;
	var dateDivWidth = 1824/type;
	$(".cal.wrap.div").css("height", dateDivHeight);
	$(".cal.wrap.div").css("width", dateDivWidth);
}

//td 코드 생성
function createTdCode(calDate) {
	var code="";
	
	code += '<td class="cal date td usable">';
	code +=		'<div class="cal wrap div">'
	code += 		'<div class="cal date div '+calDate+'">'+calDate+'</div>';
	code += 		'<div class="cal schedule div0">'+'</div>';
	code += 		'<div class="cal schedule div1">'+'</div>';
	code += 		'<div class="cal schedule div2">'+'</div>';
	code +=		'</div>'
	code += '</td>';
	return code;
}

//Set Schedule List
function setSchedule(){
	var query = {
		thisMonth : $(".cal.month").text(), 
		thisYear : $(".cal.year").text()
	};
	
	$.each($(".cal.schedule"), function(i, elt) { 
		$(this).empty(); 
	}) //기존에 append된 정보는 삭제
	
	$.ajax({
		url  : "/schedule/getSchedule",
		type : "post",
		data : query,
		success : function(data){
			var srcDate;
			var addCode;
			var fullDate;

			var destParDiv;
			var destDiv0;
			var destDiv1;
			var destDiv2;
			var destDiv;
			
			$.each(data, function(idx, schedule) {
				fullDate = schedule.schedule.simpleDate; //controller에서 받은 날짜

				destDate=parseInt(fullDate.split('-')[2])+""; //0 제거
	
				//SCC name을 입력할 Div
				destParDiv = $(".cal.date.div."+destDate); 
				
				//Div중 children 수가 가장 작은 것을 찾음
				destDiv0 = destParDiv.siblings(".schedule.div0");
				destDiv1 = destParDiv.siblings(".schedule.div1");
				destDiv2 = destParDiv.siblings(".schedule.div2");
				if(destDiv0.height()<= destDiv1.height()
						&& destDiv0.height() <= destDiv2.height() ){
					destDiv = destDiv0;
				} else if(destDiv1.height() <= destDiv2.height()){
					destDiv = destDiv1;
				} else {
					destDiv = destDiv2;
				}
				
				//addCode
				addCode = '<p class="p_schedule" style="color:#'+schedule.offer.color+';"'
					+'data-offer_code="'+schedule.offer.code
					+'"data-schedule_code="'+schedule.schedule.code
					+'"data-date="'+schedule.schedule.simpleDate
					+'"data-branch_code="'+schedule.scc.branchCode
					+'"data-scc_code="'+schedule.scc.sccCode
					+'"data-program="'+schedule.program.code
					+'"data-color="'+schedule.offer.color
					+'"data-begin_date="'+schedule.offer.simpleBeginDate
					+'"data-end_date="'+schedule.offer.simpleEndDate
					+'">'
					+schedule.scc.name+'</p>'; //추가될 코드
				
				destDiv.append(addCode); //코드 추가
			})
		}
	})
}

//On Call create
$(document).on("click", ".cal.wrap.div",function(){
	var thisYear  = $(".cal.year").text();
	var	thisMonth = $(".cal.month").text();
	var date = pad($(this).children(".cal.date.div").text(),2);
	
	$(".input_date").val(thisYear+"-"+thisMonth+"-"+date);
});

//Create
$(document).on("click",".btn_create",function() {
//	var date = ;
	var thisYear  = $(".cal.year").text();
	var	thisMonth = $(".cal.month").text();
	
	var dateStrList = new Array(); //ajax로 전송할 배열

	var checkedDay = new Array(); //선택된 요일 배열
	
	if($(".checkbox.week").is(":checked")){ //매주 항목이 선택
		$(".day:checked").each(function() { //선택된 요일을 배열에 담음
			checkedDay.push($(this).val());
		});
	} else{ //매주 항목이 선택되지 않음
		dateStrList.push($(".input_date").val());
	};
	
	var lastDay=lastDateList[parseInt(thisMonth)-1]; //이번 달 마지막 날
  
	var checkDate = new Date(); //요일을 체크할 날짜 세팅
	checkDate.setFullYear(thisYear);
	checkDate.setMonth(thisMonth);
	
	for(i=1; i<=lastDay; i++){
		checkDate.setDate(i); //체크할 날짜 세팅
		
		for(j=0; j<=checkedDay.length; j++){ //체크할 날짜의 요일과 선택된 요일 배열 비교
			if(checkedDay[j]==checkDate.getDay()){
				dateStrList.push(checkDate.getFullYear()+"-"+	
					pad(checkDate.getMonth(),2)+"-"+
					pad(checkDate.getDate(),2)); //배열에 스케쥴을 입력할 날짜 push
			} 
		}
	}
	
	var query = {
			branchCode : 	$(".input.scc.select option:selected").data("branch_code"),
			sccCode:		$(".input.scc.select option:selected").data("scc_code"),
			program:		$(".input.program.select").val(),
			regMonthStr:	$(".cal.year").text()+"-"+$(".cal.month").text()+"-01",
			beginDateStr:	$(".input_begin").val(),
			endDateStr:		$(".input_end").val(),
			activeUser:		0,
			color:			$(".input_color").val().substring(1),
			dateStrList:	dateStrList 
	};
	
	$.ajax({
		url  : "/schedule/createSchedule",
		type : "post",
		data :  query,
		traditional : true,
		success : function(data){ 
			setSchedule();
		}
	})
	
});

//Modify button clicked
$(document).on("click", ".p_schedule",function(){
	$(this).addClass("modifying");

	$(".input_date").val($(this).data("date"));
	//$(".input.scc.select").val($(this).data("date"));
	$(".input.program.select").val($(this).data("program"));
	$(".input_color").val("#"+$(this).data("color"));
	$(".input_begin").val($(this).data("begin_date"));
	$(".input_end").val($(this).data("end_date"));
	
	//save 버튼 변경
	$(".btn_create").text("수정").attr("class","btn_modify_save");
	//삭제 버튼 추가
	$(".p_btn").append('<button class="btn_delete">삭제</button>');
	//Show mode select radio button 
	$(".input.wrap.mode.div").css("display","block");
	
	return false;
});

//Disable my mode
$(document).on("change", "input[type=radio][name=mod]",function(){
	if($(this).val()==0){ //0: 전체 수정
		$(".input_date").prop("disabled", true);
		$(".input.scc.select").prop("disabled", false);
		$(".input.program.select").prop("disabled", false);
		$(".input_color").prop("disabled", false); 
		$(".input_begin").prop("disabled", false);
		$(".input_end").prop("disabled", false);
		$(".").prop("disabled", false);
		$(".").prop("disabled", false);
		
	} else { // 1:선택날짜 수정
		$(".input_date").prop("disabled", false);
	}
});


//Modify AJAX
$(document).on("click", ".btn_modify_save",function(){
	var query = {
			code:			$(".modifying").data("offer_code"),
			offer:			$(".modifying").data("offer_code"),
			branchCode: 	$(".input.scc.select option:selected").data("branch_code"),
			sccCode:		$(".input.scc.select option:selected").data("scc_code"),
			program:		$(".input.program.select").val(),
			regMonthStr:	$(".cal.year").text()+"-"+$(".cal.month").text()+"-01",
			beginDateStr:	$(".input_begin").val(),
			endDateStr:		$(".input_end").val(),
			activeUser:		0,
			color:			$(".input_color").val().substring(1),
			schCode:		$(".modifying").data("schedule_code"),
			schDate:		$(".input_date").val(),
			modeFlag:		$("input[name=mod]:checked").val() //0: 전체 수정, 1:선택날짜 수정
		};
		
		$.ajax({
			url  : "/schedule/modifySchedule",
			type : "post",
			data :  query,
			traditional : true,
			success : function(data){ 
				$(".input_color").val("#"+data);
			}
		})
});

//Delete AJAX
$(document).on("click", ".p_schedule",function(){
});

//Modal Toggle
$(document).on("click",".btn_create, .modal_background, .btn_modify_save, " +
		".btn_delete, .btn_reset",function() {
	clearAll();
});

$(document).on("click",".btn_create, .btn_reset, .btn_modify_save, .btn_delete, .modal_background, " +
		".cal.date.td.usable, .p_schedule",function() {
	$(".modal, .modal_background").toggle();
});




//Set color by selected program
$(document).on("change", ".input.program.select",function(){
	var thisYear  = $(".cal.year").text();
	var	thisMonth = $(".cal.month").text();
	var regMonth  = thisYear + "-" + thisMonth + "-01";
		
	var query = {
		regMonthStr  :	regMonth,
		programCode  :	$(".input.program.select").val()
	};
	
	$.ajax({
		url  : "/schedule/setColor",
		type : "post",
		data :  query,
		traditional : true,
		success : function(data){ 
			$(".input_color").val("#"+data);
		}
	})
});

//Close, Save시 Cat1 input clear
function clearAll() {
	$(".input_date").val("");
	$(".input.scc.select").val("");
	$(".input.program.select").val("");
	$(".checkbox.week").prop('checked', false);
	$(".checkbox.day").prop('checked', false);
	
	setDefaultValue(); //Set begin, end date 
	$(".modifying").removeClass("modifying"); //Modifying class
	$(".btn_delete").remove(); //Delete button
	$(".btn_modify_save").text("추가").attr("class","btn_create"); //Modify button
	$(".input.wrap.mode.div").css("display","none"); //Modify mode select button
	$(".checkbox.day").prop("disabled", true); //checkbox disable
	$(".input_date").prop("disabled", false); //input date disable

}



//On Mouse Over - create
$(document).on("mouseover",".cal.date.td.usable",function() {
	$(this).css("background", "#FFE5D4");
});

$(document).on("mouseout",".cal.date.td.usable",function() {
	$(this).css("background", "#FFFFFF");
});


//On Mouse Over - modify
$(document).on("mouseover", ".p_schedule",function(){
	$(this).css("text-decoration", "underline");
});

$(document).on("mouseout",".p_schedule",function() {
	$(this).css("text-decoration", "none");
});



//Set modal default value(begin, end date )
function setDefaultValue(){
	var thisYear  = $(".cal.year").text();
	var	thisMonth = $(".cal.month").text();
	var firstDate = $(".cal.date.div").first().text();
	var lastDate  = $(".cal.date.div").last().text();
	//begin and end date
	$(".input_begin").val(thisYear+"-"+thisMonth+"-"+pad(firstDate,2));
	$(".input_end").val(thisYear+"-"+thisMonth+"-"+pad(lastDate,2));
}



//n에 width 자리수에 맞게 0 추가
function pad(n, width) {
	n = n + '';
	return n.length >= width ? n : new Array(width - n.length + 1).join('0')+n;
}



//Weekly checkBox Control
$(document).on("change",".checkbox.week",function() {
    if($(this).is(':checked')) {
    	$(".checkbox.day").prop("disabled", false);
    	$(".input_date").prop("disabled", true);
    } else {
    	$(".checkbox.day").prop("disabled", true);
    	$(".checkbox.day").prop('checked', false);
    	$(".input_date").prop("disabled", false);
    }
});







