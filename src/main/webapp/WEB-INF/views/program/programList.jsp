<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/program/programList.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/programList.js"></script>

<section>
<div class="programList">
	<table class="program_list_table">
		<tr>
			<th class="th name">프로그램</th> 
			<th class="th cat1">카테고리1</th>
			<th class="th cat2">카테고리2</th>
			<th class="th agency">기관</th><th></th><th></th>
		</tr> 
		<c:forEach var="program" items="${programList}">
			<tr class="program_list_tr">
				<td class="list code" style="display:none;">${program.code}</td>
				<td class="list name">${program.name}</td>
				<td class="list cat1">${program.cat1}</td>
				<td class="list cat2">${program.cat2}</td>
				<td class="list agency">${program.agency}</td>
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
				<th>프로그램</th> 
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
	 			<td><input type="text" class="input agency"></td>
	 		</tr>
		</table>
		<button type="reset"  class="close">취소</button> 
		<button type="button" class="save">추가</button>
	</form>
</div>
</section>
<%@ include file="../include/footer.jsp"%> 