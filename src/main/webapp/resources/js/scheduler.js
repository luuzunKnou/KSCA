//On Save Button Click
$(document).ready(function() {
	//날짜, 년월일 변수 생성
	var date  = new Date();
	var year  = date.getFullYear();
	var month = date.getMonth()+1;
	var date  = date.getDate();	
	
	drawCal(year, month, date, "5");
	setSchedule();
});

//달력 생성 함수
function drawCal(year, month, date, type) { //type: 휴일 표시 유무(5,7)
	
	
	
	//1일의 요일
	var firstDate = new Date(year, month, 1);
	var firstDay  = firstDate.getDay();
	
	//1~12월달의 마지막 날
	var lastDateList = [31,28,31,30,31,30,31,31,30,31,30,31];
	
	//당월 마지막 날
	var lastDate = lastDateList[month-1];
	
	//윤년 2월달 처리(2월 29일)
	if(year%4==0 && year%100!=0 || year%400==0) {
		lastDate=29;
	}
	
	//Row Count //firstDay: 1일의 요일=빈칸 수
	var rowCnt = Math.ceil( (firstDay+lastDate)/7 );
	
	$(".cal.year").text(year);
	$(".cal.month").text(month);
	
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
		for(var i=1; i<=rowCnt; i++){ //rowCnt 수정 필요
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
	
	code += '<td class="cal date td">';
	code +=		'<div class="cal wrap div">'
	code += 		'<div class="cal date div '+calDate+'">'+calDate+'</div>';
	code += 		'<div class="cal schedule div">'+'</div>';
	code +=		'</div>'
	code += '</td>';
	return code;
}

function setSchedule(){
	var query = {
		thisMonth : $(".cal.month").text(),
		thisYear : $(".cal.year").text()
	};
	
	$.each($(".cal.schedule.div"), function(i, elt) { 
		$(this).empty(); 
	}) //기존에 append된 정보는 삭제
	
	
	$.ajax({
		url  : "/schedule/getSchedule",
		type : "post",
		data : query,
		success : function(data){
			var srcDate;
			var destDiv;
			var addCode;
			$.each(data, function(idx, schedule) {
				var fullDate = schedule.schedule.simpleDate; //controller에서 받은 날짜
				destDate=parseInt(fullDate.split('-')[2])+""; //0 제거
				destDiv = $(".cal.date.div."+destDate).siblings(".schedule"); //SCC name을 입력할 Div
				addCode = '<p class="p_schedule" style="color:#'
					+schedule.offer.color+';">'
					+schedule.scc.name+'</p>'; //추가될 코드
				
				destDiv.append(addCode); //코드 추가
			})
		}
	})
}

$(document).on("click",".btn_create",function() {
	var query = {
			branchCode : "99",
			sccCode:"001",
			program:"1",
			beginDateStr:"2019-02-01",
			endDateStr:"2019-02-28",
			monthlyOper:10,
			activeUser:10,
			color:"FFC740",
			offer:"1",
			dateStr:"2019-02-13"
	};
	
	$.ajax({
		url  : "/schedule/createSchedule",
		type : "post",
		data : query,
		success : function(data){
			setSchedule();
		}
	})
});


//Modal Toggle
$(function(){
	$(document).on("click","btn_create, modal_background, btn_reset",function() {
		clearAll()
	});
	
	$(document).on("click",".btn_create, .btn_reset, .cal.wrap.div," +
			".modal_background",function() {
		$(".modal, .modal_background").toggle();
	});
});

//Close, Save시 Cat1 input clear
function clearAll() {
	console.log("ccc"); 
	$(".input.code").val("");
	$(".input.name").val("");
	$(".p_checkCode").text("");
	$(".m1.modify_save").text("등록").attr("class","m1 save");
	$(".m1.delete").remove();
	$(".m1.dest_cat1_code").val("");
	$(".modifying").removeClass("modifying");
}

//On Mouse Over
$(document).on("mouseover",".cal.wrap.div",function() {
	$(this).css("background", "#FFE5D4");
});

$(document).on("mouseout",".cal.wrap.div",function() {
	$(this).css("background", "#FAFBFC");
});












