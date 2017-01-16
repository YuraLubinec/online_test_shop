<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="container pull-down">
  <div class="row">
    <c:forEach items="${items}" var="item">
      <div class="col-sm-3 col-lg-3 col-md-3 item">
        <div class="thumbnail">
          <div class="hover">
            <img alt="no photo" src=<c:url value="/item/${item.id}/photo" />>
            <div class="caption">
              <h4 class="pull-right"><c:out value="$ ${item.price}" /></h4>
              <h4><c:out value="${item.name}" /></h4>
              <button id="${item.id}" class="deleteFromCart btn btn-default">Remove</button>
              <a class="btn btn-info " href=<c:url value="/item/${item.id}" />>More info</a>
              <sec:authorize access="hasAuthority('CUSTOMER')">
                <button id="${item.id}" class="btn btn-primary">
                  <span class="glyphicon glyphicon-usd" aria-hidden="true"></span> Buy!
                </button>
              </sec:authorize>
            </div>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
  <button class="btn btn-default back-btn pull-down">Back</button>
</div>

<input id="contextPath" type="hidden" value="${pageContext.request.contextPath}" />

<!-- Main page script -->
<script src=<c:url value="/resources/js/user/user.js" />></script>