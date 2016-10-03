<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
  <div class="row">
    <div class="col-md-4">

      <form:form method="POST" modelAttribute="itemDTO" class="form-group pull-down" enctype="multipart/form-data">
        <fieldset>

          <legend>Item info</legend>

          <div>
            <form:label path="name" >Name*:</form:label>
            <form:input type="text" path="name" class="form-control" cssErrorClass="error" />
            <form:errors path="name" class="help-block with-errors" />
          </div>

          <div>
            <form:label path="description" >Description*:</form:label>
            <form:textarea path="description" class="form-control txt-area" cssErrorClass="error txt-area" />
            <form:errors path="description" class="help-block with-errors" />
          </div>

          <div>
            <form:label path="price" >Price*:</form:label>
            <form:input type="number" path="price" class="form-control" cssErrorClass="error" />
            <form:errors path="price" class="help-block with-errors" />
          </div>

          <div class="pull-down">
            <form:label path="photo" >Photo:</form:label>
            <label class="btn btn-default btn-file">
              <span class="glyphicon glyphicon-upload" aria-hidden="true"></span> Select photo 
    		  <form:input type="file" path="photo" class="display-none"  />
            </label>
            <form:errors path="photo" class="help-block with-errors" />
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