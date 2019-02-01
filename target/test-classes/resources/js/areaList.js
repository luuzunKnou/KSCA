<!-- Modal JavaScript -->
var modal 	= document.getElementById('myModal');
var open 	= document.getElementsByClassName("btn_create")[0];
var close 	= document.getElementsByClassName("close")[0];      
var save	= document.getElementsByClassName("save")[0];
var inputs 	= document.getElementsByClassName("input");

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
			modal.style.display = "none";
			clear();
			$(".manager_list_table").append("<tr class='area_list_tr'>"
				+"<td>"+data.code+"</td>"
				+"<td>"+data.city+"</td>"
				+"<td>"+data.cityCode+"</td>"
				+"<td>"+data.gu+"</td>"
				+"<td>"+data.guCode+"</td>"
				+"<td>"+data.branch+"</td>"
				+"<td>"+data.branchCode+"</td>"
				+"<td><button class='btn_delete'>삭제</button></td>"
				+"</tr>"
			);
		}
	})
});

//open 버튼 동작
open.onclick = function() {
    modal.style.display = "block";
    clear();
}

//close 버튼 동작
close.onclick = function() {
    modal.style.display = "none";
    clear();
}

// modal 바깥 클릭 시 닫음
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
        clear();
    }
}

//Close, Save시 Branch input clear
function clear() {
	var cityCode = ${defaultArea.cityCode};
	var guCode = ${defaultArea.guCode};
	var defaultCode=pad(cityCode,2)+"-"+pad(guCode,2);
	
	document.getElementsByClassName("code")[0].value=defaultCode;
	document.getElementsByClassName("branch")[0].value="";
	document.getElementsByClassName("branch_code")[0].value="";
}

//자리수에 맞게 0 추가
function pad(n, width) {
	n = n + '';
	return n.length >= width ? n : new Array(width - n.length + 1).join('0')+n;
}

//Key up - Make Code
$(".input").keyup(function(){
	var code = $(".city_code").val()+"-"
			  +$(".gu_code").val()	+"-"
			  +$(".branch_code").val();
	$(".code").val(code);
});
