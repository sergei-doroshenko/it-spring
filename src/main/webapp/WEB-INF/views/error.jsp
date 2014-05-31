<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Error page</title>
		<link rel="icon" href="<c:url value="/static/img/bug.ico"/>" type="image/x-icon">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/static/css/ui-lightness/jquery-ui.css"/>" />
        <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/static/css/ui-lightness/jquery.ui.theme.css"/>" />
        <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/static/css/ui-lightness/jquery-ui-1.10.4.custom.css"/>" />
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/static/css/default.css"/>" />        
		
		<script type="text/javascript" src="<c:url value="/static/js/jquery-1.9.0.min.js"/>"> </script>		
		<script type="text/javascript" src="<c:url value="/static/js/jquery-ui-1.10.4.custom.min.js"/>"> </script>
		<script type="text/javascript" src="<c:url value="/static/js/jquery.cookie.js"/>"> </script>
        <script type="text/javascript" src="<c:url value="/static/js/jquery.form.js"/>"> </script>
		<script type="text/javascript" src="<c:url value="/static/js/issuetracker-main.js"/>"> </script>
	</head>
	<body>
		<div class="page-wrapper">
             <div class="header">
                <jsp:include page="${constants.URL_HEADER}"></jsp:include>
             </div><!--end header-->
             <div id="menu-bar" class="menu-bar">
                <jsp:include page="${constants.URL_MENU_TOP}"></jsp:include>
             </div><!-- end menu-bar -->
             <div class="content">
	    		<p><span>Sorry, an error occurred.</span></p>
	    		<c:out value="${error}"/>
             </div><!--end content-->
             <div class="footer">
                 <jsp:include page="${constants.URL_FOOTER}"></jsp:include>
             </div><!--end footer-->
        </div><!--end page-wrapper-->
	</body>
</html>