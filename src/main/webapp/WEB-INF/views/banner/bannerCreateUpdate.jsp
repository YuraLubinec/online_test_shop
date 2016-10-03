<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
  <div class="row">
    <div class="col-md-3">

      <form:form action="" method="POST" modelAttribute="bannerDTO" class="" enctype="multipart/form-data">

        <div>
          <form:label path="name">Name*:</form:label>
          <form:input type="text" path="name" class="form-control" cssErrorClass="" />
          <form:errors path="name" class="" />
        </div>

        <div class="pull-down">
          <form:label path="photo">Photo:</form:label>
          <label class="btn btn-default btn-file">
          	<span class="glyphicon glyphicon-upload" aria-hidden="true"></span> Select photo
    	    <form:input type="file" path="photo" class="display-none" />
          </label>
          <form:errors path="photo" class="" />
        </div>
		
		<div class="pull-down">
          <button id="" type="submit" class="btn btn-success">Save</button>
          <a href=<c:url value="/admin/banners" /> class="btn btn-default">Cancel</a>
		</div>
		
      </form:form>
    </div>
  </div>
</div>