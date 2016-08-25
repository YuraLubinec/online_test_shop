<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${succes==true}">
  <div class="">
    <div class="">
      <strong>Success!</strong> You have successfully logged in!
      <a href=<c:url value="/logout" /> class="">Logout</a>
    </div>
  </div>
</c:if>

<c:if test="${succes!=true}">
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

            <form role="" action="${pageContext.request.contextPath}/loginCheck" method="POST">
              <fieldset>
                <div class="">
                  <input class="" type="email" name="username" placeholder="enter login" autofocus>
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