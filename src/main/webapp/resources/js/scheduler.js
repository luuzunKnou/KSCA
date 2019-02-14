//On Save Button Click
$(document).ready(function() {
	//날짜, 년월일 변수 생성
	var date  = new Date();
	var year  = date.getFullYear();
	var month = date.getMonth()+1;
	var date  = date.getDate();	
	
	drawCal(year, month, date, "5");
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
	code += 		'<div class="cal date div">'+calDate+'</div>';
	code += 		'<div class="cal schedule div">'+'</div>';
	code +=		'</div>'
	code += '</td>';
	return code;
}














