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
	
	if(cat2Code=="-"){
		//alert("데이터가 없습니다.");
		$(".modal.m2, .m2.modal_background").toggle();
		clear();
		return;
	}
	
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
	$(".m2.p_btn").append('<button class="m2 delete">삭제</button>');
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
	
	$.ajax({
		url  : "/cat/updateCat2",
		type : "post",
		data : query,
		success : function(data){
			var modifyingTr=$(".modifying");
			modifyingTr.children(".list.name2").text(data.name);
			modifyingTr.find(".code2_cat1").text(data.cat1);
			modifyingTr.find(".code2_code").text(data.code);
			
			//Cat1 Code 변경 시
			if(data.cat1 != destCat1){
				var targetTd = $(".list.code1:contains("+data.cat1+")");
				var removeTd = $(".list.code1:contains("+destCat1+")");
				addNewTr(targetTd, data);
				
				removeTr(removeTd, data, modifyingTr);
			}
			
			alert("수정되었습니다.");
			clear();
		}
	})
});

/* Delete Button AJAX*/ 
$(document).on("click",".m2.delete",function() {
	var modifyingTr=$(".modifying");
	
	/*if($(".m2.input.cat2.code").val()=="-"){
		alert("삭제할 데이터가 없습니다.");
		clear();
		return;
	}*/
	var result = confirm("정말 삭제하시겠습니까?");
	
	if(result){
		var query = {
			cat1 : $(".m2.input.cat1.code").val(),
			code : $(".m2.input.cat2.code").val()
		};
		$.ajax({
			url  : "/cat/removeCat2",
			type : "post",
			data : query,
			success : function(data){
				if(data=="ERROR:cascade"){
					alert("카테고리에 등록된 프로그램이 존재하므로 삭제할 수 없습니다.");
				} else {
					var targetTd = $(".list.code1:contains("+data.cat1+")");
					removeTr(targetTd, data, modifyingTr);
					alert("삭제되었습니다.");
				}
			}
		})
	}
	clear();
});

//Check Duplication Cat2 Code AJAX
$(document).on("keyup change",".m2.input.code, .m2.input.cat1.code",function() {
	var query = {
		code : $(".m2.input.cat2.code").val(),
		cat1 : $(".m2.input.cat1.code").val()
	};
	
	$.ajax({
		url  : "/cat/checkCat2",
		type : "post",
		data : query,
		success : function(data){
			if(data == 0){ //중복
				$(".m2.p_checkCode").text("이미 존재하는 코드입니다.");
				$(".m2.p_checkCode").css("color","red");
				$(".m2.save").prop("disabled",true);
				$(".m2.modify_save").prop("disabled",true);
				
			} else { //사용가능
				$(".m2.p_checkCode").text("등록 가능한 코드입니다.");
				$(".m2.p_checkCode").css("color","#2EB74E");
				$(".m2.save").prop("disabled",false);
				$(".m2.modify_save").prop("disabled",false);
			}
		}
	});
});



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
	$(document).on("click",".m2.btn_create, .m2.modal_background, .m2.close",function() {
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
	$(".m2.dest_cat1_code").val("");
	$(".m2.dest_cat2_code").val("");
	$(".modifying").removeClass("modifying");
}

//targetTd Rowspan +1
function incRowSpan(targetTd) {
	var rowspanVal = targetTd.attr("rowspan")*1+1; //*1 : parseInt
	targetTd.attr("rowspan", rowspanVal);
	targetTd.siblings("td").attr("rowspan", rowspanVal);
}

//add new tr from data
function addNewTr(targetTd, data){
	var nextTr = targetTd.parent().next(); //Category2 tr
	if(nextTr.find(".code2_code").text()=="-"){
		nextTr.remove();
	} else {
		incRowSpan(targetTd);
	}
	
	targetTd.parent().after(
		'<tr class="cat2_list_tr">'+
			'<td class="list name2">'+data.name+'</td>'+
			'<td class="list code2">'+
				'<span class="code2_cat1">'+data.cat1+'</span> '+
				'<span class="code2_code">'+data.code+'</span>'+
			'</td>'+
		'</tr>'
	);
}

//targetTd Remove and Rowspan -1
function removeTr(targetTd, data, modifyingTr){
	var rowspanVal = targetTd.attr("rowspan")*1-1; //*1 : parseInt

	if(rowspanVal==1){ //마지막 항목 삭제
		rowspanVal=2;
		data.name="-";
		data.code="-";
		addNewTr(targetTd, data)
	}
	
	modifyingTr.remove();
	targetTd.attr("rowspan", rowspanVal);
	targetTd.siblings("td").attr("rowspan", rowspanVal);
}


