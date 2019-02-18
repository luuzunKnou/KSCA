<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/schedule/scheduler.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/schedule/schModal.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/colorPicker/spectrum.css"> <!-- ColorPicker -->
<script src="${pageContext.request.contextPath}/resources/js/scheduler.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/colorPicker/spectrum.js"></script> <!-- ColorPicker -->

<section>
	<div id="scheduler">
		<table class="cal_table">
			<tr class="t_header">
				<th class="cal title" colspan="7">
					<span class="cal left">&lt;&lt;</span>
					<span class="cal year"></span>.
					<span class="cal month"></span>
					<span class="cal right">&gt;&gt;</span>
				</th>
			</tr>
		</table>
	</div>
</section>

<!-- The Modal-->
<div class="modal">
	<!-- Modal content -->
	<form>
		<input type="hidden" class="input_offer_code">

		<div class="input wrap date div">
			<label>날짜</label>
			<div class="input inner date div">
				<input type="text" class="input_date">
			</div>
		</div>
		
		<div class="input wrap scc div">
			<label>경로당</label>
			<div class="input inner scc div">
				<select class="input scc select">
 					<option selected="selected" value="">- 선택 -</option>
					<c:forEach var="scc" items="${sccList}">
						<option data-branch_code="${scc.branchCode}" data-scc_code="${scc.sccCode}"
							>${scc.name}</option>	
					</c:forEach> 
 				</select>
			</div>
		</div>
		
		<div class="input wrap program div">
			<label>프로그램</label>
			<div class="input inner program div">
				<select class="input program select">
					<option selected="selected" value="">- 선택 -</option>
					<c:forEach var="program" items="${programList}">
						<option value="${program.program.code}">${program.program.name}&nbsp&nbsp(${program.agency.name})</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="input wrap color div">
			<label>색상</label>
			<div class="input inner color div">
				<input type='color' name='color' class="input_color"/>
			</div>
		</div>
		 
		<div class="input wrap begin div">
			<label>기간 시작</label>
			<div class="input inner begin div">
				<input type="text" class="input_begin">
			</div>
		</div>
		
		<div class="input wrap end div">
			<label>기간 종료</label>
			<div class="input inner end div">
				<input type="text" class="input_end">
			</div>
		</div>
		
		<div class="input week div">
			<label>주간 반복 </label><input type="checkbox" name="weekly" class="checkbox week">
			<div class="input day div">
				<input type="checkbox" name="day" class="checkbox day" value="1" disabled="disabled">월
				<input type="checkbox" name="day" class="checkbox day" value="2" disabled="disabled">화
				<input type="checkbox" name="day" class="checkbox day" value="3" disabled="disabled">수
				<input type="checkbox" name="day" class="checkbox day" value="4" disabled="disabled">목
				<input type="checkbox" name="day" class="checkbox day" value="5" disabled="disabled">금
			</div>
		</div>
		
		<div class="input wrap mode div">
			<label>수정 방식 선택</label>
			<div class="input inner mode div">
				<input type="radio" name="mod" value="">전체 수정
				<input type="radio" name="mod" value="">선택된 날짜만 수정
			</div>
		</div> 
	</form>
	
	<p class="p_btn">
		<button class="btn_create">추가</button>
		<button class="btn_reset">취소</button>
	</p>
</div>
<div class="m1 modal_background"></div>
<%@ include file="../include/footer.jsp"%> 