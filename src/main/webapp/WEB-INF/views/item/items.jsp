<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="container">
  <div class="row">
    <div class="col-md-12">
      
      <div class="row carousel-holder">
        <div class="col-md-12 no-side-padding">
          <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
              <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
              <li data-target="#carousel-example-generic" data-slide-to="1"></li>
              <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
              <div class="item active">
                <img class="slide-image" src=<c:url value="/banner/${activeBannerId}/photo" /> alt="no photo">
              </div>
              <c:forEach
                items="${banners}" var="banner">
                <div class="item"> 
                  <img class="slide-image" src=<c:url value="/banner/${banner.id}/photo" /> alt="no photo">
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
      
      <div class="row search-field">
        <div class="navbar-form navbar-right">
          <form:form modelAttribute="itemFilterDTO" method="GET">
            <div class="form-group">
              <form:input type="text" path="itemNameFilter" class="form-control" placeholder="Search item" />
            </div> 
            <button type="submit" class="btn btn-default">
              <span class="glyphicon glyphicon-search"></span>
            </button>
          </form:form>
        </div>
      </div>
      
      <div class="row">
        <c:forEach items="${items}" var="item">
          <div class="col-sm-3 col-lg-3 col-md-3 item">
            <div class="thumbnail"> <img alt="no photo" src=<c:url value="/item/${item.id}/photo" />>
              <div class="caption">
                
                <h4 class="pull-right">
                  <c:out value="$ ${item.price}" />
                </h4>
                
                <h4>
                  <sec:authorize access="hasAuthority('ADMIN')">
                    <a href=<c:url value="/admin/item/${item.id}" />>${item.name}</a>
                  </sec:authorize>
                  <sec:authorize access="isAnonymous() or hasAuthority('CUSTOMER')">
                    <c:out value="${item.name}" />
                  </sec:authorize>
                </h4>
               
                <sec:authorize access="hasAuthority('ADMIN')"> 
                  <button id="${item.id}" class="delete pull-right btn btn-default right-btn">
                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete 
                  </button>
                </sec:authorize>
                
                <sec:authorize access="hasAuthority('CUSTOMER')">
                  <button id="${item.id}" class="addToCart pull-right btn btn-primary right-btn">
                    <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> Add to Cart
                  </button> 
                </sec:authorize> <a class="btn btn-info pull-right" href=<c:url value="/item/${item.id}" />>More info</a>
              
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