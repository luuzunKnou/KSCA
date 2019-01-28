<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/manager/logIn.css" rel="stylesheet">
	<section>
		 <div id="login">
		 	<form id="form_login" action="/manager/logInPost" method="post">
		 		<div class="div_label">
		 			<label class="login_label" for="input_id">ID</label>
		 		</div>
        		<input type="text" id="input_id" name="id" required autofocus><br>
        			
        		<div class="div_label">
        			<label class="login_label" for="input_password">Password</label>
        		</div>
        		<input type="password" id="input_password" name="password" required>
	        	
	        	<div id="div_btn_login">
	        		<button class="btn_login" type="submit">로그인</button><br>
	        		<button class="btn_login" 
	        			onclick= "location.href='signUp'">회원가입</button>
	        	</div> 
		 	</form>
		 </div>
	</section>  
<%@ include file="../include/footer.jsp"%> 
 