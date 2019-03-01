<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/manager/modify.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/inputCheck/signUpInputCheck.js"></script>

<section>
<div id="sign_up">
	<form class="form-horizontal" method="post">

		<!-- 아이디 -->
		<div class="form_group">
			<label class="sign_up_label" for="inputID">ID</label>
			<div class="col-sm-6">
				<input class="sign_up_form" id="inputID" type="text"
					name="id" readonly="readonly" value="${managerInfo.id}">
			</div>
		</div>

		<!-- 이메일 -->
		<div class="form_group">
			<label class="sign_up_label" for="inputEmail">이메일</label>
			<div class="col-sm-6">
				<input class="sign_up_form" id="inputEmail" type="email"
					name="mail" required value="${managerInfo.mail}">
			</div>
		</div> 

		<!-- 비밀번호 -->
		<div class="form_group">
			<label class="sign_up_label" for="inputPassword">새 비밀번호</label>
			<div class="col-sm-6">
				<input class="sign_up_form" id="inputPassword" type="password"
					name="password" placeholder="특수문자 포함 8자 이상">
			</div>
		</div>

		<!-- 비밀번호 확인 -->
		<div class="form_group">
			<label class="sign_up_label" for="inputPasswordCheck">비밀번호
				확인</label>
			<div class="col-sm-6">
				<input class="sign_up_form" id="inputPasswordCheck" type="password"
					name="chkPassward">
			</div>
		</div>

		<!-- 이름 입력 -->
		<div class="form_group">
			<label class="sign_up_label" for="inputName">이름</label>
			<div class="col-sm-6">
				<input class="sign_up_form" id="inputName" type="text"
					name="name" required value="${managerInfo.name}">
			</div>
		</div>

		<!-- 휴대폰 번호 -->
		<div class="form_group">
			<label class="sign_up_label" for="inputNumber">휴대폰번호</label>
			<div class="col-sm-6">
				<input type="text" class="sign_up_form" id="inputNumber"
					name="tel" required value="${managerInfo.tel}"/>
			</div>
		</div>

		<!-- 담당 지회 -->
		<div class="form_group">
			<div class="col-sm-6">
			<label class="sign_up_label" for="input_city">연합회/코드</label>
				<div id="locationField">
					<input id="input_city" class="sign_up_form name" value="${managerInfo.area.city}"
						name="city" type="text" placeholder="대구" readonly="readonly"></input>
					<input id="input_city_code" class="sign_up_form code" value="${managerInfo.area.cityCode}"
						name="cityCode"type="text" placeholder="03" readonly="readonly"></input>
				</div>
				
			<label class="sign_up_label" for="input_city">지회/코드</label>
				<div id="locationField">
				<input id="input_gu" class="sign_up_form name" value="${managerInfo.area.gu}"
						name="gu" type="text" placeholder="중구" readonly="readonly"></input>
					<input id="input_gu_code" class="sign_up_form code" value="${managerInfo.area.guCode}"
						name="guCode" type="text" placeholder="01" readonly="readonly"></input>
				</div>
			</div>
		</div>

		<!-- 버튼 -->
		<div class="form_group">
			<div>
				<button class="sign_up_btn modify" type="submit">
					수정
				</button>
				<button class="sign_up_btn remove" type="submit">
					탈퇴
				</button>
			</div>
		</div>
		<script type="text/javascript">
		$(document).ready(function(){
			var formObj = $(".form-horizontal");
			console.log("formObj: "+formObj);
			
			$(".modify").on("click", function(){
				formObj.attr("action","/manager/modify");
				formObj.submit();
			});
			
			$(".remove").on("click", function(){
				formObj.attr("action","/manager/remove");
				formObj.submit();
			});
		});
		</script>
	</form>
	</div>
</section>
<%@ include file="../include/footer.jsp"%> 