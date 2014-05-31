<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<title>Bag Tracer</title>
		<link rel="icon" href="<c:url value="/static/img/bug.ico"/>" type="image/x-icon">
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
			<div id="header" class="header">
				<jsp:include page="${constants.URL_HEADER}"/>
			</div><!--end header-->
			<div id="menu-bar" class="menu-bar">
				<jsp:include page="${constants.URL_MENU_TOP}"></jsp:include>
			</div><!-- end menu-bar -->
			<div class="content">
				<div class="table-container">
					<table id="list"></table> 
					<div id="pager"></div>
					<div id="mysearch"></div>
				</div><!--end issue-table-->
				
			</div><!--end content-->
			<div id="searc-form" class="search-container hidden-block"> 
    			<a id="search-button" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset">
    				<span class="ui-icon ui-icon-search"></span>Find
    			</a>	
			</div>
			<div class="footer">
				<jsp:include page="${constants.URL_FOOTER}"></jsp:include>
			</div><!--end footer-->
		</div><!--end page-wrapper-->		
		<script type="text/javascript">
			$(document).ready(function () {
				createIssueTable();
			});
		</script>
	</body>
</html>