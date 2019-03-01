//On Save Button Click
$(document).on("click",".save",function() {
	if(!submitCheck()){
		return;
	};
	
	var query = {
		name	: $(".input.name").val(),
		manager : $(".input.manager").val(),
		tel		: $(".input.tel").val()
	};
			
	$.ajax({
		url  : "/agency/createAgency",
		type : "post",
		data : query,
		success : function(data){
			$(".agency_list_table").append(
				'<tr class="agency_list_tr">'+
					'<td class="list code" style="display:none;">'+data.code+'</td>'+
					'<td class="list name">'+data.name+'</td>'+
					'<td class="list manager">'+data.manager+'</td>'+
					'<td class="list tel">'+data.tel+'</td>'+
					'<td><button class="btn_modify">수정</button></td>'+
					'<td><button class="btn_delete">삭제</button></td>'+
				'</tr>'
			);
			
			alert("등록되었습니다.");
			clear();

			$("body").animate({
				scrollTop: $(document).height()-200
			}, 1000);
		}
	})
});


//Delete Button
$(document).on("click",".btn_delete",function() {
	var result = confirm("정말 삭제하시겠습니까?");
	if(result){
		var deleteTr = $(this).parent().parent();
		var query = {
			code 	: deleteTr.children(".list.code").text(), 
		};
		
		$.ajax({
			url  : "/agency/removeAgency",
			type : "post",
			data : query,
			success : function(data){
				deleteTr.remove();
			}
		})
	}
});


//Modify Button Click
$(document).on("click",".btn_modify",function() {
	var modifyingTr = $(this).parent().parent();
	modifyingTr.addClass("modifying");
	
	//Set input value
	$(".input.code").val(		modifyingTr.children(".list.code").text());
	$(".input.name").val(		modifyingTr.children(".list.name").text());
	$(".input.manager").val(	modifyingTr.children(".list.manager").text());
	$(".input.tel").val(		modifyingTr.children(".list.tel").text());
	
	//change button and opacity
	$(".save").text("수정").attr("class","modify");
	$(".agencyList").css("opacity",0.2);
});

//Modify Ajax
$(document).on("click",".modify",function() {
	if(!submitCheck()){
		return;
	};
	
	var query = {
		code	: $(".input.code").val(), 
		name	: $(".input.name").val(),
		manager : $(".input.manager").val(),
		tel		: $(".input.tel").val()
	};
	
	$.ajax({
		url  : "/agency/modifyAgency",
		type : "post",
		data : query,
		success : function(data){
			var modifyingTr=$(".modifying");
			
			modifyingTr.children(".list.code").text(data.code);
			modifyingTr.children(".list.name").text(data.name);
			modifyingTr.children(".list.manager").text(data.manager);
			modifyingTr.children(".list.tel").text(data.tel);
			
			alert("수정되었습니다.");
			clear();
			
			//change button and opacity
			$(".modify").text("추가").attr("class","save");
			$(".agencyList").css("opacity",1.0);
		}
	});
});

//Modify - Reset button
$(document).on("click",".close",function() {
	$(".modify").text("추가").attr("class","save");
	$(".agencyList").css("opacity",1.0);
	clear();
});

function clear(){
	$(".input.code").val("");
	$(".input.name").val("");
	$(".input.manager").val("");
	$(".input.tel").val("");
	
	$(".modifying").removeClass("modifying");
}