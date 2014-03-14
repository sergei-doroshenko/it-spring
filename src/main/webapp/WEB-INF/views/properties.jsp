<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<title>Issue Tracker</title>
		 
		<link rel="stylesheet" type="text/css" media="screen" href="/issuetracker/css/ui-lightness/jquery-ui.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="/issuetracker/css/ui.jqgrid.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="/issuetracker/css/default.css" />
				
		<script type="text/javascript" src="/issuetracker/js/jquery-1.9.0.min.js"> </script>		
		<script type="text/javascript" src="/issuetracker/js/jquery-ui-1.10.4.custom.min.js"> </script>
		<script type="text/javascript" src="/issuetracker/js/i18n/grid.locale-ru.js"> </script>
		<script type="text/javascript" src="/issuetracker/js/i18n/grid.locale-en.js"> </script>
		<script type="text/javascript" src="/issuetracker/js/jquery.jqGrid.min.js"> </script>
		<script type="text/javascript" src="/issuetracker/js/jquery.cookie.js"> </script>
        <script type="text/javascript" src="/issuetracker/js/jquery.form.js"> </script>
		<script type="text/javascript" src="/issuetracker/js/issuetracker-main.js"> </script>
		<script type="text/javascript" src="/issuetracker/js/issuetracker-tables.js"> </script>
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
				<div class="prop-table-container">
					<table id="statuses-table"></table> 
					<div id="statuses-pager"></div>
				</div><!--end statuses table container-->
				<div class="prop-table-container">
					<table id="resolutions-table"></table> 
					<div id="resolutions-pager"></div>
				</div><!--end prop table container-->
				<div class="prop-table-container">
					<table id="priority-table"></table> 
					<div id="priority-pager"></div>
				</div><!--end prop table container-->
				<div class="prop-table-container">
					<table id="types-table"></table> 
					<div id="types-pager"></div>
				</div><!--end prop table container-->
				<div class="projects-table-container">
					<table id="projects-table"></table> 
					<div id="projects-pager"></div>
				</div><!--end projects table container-->
				<div class="builds-table-container">
					<table id="builds-table"></table> 
					<div id="builds-pager"></div>
				</div><!--end projects table container-->
			</div><!--end content-->
			<div class="footer">
				<jsp:include page="${constants.URL_FOOTER}"></jsp:include>
			</div><!--end footer-->
		</div><!--end page-wrapper-->		
		<script type="text/javascript">
			$(document).ready(function () {
				createStatusesTable();
				createResolutionsTable();
				createPrioritiesTable();
				createTypesTable();
				createProjectsTable();
				createBuildsTable();
			});
		</script>
	</body>
</html>