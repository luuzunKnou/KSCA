//1~12월달의 마지막 날
var lastDateList = [31,28,31,30,31,30,31,31,30,31,30,31];

$(document).ready(function() {
	//날짜, 년월일 변수 생성
	var date  = new Date();
	var year  = date.getFullYear();
	var month = date.getMonth();

	drawCal(year, month);
});

//달력 생성 함수
function drawCal(year, month) { 
	//1일의 요일
	var firstDate = new Date(year, month, 1);
	var firstDay  = firstDate.getDay();
	
	
	//당월 마지막 날
	var lastDate = lastDateList[month];
	
	//윤년 2월달 처리(2월 29일)
	if(year%4==0 && year%100!=0 || year%400==0) {
		lastDate=29;
	}
	
	//Row Count ... firstDay: 1일의 요일=빈칸 수
	var rowCnt = Math.ceil( (firstDay+lastDate)/7 );
		
	$(".cal.year").text(year);
	$(".cal.month").text(pad(month+1,2));
	
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
	var calDate= 1;	//달력에 입력될 날짜
	var divCnt = 0;	//실제 생성되는 div 갯수
	var addCode;	//반복문 안에서 더해질 코드
	
	//첫 번째 주 또는 마지막 주가 토/일요일때문에 add되지 않으면 space 한줄 추가되는 현상 발생

	for(var i=1; i<=rowCnt; i++){
		cal += '<tr class="cal date tr">';
		for(var j=1; j<=7; j++){
			if(i==1 && j<=firstDay || calDate>lastDate ){ //앞,뒤 공백
				addCode = '<td class="cal date td space"></td>';
			} else { //날짜 입력
				addCode = createTdCode(calDate);
				calDate++;
			}
			
			if(j%7==1 || j%7==0){ //토,일요일에 해당하는 날짜는 표시하지 않음
				
			} else {
				cal += addCode;
				divCnt+=1;
			}
		}
		cal += '</tr>';
	}
	$(".cal_table").append(cal);

	var lastDay  = new Date(year, month, lastDate).getDay(); //마지막 날의 요일

	if(firstDay==6){  // 첫 날이 토요일
		$(".cal.date.tr").first().remove();
		rowCnt--;
	}
	
	if(lastDay==0){  // 마지막 날이 일요일
		$(".cal.date.tr").last().remove();
		rowCnt--;
	}
	
	//set width and height
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

//월 변경
$(document).on("click",".cal.left, .cal.right",function() {
		
	var date  = new Date();
	var year  =  $(".cal.year").text();
	var month =  $(".cal.month").text(); //0~11
	
	if($(this).attr('class').match("right")){
		if(month==12){
			year++;
			month=1;
		}else {
			month++;
		}
	} else {
		if(month==1){
			year--;
			month=12;
		}else {
			month--;
		}
	}
	
	$(".t_header").nextAll().remove();
	drawCal(year, month-1);
});

//click Year or Month
$(document).on("click",".cal.year, .cal.month",function() {
	var changeCode = "";
	changeCode += "<input type='text' class='modifying "+$(this).data("tag")+
			"' value='"+$(this).text()+"'>";
	
	$(this).html(changeCode);
	$(".modifying").focus();
	
	//set .css("","")
	$(".modifying").css("width","50px").css("font-family","Cabin").css("font-weight","bold")
				   .css("font-size","20px").css("line-height","30px");
	
	$(this).removeClass();
});

//input Enter or FocusOut
$(document).on("keydown blur",".modifying",function(e) {
	if (e.which == 13 || e.type=="focusout") {
		changeCal($(this));
	}
});

//change Calender
function changeCal(destObj){
	destObj.parent().attr("class","cal "+destObj.parent().data("tag"));
	destObj.parent().html(destObj.val());
	
	var date  = new Date();
	var year  =  $(".cal.year").text();
	var month =  $(".cal.month").text()-1; //0~11
	
	if(year>3000 || year<1500){
		year=date.getFullYear();
	} 
	
	if(month>11 || month<0){
		month=date.getMonth();
	}
		
	$(".t_header").nextAll().remove();
	drawCal(year, month);
}

//excel download
$(document).on("click",".btn_download",function(e) {
	var regMonth = $(".cal.year").text()+"-"+$(".cal.month").text()+"-01";
	window.location.href = "ExcelPoi?regMonth="+regMonth;
});

//n에 width 자리수에 맞게 0 추가
function pad(n, width) {
	n = n + '';
	return n.length >= width ? n : new Array(width - n.length + 1).join('0')+n;
}