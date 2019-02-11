<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/branch/branchList.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/branch/branchModal.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/branchList.js"></script>

<section>
<div class="branchList">
	<!-- Paging : 15 obj -->
	<table class="branch_list_table">
		<tr>
			<th class="th code">코드</th> 
			<th class="th city">연합회</th> 
			<th class="th city_code">연합회 코드</th> 
			<th class="th gu">지회</th> 
			<th class="th gu_code">지회 코드</th>
			<th class="th branch">분회</th> 
			<th class="th branch_code">분회 코드</th> <th class="td_btn"></th> <th class="td_btn"></th>
		</tr>
		<c:forEach var="branch" items="${branchList}">
			<tr class="branch_list_tr">
				<td>${area.code}-${branch.branchCode}</td>
				<td>${area.city}</td>
				<td>${area.cityCode}</td>
				<td>${area.gu}</td>
				<td>${area.guCode}</td>
				<td class="list_branch">${branch.branch}</td>
				<td class="list_branch_code">${branch.branchCode}</td>
				<td><button class="btn_modify">수정</button></td>
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
					<th>코드</th> 
					<th>연합회</th> 
					<th>연합회 코드</th> 
					<th>지회</th> 
					<th>지회 코드</th>
		 			<th>분회</th> 
		 			<th>분회 코드</th>
		 		</tr>
		 		<tr>
		 			
		 			<td><input type="hidden" class="dest_branch_code">
		 				<input type="text" readonly="readonly" 
		 				class="input code" value="${area.code}"></td>

		 			<td><input type="text" readonly="readonly" 
		 				class="input city" value="${area.city}"></td>

		 			<td><input type="text" readonly="readonly"
		 				class="input city_code" value="${area.cityCode}"></td>

		 			<td><input type="text" readonly="readonly"
		 				class="input gu" value="${area.gu}"></td>

		 			<td><input type="text" readonly="readonly"
		 				class="input gu_code" value="${area.guCode}"></td>

		 			<td><input type="text" class="input branch" 
		 				required="required"></td>

		 			<td><input type="text" class="input branch_code" 
		 				required="required"></td>
		 		</tr>
			</table>
		</form>
		<span id="p_checkCode"></span>
		<button class="close">취소</button>
		<button class="save">저장</button>
	</div>
	<div id="modal_background"></div>
</div>
</section>
<%@ include file="../include/footer.jsp"%> 