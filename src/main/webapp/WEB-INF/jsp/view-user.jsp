<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <meta charset="UTF-8">
        <title>Issue edit page</title>
        <link rel="stylesheet" type="text/css" media="screen" href="css/default.css" />
        <script type="text/javascript" src="js/jquery-1.9.0.min.js"> </script>
        <script type="text/javascript" src="js/jquery.cookie.js"> </script>
        <script type="text/javascript" src="js/issue-tracker-main.js"> </script>
     </head>
	<body>
	
		<div class="page-wrapper">
             <div class="header">
                <jsp:include page="${constants.URL_HEADER_SIMPLE}"/>
             </div><!--end header-->
             <div id="menu-bar" class="menu-bar">
                <jsp:include page="${constants.URL_MENU_TOP}"/>
             </div><!-- end menu-bar -->
             <div class="content">
	             	<jsp:include page="${constants.URL_BUTTONS_VIEW_USER}"/>
	             	<span>${user.firstName} ${user.lastName}</span>
	         </div><!--end content-->
             <div class="footer">
                 <jsp:include page="${constants.URL_FOOTER}"></jsp:include>
             </div><!--end footer-->
        </div><!--end page-wrapper-->
        <script type="text/javascript">
			$(document).ready(function () {	
				bindLongin();
				$('#en-loc').click(function(ev) {
					changeLocaleUrl (ev);
				});
				$('#ru-loc').click(function(ev) {
					changeLocaleUrl (ev);
				});
			});
		</script>  	
	</body>
</html>