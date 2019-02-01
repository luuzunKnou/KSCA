$(document).ready(function(){
	//Modal JavaScript
	var modal 	= $("#myModal");
    var close 	= $(".close");      
    var save	= $(".save");
	var inputs 	= $(".input");
	
	/* Create Branch AJAX */
	$(".save").click(function() {
		var query = {
				code 		: $(".code").val(),
				city 		: $(".city").val(),
				cityCode	: $(".city_code").val(),
				gu			: $(".gu").val(),
				guCode 		: $(".gu_code").val(),
				branch 		: $(".branch").val(),
				branchCode	: $(".branch_code").val()
		};
		
		$.ajax({
			url  : "/area/createBranch",
			type : "post",
			data : query,
			success : function(data){
				clear();
				if(data.code=="DUPLICATED"){
					alert("이미 존재하는 분회입니다.");
				} else {
					$(".area_list_table").append("<tr class='area_list_tr'>"
						+"<td class='list_code'>"+data.code+"</td>"
						+"<td>"+data.city+"</td>"
						+"<td>"+data.cityCode+"</td>"
						+"<td>"+data.gu+"</td>"
						+"<td>"+data.guCode+"</td>"
						+"<td>"+data.branch+"</td>"
						+"<td>"+data.branchCode+"</td>"
						+"<td><button class='btn_delete'>삭제</button></td>"
						+"</tr>"
					);
					alert("등록되었습니다.");
				}
			}
		})
	});
	
	//토글 함수
    $(function(){
    	$(".btn_create,#modal_background,.close,.save").click(function () {
    		$("#myModal,#modal_background").toggle();
    		clear();
    	});
    });
    
    //Close, Save시 Branch input clear
    function clear() {
    	var cityCode = $(".city_code").val();
    	var guCode 	 = $(".gu_code").val();
    	var defaultCode=pad(cityCode,2)+"-"+pad(guCode,2);
    	
    	$(".code").val(defaultCode);
    	$(".branch").val("");
    	$(".branch_code").val("");
	}
    
    //자리수에 맞게 0 추가
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

	//Delete Button 
	$(".btn_delete").click(function() {
		var result = confirm("정말 삭제하시겠습니까?");
		if(result){
			var query = {
				code : $(this).parent().siblings(".list_code").text()
			};
			
			$.ajax({
				url  : "/area/removeBranch",
				type : "post",
				data : query,
				success : function(data){
					if(data=="ERROR"){
						alert("삭제할 수 없는 항목입니다.");
					} else {
						$(".area_list_tr:contains("+data+")").remove();
					}
				}
			})
		}
	});
});