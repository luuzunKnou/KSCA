$(document).ready(function() {
	setOfferProgram();
});

function setOfferProgram(){
	var regMonth = $(".cal.year").text()+"-"+$(".cal.month").text()+"-01";
	var query = {
		regMonth : regMonth
	};
	
	$.each($(".pro_list_table tr"), function(i, elt) { 
		if(i!=0){ //첫 번째 tr 제외(th)
			$(this).empty(); 
		}
	}) //기존에 append된 정보는 삭제
	
	$.ajax({
		url  : "/schedule/getOfferProgram",
		type : "post",
		data : query,
		success : function(data){
			var destTable = $(".pro_list_table");
			
			$.each(data, function(idx, data) {
				var addCode = "<tr>" 
					+"<td>"+data.program.name+"  ("+ data.agency.name +")"+"</td>"
					+"<td style='background-color:#"+data.offerProgram.color+";'></td>"
					+"<td>"+data.offerProgram.simpleBeginDate+"</td>"
					+"<td>"+data.offerProgram.simpleEndDate+"</td>"
					+"</tr>";
				destTable.append(addCode); //코드 추가
			})
		}
	})
}


//On Call create
$(document).on("click", ".cal.wrap.div",function(){
	var thisYear  = $(".cal.year").text();
	var	thisMonth = $(".cal.month").text();
	var date = pad($(this).children(".cal.date.div").text(),2);
	
	$(".m1.input_date").val(thisYear+"-"+thisMonth+"-"+date);
});

//Create
$(document).on("click",".m1.btn_create",function() {

});



//Modify AJAX
$(document).on("click", ".btn_modify_save",function(){
	
});

//Delete AJAX
$(document).on("click", ".p_schedule",function(){
	
});

//Modal Toggle
$(document).on("click",".m2.modal_background, .m2.btn_reset",function() {
	clearAll();
});

$(document).on("click",".btn_pro_manager, .m2.btn_create, .m2.modal_background, .m2.btn_reset",function() {
	$(".m2.modal, .m2.modal_background").toggle();
});


//Close, Save시 Cat1 input clear
function clearAll() {
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
}


//n에 width 자리수에 맞게 0 추가
function pad(n, width) {
	n = n + '';
	return n.length >= width ? n : new Array(width - n.length + 1).join('0')+n;
};







