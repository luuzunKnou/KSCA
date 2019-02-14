//On Save Button Click
$(document).on("click",".save",function() {
	var query = {
		name  : $(".input.name").val(),
		cat1  : $(".input.cat1").val(),
		cat2  : $(".input.cat2").val(),
		agency: $(".input.agency").val()
	};
			
	$.ajax({
		url  : "/program/createProgram",
		type : "post",
		data : query,
		success : function(data){
			$(".program_list_table").append(
				'<tr class="program_list_tr">'+
					'<td class="list a_code" style="display:none;">'+data.agency.code+'</td>'+
					'<td class="list code" style="display:none;">'+data.program.code+'</td>'+
					'<td class="list name">'+data.program.name+'</td>'+
					'<td class="list cat1">'+
						'<span class="list cat1 name">'+data.cat1.name+'</span> ('+
						'<span class="list cat1 code">'+data.cat1.code+'</span>)</td>'+
					'<td class="list cat2">'+
						'<span class="list cat2 name">'+data.cat2.name+'</span> ('+
						'<span class="list cat2 code">'+data.cat2.code+'</span>)</td>'+
					'<td class="list a_name">'+data.agency.name+'</td>'+
					'<td class="list a_manager">'+data.agency.manager+'</td>'+
					'<td class="list a_tel">'+data.agency.tel+'</td>'+
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
			code 	: deleteTr.children(".list.code").text() 
		};
		
		$.ajax({
			url  : "/program/removeProgram",
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
	$(".input.code").val(	modifyingTr.children(".list.code").text());
	$(".input.name").val(	modifyingTr.children(".list.name").text());
	$(".input.cat1").val(	modifyingTr.find(".list.cat1.code").text());
	setCat2Option();
	
	setTimeout(function() {
		$(".input.cat2").val(	modifyingTr.find(".list.cat2.code").text());
	}, 500);
	clearTimeout()

	$(".input.agency").val(	modifyingTr.children(".list.a_code").text());
	
	//change button and opacity
	$(".save").text("수정").attr("class","modify");
	$(".programList").css("opacity",0.2);
});

//Modify Ajax
$(document).on("click",".modify",function() {
	var query = {
		code	: $(".input.code").val(), 
		name	: $(".input.name").val(),
		cat1 	: $(".input.cat1").val(),
		cat2 	: $(".input.cat2").val(),
		agency	: $(".input.agency").val()
	};
	
	$.ajax({
		url  : "/program/modifyProgram",
		type : "post",
		data : query,
		success : function(data){
			var modifyingTr=$(".modifying");
			
			modifyingTr.children(".list.code").text(data.program.code);
			modifyingTr.children(".list.a_code").text(data.agency.code);
			modifyingTr.children(".list.name").text(data.program.name);
			modifyingTr.find(".list.cat1.name").text(data.cat1.name);
			modifyingTr.find(".list.cat1.code").text(data.cat1.code);
			modifyingTr.find(".list.cat2.name").text(data.cat2.name);
			modifyingTr.find(".list.cat2.code").text(data.cat2.code);
			modifyingTr.children(".list.a_name").text(data.agency.name);
			modifyingTr.children(".list.a_manager").text(data.agency.manager);
			modifyingTr.children(".list.a_tel").text(data.agency.tel);
			
			alert("수정되었습니다.");
			clear();
			
			//change button and opacity
			$(".modify").text("추가").attr("class","save");
			$(".programList").css("opacity",1.0);
		}
	});
});

//Get Category2 List
$(document).on("change",".input.cat1",function() {
	setCat2Option();
});

//Set Category 2
function setCat2Option(){
	var query = {
			code : $(".input.cat1").val()
		};
		
	var option = '<option selected="selected" value="">- 선택 -</option>';
	$.ajax({
		url  : "/program/getCat2List",
		type : "post",
		data : query,
		success : function(data){
			$.each(data, function (index, item) { 
				option += '<option value="'+item.code+'">'+item.name+' ('+item.code+')'+'</option>';
			});
			
			$(".input.cat2").html(option);
		}
	});
}

//Modify - Reset button
$(document).on("click",".close",function() {
	$(".modify").text("추가").attr("class","save");
	$(".programList").css("opacity",1.0);
	clear();
});

function clear(){
	$(".input.code").val("");
	$(".input.name").val("");
	$(".input.cat1").val("");
	$(".input.cat2").val("");
	$(".input.agency").val("");
	
	$(".modifying").removeClass("modifying");
}