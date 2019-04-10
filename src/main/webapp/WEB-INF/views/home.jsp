<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/home.css" rel="stylesheet">
	<script>
		alert("테스트 기간입니다. 누구나 가입하실 수 있으며 기능이 제한됩니다 (카테고리 추가, 수정, 삭제가 데이터베이스에 반영되지 않습니다. 대분류 항목을 삭제할 수 없습니다).");
		var result = '${msg}';
		if(result.length!=0){
			alert(result);
		}
		window.location.href='schedule/scheduler';
	</script>
<%@ include file="include/footer.jsp"%>  
 