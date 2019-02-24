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
	var regMonth = $(".cal.year").text()+"-"+$(".cal.month").text()+"-01";
	var query = {
		srcMonth  : $(".m3.input.year").find(':selected').data('year')+"-"+
					pad($(".m3.input.year").find(':selected').data('month'),2)+"-01",
		destMonth : regMonth
	};
	console.log(query);
	$.ajax({
		url  : "/schedule/loadSchedule",
		type : "post",
		data : query,
		success : function(data){
			setSchedule();
		}
	})
});

//Modal Toggle
$(document).on("click",".m3.btn_reset, .m3.modal_background",function() {
	clearM3All();
});

$(document).on("click",".btn_load, .m3.btn_load_select, .m3.btn_reset, .m3.modal_background",function() {
	$(".m3.modal, .m3.modal_background").toggle();
});


//Close, Save시 clear
function clearM3All() {
	$(".m1.input_date").val("");
	$(".m1.input.scc.select").val("");
}