<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/manager/managerManagement.css" rel="stylesheet">
<section>
<div id="managerManagement">
	<form action="managerManagement" method="post">
		<div class="div_head"><h3>Wating Manager List</h3></div>
			<ul class="manager_list_ul">
				<c:forEach var="manager" items="${managers}">
					<li class="manager_list_li">
						<span>${manager.id}</span>
						<input class="checkbox" name="manager" 
							type="checkbox" value="${manager.id}"/> 
					</li>
				</c:forEach> 
			</ul>
			<button class="btn_success" type="submit">승인 완료</button>
	</form>   
</div>
</section>
<%@ include file="../include/footer.jsp"%>  