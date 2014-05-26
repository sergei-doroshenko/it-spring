<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
     <head>
        <meta charset="UTF-8">
        <title>Users page</title>
        <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/static/css/ui-lightness/jquery-ui.css"/>" />
        <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/static/css/ui-lightness/jquery.ui.theme.css"/>" />
        <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/static/css/ui-lightness/jquery-ui-1.10.4.custom.css"/>" />
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/static/css/ui.jqgrid.css"/>" />
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/static/css/default.css"/>" />        
		
		<script type="text/javascript" src="<c:url value="/static/js/jquery-1.9.0.min.js"/>"> </script>		
		<script type="text/javascript" src="<c:url value="/static/js/jquery-ui-1.10.4.custom.min.js"/>"> </script>
		<script type="text/javascript" src="<c:url value="/static/js/i18n/grid.locale-ru.js"/>"> </script>
		<script type="text/javascript" src="<c:url value="/static/js/i18n/grid.locale-en.js"/>"> </script>
		<script type="text/javascript" src="<c:url value="/static/js/jquery.jqGrid.min.js"/>"> </script>
		<script type="text/javascript" src="<c:url value="/static/js/jquery.cookie.js"/>"> </script>
        <script type="text/javascript" src="<c:url value="/static/js/jquery.form.js"/>"> </script>	
		<script type="text/javascript" src="<c:url value="/static/js/issuetracker-main.js"/>"> </script>
		<script type="text/javascript" src="<c:url value="/static/js/issuetracker-tables.js"/>"> </script>
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
	             	<div id="users-table-container">
		             	<table id="users-table"></table> 
						<div id="users-pager"></div>
	             	</div><!-- End of users table container -->
	             	<div id="roles-table-container">
	             		<table id="roles-table"></table> 
						<div id="roles-pager"></div>
	             	</div><!--  End of roles table container -->
	         </div><!--end content-->
             <div class="footer">
                 <jsp:include page="${constants.URL_FOOTER}"></jsp:include>
             </div><!--end footer-->
        </div><!--end page-wrapper-->
        <script type="text/javascript">
			$(document).ready(function () {
				createUsersTable();
				createRolesTable();
			});
		</script> 	
	</body>
</html>