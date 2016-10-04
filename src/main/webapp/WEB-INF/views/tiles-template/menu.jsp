<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container">
    <ul class="nav navbar-nav">
      <li class="home-navbar">
        <a class="home-navbar" href=<c:url value="/" />>
        <span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home
        </a>
      </li>

      <sec:authorize access="isAuthenticated()">
        <li>
          <a href=<c:url value="/user" />>
          <span class="glyphicon glyphicon-user" aria-hidden="true"></span> Profile info
          </a>
        </li>
      </sec:authorize>
      <sec:authorize access="hasAuthority('CUSTOMER')">
        <li>
          <a href=<c:url value="/user/cart" />>
          <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> User cart
          </a>
        </li>
      </sec:authorize>

      <sec:authorize access="hasAuthority('ADMIN')">
        <li class="dropdown">
          <a href="#" data-toggle="dropdown" class="dropdown-toggle"> <span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Admin Tools
          </a>
          <ul class="dropdown-menu">
            <li>
              <a href=<c:url value="/admin/banners" />>List of banners</a>
            </li>
            <li>
              <a href=<c:url value="/admin/banners/banner/newBanner" />>Add new banner
              </a>
            </li>
            <li>
              <a href=<c:url value="/admin/item/newItem" />>Add new item</a>
            </li>
          </ul>
        </li>
      </sec:authorize>
    </ul>

    <sec:authorize access="isAuthenticated()">
      <ul class="nav navbar-nav navbar-right">
        <li class="navbar-right-button">
          <a href=<c:url value="/logout" />>
          <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Logout
          </a>
        </li>
      </ul>
    </sec:authorize>

    <sec:authorize access="isAnonymous()">
      <ul class="nav navbar-nav navbar-right">
        <li>
          <a href=<c:url value="/registration" />>
          <span class="glyphicon glyphicon-registration-mark" aria-hidden="true"></span> Registration
          </a>
        </li>
        <li class="navbar-right-button">
          <a href=<c:url value="/login" />>
          <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> Login
          </a>
        </li>
      </ul>
    </sec:authorize>

  </div>
</nav>