<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="form-group">
	<c:if test="${not empty param.success}">
		<div class="alert alert-success col-md-4 col-md-offset-4">
			<strong>Success!</strong> You have successfully logged in!
		</div>
	</c:if>
</div>

<c:if test = "${empty param.success}">
  <div class="">
    <div class="">
      <div class="">
        <div class="">
          <div class="">
            <h3 class="">Please Sign In</h3>
          </div>
          <div class="">
            <c:if test="${not empty param.error}">
              <div class="alert alert-danger">
                <strong>Error!</strong> Invalid login and password!
              </div>
            </c:if>
            <c:if test="${not empty param.logout}">
              <div class="alert alert-success">
                <strong>Success!</strong> You've been logged out successfully.
              </div>
            </c:if>
            <form role="form" action="${pageContext.request.contextPath}/loginCheck" method="POST">
              <fieldset>
                <div class="">
                  <input class="" type="email" name="login" placeholder="enter login" autofocus>
                </div>
                <div class="">
                  <input class="" type="password" name="password" placeholder="enter password" value="">
                </div>
                <input type="submit" class="" value="Login" />
              </fieldset>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  </c:if>