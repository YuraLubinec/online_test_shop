<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>
    <tiles:insertAttribute name="title" ignore="true" />
  </title>

  <!-- Google Fonts -->
  <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
  <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
  <link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>

  <!-- Bootstrap -->
  <link href="<c:url value = "/store-components/css/bootstrap.min.css" />" rel="stylesheet">

  <!-- Font Awesome -->
  <link href="<c:url value = "/resources/store-components/css/font-awesome.min.css" />" rel="stylesheet">

  <!-- Custom CSS -->
  <link href="<c:url value = "/resources/store-components/ccss/owl.carousel.css" />" rel="stylesheet">
  <link href="<c:url value = "/resources/store-components/css/style.css" />" rel="stylesheet">
  <link href="<c:url value = "/resources/store-components/css/responsive.css" />" rel="stylesheet">


  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

  <!-- jQuery -->
  <script src="<c:url value = "/resources/store-components/js/jquery.min.js" />"></script>

  <!-- Bootstrap JS -->
  <script src="<c:url value = "/resources/store-components/js/bootstrap.min.js" />"></script>

  <!-- jQuery sticky menu -->
  <script src="<c:url value = "/resources/store-components/js/owl.carousel.min.js" />"></script>
  <script src="<c:url value = "/resources/store-components/js/jquery.sticky.js" />"></script>

  <!-- jQuery easing -->
  <script src="<c:url value = "/resources/store-components/js/jquery.easing.1.3.min.js" />"></script>


</head>

<body>
  
  <tiles:insertAttribute name="menu" />

  <div id="page-wrapper">
    <tiles:insertAttribute name="body" />
  </div>

</body>

</html>