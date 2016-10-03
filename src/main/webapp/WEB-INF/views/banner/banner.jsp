<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
  <div class="row">
    <div class="col-md-12">
      <div class="row">
        <c:forEach items="${banners}" var="banner">
          <div class="col-sm-3 col-lg-3 col-md-3 item">
            <div class="thumbnail">
              <div class="hover">
                <img src=<c:url value="/banner/${banner.id}/photo" /> alt="no photo">
                <div class="caption">
                  <h4>
                    <c:out value="${banner.name}" />
                  </h4>
                  <button id="${banner.id}" class="delete pull-right btn btn-default">
                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete 
              </button>
                </div>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
      <div class="pull down">
        <a href=<c:url value="/admin/banners/banner/newBanner" /> class="btn btn-primary">Add new Banner</a>
        <a href=<c:url value="/" /> class="btn btn-default">Cancel</a>
      </div>
    </div>
  </div>
</div>

<input id="contextPath" type="hidden" value="${pageContext.request.contextPath}" />

<!-- Main page script -->
<script src=<c:url value="/resources/js/banner/banner.js" />></script>