<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/schedule/scheduler.css" rel="stylesheet">
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
		<button class="btn_create">TestBtn</button>
	</div>
</section>
<%@ include file="../include/footer.jsp"%> 