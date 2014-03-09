<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login page</title>
	</head>
	<body onload='document.f.j_username.focus();'>
		<h3>Login with Username and Password</h3>
		<form name='f' method='POST' action='issuetracker/j_spring_security_check'>
			<table>
				<tr><td>User:</td><td>
				<input type='text' name='j_username' value=''>
				</td></tr>
				<tr><td>Password:</td><td>
				<input type='password' name='j_password'/>
				</td></tr>
				<tr>
					<th></th>
					<td><input id="remember_me" name="_spring_security_remember_me" type="checkbox"/>
					<label for="remember_me" class="inline">Remember me</label></td>
				</tr>
				<tr><td colspan='2'><input name="submit" type="submit"/></td></tr>
				<tr><td colspan='2'><input name="reset" type="reset"/></td></tr>
			</table>
		</form>
	</body>
</html>