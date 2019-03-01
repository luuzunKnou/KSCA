<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/program/programList.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/programList.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/inputCheck/programInputCheck.js"></script>

<section>
<div class="programList">
	<table class="program_list_table">
		<tr>
			<th class="th name">프로그램 이름</th> 
			<th class="th cat1">카테고리1</th>
			<th class="th cat2">카테고리2</th>
			<th class="th a_name">기관</th>
			<th class="th a_manager">담당자</th>
			<th class="th a_tel">연락처</th>
			<th></th><th></th>
		</tr>
		<c:forEach var="program" items="${programList}">
			<tr class="program_list_tr">
				<td class="list code" style="display:none;">${program.program.code}</td>
				<td class="list a_code" style="display:none;">${program.agency.code}</td>
				<td class="list name">${program.program.name}</td>
				<td class="list cat1">
					<span class="list cat1 name">${program.cat1.name}</span> (
					<span class="list cat1 code">${program.cat1.code}</span>)</td> 
				<td class="list cat2">
					<span class="list cat2 name">${program.cat2.name}</span> (
					<span class="list cat2 code">${program.cat2.code}</span>)</td>
				<td class="list a_name">${program.agency.name}</td>
				<td class="list a_manager">${program.agency.manager}</td>
				<td class="list a_tel">${program.agency.tel}</td>
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
				<th>프로그램 이름</th> 
				<th>카테고리1</th>
				<th>카테고리2</th>
				<th>기관</th><th>
			</tr>
	 		<tr>
	 			<td><input type="text" class="input name"></td>
				<td>
					<select class="input cat1">
	 					<option selected="selected" value="">- 선택 -</option>
						<c:forEach var="cat1" items="${catList}">
							<option value="${cat1.code}">${cat1.name} (${cat1.code})</option>
						</c:forEach>
	 				</select>
				</td>
				
				<td>
					<select class="input cat2">
	 					<option selected="selected" value="">- 선택 -</option>
	 				</select>
				</td>
				<td>
					<select class="input agency">
	 					<option selected="selected" value="">- 선택 -</option>
						<c:forEach var="agency" items="${agencyList}">
							<option value="${agency.code}">${agency.name}</option>
						</c:forEach>
	 				</select>
 				</td>
	 		</tr>
		</table>
		<button type="reset"  class="close">취소</button> 
		<button type="button" class="save">추가</button>
	</form>
</div>
</section>
<%@ include file="../include/footer.jsp"%> 