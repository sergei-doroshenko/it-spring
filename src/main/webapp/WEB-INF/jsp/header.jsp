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
<fmt:setBundle basename="i18n.main" var="lang"/>
<!-- End of i18n -->
<div id="lang">
   	<a href="Main.do?command=localize&lang=en_EN">EN</a>
	<a href="Main.do?command=localize&lang=ru_RU">RU</a>
</div>
<c:out value="${enlink} ${rulink}"/>
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
		
