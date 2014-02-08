<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.training.issuetracker.domain.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="logo">
	<h2>Issue Tracker</h2>
</div><!--end logo-->

<!-- i18n -->
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope[constants.KEY_LOCALE]}"/>
<fmt:setBundle basename="i18n.main" var="lang"/>
<!-- End of i18n -->        

<div id="lang">
   	<span id="en-loc" class="button-locale">EN</span>
	<span id="ru-loc" class="button-locale">RU</span>
</div>
<div id="dialog-confirm" title="Empty the recycle bin?">
	<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>Are you sure?</p>
</div><!-- End of user dialog -->
<div id="dialog-form" class="user-dialog" title="Create new user">
	<p class="validateTips">All form fields are required.</p>
	<form>
		<fieldset>
			<label for="name">Name</label>
			<input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all">
			<label for="email">Email</label>
			<input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all">
			<label for="password">Password</label>
			<input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all">
		</fieldset>
	</form><!-- End of user form -->
</div><!-- End of user dialog form -->
<div id="user-info" class="user-info">
	<div id="error"></div>
     <form id="auth-form">
	      <a class="logout" href="${constants.URL_LOGOUT_COMMAND}"><fmt:message key="user.logout" bundle="${lang}"/></a>
     </form>
</div><!--end user-info-->
		
