<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/area/areaList.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/modal.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/areaList.js"></script>
<script>
		var result = '${msg}';
		if(result.length!=0){
			alert(result);
		} 
</script>
<section>
<div class="areaList">
	<table class="area_list_table">
		<tr>
			<th>코드</th> <th>연합회</th> <th>연합회 코드</th> <th>지회</th> <th>지회 코드</th>
			<th>분회</th> <th> 분회 코드</th> <th></th>
		</tr>
		<c:forEach var="area" items="${areaList}">
			<tr class="area_list_tr">
				<td class="list_code">${area.code}</td>
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
	<div id="modal_background"></div>
</div>
</section>
<%@ include file="../include/footer.jsp"%> 