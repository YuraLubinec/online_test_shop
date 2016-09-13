<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="">
  <form:form action="" method="POST" modelAttribute="itemDTO" class="" enctype="multipart/form-data">
    <fieldset>

      <legend></legend>

      <div class="">
      
        <div class="">
          <form:label path="name" class="">Name*:</form:label>
          <div class="">
            <form:input type="text" path="name" class="" cssErrorClass="" />
            <form:errors path="name" class="" />
          </div>
        </div>

        <div class="">
          <label class="">Description*:</label>
          <div class="" >
            <form:input path="description" class="" cssErrorClass="" />
            <form:errors path="description" class="" />
          </div>
        </div>

        <div class="">
         <label class="">Photo:</label>
          <div class="">
            <form:input type="file" path="photo" class="" />
            <form:errors path="photo" class="" />
          </div>
        </div>
        
        <c:if test="${photo_type_error!=null}">
		  <c:out value="${photo_type_error}"></c:out>
	    </c:if> 
	     
      </div>

      <div class="">
        <div class="">
          <div class="">
            <button id="" type="submit" class="">Save</button>
            <a href=<c:url value="/" /> class="">Cancel</a>
          </div>
        </div>
      </div>

    </fieldset>
  </form:form>
</div>