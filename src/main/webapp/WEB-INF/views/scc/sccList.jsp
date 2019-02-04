<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/scc/sccList.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/scc/sccModal.css" rel="stylesheet">

<section>
<div class="sccList">
	<table class="scc_list_table">
		<tr>
			<th>코드</th> <th>동</th> <th>경로당 이름</th> <th>소재지</th> <th>등록일자</th>
			<th>규모(대지)</th> <th>규모(건물)</th> <th>회원 수</th> <th>회원 수(남)</th>
			<th>회원 수(여)</th> <th>소유</th> <th>전화번호</th> <th>회장 명</th> 
			<th>회장 연락처</th><th></th><th></th>
		</tr>
		<c:forEach var="scc" items="${sccList}">
			<tr class="scc_list_tr">
				<td class="list code">${scc.code}</td>
				<td class="list dong">${scc.dong}</td>
				<td class="list name">${scc.name}</td>
				<td class="list address">${scc.address}</td>
				<td class="list reg_date">${scc.simpleRegDate}</td>
				<td class="list site">${scc.site}</td>
				<td class="list building">${scc.building}</td>
				<td class="list member">${scc.member}</td>
				<td class="list male">${scc.male}</td>
				<td class="list female">${scc.female}</td>
				<td class="list own">${scc.own}</td>
				<td class="list tel">${scc.tel}</td>
				<td class="list president">${scc.president}</td>
				<td class="list phone">${scc.phone}</td>
				<td><button class="btn_modify">수정</button></td>
				<td><button class="btn_delete">삭제</button></td>
			</tr>
		</c:forEach>
	</table>
</div>

<div id="form_div">
	<form>
		<table>
			<tr>
				<th>코드</th> <th>동</th> <th>경로당 이름</th> <th>소재지</th> <th>등록일자</th>
				<th>규모(대지)</th> <th>규모(건물)</th> <th>회원 수</th> <th>회원 수(남)</th>
				<th>회원 수(여)</th> <th>소유</th> <th>전화번호</th> <th>회장 명</th> <th>회장 연락처</th>
			</tr>
	 		<tr>
	 			<td><span class="front_code">${frontCode}</span>
	 				<select class="input branch">
	 					<option selected="selected"> 분회 선택 </option>
	 					<c:forEach var="branchCode" items="${branchCodeList}" varStatus="status">
		 					<option value="${branchCode}">${branchCode} (${branchNameList[status.index]})</option>
	 					</c:forEach>
	 				</select><span> -</span>
	 				<input type="text" class="input code"></td>
				<td><input type="text" class="input dong"></td>
				<td><input type="text" class="input name"></td>
				<td><input type="text" class="input address"></td>
				<td><input type="text" class="input reg_date"></td>
				<td><input type="text" value="0" class="input site"></td>
				<td><input type="text" value="0" class="input building"></td>
				<td><input type="text" value="0" class="input member"></td>
				<td><input type="text" value="0" class="input male"></td>
				<td><input type="text" value="0" class="input female"></td>
				<td>
					<select class="input own">
						<option value="공설" selected="selected"> 공설 </option>
						<option value="사설"> 사설 </option>
					</select>
				<td><input type="text" class="input tel"></td>
				<td><input type="text" class="input president"></td>
				<td><input type="text" class="input phone"></td>
	 		</tr>
		</table>
		<button type="reset"  class="close">취소</button> 
		<button type="button" class="save">추가</button>
	</form>
	<script>
	$(document).ready(function(){
		$(document).on("click",".save",function() {
			var frontCode = $(".front_code").text(); 
			var branchCode= $(".input.branch").val(); 
			var sccCode   = $(".input.code").val();
			
			var code = frontCode+branchCode+"-"+pad(sccCode,3);
			//console.log($(".input.reg_date").val());
			var query = {
				code		: code,
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
				url  : "/scc/createScc",
				type : "post",
				data : query,
				success : function(data){
					if(data.code=="DUPLICATED"){
						alert("이미 존재하는 경로당입니다.");
					} else {
						$(".scc_list_table").append("<tr class='scc_list_tr'>"
							+"<td class='list code'>"	 +data.code+"</td>"
							+"<td class='list dong'>"	 +data.dong+"</td>"
							+"<td class='list name'>"	 +data.name+"</td>"
							+"<td class='list address'>" +data.address+"</td>"
							+"<td class='list simpleRegDate'>"+data.simpleRegDate+"</td>"
							+"<td class='list site'>"	 +data.site+"</td>"
							+"<td class='list building'>"+data.building+"</td>"
							+"<td class='list member'>"	 +data.member+"</td>"
							+"<td class='list male'>"	 +data.male+"</td>"
							+"<td class='list female'>"	 +data.female+"</td>"
							+"<td class='list own'>"	 +data.own+"</td>"
							+"<td class='list tel'>"	 +data.tel+"</td>"
							+"<td class='list president'>"+data.president+"</td>"
							+"<td class='list phone'>"	 +data.phone+"</td>"
							+"<td><button class='btn_modify'>수정</button></td>"
							+"<td><button class='btn_delete'>삭제</button></td>"
							+"</tr>"
						);
						alert("등록되었습니다.");
						$("body").animate({
							scrollTop: $(document).height()
						}, 1000);
					}
				}
			})
		});
		
		
		//Delete Button
		$(document).on("click",".btn_delete",function() {
			var result = confirm("정말 삭제하시겠습니까?");
			if(result){
				var query = {
					code : $(this).parent().siblings(".list.code").text()
				};
				
				$.ajax({
					url  : "/scc/removeScc",
					type : "post",
					data : query,
					success : function(data){
						$(".scc_list_tr:contains("+data+")").remove();
					}
				})
			}
		});
		
		
		//Modify Button Click
		$(document).on("click",".btn_modify",function() {
			var modifyingTr = $(this).parent().parent();
			var code = $(this).parent().siblings(".list.code").text();
			var branchCode = code.split('-')[2];
			var sccCode = code.split('-')[3];
			
			//Set input value
			$(".input.branch").val(branchCode);
			$(".input.code").val(sccCode);
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
			var frontCode = $(".front_code").text(); 
			var branchCode= $(".input.branch").val(); 
			var sccCode   = $(".input.code").val();
			var code = frontCode+branchCode+"-"+pad(sccCode,3);
			//console.log($(".input.reg_date").val());
			var query = {
				code		: code,
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
				url  : "/scc/modifyScc",
				type : "post",
				data : query,
				success : function(data){
					var modifyingTr=$('td:contains("'+data.code+'")').parent();

					modifyingTr.children(".list.code").text(data.code);
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
					//change button and opacity
					$(".modify").text("추가").attr("class","save");
					$(".sccList").css("opacity",1.0);
				}
			});
		});
		
	    //n에 width 자리수에 맞게 0 추가
		function pad(n, width) {
			n = n + '';
			return n.length >= width ? n : new Array(width - n.length + 1).join('0')+n;
		}
	});
	</script>
</div>
</section>
<%@ include file="../include/footer.jsp"%> 