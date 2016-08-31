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
  
  <sec:authorize access ="hasAuthority('ADMIN')">
    <a href=<c:url value='/item/newItem'/>>New Item</a>
  </sec:authorize>

  <table id="items">
    <thead>
      <tr>
        <th class="col-md-2">Name</th>
        <th class="col-md-5">Description</th>
        <th class="col-md-1">Amount</th>
        <th class="col-md-4">Photo</th>
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
            <c:out value="${item.amount}"></c:out>
          </td>
          <td>
          	<img alt="no photo" src=<c:url value="/item/${item.id}/photo" />>
          </td>

        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>