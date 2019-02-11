//On Save Button Click
$(document).on("click",".save",function() {
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
	$(".input.dest_area_code").val(		modifyingTr.find(".list.area_code").text());;
	$(".input.dest_branch_code").val(	modifyingTr.find(".list.branch_code").text());;
	$(".input.dest_scc_code").val(		modifyingTr.find(".list.scc_code").text());
	
	$(".input.branch").val(		modifyingTr.find(".list.branch_code").html());;
	$(".input.scc_code").val(	modifyingTr.find(".list.scc_code").html());
	$(".input.dong").val(		modifyingTr.children(".list.dong").text());
	$(".input.name").val(		modifyingTr.children(".list.name").text());		
	$(".input.address").val(	modifyingTr.children(".list.address").text());
	$(".input.reg_date").val(	modifyingTr.children(".list.reg_date").text());
	$(".input.site").val(		modifyingTr.children(".list.site").text());
	$(".input.building").val(	modifyingTr.children(".list.building").text());
	$(".input.member").val(		modifyingTr.children(".list.member").text());
	$(".input.male").val(		modifyingTr.children(".list.male").text());
	$(".input.female").val(		modifyingTr.children(".list.female").text());
	$(".input.own").val(		modifyingTr.children(".list.own").text());
	$(".input.tel").val(		modifyingTr.children(".list.tel").text());
	$(".input.president").val(	modifyingTr.children(".list.president").text());
	$(".input.phone").val(		modifyingTr.children(".list.phone").text());
	
	//change button and opacity
	$(".save").text("수정").attr("class","modify");
	$(".sccList").css("opacity",0.2);
});

//Modify Ajax
$(document).on("click",".modify",function() {
	var query = {
		destAreaCode	: $(".input.dest_area_code").val(),
		destBranchCode	: $(".input.dest_branch_code").val(), 
		destSccCode		: $(".input.dest_scc_code").val(),
		
		areaCode 	: $(".input.area_code").text(), 
		branchCode	: $(".input.branch").val(), 
		sccCode   	: $(".input.scc_code").val(),
		dong 		: $(".input.dong").val(),
		name 		: $(".input.name").val(),
		address		: $(".input.address").val(),
		regDateStr	: $(".input.reg_date").val(),
		site 		: $(".input.site").val(),
		building 	: $(".input.building").val(),
		member 		: $(".input.member").val(),
		male 		: $(".input.male").val(),
		female		: $(".input.female").val(),
		own 		: $(".input.own").val(),
		tel	 		: $(".input.tel").val(),
		president 	: $(".input.president").val(),
		phone 		: $(".input.phone").val()
	};
	
	$.ajax({
		url  : "/agency/modifyAgency",
		type : "post",
		data : query,
		success : function(data){
			var modifyingTr=$(".modifying");
			
			modifyingTr.children(".list.area").text(data.areaCode);
			modifyingTr.children(".list.branch").text(data.branchCode);
			modifyingTr.children(".list.scc_code").text(data.sccCode);
			modifyingTr.children(".list.dong").text(data.dong);
			modifyingTr.children(".list.name").text(data.name);		
			modifyingTr.children(".list.address").text(data.address);
			modifyingTr.children(".list.reg_date").text(data.simpleRegDate);
			modifyingTr.children(".list.site").text(data.site);
			modifyingTr.children(".list.building").text(data.building);
			modifyingTr.children(".list.member").text(data.member);
			modifyingTr.children(".list.male").text(data.male);
			modifyingTr.children(".list.female").text(data.female);
			modifyingTr.children(".list.own").text(data.own);
			modifyingTr.children(".list.tel").text(data.tel);
			modifyingTr.children(".list.president").text(data.president);
			modifyingTr.children(".list.phone").text(data.phone);
			
			alert("수정되었습니다.");
			clear();
			
			//change button and opacity
			$(".modify").text("추가").attr("class","save");
			$(".sccList").css("opacity",1.0);
		}
	});
});

//Modify - Reset button
$(document).on("click",".close",function() {
	$(".modify").text("추가").attr("class","save");
	$(".sccList").css("opacity",1.0);
});

function clear(){
	$(".input.branch").val("default"); 
	$(".input.scc_code").val("");
	$(".input.dong").val("");
	$(".input.name").val("");
	$(".input.address").val("");
	$(".input.reg_date").val("");
	$(".input.site").val("0");
	$(".input.building").val("0");
	$(".input.member").val("0");
	$(".input.male").val("0");
	$(".input.female").val("0");
	$(".input.own").val("공설");
	$(".input.tel").val("");
	$(".input.president").val("");
	$(".input.phone").val("");
	$(".modifying").removeClass("modifying");
}