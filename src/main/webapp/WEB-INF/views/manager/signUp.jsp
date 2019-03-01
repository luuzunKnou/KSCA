<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/manager/signUp.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/inputCheck/signUpInputCheck.js"></script>

<section>
	<div id="sign_up">
	<div class="page-header">
		<!-- <h1>회원가입 <small>horizontal form</small></h1> -->
	</div>
	<form class="form-horizontal" method="post">

		<!-- 아이디 -->
		<div class="form_group">
			<label class="sign_up_label" for="inputID">ID</label>
			<div class="col-sm-6 div_id">
				<input class="sign_up_form" id="inputID" type="text"
					name="id" required>
				<p id='p_checkID'></p>
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
					<input id="input_city_code" class="sign_up_form code 11"
						name="cityCode"type="text" placeholder="03"></input>
				</div>
				
			<label class="sign_up_label" for="input_city">지회/코드</label>
				<div id="locationField">
				<input id="input_gu" class="sign_up_form name"
						name="gu" type="text" placeholder="중구"></input>
					<input id="input_gu_code" class="sign_up_form code"
						name="guCode" type="text" placeholder="01"></input>
				</div>
			</div>
			<p id='p_checkCode'></p>
		</div>
		
		<!-- Check ID AJAX -->
		<script>
			$("#inputID").keyup(function() {
				var query = {id : $("#inputID").val()};
				$.ajax({
					url  : "/manager/checkID",
					type : "post",
					data : query,
					success : function(data){
						if(data == 0){ //아이디 중복
							$("#p_checkID").text("사용할 수 없는 아이디입니다.");
							$("#p_checkID").css("color","red");
							$(".btn_submit").prop("disabled",true);
						} else { //사용가능
							$("#p_checkID").text("사용가능한 아이디입니다.");
							$("#p_checkID").css("color","#2EB74E");
							$(".btn_submit").prop("disabled",false);
						}
					}
				});
			});
		</script>
		
		<!-- Check Code AJAX -->
		<script>
			$(".code").keyup(function() {
				var query = {cityCode : $("#input_city_code").val(),
							   guCode : $("#input_gu_code").val()};
				$.ajax({
					url  : "/manager/checkCode",
					type : "post",
					data : query,
					success : function(data){
						if(data == 0){ //아이디 중복
							$("#p_checkCode").text("이미 등록된 코드입니다.");
							$("#p_checkCode").css("color","red");
							$(".btn_submit").prop("disabled",true);
						} else { //사용가능
							$("#p_checkCode").text("등록 가능한 코드입니다.");
							$("#p_checkCode").css("color","#2EB74E");
							$(".btn_submit").prop("disabled",false);
						}
					}
				});
			});
		</script>

		<!-- 버튼 -->
		<div class="form_group">
			<div>
				<button class="sign_up_btn btn_submit" type="submit">
					회원가입
				</button>
				<button class="sign_up_btn btn_reset" type="reset">
					가입취소
				</button>
			</div>
		</div>
	</form>
	</div>
</section>
<%@ include file="../include/footer.jsp"%> 