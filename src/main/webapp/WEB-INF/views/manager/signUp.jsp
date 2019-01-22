<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/signUp.css" rel="stylesheet">

<section>
	<div id="sign_up">
	<div class="page-header">
		<!-- <h1>회원가입 <small>horizontal form</small></h1> -->
	</div>
	<form class="form-horizontal" method="post">

		<!-- 아이디 -->
		<div class="form_group">
			<label class="sign_up_label" for="inputID">ID</label>
			<div class="col-sm-6">
				<input class="sign_up_form" id="inputID" type="text"
					name="id" required>
			</div>
		</div>

		<!-- 이메일 -->
		<div class="form_group">
			<label class="sign_up_label" for="inputEmail">이메일</label>
			<div class="col-sm-6">
				<input class="sign_up_form" id="inputEmail" type="email"
					name="mail" required>
			</div>
		</div> 

		<!-- 비밀번호 -->
		<div class="form_group">
			<label class="sign_up_label" for="inputPassword">비밀번호</label>
			<div class="col-sm-6">
				<input class="sign_up_form" id="inputPassword" type="password"
					name="password" placeholder="특수문자 포함 8자 이상" required>
			</div>
		</div>

		<!-- 비밀번호 확인 -->
		<div class="form_group">
			<label class="sign_up_label" for="inputPasswordCheck">비밀번호
				확인</label>
			<div class="col-sm-6">
				<input class="sign_up_form" id="inputPasswordCheck" type="password"
					name="chkPassward" required>
			</div>
		</div>

		<!-- 이름 입력 -->
		<div class="form_group">
			<label class="sign_up_label" for="inputName">이름</label>
			<div class="col-sm-6">
				<input class="sign_up_form" id="inputName" type="text"
					name="name" required>
			</div>
		</div>

		<!-- 휴대폰 번호 -->
		<div class="form_group">
			<label class="sign_up_label" for="inputNumber">휴대폰번호</label>
			<div class="col-sm-6">
				<input type="text" class="sign_up_form" id="inputNumber"
					name="tel" required />
			</div>
		</div>

		<!-- 담당 지회 -->
		<div class="form_group">
			<div class="col-sm-6">
			<label class="sign_up_label" for="input_city">연합회/코드</label>
				<div id="locationField">
					<input id="input_city" class="sign_up_form name"
						name="city" type="text" placeholder="대구"></input>
					<input id="input_city_code" class="sign_up_form code"
						name="city_code"type="text" placeholder="03"></input>
				</div>
				
			<label class="sign_up_label" for="input_city">지회/코드</label>
				<div id="locationField">
				<input id="input_gu" class="sign_up_form name"
						name="gu" type="text" placeholder="중구"></input>
					<input id="input_gu_code" class="sign_up_form code"
						name="gu_code" type="text" placeholder="01"></input>
				</div>
			</div>
		</div>

		<!-- 버튼 -->
		<div class="form_group">
			<div>
				<button class="sign_up_btn" type="submit">
					회원가입
				</button>
				<button class="sign_up_btn" type="reset">
					가입취소
				</button>
			</div>
		</div>
	</form>
	</div>
</section>
<%@ include file="../include/footer.jsp"%> 