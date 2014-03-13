<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!-- i18n -->
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope[constants.KEY_LOCALE]}"/>
<fmt:setBundle basename="i18n.main" var="lang"/>
<!-- End of i18n --> 

<ul class="menu-obj">
	<li class="menu-obj-item"><a href="${constants.URL_MAIN}"><fmt:message key="main" bundle="${lang}"/></a></li>
	<security:authorize access="isAuthenticated()">
		<li class="menu-obj-item"><a href="${constants.COMMAND_SUBMIT_ISSUE}"><fmt:message key="submitissue" bundle="${lang}"/></a></li>
	</security:authorize>
	<security:authorize access="hasRole('ADMINISTRATOR')">
    	<li class="menu-obj-item"><a href="/issuetracker/views/users"><fmt:message key="admin.users" bundle="${lang}"/></a></li>
		<li class="menu-obj-item"><a href="/issuetracker/views/properties"><fmt:message key="admin.properties" bundle="${lang}"/></a></li>
    </security:authorize>
</ul>