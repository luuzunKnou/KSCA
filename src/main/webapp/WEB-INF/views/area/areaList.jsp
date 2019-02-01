<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/area/areaList.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/modal.css" rel="stylesheet">

<section>
<div class="areaList">
	<table class="manager_list_table">
		<tr>
			<th>코드</th> <th>연합회</th> <th>연합회 코드</th> <th>지회</th> <th>지회 코드</th>
			<th>분회</th> <th> 분회 코드</th> <th></th>
		</tr>
		<c:forEach var="area" items="${areaList}">
			<tr class="area_list_tr">
				<td>${area.code}</td>
				<td>${area.city}</td>
				<td>${area.cityCode}</td>
				<td>${area.gu}</td>
				<td>${area.guCode}</td>
				<td>${area.branch}</td>
				<td>${area.branchCode}</td>
				<td><button class="btn_delete">삭제</button></td>
			</tr>
		</c:forEach>
		</table>
	<button class="btn_create">분회 추가</button>
	
	<!-- The Modal -->
	<div id="myModal" class="modal">

		<!-- Modal content -->
		<div class="modal-content">
			<form>
				<table>
					<tr>
						<th>코드</th> <th>연합회</th> <th>연합회 코드</th> <th>지회</th> <th>지회 코드</th>
			 			<th>분회</th> <th> 분회 코드</th> <th></th>
			 		</tr>
			 		<tr>
			 			<td><input type="text" readonly="readonly" 
			 				class="input code" value=""></td>

			 			<td><input type="text" readonly="readonly" 
			 				class="input city" value="${defaultArea.city}"></td>

			 			<td><input type="text" readonly="readonly"
			 				class="input city_code" value="${defaultArea.cityCode}"></td>

			 			<td><input type="text" readonly="readonly"
			 				class="input gu" value="${defaultArea.gu}"></td>

			 			<td><input type="text" readonly="readonly"
			 				class="input gu_code" value="${defaultArea.guCode}"></td>

			 			<td><input type="text" class="input branch" 
			 				required="required"></td>

			 			<td><input type="text" class="input branch_code" 
			 				required="required"></td>
			 		</tr>
				</table>
			</form>
			
			<button class="close">취소</button>
			<button class="save">저장</button>
		</div>

	</div>
	
	<!-- Modal JavaScript -->
	<script>
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
	</script>
	
</div>
</section>
<%@ include file="../include/footer.jsp"%> 