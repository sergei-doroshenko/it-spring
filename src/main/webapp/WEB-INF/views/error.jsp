<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- i18n -->
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope[constants.KEY_LOCALE]}"/>
<fmt:setBundle basename="i18n.main" var="lang"/>
<!-- End of i18n -->        
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Error page</title>
		<link rel="stylesheet" type="text/css" media="screen" href="/issuetracker/css/ui-lightness/jquery-ui.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="/issuetracker/css/ui-lightness/jquery.ui.theme.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="/issuetracker/css/ui-lightness/jquery-ui-1.10.4.custom.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="/issuetracker/css/default.css" />        
		
		<script type="text/javascript" src="/issuetracker/js/jquery-1.9.0.min.js"> </script>		
		<script type="text/javascript" src="/issuetracker/js/jquery-ui-1.10.4.custom.min.js"> </script>
		<script type="text/javascript" src="/issuetracker/js/jquery.cookie.js"> </script>
        <script type="text/javascript" src="/issuetracker/js/jquery.form.js"> </script>
		<script type="text/javascript" src="/issuetracker/js/issuetracker-main.js"> </script>
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
				<% if(exception != null) {%>
				          
		            <p>Here is the exception stack trace<span>(only during the learning process)</span>: </p>
		            
		        <% exception.printStackTrace(new PrintWriter(out));}%>
             </div><!--end content-->
             <div class="footer">
                 <jsp:include page="${constants.URL_FOOTER}"></jsp:include>
             </div><!--end footer-->
        </div><!--end page-wrapper-->
	</body>
</html>