<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<%@ include file="../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/cat/catList.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/cat/catModal.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/cat1List.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/cat2List.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/inputCheck/catInputCheck.js"></script>

<section>
<div class="catList">
	<table class="cat_list_table" border="1">
		<tr>
			<th>대분류</th> <th>대분류 코드</th> <th>소분류</th> <th>소분류 코드</th>
		</tr>
		<c:forEach var="cat1" items="${categoryList}">
			<tr class="cat1_list_tr">
				<td rowspan="<c:out value='${fn:length(cat1.cat2List)+1}'/>" 
					class="list name1">${cat1.name}</td>
				<td rowspan="<c:out value='${fn:length(cat1.cat2List)+1}'/>" 
					class="list code1">${cat1.code}</td> <!--  -->
				<c:forEach var="cat2" items="${cat1.cat2List}">
					<tr class="cat2_list_tr">
						<td class="list name2">${cat2.name}</td>
						<td class="list code2">
							<span class="code2_cat1">${cat2.cat1}</span>
							<span class="code2_code">${cat2.code}</span>
						</td>
					</tr>				
				</c:forEach>
			</tr>
		</c:forEach>
		</table>
	<button class="btn_create cat1">대분류 추가</button>
	<button class="btn_create cat2">소분류 추가</button>
	
	<!-- The Modal Cat1-->
	<div class="modal m1">
		<!-- Modal content -->
		<form>
			<input type="hidden" class="m1 dest_cat1_code">
			<table>
				<tr>
					<th>대분류</th> <th>대분류 코드</th>
		 		</tr>
		 		<tr>
		 			<td><input type="text" class="m1 input cat1 name"></td>
		 			<td><input type="text" class="m1 input cat1 code"></td>
		 		</tr>
			</table>
		</form>
		<p class="m1 p_checkCode"> </p>
		<p class="m1 p_btn">
			<button class="m1 save">등록</button>
			<button class="m1 close">취소</button>
		</p>
	</div>
	<div class="m1 modal_background"></div>
	
	<!-- The Modal Cat2-->
	<div class="modal m2">
		<!-- Modal content -->
		<form>
			<input type="hidden" class="m2 dest_cat1_code">
			<input type="hidden" class="m2 dest_cat2_code">
			<table>
				<tr>
					<th>대분류</th> <th>대분류 코드</th> <th>소분류</th> <th>소분류 코드</th>
		 		</tr>
		 		<tr>
		 			<td>
		 				<select class="m2 input cat1 name">
		 					<option selected="selected" value="">- 선택 -</option>
							<c:forEach var="cat1" items="${categoryList}">
								<option value="${cat1.name}">${cat1.name}</option>
							</c:forEach>
		 				</select>
		 			</td>
		 			<td>
		 				<select class="m2 input cat1 code">
		 					<option selected="selected" value="">- 선택 -</option>
							<c:forEach var="cat1" items="${categoryList}">
								<option value="${cat1.code}">${cat1.code}</option>
							</c:forEach>
		 				</select>
		 			</td>
		 			<td><input type="text" class="m2 input cat2 name"></td>
		 			<td><input type="text" class="m2 input cat2 code"></td>
		 		</tr>
			</table>
		</form>
		<p class="m2 p_checkCode"></p>
		<p class="m2 p_btn">
			<button class="m2 save">등록</button>
			<button class="m2 close">취소</button>
		</p>
	</div>
	<div class ="m2 modal_background"></div>
</div>
</section>
<%@ include file="../include/footer.jsp"%> 