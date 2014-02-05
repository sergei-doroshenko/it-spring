<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Search page</title>
		<link rel="stylesheet" type="text/css" media="screen" href="css/default.css" />
        <script type="text/javascript" src="js/jquery-1.9.0.min.js"> </script>
        <script type="text/javascript" src="js/jquery.cookie.js"> </script>
        <script type="text/javascript" src="js/login.js"> </script>
	</head>
	<body>
		<!------------ i18n ------------------>
		<fmt:requestEncoding value="UTF-8" />
		<c:choose>
			<c:when test="${!empty param.lang}">
				<c:set var="language" value="${param.lang}" scope="session"/>
				<fmt:setLocale value="${param.lang}"/>
			</c:when>
			<c:otherwise>
				<c:if test="${!empty sessionScope.language}">
					<fmt:setLocale value="${sessionScope.language}"/>
				</c:if>
			</c:otherwise>
		</c:choose>
				
		<fmt:setBundle basename="i18n.main" var="lang"/>
		
		<!----------- End of i18n ----------------> 
		<div class="page-wrapper">
             <div class="header">
                <jsp:include page="${constants.URL_HEADER}"></jsp:include>
             </div><!--end header-->
             <div id="menu-bar" class="menu-bar">
                <jsp:include page="${constants.URL_MENU_TOP}"></jsp:include>
             </div><!-- end menu-bar -->
             <div class="content">
	    		<p><span>Sorry. This page in is in the developing process... </span><p>
             </div><!--end content-->
             <div class="footer">
                 <jsp:include page="${constants.URL_FOOTER}"></jsp:include>
             </div><!--end footer-->
             <script type="text/javascript">
           		$( document ).ready(function () {
           			bindLongin();
           			$('#en-loc').click(function(ev) {
           				changeLocaleUrl (ev);
           			});
           			$('#ru-loc').click(function(ev) {
           				changeLocaleUrl (ev);
           			});
           		});
           	</script>
        </div><!--end page-wrapper-->
	</body>
</html>