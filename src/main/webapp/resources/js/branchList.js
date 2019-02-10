/* Create Branch AJAX */
$(document).on("click",".save",function() {
	var query = {
		branch 		: $(".input.branch").val(),
		branchCode	: $(".input.branch_code").val()
	};
	
	var city 	 = $(".input.city").val();
	var cityCode = $(".input.city_code").val();
	var gu 		 = $(".input.gu").val();
	var guCode 	 = $(".input.gu_code").val();
	
	$.ajax({
		url  : "/branch/createBranch",
		type : "post",
		data : query,
		success : function(data){
			$(".branch_list_table").append("<tr class='branch_list_tr'>"
				+"<td>"+data.areaCode+"-"+data.branchCode+"</td>"
				+"<td>"+city+"</td>"
				+"<td>"+cityCode+"</td>"
				+"<td>"+gu+"</td>"
				+"<td>"+guCode+"</td>"
				+"<td class='list_branch'>"+data.branch+"</td>"
				+"<td class='list_branch_code'>"+data.branchCode+"</td>"
				+"<td><button class='btn_modify'>수정</button></td>"
				+"<td><button class='btn_delete'>삭제</button></td>"
				+"</tr>"
			);
			alert("등록되었습니다.");
			clear();
		}
	})
});

/* Click modify Button*/
$(document).on("click",".btn_modify",function() {
	//분회/분회코드 가져오기
	var branch = $(this).parent().siblings(".list_branch").text();
	var branchCode = $(this).parent().siblings(".list_branch_code").text();
	
	$(".dest_branch_code").val(branchCode);
	$(".branch").val(branch);
	$(".branch_code").val(branchCode);
	
	//save 버튼 변경
	$(".save").text("수정").attr("class","modify_save");
});

/* Update Branch AJAX */
$(document).on("click",".modify_save",function() {
	var query = {
		destBranchCode : $(".dest_branch_code").val(),
		branch 		: $(".input.branch").val(),
		branchCode	: $(".input.branch_code").val()
	};
	var modifyingTr=$(".list_branch_code:contains("+$(".dest_branch_code").val()+")").parent();
	
	$.ajax({
		url  : "/branch/updateBranch",
		type : "post",
		data : query,
		success : function(data){
			modifyingTr.children(".list_branch").text(data.branch);
			modifyingTr.children(".list_branch_code").text(data.branchCode);
			alert("수정되었습니다.");
			clear();
		}
	})
});

/* Delete Button AJAX*/ 
$(document).on("click",".btn_delete",function() {
	var result = confirm("정말 삭제하시겠습니까?");
	if(result){
		var query = {
			branchCode : $(this).parent().siblings(".list_branch_code").text()
		};
		
		$.ajax({
			url  : "/branch/removeBranch",
			type : "post",
			data : query,
			success : function(data){
				if(data=="ERROR:def"){
					alert("삭제할 수 없는 항목입니다.");
				} else if(data=="ERROR:cascade"){
					alert("분회에 소속된 경로당이 존재하므로 삭제할 수 없습니다.");
				} else {
					$(".list_branch_code:contains("+data+")").parent().remove();
				}
			}
		})
	}
});

//Check Duplication Branch Code AJAX
$(document).on("keyup",".input.branch_code",function() {
	var query = {
		areaCode   : $(".input.city_code").val()+"-"+$(".input.gu_code").val(),
		branchCode : $(".input.branch_code").val()
	};
	
	$.ajax({
		url  : "/branch/checkBranch",
		type : "post",
		data : query,
		success : function(data){
			if(data == 0){ //중복
				$("#p_checkCode").text("이미 존재하는 코드입니다.");
				$("#p_checkCode").css("color","red");
				$(".save").prop("disabled",true);
				$(".modify_save").prop("disabled",true);
				
			} else { //사용가능
				$("#p_checkCode").text("등록 가능한 코드입니다.");
				$("#p_checkCode").css("color","#2EB74E");
				$(".save").prop("disabled",false);
				$(".modify_save").prop("disabled",false);

			}
		}
	});
});

//Modal Toggle
$(function(){
	$(document).on("click","#modal_background, .close",function() {
		clear();
	});
	
	$(document).on("click",".save, .btn_modify, .modify_save, " +
			".btn_create, #modal_background, .close",function() {
		$("#myModal,#modal_background").toggle();
	});
});

//Close, Save시 Branch input clear
function clear() {
	var cityCode = $(".input.city_code").val();
	var guCode 	 = $(".input.gu_code").val();
	var defaultCode=pad(cityCode,2)+"-"+pad(guCode,2);
	
	$(".input.code").val(defaultCode);
	$(".dest_branch_code").val("");
	$(".input.branch").val("");
	$(".input.branch_code").val("");
	$(".modify_save").text("저장").attr("class","save");
	$("#p_checkCode").text("");
}

//n에 width 자리수에 맞게 0 추가
function pad(n, width) {
	n = n + '';
	return n.length >= width ? n : new Array(width - n.length + 1).join('0')+n;
}

//Key up시 Code 생성
$(document).on("keyup",".input",function() {
	var code = $(".input.city_code").val()+"-"
			  +$(".input.gu_code").val()	+"-"
			  +$(".input.branch_code").val();
	$(".input.code").val(code);
});
