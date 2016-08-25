<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav>
	<a href=<c:url value="/" />>Home</a>
	<a href=<c:url value="/registration" />>Registration</a>
	<a href=<c:url value="/login" />>Login</a>
	<sec:authorize access = "isAuthenticated()">
		<a href=<c:url value="/user" />>Profile Info</a>
		<a href=<c:url value="/logout" />>Logout</a>
	</sec:authorize>
</nav>