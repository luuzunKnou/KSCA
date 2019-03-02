$(document).ready(function() {
	setOfferProgram();
	setDefaultValue();
});

function setOfferProgram(){
	var regMonth = $(".cal.year").text()+"-"+$(".cal.month").text()+"-01";
	var query = {
		regMonth : regMonth
	};
	$.each($(".pro_list_table tr"), function(i, elt) { 
		if(i!=0){ //첫 번째 tr(th) 제외
			$(this).empty(); 
		}
	}) //기존에 append된 정보는 삭제 
	
	$.ajax({
		url  : "/schedule/getOfferProgram",
		type : "post",
		data : query,
		success : function(data){
			var destTable = $(".pro_list_table");
			var addCode;
			$.each(data, function(idx, data) {
				addCode += "<tr data-offer_program_code="+data.offerProgram.code+">" 
					+"<td class='list color' data-color='"+data.offerProgram.color+"';>"
					+	"<div class='color_div' style='background-color:#"+data.offerProgram.color+"';></div>"
					+"</td>" 
					+"<td class='list pname' data-pcode="+data.program.code+">"+data.program.name+"  ("+ data.agency.name +")</td>"
					+"<td class='list begin'>"+data.offerProgram.simpleBeginDate+"</td>"
					+"<td class='list end'>"+data.offerProgram.simpleEndDate+"</td>"
					+'<td><button class="m2 btn_modify">수정</button></td>'
					+'<td><button class="m2 btn_delete">삭제</button></td>'
					+"</tr>";
			})
			destTable.append(addCode); //코드 추가
		}
	})
}

//Create
$(document).on("click",".m2.btn_create",function() {
	if(!submitCheckPro()){
		return false;
	};
	
	var query = {
			program	 	 : $(".input.pname").val(),
			regMonthStr	 : $(".cal.year").text()+"-"+$(".cal.month").text()+"-01",
			beginDateStr : $(".input.begin").val(),
			endDateStr 	 : $(".input.end").val(),
			color		 : $(".input.color").val().substring(1)
		}
	$.ajax({
		url  : "/schedule/createOfferProgram",
		type : "post",
		data : query,
		success : function(data){
			setOfferProgram();
		}
	})
	clearM2All();
	return false;
});


//Modify Click
$(document).on("click", ".m2.btn_modify",function(){
	var modifyTr = $(this).parent().parent();
	
	$(".input_offer_program_code").val(modifyTr.data("offer_program_code"));
	$(".input.color").val("#"+modifyTr.children(".color").data("color")); 
	$(".input.pname").val(modifyTr.children(".pname").data("pcode"));
	$(".input.begin").val(modifyTr.children(".begin").text());
	$(".input.end").val(modifyTr.children(".end").text());
	
	//change button and opacity
	$(".m2.btn_create").text("수정").attr("class","m2 btn_modify_save");
	$(".m2.btn_reset").attr("class","m2 btn_modify_reset");

	$(".pro_list_div").css("opacity",0.2);
});

//Modify AJAX
$(document).on("click", ".m2.btn_modify_save",function(){
	if(!submitCheckPro()){
		return false;
	};
	
	var query = {
		code 		 : $(".input_offer_program_code").val(),
		program	 	 : $(".input.pname").val(),
		regMonthStr	 : $(".cal.year").text()+"-"+$(".cal.month").text()+"-01",
		beginDateStr : $(".input.begin").val(),
		endDateStr 	 : $(".input.end").val(),
		color		 : $(".input.color").val().substring(1)
	};
	
	$.ajax({
		url  : "/schedule/modifyOfferProgram",
		type : "post",
		data : query,
		success : function(data){
			setOfferProgram();
			setSchedule();
		}
	})
	
	clearM2All();
	return false;
});

//Delete AJAX
$(document).on("click", ".m2.btn_delete",function(){
	var result = confirm("정말 삭제하시겠습니까?");
	if(result){
		var deleteTr = $(this).parent().parent();
		var query = {
			offerProgramCode : deleteTr.data("offer_program_code") 
		};
		
		$.ajax({
			url  : "/schedule/deleteOfferProgram",
			type : "post",
			data : query,
			success : function(data){
				deleteTr.remove();
				setSchedule();
			}
		})
	}
});


//Modify Reset 
$(document).on("click", ".m2.btn_modify_reset",function(){
	clearM2All();
	return false;
});

//Check Duplication AJAX
$(document).on("change",".m2.input.pname",function() {
	var query = {
		program 	: $(".m2.input.pname").val(),
		regMonthStr	: $(".cal.year").text()+"-"+$(".cal.month").text()+"-01"
	};
	
	$.ajax({
		url  : "/schedule/checkOfferProgram",
		type : "post",
		data : query,
		success : function(data){
			if(data == 0){ //중복
				$("#checkCode").text("이미 존재하는 프로그램입니다.");
				$("#checkCode").css("color","red");
				$(".m2.btn_create").prop("disabled",true);
				$(".m2.btn_modify_reset").prop("disabled",true);
				
			} else { //사용가능
				$("#checkCode").text("등록 가능한 프로그램입니다.");
				$("#checkCode").css("color","#2EB74E");
				$(".m2.btn_create").prop("disabled",false);
				$(".m2.btn_modify_reset").prop("disabled",false);
			}
		}
	});
});



//Modal Toggle
$(document).on("click",".m2.modal_background, .m2.btn_reset",function() {
	clearM2All();
	return false;
});

$(document).on("click",".btn_pro_manager, .m2.modal_background, .m2.btn_reset",function() {
	$(".m2.modal, .m2.modal_background").toggle(); 
});

$(document).on("click",".btn_pro_manager",function() {
	setOfferProgram();
});



//Close, Save시 Cat1 input clear
function clearM2All() {
	$(".m2.input.color").val("#000000");
	$(".m2.input.pname").val("");
	setDefaultValue(); //Set begin, end date 
	$(".m2.btn_modify_save").text("추가").attr("class","m2 btn_create"); //Modify button
	$(".m2.btn_modify_reset").attr("class","m2 btn_reset");
	$(".pro_list_div").css("opacity",1.0);
	$("#checkCode").html("&nbsp");
}

//Set modal default value(begin, end date )
function setDefaultValue(){
	var thisYear  = $(".cal.year").text();
	var	thisMonth = $(".cal.month").text();
	var firstDate = $(".cal.date.div").first().text();
	var lastDate  = $(".cal.date.div").last().text();
	//begin and end date
	$(".input.begin").val(thisYear+"-"+thisMonth+"-"+pad(firstDate,2));
	$(".input.end").val(thisYear+"-"+thisMonth+"-"+pad(lastDate,2));
}
