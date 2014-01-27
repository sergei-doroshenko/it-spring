<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
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
		<script type="text/javascript" src="js/login.js"> </script>
		<script type="text/javascript" src="js/table.js"> </script>
	</head>
	<body>
		<div class="page-wrapper">
			<div id="header" class="header">
				<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
			</div><!--end header-->
			<div id="menu-bar" class="menu-bar">
				<jsp:include page="/WEB-INF/jsp/menubar.jsp"></jsp:include>
			</div><!-- end menu-bar -->
			<div class="content">
				<div class="table-container">
					<table id="list"><tr><td></td></tr></table> 
					<div id="pager"></div>
				</div><!--end issue-table-->
			</div><!--end content-->
			<div class="footer">
				<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
			</div><!--end footer-->
		</div><!--end page-wrapper-->
		<script type="text/javascript">
			$(document).ready(function () {
				createIssueTable();
				if($('#auth-form')) {
					bindLongin();
				}
			});
		</script>
			
	</body>
</html>