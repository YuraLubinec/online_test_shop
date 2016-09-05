<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:if test="${userName != null}">
  Hello <strong><c:out value="${userName}"></c:out></strong>
</c:if>

<div>
 
  <table id="items">
    <thead>
      <tr>
        <th class="col-md-2">Name</th>
        <th class="col-md-5">Description</th>
        <th class="col-md-4">Photo</th>
        <th class="col-md-1"></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${items}" var="item">
        <tr class="">

          <td>
            <a href=<c:url value="/item/${item.id}" />>${item.name}</a>
          </td>
          <td>
            <c:out value="${item.description}"></c:out>
          </td>
          <td>
          	<img alt="no photo" src=<c:url value="/item/${item.id}/photo" />>
          </td>
          <td>
            <button id="${item.id}" class="deleteFromCart">Remove</button>
          </td>

        </tr>
      </c:forEach>
    </tbody>
  </table>
  
</div>

<input id="contextPath" type="hidden" value="${pageContext.request.contextPath}" />

<!-- Main page script -->
<script src=<c:url value="/resources/js/user/user.js" />></script>