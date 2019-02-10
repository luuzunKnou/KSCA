/* Create Cat2 AJAX */
$(document).on("click",".m2.save",function() {
	var query = {
		code  : $(".m2.cat2.code").val(),
		name  : $(".m2.cat2.name").val(),
		cat1  : $(".m2.cat1.code").val()
	};

	$.ajax({
		url  : "/cat/createCat2",
		type : "post",
		data : query,
		success : function(data){
			var targetTd = $(".list.code1:contains("+data.cat1+")");
			incRowSpan(targetTd);
			addNewTr(targetTd, data);

			alert("등록되었습니다.");
			clear();
		}
	})
});

/* Click modify Button*/
$(document).on("click",".list.name2, .list.code2",function() {
	//Cat1 name, code 가져오기
	var modifyingTr = $(this).parent();
	modifyingTr.addClass("modifying");
	
	var cat2Name = $(this).parent().children(".list.name2").text();
	var cat2Code = $(this).parent().find(".code2_code").text();
	var cat1Code = $(this).parent().find(".code2_cat1").text();
	
	//set Cat1 Option
	$(".m2.input.cat1.code").val(cat1Code);
	setNameOption();
	
	//Set DestCode
	$(".m2.dest_cat1_code").val(cat1Code);
	$(".m2.dest_cat2_code").val(cat2Code);
	
	//set Cat2 Text
	$(".m2.input.cat2.name").val(cat2Name);
	$(".m2.input.cat2.code").val(cat2Code);
	
	//save 버튼 변경
	$(".m2.save").text("수정").attr("class","m2 modify_save");
	
	//삭제 버튼 추가
	$(".modal.m2").append('<button class="m2 delete">삭제</button>');
});

/* Update Cat2 AJAX */
$(document).on("click",".m2.modify_save",function() {
	var destCat1 = $(".m2.dest_cat1_code").val();
	var query = {
		destCode : $(".m2.dest_cat2_code").val(),
		destCat1 : destCat1,
		cat1	 : $(".m2.input.cat1.code").val(),
		code 	 : $(".m2.input.cat2.code").val(),
		name	 : $(".m2.input.cat2.name").val()
	};
	
	var modifyingTr=$(".modifying");
	
	$.ajax({
		url  : "/cat/updateCat2",
		type : "post",
		data : query,
		success : function(data){
			modifyingTr.children(".list.name2").text(data.name);
			modifyingTr.children(".list.code2").text(data.cat1+" "+data.code);
			
			//Cat1 Code 변경 시
			if(data.cat1 != destCat1){
				var targetTd = $(".list.code1:contains("+data.cat1+")");
				incRowSpan(targetTd);
				addNewTr(targetTd, data);
				//remove module 필요
			}
			
			alert("수정되었습니다.");
			clear();
		}
	})
	modifyingTr.removeClass("modifying");
});

/* Delete Button AJAX*/ 
$(document).on("click",".m2.delete",function() {
	var result = confirm("정말 삭제하시겠습니까?");
	if(result){
		var query = {
			code : $(".m2.input.code").val()
			
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
					
					alert("삭제되었습니다.");
				}
			}
		})
	}
	clear();
});

//Check Duplication Cat2 Code AJAX
//$(document).on("keyup",".m2.input.code",function() {
//	var query = {
//		code : $(".m2.input.code").val() 
//	};
//	
//	$.ajax({
//		url  : "/cat/checkCat1",
//		type : "post",
//		data : query,
//		success : function(data){
//			if(data == 0){ //중복
//				$(".m2.p_checkCode").text("이미 존재하는 코드입니다.");
//				$(".m2.p_checkCode").css("color","red");
//				$(".m2.save").prop("disabled",true);
//				$(".m2.modify_save").prop("disabled",true);
//				
//			} else { //사용가능
//				$(".m2.p_checkCode").text("등록 가능한 코드입니다.");
//				$(".m2.p_checkCode").css("color","#2EB74E");
//				$(".m2.save").prop("disabled",false);
//				$(".m2.modify_save").prop("disabled",false);
//			}
//		}
//	});
//});



//set name, code select option 
$(document).on("change",".m2.cat1.name",function() {
	setCodeOption();
});

$(document).on("change",".m2.cat1.code",function() {
	setNameOption();
});

function setNameOption(){
	var index = $(".m2.cat1.code option").index($(".m2.cat1.code option:selected"));
	$(".m2.cat1.name option:eq("+index+")").prop("selected", true);
}
function setCodeOption(){
	var index = $(".m2.cat1.name option").index($(".m2.cat1.name option:selected"));
	$(".m2.cat1.code option:eq("+index+")").prop("selected", true);
}



//Modal Toggle
$(function(){
	$(document).on("click",".btn_create, .modal_background, .close",function() {
		clear();
	});
	
	$(document).on("click",".m2.save, .m2.modify_save, .list.name2, .list.code2," +
			".btn_create.cat2, .m2.delete, .m2.modal_background, .m2.close",function() {
		$(".modal.m2, .m2.modal_background").toggle();
	});
});

//Close, Save시 Cat2 input clear
function clear() {
	$(".input.code").val("");
	$(".input.name").val("");
	$(".p_checkCode").text("");
	$(".m2.modify_save").text("등록").attr("class","m2 save");
	$(".m2.delete").remove();
}

//targetTd에 Rowspan +1
function incRowSpan(targetTd) {
	var rowspanVal = targetTd.attr("rowspan")*1+1; //*1 : parseInt
	targetTd.attr("rowspan", rowspanVal);
	targetTd.siblings("td").attr("rowspan", rowspanVal);
}

function addNewTr(targetTd, data){
	targetTd.parent().after(
		'<tr class="cat2_list_tr">'+
			'<td class="list name2">'+data.name+'</td>'+
			'<td class="list code2">'+
				'<span class="code2_cat1">'+data.cat1+'</span>'+
				'<span class="code2_code"> '+data.code+'</span>'+
			'</td>'+
		'</tr>'
	);
}