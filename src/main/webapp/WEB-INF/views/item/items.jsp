<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:if test="${userName != null}">
  Hello <strong><c:out value="${userName}"></c:out></strong>
</c:if>

<div>
  <form:form modelAttribute="itemFilterDTO" method="GET">
    <fieldset>
      <h4>Filters</h4>
      <div class="">
        <div class="">
          <form:input type="text" path="itemNameFilter" class="form-control" placeholder="Item name" />
        </div>
      </div>
    </fieldset>
  </form:form>

  <sec:authorize access="hasAuthority('ADMIN')">
    <a href=<c:url value='/admin/item/newItem' />>New Item</a>
  </sec:authorize>

  <table id="items">
    <thead>
      <tr>
        <th class="col-md-1">Name</th>
        <th class="col-md-2">Description</th>
        <th class="col-md-1">Price</th>
        <th class="col-md-2">Photo</th>  
        <sec:authorize access="isAuthenticated()">
          <th class="col-md-1">Add To Cart</th>
        </sec:authorize>
        <sec:authorize access="hasAuthority('ADMIN')">
          <th class="col-md-1">Remove</th>
        </sec:authorize>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${items}" var="item">
        <tr class="">

          <td>
            <sec:authorize access="hasAuthority('ADMIN')">
              <a href=<c:url value="/admin/item/${item.id}" />>${item.name}</a>
            </sec:authorize>
            <sec:authorize access="isAnonymous() or hasAuthority('CUSTOMER')">
              <a href=<c:url value="/item/${item.id}" />>${item.name}</a>
            </sec:authorize>
          </td>
          <td>
            <c:out value="${item.description}"></c:out>
          </td>
          <td>
            <c:out value="${item.price}"></c:out>
          </td>
          <td>
            <img alt="no photo" src=<c:url value="/item/${item.id}/photo" />>
          </td>
          <sec:authorize access="isAuthenticated()">
            <td>
              <button id="${item.id}" class="addToCart">Add to Cart</button>
            </td>
          </sec:authorize>
          <sec:authorize access="hasAuthority('ADMIN')">
            <td>
              <button id="${item.id}" class="delete">Remove Item</button>
            </td>
          </sec:authorize>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

<input id="contextPath" type="hidden" value="${pageContext.request.contextPath}" />

<!-- Main page script -->
<script src=<c:url value="/resources/js/item/item.js" />></script>