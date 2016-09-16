<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
  <form:form action="" method="POST" modelAttribute="bannerDTO" class="" enctype="multipart/form-data">
    <fieldset>

      <div class="">

        <div class="">
          <form:label path="name" class="">Name*:</form:label>
          <div class="">
            <form:input type="text" path="name" class="" cssErrorClass="" />
            <form:errors path="name" class="" />
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

      <button id="" type="submit" class="btn btn-primary">Save</button>
    </fieldset>
  </form:form>
</div>