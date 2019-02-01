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
			<th>회장 연락처</th><th></th>
		</tr>
		<c:forEach var="scc" items="${sccList}">
			<tr class="scc_list_tr">
				<td class="list code">${scc.code}</td>
				<td class="list dong">${scc.dong}</td>
				<td class="list name">${scc.name}</td>
				<td class="list address">${scc.address}</td>
				<td class="list reg_data">${scc.simpleRegData}</td>
				<td class="list site">${scc.site}</td>
				<td class="list building">${scc.building}</td>
				<td class="list member">${scc.member}</td>
				<td class="list male">${scc.male}</td>
				<td class="list female">${scc.female}</td>
				<td class="list own">${scc.own}</td>
				<td class="list tel">${scc.tel}</td>
				<td class="list president">${scc.president}</td>
				<td class="list phone">${scc.phone}</td>
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
	 			<!-- 분회 선택 추가 -->
	 			<td><span class="front_code">${frontCode}</span>
	 				<input type="text" class="input code"></td>
				<td><input type="text" class="input dong"></td>
				<td><input type="text" class="input name"></td>
				<td><input type="text" class="input address"></td>
				<td><input type="text" class="input reg_date"></td>
				<td><input type="text" class="input site"></td>
				<td><input type="text" class="input building"></td>
				<td><input type="text" class="input member"></td>
				<td><input type="text" class="input male"></td>
				<td><input type="text" class="input female"></td>
				<td><input type="text" class="input own"></td>
				<td><input type="text" class="input tel"></td>
				<td><input type="text" class="input president"></td>
				<td><input type="text" class="input phone"></td>
	 		</tr>
		</table>
		<button class="save">추가</button>
		<button class="close">취소</button>
	</form>
</div>
</section>
<%@ include file="../include/footer.jsp"%> 