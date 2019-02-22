//On Save Button Click
var lastDateList = [31,28,31,30,31,30,31,31,30,31,30,31];

$(document).ready(function() {
	//날짜, 년월일 변수 생성
	var date  = new Date();
	var year  = date.getFullYear();
	var month = date.getMonth()+1;
	var date  = date.getDate();	

	drawCal(year, month, date);
});

//달력 생성 함수
function drawCal(year, month, date) { 
	
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
	cal += 		'<td class="cal day th">MONDAY</th>';
	cal += 		'<td class="cal day th">TUESDAY</th>';
	cal += 		'<td class="cal day th">WEDNESDAY</th>';
	cal += 		'<td class="cal day th">THURSDAY</th>';
	cal += 		'<td class="cal day th">FRIDAY</th>';
	cal +=	'</tr>';
	
	//월~금 달력
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

	var dateDivHeight = 700/rowCnt;
	var dateDivWidth = 1824/5;
	$(".cal.wrap.div").css("height", dateDivHeight);
	$(".cal.wrap.div").css("width", dateDivWidth);
	
	setSchedule(); //Set Schedule List
	setDefaultValue(); //Set modal default value
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
				addCode = '<p class="p_schedule" style="color:#'+schedule.offerProgram.color+';"'
					+'data-offer_code="'+schedule.offer.code
					+'"data-schedule_code="'+schedule.schedule.code
					+'"data-date="'+schedule.schedule.simpleDate
					+'"data-branch_code="'+schedule.scc.branchCode
					+'"data-scc_code="'+schedule.scc.sccCode
					+'"data-offer_program_code="'+schedule.offerProgram.code
					+'">'
					+schedule.scc.name+'</p>'; //추가될 코드
				
				destDiv.append(addCode); //코드 추가
			})
		}
	})
}

//On create clicked
$(document).on("click", ".cal.wrap.div",function(){
	var thisYear  = $(".cal.year").text();
	var	thisMonth = $(".cal.month").text();
	var date = pad($(this).children(".cal.date.div").text(),2);
	$(".m1.input_date").val(thisYear+"-"+thisMonth+"-"+date);
	setOfferProgramList();
});

//On Create clicked Set ProgramList
function setOfferProgramList(setList, value){
	var dest = $(".m1.input.program.select");
	var regMonth = $(".cal.year").text()+"-"+$(".cal.month").text()+"-01";
	var query = {
		regMonth : regMonth
	};

	dest.children().remove(); //기존에 append된 정보는 삭제 
	
	$.ajax({
		url  : "/schedule/getOfferProgram",
		type : "post",
		data : query,
		success : function(data){
			var addCode='<option selected="selected" value="">- 선택 -</option>';
			$.each(data, function(idx, data) {
				addCode += 
					"<option value='"+data.offerProgram.code+"' "+
						"style='color:#"+data.offerProgram.color+"';>" +
						data.program.name+"&nbsp&nbsp("+data.agency.name+")"+
					"</option>"
			})
			dest.append(addCode); //코드 추가
			if(setList!=null && value!=null){
				setList.val(value);
			}
		}
	})
};

