/* Create Branch AJAX */
$(document).on("click",".m1.save",function() {
	var query = {
		code  : $(".m1.name").val(),
		name  : $(".m1.code").val()
	};

	$.ajax({
		url  : "/cat/createCat1",
		type : "post",
		data : query,
		success : function(data){
			$(".cat_list_table").append(
				'<tr class="cat1_list_tr">'+
					'<td rowspan="2" class="list name1">'+data.name+'</td>'+
					'<td rowspan="2" class="list code1">'+data.code+'</td>'+
					'<tr class="cat2_list_tr">'+
						'<td class="list name2">-</td>'+
						'<td class="list code2">'+
							'<span class="code2_cat1">'+data.code+'</span>'+
							'<span class="code2_code"> -</span>'+
						'</td>'+
					'</tr>'+
				'</tr>'
			);
			alert("등록되었습니다.");
			clear();
		}
	})
});

/* Click modify Button*/
$(document).on("click",".list.name1, .list.code1",function() {
	//Cat1 name, code 가져오기
	var modifyingTr = $(this).parent();
	modifyingTr.addClass("modifying");
	
	var cat1Name = $(this).parent().children(".list.name1").text();
	var cat1Code = $(this).parent().children(".list.code1").text();
	
	$(".m1.input.name").val(cat1Name);
	$(".m1.input.code").val(cat1Code);
	$(".m1.dest_cat1_code").val(cat1Code);
	
	//save 버튼 변경
	$(".m1.save").text("수정").attr("class","m1 modify_save");
	
	//삭제 버튼 추가
	$(".modal.m1").append('<button class="m1 delete">삭제</button>');
});

/* Update Branch AJAX */
$(document).on("click",".m1.modify_save",function() {
	var query = {
		destCode : $(".m1.dest_cat1_code").val(),
		code 	 : $(".m1.input.code").val(),
		name	 : $(".m1.input.name").val()
	};
	
	var modifyingTr=$(".modifying");
	
	$.ajax({
		url  : "/cat/updateCat1",
		type : "post",
		data : query,
		success : function(data){
			modifyingTr.children(".list.name1").text(data.name);
			modifyingTr.children(".list.code1").text(data.code);
			
			modifyingTr.removeClass("modifying");
			
			alert("수정되었습니다.");
			clear();
		}
	})
});

/* Delete Button AJAX*/ 
$(document).on("click",".m1.delete",function() {
	var result = confirm("정말 삭제하시겠습니까?");
	if(result){
		var query = {
			code : $(".m1.input.code").val()
		};
		
		$.ajax({
			url  : "/cat/removeCat1",
			type : "post",
			data : query,
			success : function(data){
				if(data=="ERROR:cascade"){
					alert("하위 항목이 존재하므로 삭제할 수 없습니다.");
					clear();

				} else {
					//rowspan 사용 시 하위 tr이 child가 아닌 sibiling형태로 추가됨..
					$(".list.code1:contains("+data+")").parent().next().remove();
					$(".list.code1:contains("+data+")").parent().remove();
					
					alert("삭제되었습니다.");
					clear();
				}
			}
		})
	}
});

//Check Duplication Branch Code AJAX
$(document).on("keyup",".branch_code",function() {
	var query = {
		areaCode   : $(".city_code").val()+"-"+$(".gu_code").val(),
		branchCode : $(".branch_code").val()
	};
	
	$.ajax({
		url  : "/branch/checkBranch",
		type : "post",
		data : query,
		success : function(data){
			if(data == 0){ //중복
				$(".p_checkCode").text("이미 존재하는 코드입니다.");
				$(".p_checkCode").css("color","red");
				$(".save").prop("disabled",true);
				$(".modify_save").prop("disabled",true);
				
			} else { //사용가능
				$(".p_checkCode").text("등록 가능한 코드입니다.");
				$(".p_checkCode").css("color","#2EB74E");
				$(".save").prop("disabled",false);
				$(".modify_save").prop("disabled",false);

			}
		}
	});
});

//Modal Toggle
$(function(){
	$(document).on("click",".btn_create, .modal_background, .close",function() {
		clear();
	});
	
	$(document).on("click",".m1.save, .m1.modify_save, .list.name1, .list.code1," +
			".btn_create.cat1, .m1.delete, .m1.modal_background, .m1.close",function() {
		$(".modal.m1, .m1.modal_background").toggle();
	});
});

//Close, Save시 Branch input clear
function clear() {
	$(".input.code").val("");
	$(".input.name").val("");
	$(".p_checkCode").text("");
	$(".m1.modify_save").text("등록").attr("class","m1 save");
	$(".m1.delete").remove();
}

//n에 width 자리수에 맞게 0 추가
function pad(n, width) {
	n = n + '';
	return n.length >= width ? n : new Array(width - n.length + 1).join('0')+n;
}

//On Mouse Over
$(document).on("mouseover",".cat_list_table td",function() {
	$(this).css("background", "#FFE5D4");
});

$(document).on("mouseout",".cat_list_table td",function() {
	$(this).css("background", "#FAFBFC");
});