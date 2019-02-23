//On create clicked
//$(document).on("click", ".cal.wrap.div",function(){
//	var thisYear  = $(".cal.year").text();
//	var	thisMonth = $(".cal.month").text();
//	var date = pad($(this).children(".cal.date.div").text(),2);
//	$(".m1.input_date").val(thisYear+"-"+thisMonth+"-"+date);
//	setOfferProgramList();
//});

//On Load clicked
$(document).on("click",".m3.btn_load_select",function(){
	alert("??");
	var regMonth = $(".cal.year").text()+"-"+$(".cal.month").text()+"-01";
	var query = {
		regMonth  : regMonth,
		loadYear : $(".m3.input.year").find(':selected').data('year'),
		loadMonth : pad($(".m3.input.year").find(':selected').data('month'),2)
	};
	console.log(query);
	$.ajax({
		url  : "/schedule/loadSchedule",
		type : "post",
		data : query,
		success : function(data){
			
		}
	})
});

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

//Modal Toggle
$(document).on("click",".m3.btn_reset, .m3.modal_background",function() {
	clearM1All();
});

$(document).on("click",".btn_load, .m3.btn_load_select, .m3.btn_reset, .m3.modal_background",function() {
	$(".m3.modal, .m3.modal_background").toggle();
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