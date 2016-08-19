<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
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
                  <td><c:out value="${item.name}"></c:out></td>
                  <td><c:out value="${item.description}"></c:out></td>
                  <td><c:out value="${item.amount}"></c:out></td>
                  <td><c:out value="${item.photo}"></c:out></td>
                  
                </tr>
              </c:forEach>
            </tbody>
          </table>

</div>