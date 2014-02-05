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

<div id="user-info" class="user-info">
	<div id="error"></div>
     <form id="auth-form">
	      <a class="logout" href="${constants.URL_LOGOUT_COMMAND}"><fmt:message key="user.logout" bundle="${lang}"/></a>
     </form>
</div><!--end user-info-->
		
