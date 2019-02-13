//On Save Button Click
$(document).ready(function() {
	drawCal();
});

//달력 생성 함수
function drawCal() {
	
	//날짜, 년월일 변수 생성
	var date  = new Date();
	var year  = date.getFullYear();
	var month = date.getMonth();
	var date  = date.getDate();	
	
	//1일의 요일
	var firstDate = new Date(year, month, 1); //필요한가?
	var firstDay  = firstDate.getDay();
	
	//1~12월달의 마지막 날
	var lastDateList = [31,28,31,30,31,30,31,31,30,31,30,31];
	
	//당월 마지막 날
	var lastDate = lastDateList[month];
	
	//윤년 2월달 처리(2월 29일)
	if(year%4 && year%100 !=0 || y%400==0) lastDate=29;
	
	//Row Count //firstDay: 1일의 요일=빈칸 수
	var rowCnt = Math.ceil( (firstDay+lastDate)/7 );
	
	$(".cal.year").text(year);
	$(".cal.month").text(month);
	
	var cal=""; //Calender 코드
	
	//요일 th 입력
	cal +=	'<tr class="cal day tr">'
	cal += 		'<td class="cal day th">일</th>';
	cal += 		'<td class="cal day th">월</th>';
	cal += 		'<td class="cal day th">화</th>';
	cal += 		'<td class="cal day th">수</th>';
	cal += 		'<td class="cal day th">목</th>';
	cal += 		'<td class="cal day th">금</th>';
	cal += 		'<td class="cal day th">토</th>';
	cal +=	'</tr>';
	
	//
	var date=1;
	for(var i=1; i<=rowCnt; i++){
		cal += '<tr class="cal date tr">';
		
		for(var j=1; j<=7; j++){
			if(i==1 && j<=firstDay || date>lastDate ){ //앞,뒤 공백
				cal += '<td class="cal date td space"></td>';
			} else { //날짜 입력
				cal += createTdCode(date);
				date++;
			}
		}
		cal += '</tr>';
	}
	$(".cal_table").append(cal);
}

//td 코드 생성
function createTdCode(date) {
	var code="";
	
	code += '<td class="cal date td">';
	code +=		'<div class="cal div">'
	code += 		'<div class="cal date div">'+date+'</div>';
	code += 		'<div class="cal schedule div">'+'</div>';
	code +=		'</div>'
	code += '</td>';
	return code;
}














