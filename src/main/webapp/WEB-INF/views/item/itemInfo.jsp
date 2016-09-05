<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="hasAuthority('CUSTOMER') or isAnonymous()">

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
    <label class="">Photo:</label>
    <img alt="no photo" src=<c:url value="/item/${item.id}/photo" />>
  </div>  
</div>

<a href=<c:url value="/" /> class="">Cencel</a>

</sec:authorize>

<sec:authorize access="hasAuthority('ADMIN')">

<div class="">
  <form:form action="" method="POST" modelAttribute="item" class="" enctype="multipart/form-data">
    <fieldset>

      <div class="">
		
		<form:hidden path="id"/>      
      
        <div class="">
          <form:label path="name" class="">Name:</form:label>
          <div class="">
            <form:input type="text" path="name" class="" cssErrorClass="" />
            <form:errors path="name" class="" />
          </div>
        </div>

        <div class="">
          <label class="">Description:</label>
          <div class="">
            <form:input type="text" path="description" class="" />
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
            <button id="" type="submit" class="">Update</button>
            <a href=<c:url value="/" /> class="">Cencel</a>
          </div>
        </div>
      </div>

    </fieldset>
  </form:form>
</div>
</sec:authorize>