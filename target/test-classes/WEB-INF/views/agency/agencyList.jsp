<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/agency/agencyList.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/agencyList.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/inputCheck/agencyInputCheck.js"></script>

<section>
<div class="agencyList">
	<table class="agency_list_table">
		<tr>
			<th class="th name">기관 이름</th> 
			<th class="th manager">담당자</th>
			<th class="th tel">전화번호</th><th></th><th></th>
		</tr> 
		<c:forEach var="agency" items="${agencyList}">
			<tr class="agency_list_tr">
				<td class="list code" style="display:none;">${agency.code}</td>
				<td class="list name">${agency.name}</td>
				<td class="list manager">${agency.manager}</td>
				<td class="list tel">${agency.tel}</td>
				<td><button class="btn_modify">수정</button></td>
				<td><button class="btn_delete">삭제</button></td>
			</tr>
		</c:forEach>
	</table>
</div>

<div id="form_div">
	<form>
		<input type="hidden" class="input code"> 
		<table>
			<tr>
				<th class="th name">기관 이름</th> 
				<th class="th manager">담당자</th>
				<th class="th tel">전화번호</th> 
			</tr>
	 		<tr>
				<td><input type="text" class="input name"></td>
				<td><input type="text" class="input manager"></td>
	 			<td><input type="text" class="input tel"></td>
	 		</tr>
		</table>
		<button type="reset"  class="close">취소</button> 
		<button type="button" class="save">추가</button>
	</form>
</div>
</section>
<%@ include file="../include/footer.jsp"%> 