<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container">
    <div class="" id="">
      <ul class="nav navbar-nav">

        <li class="home-navbar">
          <a class="home-navbar" href=<c:url value="/" />>Home</a>
        </li>

        <sec:authorize access="isAuthenticated()">
          <li>
            <a href=<c:url value="/user" />>Profile Info</a>
          </li>
          <li>
            <a href=<c:url value="/user/cart" />>Cart</a>
          </li>
          <li>
            <a href=<c:url value="/logout" />>Logout</a>
          </li>
        </sec:authorize>

        <sec:authorize access="isAnonymous()">
          <li>
            <a href=<c:url value="/registration" />>Registration</a>
          </li>
          <li>
            <a href=<c:url value="/login" />>Login</a>
          </li>
        </sec:authorize>

      </ul>
    </div>
  </div>
</nav>