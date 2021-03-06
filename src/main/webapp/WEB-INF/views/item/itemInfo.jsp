<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="container">
  <div class="col-md-8 col-md-offset-2 hover">
    <div class="row">
      <img alt="no photo" class="image" src=<c:url value="/item/${item.id}/photo" />>
    </div>
    <div class="row">
      <div class="caption item-info">
        <h4 class="pull-right">
          <c:out value="$ ${item.price}"></c:out>
        </h4>
        <h4>
          <c:out value="${item.name}" />
        </h4>
        <p>
          <c:out value="${item.description}"></c:out>
        </p>
        <button class="btn btn-default pull-right right-btn back-btn">Back</button>
        <sec:authorize access="hasAuthority('CUSTOMER')">
          <button id="${item.id}" class="addToCart btn btn-primary pull-right">
            <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> Add to Cart
          </button>
        </sec:authorize>
      </div>
    </div>
  </div>
</div>

<input id="contextPath" type="hidden" value="${pageContext.request.contextPath}" />

<!-- Main page script -->
<script src=<c:url value="/resources/js/item/item.js" />></script>
