<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
  <div class="row">
    <div class="col-md-12">
      <table>
        <thead>
        <tr>
          <th class="col-md-1">Name</th>
          <th class="col-md-4">Photo</th>
          <th class="col-md-1"></th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${banners}" var="banner">
            <tr>
            <td>
              <c:out value="${banner.name}"></c:out>
            </td>
            <td>
              <img class="" src=<c:url value="/banner/${banner.id}/photo" /> alt="no photo">
            </td>
            <td>
            	<button id="${banner.id}" class="delete btn btn-default">Delete</button>
            </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>  
    </div>
  </div>
</div>


<input id="contextPath" type="hidden" value="${pageContext.request.contextPath}" />

<!-- Main page script -->
<script src=<c:url value="/resources/js/banner/banner.js" />></script>