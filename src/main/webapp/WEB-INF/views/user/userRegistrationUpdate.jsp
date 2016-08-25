<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="">
  <form:form action="" method="POST" modelAttribute="user" class="">
    <fieldset>

<%--       <form:hidden path="id" /> --%>

      <div class="form-group">
        <c:if test="${not empty param.success}">
          <div class="">
            <strong>Success!</strong> Account has been successfully created!
          </div>
        </c:if>
      </div>

      <legend>User account data</legend>

      <div class="">
        <div class="">
          <form:label path="login" class="">Email*:</form:label>
          <div class="">
            <form:input type="email" path="login" class="" cssErrorClass="" />
            <form:errors path="login" class="" />
          </div>
        </div>

        <div class="">
          <label class="">Password*:</label>
          <div class="">
            <form:input path="password" class="" cssErrorClass="" />
            <form:errors path="password" class="" />
          </div>
        </div>

        <div class="">
          <label class="">First name*:</label>
          <div class="">
            <form:input type="text" path="name" class="" />
          </div>
        </div>

        <div class="">
          <label class="">Last name*:</label>
          <div class="">
            <form:input type="text" path="surname" class="" />
          </div>
        </div>
      </div>

      <div class="">
        <div class="">
          <div class="">
            <button id="" type="submit" class="">Save</button>
          </div>
        </div>
      </div>

    </fieldset>
  </form:form>
</div>

<!-- <script src=<c:url value="/resources/bower_components/jquery/dist/jquery.validate.min.js" />></script> -->

<!-- Main page script -->
<!-- <script src=<c:url value="/resources/js/account.js" />></script> -->