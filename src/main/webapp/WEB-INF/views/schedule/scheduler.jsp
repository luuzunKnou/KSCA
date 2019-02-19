<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/schedule/scheduler.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/schedule/schModal.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/schedule/schModal2.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/colorPicker/spectrum.css"> <!-- ColorPicker -->
<script src="${pageContext.request.contextPath}/resources/js/scheduler.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/schModal2.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/colorPicker/spectrum.js"></script> <!-- ColorPicker -->

<section>
	<div id="scheduler">
		<table class="cal_table">
			<tr class="t_header">
				<th></th>
				<th class="cal title" colspan="3">
					<span class="cal left">&lt;&lt;</span>
					<span class="cal year"></span>.
					<span class="cal month"></span>
					<span class="cal right">&gt;&gt;</span>
				</th> 
				<th class="btn_group">
					<button class="btn_load">불러오기</button>
					<button class="btn_pro_manager">프로그램 관리</button>
				</th>
			</tr>
		</table>
	</div>
</section>

<!-- The Modal-->
<div class="m1 modal">
	<!-- Modal content -->
	<form>
		<input type="hidden" class="m1 input_offer_code">

		<div class="m1 input wrap date div">
			<label>날짜</label>
			<div class="m1 input inner date div">
				<input type="text" class="m1 input_date">
			</div>
		</div>
		
		<div class="m1 input wrap scc div">
			<label>경로당</label>
			<div class="m1 input inner scc div">
				<select class="m1 input scc select">
 					<option selected="selected" value="">- 선택 -</option>
					<c:forEach var="scc" items="${sccList}">
						<option data-branch_code="${scc.branchCode}" data-scc_code="${scc.sccCode}"
							>${scc.name}</option>	
					</c:forEach> 
 				</select>
			</div>
		</div>
		
		<div class="m1 input wrap program div">
			<label>프로그램</label>
			<div class="m1 input inner program div">
				<select class="m1 input program select">
					<option selected="selected" value="">- 선택 -</option>
					<c:forEach var="program" items="${programList}">
						<option value="${program.program.code}">${program.program.name}&nbsp&nbsp(${program.agency.name})</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="m1 input wrap color div">
			<label>색상</label>
			<div class="m1 input inner color div">
				<input type='color' name='color' class="m1 input_color"/>
			</div>
		</div>
		 
		<div class="m1 input wrap begin div">
			<label>기간 시작</label>
			<div class="m1 input inner begin div">
				<input type="text" class="m1 input_begin">
			</div>
		</div>
		
		<div class="m1 input wrap end div">
			<label>기간 종료</label>
			<div class="m1 input inner end div">
				<input type="text" class="m1 input_end">
			</div>
		</div>
		
		<div class="m1 input week div">
			<label>주간 반복 </label><input type="checkbox" name="weekly" class="m1 checkbox week">
			<div class="m1 input day div">
				<input type="checkbox" name="day" class="checkbox day" value="1" disabled="disabled">월
				<input type="checkbox" name="day" class="checkbox day" value="2" disabled="disabled">화
				<input type="checkbox" name="day" class="checkbox day" value="3" disabled="disabled">수
				<input type="checkbox" name="day" class="checkbox day" value="4" disabled="disabled">목
				<input type="checkbox" name="day" class="checkbox day" value="5" disabled="disabled">금
			</div>
		</div>
		
		<div class="m1 input wrap mode div">
			<label>수정 방식 선택</label>
			<div class="m1 input inner mode div">
				<input type="radio" name="mod" value="0" checked="checked">전체 수정
				<input type="radio" name="mod" value="1" >선택된 날짜만 수정
			</div>
		</div> 
	</form>
	
	<p class="m1 p_btn">
		<button class="m1 btn_create">추가</button>
		<button class="m1 btn_reset">취소</button>
	</p>
</div>
<div class="m1 modal_background"></div>

<!-- The Modal-->
<div class="m2 modal">
	<!-- Modal content -->
	<table class="pro_list_table">
		<tr>
			<th>프로그램 이름</th>
			<th>색상</th>
			<th>시작 날짜</th>
			<th>종료 날짜</th>
		</tr>
	</table>

	<form>	
		<input type="hidden" class="input_offer_code">
		
		<table class="pro_form_table">
			<tr>
				<th>프로그램 이름</th>
				<th>색상</th>
				<th>시작 날짜</th>
				<th>종료 날짜</th>
			</tr>
			<tr>
				<td>
					<select class="input program select">
						<option selected="selected" value="">- 선택 -</option>
						<c:forEach var="program" items="${programList}">
							<option value="${program.program.code}">${program.program.name}&nbsp&nbsp(${program.agency.name})</option>
						</c:forEach>
					</select>
				</td>
				<td><input type='color' name='color' class="input_color"/></td>
				<td><input type="text" class="input_begin"></td>
				<td><input type="text" class="input_end"></td>
			</tr>
		</table>
	</form>
	<p class="m2 p_btn">
		<button class="m2 btn_create">추가</button>
		<button class="m2 btn_reset">취소</button>
	</p>
</div>
<div class="m2 modal_background"></div>
<%@ include file="../include/footer.jsp"%> 