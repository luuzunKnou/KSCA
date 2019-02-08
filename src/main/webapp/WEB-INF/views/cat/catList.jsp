<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<%@ include file="../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/cat/catList.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/cat/catModal.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/catList.js"></script>

<section>
<div class="catList">
	<table class="cat_list_table" border="1">
		<tr>
			<th>대분류</th> <th>대분류 코드</th> <th>소분류</th> <th>소분류 코드</th>
		</tr>
		<c:forEach var="cat1" items="${categoryList}">
			<tr class="cat1_list_tr">
				<td rowspan="<c:out value='${fn:length(cat1.cat2List)+1}'/>">${cat1.name}</td>
				<td rowspan="<c:out value='${fn:length(cat1.cat2List)+1}'/>">${cat1.code}</td>
				<c:forEach var="cat2" items="${cat1.cat2List}">
					<tr>
						<td>${cat2.name}</td>
						<td>${cat2.code}</td>
					</tr>				
				</c:forEach>
			</tr>
<!-- 			<td><button class="btn_modify">수정</button></td> -->
<!-- 			<td><button class="btn_delete">삭제</button></td> -->
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
		 			<th>분회</th> <th> 분회 코드</th>
		 		</tr>
		 		<tr>
		 			
		 			<td><input type="hidden" class="dest_cat_code">
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

		 			<td><input type="text" class="input cat" 
		 				required="required"></td>

		 			<td><input type="text" class="input cat_code" 
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