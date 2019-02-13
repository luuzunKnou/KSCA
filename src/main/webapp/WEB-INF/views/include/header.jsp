<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ include file="include/header.jsp"%> --%>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.0.min.js" ></script>
<link href="${pageContext.request.contextPath}/resources/css/default.css" rel="stylesheet">
<!-- <link href="https://fonts.googleapis.com/css?family=Cabin" rel="stylesheet"> -->
<!-- <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet"> -->
<html>
<head>
	<title>The Korea Senior Citizens Assoiation Program Schduler</title>
</head>

<body>
<div id="wrap">
	<nav>
		<div id="logo" onclick="javascript:location.href='../'">
			<p>사단법인 대한노인회</p> 
			<p>The Korea Senior Citizens Assoiation</p>
		</div>
		<div id="menu">
			<ul>
				<li class="menu_btn"><a href="${pageContext.request.contextPath}
					/introduce/introduce">소개 및 사용법</a></li>
				<li class="menu_btn"><a href="${pageContext.request.contextPath}
					/branch/branchList">분회 관리</a></li>
				<li class="menu_btn"><a href="${pageContext.request.contextPath}
					/scc/sccList">경로당 관리</a></li>
				<li class="menu_btn"><a href="${pageContext.request.contextPath}
					/agency/agencyList">기관 관리</a></li>
				<li class="menu_btn"><a href="${pageContext.request.contextPath}
					/program/programList">프로그램 관리</a></li>
				<li class="menu_btn"><a href="${pageContext.request.contextPath}
					/schedule/scheduler">스케줄 관리</a></li>
					
				<c:if test="${login.permToString() eq 'Master'}">
				<li class="menu_btn"><a href="${pageContext.request.contextPath}
					/cat/catList">카테고리 관리</a></li>
				<li class="menu_btn"><a href="${pageContext.request.contextPath}
					/manager/managerManagement">매니저 관리</a></li>
				</c:if>
			</ul> 
		</div>  
		<div id="login_menu">
			<ul> 
			<c:if test="${empty login.id}">
				<li class="login_btn"><a href="${pageContext.request.contextPath}
					/manager/logIn">로그인</a></li>
				<li class="login_btn"><a href="${pageContext.request.contextPath}
					/manager/signUp">회원가입</a></li>
			</c:if>
			<c:if test="${!empty login.id}">
				<li class="login_btn""><a href="${pageContext.request.contextPath}
					/manager/logOut">로그아웃</a></li>
				<li class="login_btn""><a href="${pageContext.request.contextPath}
					/manager/modify">내 정보</a></li>
				<li class="login_btn">${login.id}님 환영합니다.</li>
			</c:if>
			</ul>
		</div>
	</nav>