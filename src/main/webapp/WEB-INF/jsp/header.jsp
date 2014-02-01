<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.training.issuetracker.domain.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="logo">
	<h2>Issue Tracker</h2>
</div><!--end logo-->

<c:set var="backurl" value="${pageContext.request.servletPath}"/>
<!--  i18n -->
<c:set var="lang" value="${sessionScope[constants.KEY_LOCALE].language}"/>
Lang:<c:out value="${lang}"/>
Locale:${sessionScope[constants.KEY_LOCALE]}
<c:choose>
	<c:when test="${!empty lang}">
		<fmt:setLocale value="${lang}"/>
	</c:when>
	<c:otherwise>
		<fmt:setLocale value="constants.DEFAULT_LANGUAGE"/>
	</c:otherwise>
</c:choose>

<!-- i18n -->
<fmt:requestEncoding value="UTF-8" />

<fmt:setLocale value="${pageContext.response.locale}" scope="session"/>
<fmt:setBundle basename="i18n.main" var="lang"/>
<!-- End of i18n -->
<div id="lang">
   	<a href="Main.do?command=localize&lang=en_EN&backurl=${backurl}">EN</a>
	<a href="Main.do?command=localize&lang=ru_RU&backurl=${backurl}">RU</a>
</div>

<div id="user-info" class="user-info">
	<div id="error"></div>
     <form id="auth-form">
     	<c:choose>
     		<c:when test="${empty user}">
     			<label><fmt:message key="user.name" bundle="${lang}"/></label>
	        	<input name="login" id="login" class="login" type="text" value="My name"/>
	        	<label><fmt:message key="user.password" bundle="${lang}"/></label>
	        	<input name="password" id="password" class="password" type="password" />
	        	<input id="authsubmit" class="authsubmit" type="submit" value="<fmt:message key="user.login" bundle="${lang}"/>" />
     		</c:when>
     		<c:otherwise>
     			<a class="logout" href="Main.do?id=${user.id}&command=user"><c:out value="${user.firstName}  ${user.lastName}"/></a>
	      		<a href="Main.do?command=logout" class="logout"><fmt:message key="user.logout" bundle="${lang}"/></a>
     		</c:otherwise>
     	</c:choose>
     </form>
</div><!--end user-info-->
		
