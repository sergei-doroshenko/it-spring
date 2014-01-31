<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>      
<!DOCTYPE html>
<html>
     <head>
        <meta charset="UTF-8">
        <title>Delails</title>
        <link rel="stylesheet" type="text/css" media="screen" href="css/default.css" />
        <script type="text/javascript" src="js/jquery-1.9.0.min.js"> </script>
        <script type="text/javascript" src="js/jquery.cookie.js"> </script>
        <script type="text/javascript" src="js/login.js"> </script>
     </head>
     <body>
        <div class="page-wrapper">
             <div class="header">
                <jsp:include page="${constants.URL_HEADER}"/>
             </div><!--end header-->
             <div id="menu-bar" class="menu-bar">
                <jsp:include page="${constants.URL_MENU_TOP}"/>
             </div><!-- end menu-bar -->
             <div class="content">
             	<jsp:include page="${constants.URL_BUTTONS_BLOCK}"/>
	        	<c:choose>
             		<c:when test="${requestScope[constants.ENTITY_TYPE] eq constants.ISSUE_TYPE}">
             			<jsp:include page="${constants.URL_ISSUE_DETAILS}"/>
             		</c:when>
             	</c:choose>
             </div><!--end content-->
             <div class="footer">
                 <jsp:include page="${constants.URL_FOOTER}"></jsp:include>
             </div><!--end footer-->
        </div><!--end page-wrapper-->
        <script type="text/javascript">
             $( document ).ready(function () {
                  bindLongin();
                  $('.detail-col-input').attr('readonly','readonly');
                  $('.detail-col-select').attr("disabled", true);
                  $('.description').attr('readonly','readonly');
                  
             });
        </script>
     </body>
</html>