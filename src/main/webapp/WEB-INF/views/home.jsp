<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/home.css" rel="stylesheet">
	<script>
		var result = '${msg}';
		if(result=="SignUp Success"){
			alert("회원 가입 신청이 완료되었습니다.");
		}
		
		if(result=="Permission Denined"){
			alert("권한이 없습니다.");
		}
		
		if(result=="None data"){
			alert("데이터가 없습니다.");
		}
		
		if(result=="SignUp Approve"){
			alert("가입이 승인되었습니다.");
		}
	</script>
<%@ include file="include/footer.jsp"%>  
 