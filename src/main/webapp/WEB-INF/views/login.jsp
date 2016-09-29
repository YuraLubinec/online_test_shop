<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
  <div class="row">
    <div class="col-md-4 col-md-offset-4">

      <c:if test="${succes==true}">
        <div class="form-signin">
          <div class="login-title">
            <strong>Congrats!</strong> You have successfully logged in!
            <a href=<c:url value="/logout" />>Logout</a>
          </div>
        </div>
      </c:if>

      <c:if test="${succes!=true}">
        <div class="form-signin">
          <h2 class="login-title">Please sign in</h2>

          <c:if test="${not empty param.error}">
            <div class="alert alert-danger">
              <strong>Error!</strong> Invalid login or password
            </div>
          </c:if>

          <form action="${pageContext.request.contextPath}/loginCheck" method="POST">
            <fieldset>
              <input class="form-control email-field" type="email" name="username" placeholder="Email address" autofocus required>
              <input class="form-control" type="password" name="password" placeholder="enter password" required>
              <input type="submit" class="btn btn-lg btn-primary btn-block" value="Login" />
            </fieldset>
          </form>

          <a class="text-center new-account" href=<c:url value="/registration" />>Register new account</a>
        </div>
      </c:if>
    </div>
  </div>
</div>
