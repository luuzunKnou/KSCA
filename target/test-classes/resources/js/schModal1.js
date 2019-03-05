
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
	if(!submitCheckSch()){
		$(".m1.modal, .m1.modal_background").toggle();
		return;
	};
	
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
	clearM1All();
});


//주간반복, 요일 체크에 따라 Date Str 생성
function getDateList(){
	var thisYear  = $(".cal.year").text();
	var	thisMonth = parseInt($(".cal.month").text())-1;
	
	var dateStrList = new Array(); //ajax로 전송할 배열
	var checkedDay = new Array(); //선택된 요일 배열
	
	if($(".m1.checkbox.week").is(":checked")){ //매주 항목이 선택
		$(".m1.day:checked").each(function() { //선택된 요일을 배열에 담음
			checkedDay.push($(this).val());
		});
	} else{ //매주 항목이 선택되지 않음
		dateStrList.push($(".m1.input_date").val());
	};
	
	var lastDay=lastDateList[thisMonth]; //이번 달 마지막 날
  
	var checkDate = new Date(); //요일을 체크할 Year/Month Set
	checkDate.setFullYear(thisYear);
	checkDate.setMonth(thisMonth);

	
	for(i=1; i<=lastDay; i++){
		checkDate.setDate(i); //체크할 Date Set
		
		for(j=0; j<=checkedDay.length; j++){ //체크할 날짜의 요일과 선택된 요일 배열 비교
			if(checkedDay[j]==checkDate.getDay()){
				dateStrList.push(checkDate.getFullYear()+"-"+	
					pad(checkDate.getMonth()+1,2)+"-"+
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
	if(!submitCheckSch()){
		$(".m1.modal, .m1.modal_background").toggle();
		return;
	};
	
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
	clearM1All();
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
$(document).on("click",".m1.modal_background, .m1.btn_delete, .m1.btn_reset",function() {
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


//Weekly checkBox Control
$(document).on("change",".m1.checkbox.week, input[type=radio][name=mod]",function() {
	setCheckedValue();
});

function setCheckedValue(){
	var week = $(".m1.checkbox.week");
	var mode = $("input[type=radio][name=mod]:checked");
	
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


