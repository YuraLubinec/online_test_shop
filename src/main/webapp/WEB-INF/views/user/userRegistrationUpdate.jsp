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
    
      <form:hidden path="id"/>

      <div class="">
        <c:if test="${not empty param.registrationSuccess}">
          <div class="">
            <strong>Success!</strong> Account has been successfully created!
          </div>
        </c:if>
      </div>
      
      <div class="">
        <c:if test="${not empty param.updateSuccess}">
          <div class="">
            <strong>Success!</strong> Account has been successfully updated!
          </div>
        </c:if>
      </div>

      <legend>User profile info</legend>

      
          <form:label path="login" class="">Email*:</form:label>
          <div class="">
            <form:input type="email" path="login" class="form-control" cssErrorClass="" />
            <form:errors path="login" class="" />
          </div>
    

        
          <form:label path="password" class="">Password*:</form:label>
          <div class="" >
            <form:input type="password" path="password" class="form-control" cssErrorClass="" />
            <form:errors path="password" class="" />
          </div>
        

      
          <form:label path="name" class="">First name*:</form:label>
          <div class="">
            <form:input type="text" path="name" class="form-control" cssErrorClass=""/>
            <form:errors path="name" class="" />
          </div>
        

       
          <form:label path="surname" class="">Last name*:</form:label>
          <div class="">
            <form:input type="text" path="surname" class="form-control" cssErrorClass=""/>
            <form:errors path="surname" class="" />
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


<!-- <script src=<c:url value="/resources/bower_components/jquery/dist/jquery.validate.min.js" />></script> -->

<!-- Main page script -->
<!-- <script src=<c:url value="/resources/js/account.js" />></script> -->