<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<title>Issue Tracker</title>
		 
		<link rel="stylesheet" type="text/css" media="screen" href="css/ui-lightness/jquery-ui.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="css/ui.jqgrid.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="css/default.css" />
				
		<script type="text/javascript" src="js/jquery-1.9.0.min.js"> </script>
		<script type="text/javascript" src="js/i18n/grid.locale-en.js"> </script>
		<script type="text/javascript" src="js/jquery.jqGrid.min.js"> </script>
		<script type="text/javascript" src="js/jquery.cookie.js"> </script>
		<script type="text/javascript" src="js/issue-tracker-main.js"> </script>
		<script type="text/javascript" src="js/login.js"> </script>
		<script type="text/javascript" src="js/prop-table.js"> </script>
		
		<!-- i18n -->
		<fmt:requestEncoding value="UTF-8" />
		<fmt:setLocale value="${sessionScope[constants.KEY_LOCALE]}"/>
		<fmt:setBundle basename="i18n.main" var="lang"/>
		<!-- End of i18n --> 
		
	</head>
	<body>
		<div class="page-wrapper">
			<div id="header" class="header">
				<jsp:include page="${constants.URL_HEADER}"/>
			</div><!--end header-->
			<div id="menu-bar" class="menu-bar">
				<jsp:include page="${constants.URL_MENU_TOP}"></jsp:include>
			</div><!-- end menu-bar -->
			<div class="content">
				<div class="table-container">
					<table id="statuses-table"></table> 
					<div id="statuses-pager"></div>
				</div><!--end statuse-table-->
			</div><!--end content-->
			<div class="footer">
				<jsp:include page="${constants.URL_FOOTER}"></jsp:include>
			</div><!--end footer-->
		</div><!--end page-wrapper-->		
		<script type="text/javascript">
			$(document).ready(function () {
				createStatusesTable();
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