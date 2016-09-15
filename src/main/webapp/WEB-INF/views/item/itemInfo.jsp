<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="">
  <div class="">
    <label class="">Name:</label>
    <c:out value="${item.name}"></c:out>
  </div>
</div>

<div class="">
  <div class="">
    <label class="">Description:</label>
    <c:out value="${item.description}"></c:out>
  </div>
</div>

<div class="">
  <div class="">
    <label class="">Price:</label>
    <c:out value="${item.price}"></c:out>
  </div>
</div>

<div class="">
  <div class="">
    <label class="">Photo:</label>
    <img alt="no photo" src=<c:url value="/item/${item.id}/photo" />>
  </div>  
</div>

<sec:authorize access="isAuthenticated()"> 
  <button id="${item.id}" class="addToCart btn btn-primary">Add to Cart</button>               
</sec:authorize>

<a href=<c:url value="/" /> class="btn btn-default">Back</a>

<input id="contextPath" type="hidden" value="${pageContext.request.contextPath}" />

<!-- Main page script -->
<script src=<c:url value="/resources/js/item/item.js" />></script>
