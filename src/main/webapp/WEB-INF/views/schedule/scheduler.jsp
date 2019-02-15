<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/schedule/scheduler.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/schedule/schModal.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/scheduler.js"></script>

<section>
	<div id="scheduler">
		<table class="cal_table">
			<tr class="t_header">
				<th class="cal title" colspan="7">
					<span class="cal left">&lt;&lt;</span>
					<span class="cal year">2019</span>.
					<span class="cal month">1</span>
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
		<input type="hidden" class="dest_sch_code">
		<input type="hidden" class="dest_offer_code">
		
		<div class="input wrap scc div">
			<label>경로당</label>
			<div class="input inner div">
				<input type="text" class="">
			</div>
		</div>
		
		<div class="input wrap program div">
			<label>프로그램</label>
			<div class="input inner div">
				<input type="text" class="">
			</div>
		</div>
		
		<div class="input wrap color div">
			<label>색상</label>
			<div class="input inner div">
			<input type="text" class="">
			</div>
		</div>
		 
		<div class="input wrap begin div">
			<label>기간 시작</label>
			<div class="input inner div">
				<input type="text" class="">
			</div>
		</div>
		
		<div class="input wrap end div">
			<label>기간 종료</label>
			<div class="input inner div">
				<input type="text" class="">
			</div>
		</div>
		
		<div class="input week div">
			<label>주간 반복 </label><input type="checkbox" name="weekly" class="checkbox week">
			<div class="input day div">
				<input type="checkbox" name="day" class="checkbox day">월
				<input type="checkbox" name="day" class="checkbox day">화
				<input type="checkbox" name="day" class="checkbox day">수
				<input type="checkbox" name="day" class="checkbox day">목
				<input type="checkbox" name="day" class="checkbox day">금
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