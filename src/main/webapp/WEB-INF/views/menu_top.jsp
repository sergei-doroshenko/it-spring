<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<ul class="menu-obj">
	<li class="menu-obj-item"><a href="${constants.URL_MAIN}"><spring:message code="main"/></a></li>
	<security:authorize access="isAuthenticated()">
		<li class="menu-obj-item"><a href="${constants.COMMAND_SUBMIT_ISSUE}"><spring:message code="submitissue"/></a></li>
	</security:authorize>
	<security:authorize access="hasRole('ADMINISTRATOR')">
    	<li class="menu-obj-item"><a href="/issuetracker/views/users"><spring:message code="admin.users"/></a></li>
		<li class="menu-obj-item"><a href="/issuetracker/views/properties"><spring:message code="admin.properties"/></a></li>
    </security:authorize>
</ul>