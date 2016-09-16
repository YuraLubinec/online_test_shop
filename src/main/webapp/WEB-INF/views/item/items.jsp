<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="container">
  <div class="row">
    <div class="col-md-12">
        <div class="row carousel-holder">
          <div class="col-md-12">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
              <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
              </ol>
              <div class="carousel-inner">
                <div class="item active">
                  <img class="slide-image" src=<c:url value="/item/${activeItemId}/photo" /> alt="no photo">
                </div>
                <c:forEach items="${itemsForCarousel}" var="item">
                  <div class="item">
                    <img class="slide-image" src=<c:url value="/item/${item.id}/photo" /> alt="no photo">
                  </div>
                </c:forEach>
              </div>
              <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
              </a>
              <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
              </a>
            </div>
          </div>
        </div>

 <form:form modelAttribute="itemFilterDTO" method="GET">
          <fieldset>
            <div class="">
              <div class="pull-right">
                <form:input type="text" path="itemNameFilter" class="" placeholder="Search item" />
              </div>
            </div>
          </fieldset>
        </form:form>

        <sec:authorize access="hasAuthority('ADMIN')">
          <a href=<c:url value='/admin/item/newItem' />>New Item</a>
        </sec:authorize>
        <div class="row">
          <c:forEach items="${items}" var="item">
            <div class="col-sm-3 col-lg-3 col-md-3">
              <div class="thumbnail">
                <img alt="no photo" src=<c:url value="/item/${item.id}/photo" />>
                <div class="caption"> 
                  <h4 class="pull-right">
                    <c:out value="$${item.price}" />
                  </h4>    
                  <h4>
                    <sec:authorize access="hasAuthority('ADMIN')">
                      <a href=<c:url value="/admin/item/${item.id}" />>${item.name}</a>
                    </sec:authorize>
                    <sec:authorize access="isAnonymous() or hasAuthority('CUSTOMER')">
                      <a href=<c:url value="/item/${item.id}" />>${item.name}</a>
                    </sec:authorize>
                  </h4>
                  <p><c:out value="${item.description}" /></p>
                  <sec:authorize access="hasAuthority('ADMIN')">
                    <button id="${item.id}" class="delete pull-right btn btn-default">Delete</button>
                  </sec:authorize>
                  <sec:authorize access="hasAuthority('CUSTOMER')">
                    <button id="${item.id}" class="addToCart pull-right btn btn-primary">Add to Cart</button>
                  </sec:authorize>
                </div>
              </div>
            </div>
          </c:forEach>
        </div>
      </div>
    </div>
</div>

<input id="contextPath" type="hidden" value="${pageContext.request.contextPath}" />

<!-- Main page script -->
<script src=<c:url value="/resources/js/item/item.js" />></script>