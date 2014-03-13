<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<title>Issue Tracker</title>
		<link rel="stylesheet" type="text/css" media="screen" href="css/default.css" />
		<script type="text/javascript" src="js/jquery-1.9.0.min.js"> </script>		
		<script type="text/javascript" src="js/issuetracker-main.js"> </script>
	</head>
	<body>
		<jsp:forward page="/views/issues" />
	</body>
</html>