/* Create Cat1 AJAX */
$(document).on("click",".m1.save",function() {
	if(!submitCheckCat1()){
		$(".modal.m1, .m1.modal_background").toggle();
		return;
	};
	
	var query = {
		code  : $(".m1.code").val(),
		name  : $(".m1.name").val()
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
							'<span class="code2_cat1">'+data.code+'</span> '+
							'<span class="code2_code">-</span>'+
						'</td>'+
					'</tr>'+
				'</tr>'
			);
			
			var optName = new Option(data.name, data.name);
			var optCode = new Option(data.code, data.code);
			$(".m2.input.cat1.name").append(optName);
			$(".m2.input.cat1.code").append(optCode);
			
			alert("등록되었습니다.");
			clearAll();
			
			$("body").animate({
				scrollTop: $(document).height()-200
			}, 1000);
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
	$(".m1.p_btn").append('<button class="m1 delete">삭제</button>');
});

/* Update Cat1 AJAX */
$(document).on("click",".m1.modify_save",function() {
	if(!submitCheckCat1()){
		$(".modal.m1, .m1.modal_background").toggle();
		return;
	};
	
	var query = {
		destCode : $(".m1.dest_cat1_code").val(),
		code 	 : $(".m1.input.code").val(),
		name	 : $(".m1.input.name").val()
	};
	
	$.ajax({
		url  : "/cat/updateCat1",
		type : "post",
		data : query,
		success : function(data){
			var modifyingTr=$(".modifying");
			modifyingTr.children(".list.name1").text(data.name);
			modifyingTr.children(".list.code1").text(data.code);
			
			$(modifyingTr.siblings().find(".code2_cat1")).each(function() {
				var text = $(this).text();
				$(this).text(text.replace($(".m1.dest_cat1_code").val(),data.code));
			});

			//cat1 코드 수정, 삭제 시 select option 변경
			$(".m2.cat1.code option").each(function(i) {
				if($(".m1.dest_cat1_code").val()==$(this).val()){
					$(this).val(data.code);
					$(this).text(data.code);
					$(".m2.cat1.name option:eq("+i+")").val(data.name);
					$(".m2.cat1.name option:eq("+i+")").text(data.name);
				}
			})
			
			alert("수정되었습니다.");
			clearAll(); 
		}
	})
});

/* Delete Button AJAX*/ 
$(document).on("click",".m1.delete",function() {
	var code = $(".m1.input.code").val();
	var result = confirm("정말 삭제하시겠습니까?");
	if(result){
		var query = {
			code : code
		};
		
		$.ajax({
			url  : "/cat/removeCat1",
			type : "post",
			data : query,
			success : function(data){
				if(data=="ERROR:cascade"){
					alert("하위 항목이 존재하므로 삭제할 수 없습니다.");
				} else {
					//rowspan 사용 시 하위 tr이 child가 아닌 sibiling형태로 추가됨..
					$(".list.code1:contains("+data+")").parent().next().remove();
					$(".list.code1:contains("+data+")").parent().remove();
				
					//cat1 삭제시 select option 삭제
					$(".m2.cat1.code option").each(function(i) {
						console.log(code+":::"+$(this).val())
						if(code==$(this).val()){
							$(this).remove();
							$(".m2.cat1.name option:eq("+i+")").remove();
						}
					})
					
					alert("삭제되었습니다.");
				}
			}
		})
	}
	clearAll();
});

//Check Duplication Cat1 Code AJAX
$(document).on("keyup",".m1.input.code",function() {
	var query = {
		code : $(".m1.input.code").val() 
	};
	
	$.ajax({
		url  : "/cat/checkCat1",
		type : "post",
		data : query,
		success : function(data){
			if(data == 0){ //중복
				$(".m1.p_checkCode").text("이미 존재하는 코드입니다.");
				$(".m1.p_checkCode").css("color","red");
				$(".m1.save").prop("disabled",true);
				$(".m1.modify_save").prop("disabled",true);
				
			} else { //사용가능
				$(".m1.p_checkCode").text("등록 가능한 코드입니다.");
				$(".m1.p_checkCode").css("color","#2EB74E");
				$(".m1.save").prop("disabled",false);
				$(".m1.modify_save").prop("disabled",false);
			}
		}
	});
});

//Modal Toggle
$(function(){
	$(document).on("click",".m1.btn_create, .m1.modal_background, .m1.close",function() {
		clearAll()
	});
	
	$(document).on("click",".m1.save, .m1.modify_save, .list.name1, .list.code1," +
			".btn_create.cat1, .m1.delete, .m1.modal_background, .m1.close",function() {
		$(".modal.m1, .m1.modal_background").toggle();
	});
});

//Close, Save시 Cat1 input clear
function clearAll() {
	$(".input.code").val("");
	$(".input.name").val("");
	$(".p_checkCode").text("");
	$(".m1.modify_save").text("등록").attr("class","m1 save");
	$(".m1.delete").remove();
	$(".m1.dest_cat1_code").val("");
	$(".modifying").removeClass("modifying");
}

//On Mouse Over
$(document).on("mouseover",".cat_list_table td",function() {
	$(this).css("background", "#FFE5D4");
});

$(document).on("mouseout",".cat_list_table td",function() {
	$(this).css("background", "#FAFBFC");
});