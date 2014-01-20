<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Search page</title>
		<link rel="stylesheet" type="text/css" media="screen" href="css/default.css" />
        <script type="text/javascript" src="js/jquery-1.9.0.min.js"> </script>
        <script type="text/javascript" src="js/jquery.cookie.js"> </script>
        <script type="text/javascript" src="js/login.js"> </script>
	</head>
	<body>
		<div class="page-wrapper">
             <div class="header">
                <jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
             </div><!--end header-->
             <div id="menu-bar" class="menu-bar">
                <jsp:include page="/WEB-INF/jsp/menubar.jsp"></jsp:include>
             </div><!-- end menu-bar -->
             <div class="content">
	    		<p><span>Search page</span><p>
             </div><!--end content-->
             <div class="footer">
                 <jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
             </div><!--end footer-->
             <script type="text/javascript">
           		//$('.menu-obj').append('<li class="menu-obj-item"><a href="index.jsp">Main</a></li>');
           		$('.menu-obj:first').replaceWith('<li class="menu-obj-item"><a href="index.jsp">Main</a></li>');
           		$( document ).ready(function () {
           			bindLongin();	
           		});
           		
           	</script>
        </div><!--end page-wrapper-->
	</body>
</html>