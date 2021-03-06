<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="container">
  <div class="row">
    <div class="col-md-3">

      <form:form action="" method="POST" modelAttribute="user" class="form-group pull-down">
        <fieldset>

          <form:hidden path="id" />
    
            <c:if test="${not empty param.registrationSuccess}">
              <div class="alert alert-success">
                <strong>Success!</strong> Account has been successfully created!
              </div>
            </c:if>
  
           <c:if test="${not empty param.updateSuccess}">
              <div class="alert alert-success">
                <strong>Success!</strong> Account has been successfully updated!
              </div>
            </c:if>
       

          <legend>User profile info</legend>

          <form:label path="login" class="">Email*:</form:label>
          <div class="">
            <form:input type="email" path="login" class="form-control" cssErrorClass="error" />
            <form:errors path="login" class="help-block with-errors" />
          </div>

          <form:label path="password" class="">Password*:</form:label>
          <div class="">
            <form:input type="password" path="password" class="form-control" cssErrorClass="error" />
            <form:errors path="password" class="help-block with-errors" />
          </div>

          <form:label path="name" class="">First name*:</form:label>
          <div class="">
            <form:input type="text" path="name" class="form-control" cssErrorClass="error" />
            <form:errors path="name" class="help-block with-errors" />
          </div>

          <form:label path="surname" class="">Last name*:</form:label>
          <div class="">
            <form:input type="text" path="surname" class="form-control" cssErrorClass="error" />
            <form:errors path="surname" class="help-block with-errors" />
          </div>

          <div class="pull-down">
            <button id="" type="submit" class="btn btn-success">Save</button>
            <sec:authorize access="isAuthenticated()">
              <a href=<c:url value="/" /> class="btn btn-default">Cancel</a>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
              <a href=<c:url value="/login" /> class="btn btn-default">Cancel</a>
            </sec:authorize>
          </div>

        </fieldset>
      </form:form>
    </div>
  </div>
</div>

<!-- Main page script -->
<!-- <script src=<c:url value="/resources/js/account.js" />></script> -->