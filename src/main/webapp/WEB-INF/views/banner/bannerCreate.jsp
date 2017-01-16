<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
  <div class="row pull-down">
    <div class="col-md-3">

      <form:form method="POST" modelAttribute="bannerDTO" class="form-group" enctype="multipart/form-data">

		<legend>New banner</legend>
		
        <div>
          <form:label path="name">Name*:</form:label>
          <form:input type="text" path="name" class="form-control" cssErrorClass="error" />
          <form:errors path="name" class="help-block with-errors" />
        </div>

        <div class="pull-down">
          <form:label path="photo">Photo (800x300 pixels, image/jpeg format):</form:label>
          <label class="btn btn-default btn-file">
          	<span class="glyphicon glyphicon-upload" aria-hidden="true"></span> Select photo
    	    <form:input type="file" path="photo" class="display-none" />
          </label>
          <form:errors path="photo" class="help-block with-errors" />
        </div>
		
		<div class="pull-down">
          <button id="" type="submit" class="btn btn-success">Save</button>
          <a href=<c:url value="/admin/banners" /> class="btn btn-default">Cancel</a>
		</div>
		
      </form:form>
    </div>
  </div>
</div>