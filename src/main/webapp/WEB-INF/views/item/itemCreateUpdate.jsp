<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
  <div class="row">
    <div class="col-md-4">

      <form:form action="" method="POST" modelAttribute="itemDTO" class="form-group pull-down" enctype="multipart/form-data">
        <fieldset>

          <legend>Item info</legend>

          <div class="">

            <div class="">
              <form:label path="name" class="">Name*:</form:label>
              <div class="">
                <form:input type="text" path="name" class="form-control" cssErrorClass="" />
                <form:errors path="name" class="" />
              </div>
            </div>

            <div class="">
              <label class="">Description*:</label>
              <div class="">
                <form:textarea path="description" class="form-control txt-area" cssErrorClass="" />
                <form:errors path="description" class="" />
              </div>
            </div>

            <div class="">
              <label class="">Price*:</label>
              <div class="">
                <form:input type="number" path="price" class="form-control" cssErrorClass="" />
                <form:errors path="price" class="" />
              </div>
            </div>

            <div class="">
              <label class="">Photo:</label>
              <div class="">
                <label class="btn btn-default btn-file">Select photo 
    		  	  <form:input type="file" path="photo" class="display-none" />
                </label>
                <form:errors path="photo" class="" />
              </div>
            </div>

            <c:if test="${photo_type_error!=null}">
              <c:out value="${photo_type_error}"></c:out>
            </c:if>

          </div>

          <div class="pull-down">
            <button id="save-btn" type="submit" class="btn btn-success">Save</button>
            <a href=<c:url value="/" /> class="btn btn-default">Cancel</a>
          </div>

        </fieldset>
      </form:form>
    </div>
  </div>
</div>