//Create
$(document).on("click",".m1.btn_create",function() {
	var dateStrList = getDateList(); //ajax로 전송할 배열
	
	var query = {
			branchCode : 	$(".m1.input.scc.select option:selected").data("branch_code"),
			sccCode:		$(".m1.input.scc.select option:selected").data("scc_code"),
			program:		$(".m1.input.program.select").val(),
			activeUser:		0,
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


//주간반복, 요일 체크에 따라 Date Str 생성
function getDateList(){
	var thisYear  = $(".cal.year").text();
	var	thisMonth = $(".cal.month").text();
	
	var dateStrList = new Array(); //ajax로 전송할 배열
	var checkedDay = new Array(); //선택된 요일 배열
	
	if($(".m1.checkbox.week").is(":checked")){ //매주 항목이 선택
		$(".m1.day:checked").each(function() { //선택된 요일을 배열에 담음
			checkedDay.push($(this).val());
		});
	} else{ //매주 항목이 선택되지 않음
		dateStrList.push($(".m1.input_date").val());
	};
	
	var lastDay=lastDateList[parseInt(thisMonth)-1]; //이번 달 마지막 날
  
	var checkDate = new Date(); //요일을 체크할 Year/Month Set
	checkDate.setFullYear(thisYear);
	checkDate.setMonth(thisMonth);
	
	for(i=1; i<=lastDay; i++){
		checkDate.setDate(i); //체크할 Date Set
		
		for(j=0; j<=checkedDay.length; j++){ //체크할 날짜의 요일과 선택된 요일 배열 비교
			if(checkedDay[j]==checkDate.getDay()){
				dateStrList.push(checkDate.getFullYear()+"-"+	
					pad(checkDate.getMonth(),2)+"-"+
					pad(checkDate.getDate(),2)); //배열에 스케쥴을 입력할 날짜 push
			} 
		}
	}
	
	return dateStrList;
}


//Modify button clicked
$(document).on("click", ".p_schedule",function(){
	$(this).addClass("modifying");
	

	$(".m1.input_date").val($(this).data("date"));
	$(".m1.input.scc.select").val($(this).data("branch_code")+"-"+$(this).data("scc_code"));
	
	//program list 가져오기
	setOfferProgramList($(".m1.input.program.select"), $(this).data("offer_program_code"));
	
	$(".m1.btn_create").text("수정").attr("class","m1 btn_modify_save");  //save 버튼 변경
	$(".m1.p_btn").append('<button class="m1 btn_delete">삭제</button>');  //삭제 버튼 추가
	$(".m1.input.wrap.mode.div").css("display","block"); //Show mode select radio button
	$(".mode_all").prop("checked", true); 	//Mode Default 
	setCheckedValue();

	return false;
});

//Modify AJAX
$(document).on("click", ".m1.btn_modify_save",function(){
	var dateStrList = getDateList(); //ajax로 전송할 배열
	var query = {
			code:			$(".modifying").data("offer_code"),
			offer:			$(".modifying").data("offer_code"),
			branchCode: 	$(".m1.input.scc.select option:selected").data("branch_code"),
			sccCode:		$(".m1.input.scc.select option:selected").data("scc_code"),
			program:		$(".m1.input.program.select").val(),
			activeUser:		0,
			schCode:		$(".modifying").data("schedule_code"),
			dateStr:		$(".m1.input_date").val(),
			modeFlag:		$("input[name=mod]:checked").val(), //0: 전체 수정, 1:선택날짜 수정
			dateStrList:	dateStrList 
		};
		
		$.ajax({
			url  : "/schedule/modifySchedule",
			type : "post",
			data :  query,
			traditional : true,
			success : function(data){
				setSchedule();
		}
	})
});

//Delete AJAX
$(document).on("click", ".m1.btn_delete",function(){
	var query = {
			schCode:	$(".modifying").data("schedule_code"),
			offerCode:	$(".modifying").data("offer_code"),
			modeFlag:	$("input[name=mod]:checked").val() //0: 전체 수정, 1:선택날짜 수정
		};
		
		$.ajax({
			url  : "/schedule/deleteSchedule",
			type : "post",
			data :  query,
			success : function(data){
				setSchedule();
		}
	})
});

//Modal Toggle
$(document).on("click",".m1.btn_create, .m1.modal_background, .m1.btn_modify_save, " +
		".m1.btn_delete, .m1.btn_reset",function() {
	clearM1All();
});

$(document).on("click",".m1.btn_create, .m1.btn_reset, .m1.btn_modify_save, .m1.btn_delete, .m1.modal_background, " +
		".cal.date.td.usable, .p_schedule",function() {
	$(".m1.modal, .m1.modal_background").toggle();
});


//Close, Save시 Cat1 input clear
function clearM1All() {
	$(".m1.input_date").val("");
	$(".m1.input.scc.select").val("");
	$(".m1.input.program.select").val("");
	$(".m1.checkbox.week").prop('checked', false);
	$(".m1.checkbox.day").prop('checked', false);
	
	setDefaultValue(); //Set begin, end date 
	$(".modifying").removeClass("modifying"); //Modifying class
	$(".m1.btn_delete").remove(); //Delete button
	$(".m1.btn_modify_save").text("추가").attr("class","m1 btn_create"); //Modify button
	$(".m1.input.wrap.mode.div").css("display","none"); //Modify mode select button
	$(".m1.checkbox.day").prop("disabled", true); //checkbox disable
	$(".m1.input_date").prop("disabled", false); //input date disable
	
	$(".mode_all").prop("checked", false); //Mode
	$(".mode_one").prop("checked", false); //Mode
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




//n에 width 자리수에 맞게 0 추가
function pad(n, width) {
	n = n + '';
	return n.length >= width ? n : new Array(width - n.length + 1).join('0')+n;
}


//Weekly checkBox Control
$(document).on("change",".m1.checkbox.week, input[type=radio][name=mod]",function() {
	setCheckedValue();
});

function setCheckedValue(){
	var week = $(".m1.checkbox.week");
	var mode = $("input[type=radio][name=mod]:checked");
	console.log("change: "+mode.val());
	
	if(mode.val()==1){
		week.prop("checked", false);
	} else if(mode.val()==0){
		week.prop("checked", true);
	}
	
	if(week.is(':checked')){
		enableDayCheck();
	} else {
		disableDayCheck();
	}
}

function disableDayCheck(){
	$(".m1.checkbox.day").prop("disabled", true);
	$(".m1.checkbox.day").prop('checked', false);
	$(".m1.input_date").prop("disabled", false);
}

function enableDayCheck(){
	$(".m1.checkbox.day").prop("disabled", false);
	$(".m1.input_date").prop("disabled", true);
}




