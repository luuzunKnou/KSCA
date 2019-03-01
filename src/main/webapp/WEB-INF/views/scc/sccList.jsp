<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/scc/sccList.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/sccList.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/inputCheck/sccInputCheck.js"></script>

<section>
<div class="sccList">
	<table class="scc_list_table">
		<tr>
			<th class="th code">코드</th> 
			<th class="th dong">동</th> 
			<th class="th name">경로당 이름</th> 
			<th class="th address">소재지</th> 
			<th class="th reg_date">등록일자</th>
			<th class="th site">규모(대지)</th> 
			<th class="th building">규모(건물)</th> 
			<th class="th member">회원 수</th> 
			<th class="th male">회원 수(남)</th>
			<th class="th female">회원 수(여)</th> 
			<th class="th own">소유</th> 
			<th class="th tel">전화번호</th> 
			<th class="th president">회장 명</th> 
			<th class="th phone">회장 연락처</th><th></th><th></th>
		</tr> 
		<c:forEach var="scc" items="${sccList}">
			<tr class="scc_list_tr">
				<td class="list code">
					<span class="list area_code">${scc.areaCode}</span>-
					<span class="list branch_code">${scc.branchCode}</span>-
					<span class="list scc_code">${scc.sccCode}</span></td>
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
		<input type="hidden" class="input dest_area_code"> 
		<input type="hidden" class="input dest_branch_code">
		<input type="hidden" class="input dest_scc_code">
		<table>
			<tr>
				<th>코드</th> <th>동</th> <th>경로당 이름</th> <th>소재지</th> <th>등록일자</th>
				<th>규모(대지)</th> <th>규모(건물)</th> <th>회원 수</th> <th>회원 수(남)</th>
				<th>회원 수(여)</th> <th>소유</th> <th>전화번호</th> <th>회장 명</th> <th>회장 연락처</th>
			</tr>
	 		<tr>
	 			<td><span class="input area_code">${areaCode}</span>
	 				<select class="input branch">
	 					<option selected="selected" value="default"> 분회 선택 </option>
	 					<c:forEach var="branchCode" items="${branchCodeList}" varStatus="status">
		 					<option value="${branchCode}">${branchCode} (${branchNameList[status.index]})</option>
	 					</c:forEach>
	 				</select><span> -</span>
	 				<input type="text" class="input scc_code"></td>
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
		<span id="p_checkCode"></span>
		<button type="reset"  class="close">취소</button> 
		<button type="button" class="save">추가</button>
	</form>
</div>
</section>
<%@ include file="../include/footer.jsp"%> 