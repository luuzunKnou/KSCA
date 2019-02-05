$(document).ready(function(){
	/* Create Branch AJAX */
	$(document).on("click",".save",function() {
		var query = {
			branch 		: $(".branch").val(),
			branchCode	: $(".branch_code").val()
		};
		console.log($(".branch").val());
		console.log(query);
		var city 	 = $(".city").val();
		var cityCode = $(".city_code").val();
		var gu 		 = $(".gu").val();
		var guCode 	 = $(".gu_code").val();
		
		$.ajax({
			url  : "/branch/createBranch",
			type : "post",
			data : query,
			success : function(data){
				if(data.areaCode=="DUPLICATED"){
					alert("이미 존재하는 분회입니다.");
					clear();
				} else {
					$(".branch_list_table").append("<tr class='branch_list_tr'>"
						+"<td class='list_code'>"+data.areaCode+"-"+data.branchCode+"</td>"
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
			}
		})
	});
	
	/* Click modify Button*/
	$(document).on("click",".btn_modify",function() {
		//분회/분회코드 가져오기
		var branch = $(this).parent().siblings(".list_branch").text();
		var branchCode = $(this).parent().siblings(".list_branch_code").text();
		
		$(".dest_branch_code").val(branch);
		$(".branch").val(branch);
		$(".branch_code").val(branchCode);
		
		//save 버튼 변경
		$(".save").text("수정").attr("class","modify_save");
	});
	
	/* Update Branch AJAX */
	$(document).on("click",".modify_save",function() {
		var query = {
			destBranchCode : $(".dest_branch_code").val(),
			branch 		: $(".branch").val(),
			branchCode	: $(".branch_code").val()
		};
		
		$.ajax({
			url  : "/branch/updateBranch",
			type : "post",
			data : query,
			success : function(data){
				if(data.areaCode=="DUPLICATED"){
					alert("분회 코드가 중복되었습니다.");
					clear();
				} else {
					var modifyingTr=$(".list_branch_code:contains("+data.branchCode+")").parent();

					modifyingTr.children(".list_branch").text(data.branch);
					modifyingTr.children(".list_branch_code").text(data.branchCode);
					alert("수정되었습니다.");
					clear();
				}
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
	
	//토글 함수
    $(function(){
    	$(".btn_create, .btn_modify, #modal_background, .close").click(function () {
    		$("#myModal,#modal_background").toggle();
    		clear();
    	});
    	
    	$(".save").click(function () {
    		$("#myModal,#modal_background").toggle();
    	});
    });
    
    //Close, Save시 Branch input clear
    function clear() {
    	var cityCode = $(".city_code").val();
    	var guCode 	 = $(".gu_code").val();
    	var defaultCode=pad(cityCode,2)+"-"+pad(guCode,2);
    	
    	$(".code").val(defaultCode);
    	$(".dest_branch_code").val("");
    	$(".branch").val("");
    	$(".branch_code").val("");
		$(".modify_save").text("저장").attr("class","save");
	}
    
    //n에 width 자리수에 맞게 0 추가
	function pad(n, width) {
		n = n + '';
		return n.length >= width ? n : new Array(width - n.length + 1).join('0')+n;
	}
    
    //Key up시 Code 생성
	$(".input").keyup(function(){
		var code = $(".city_code").val()+"-"
				  +$(".gu_code").val()	+"-"
				  +$(".branch_code").val();
		$(".code").val(code);
	});
});