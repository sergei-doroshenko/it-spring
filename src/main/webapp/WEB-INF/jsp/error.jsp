<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>       
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Error page</title>
		<link rel="stylesheet" type="text/css" media="screen" href="css/default.css" />
        <script type="text/javascript" src="js/jquery-1.9.0.min.js"> </script>
        <script type="text/javascript" src="js/jquery.cookie.js"> </script>
        <script type="text/javascript" src="js/login.js"> </script>
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
				<% if(exception != null) {%>
				          
		            <p>Here is the exception stack trace<span>(only during the learning process)</span>: </p>
		            
		        <% exception.printStackTrace(new PrintWriter(out));}%>
             </div><!--end content-->
             <div class="footer">
                 <jsp:include page="${constants.URL_FOOTER}"></jsp:include>
             </div><!--end footer-->
             <script type="text/javascript">
           		$( document ).ready(function () {
           			bindLongin();	
           		});
           	</script>
        </div><!--end page-wrapper-->
	</body>
</html>