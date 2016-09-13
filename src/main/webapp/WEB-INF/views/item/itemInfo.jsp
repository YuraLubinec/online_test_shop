<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

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

