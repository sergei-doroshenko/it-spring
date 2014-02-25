<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- i18n -->
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope[constants.KEY_LOCALE]}"/>
<fmt:setBundle basename="i18n.main" var="lang"/>
<!-- End of i18n --> 

<ul class="menu-obj">
	<li class="menu-obj-item"><a href="${constants.URL_MAIN}"><fmt:message key="main" bundle="${lang}"/></a></li>
	<c:choose>
		<c:when test="${(user.role.name eq constants.ROLE_USER) or (user.role.name eq constants.ROLE_ADMIN)}">
			<li class="menu-obj-item"><a href="${constants.URL_MAIN_COMMAND}${constants.COMMAND_SUBMIT_ISSUE}"><fmt:message key="submitissue" bundle="${lang}"/></a></li>
			<c:if test="${user.role.name eq constants.ROLE_ADMIN}">
				<li class="menu-obj-item"><a href="view-user.jsp"><fmt:message key="admin.users" bundle="${lang}"/></a></li>
				<li class="menu-obj-item"><a href="properties.jsp"><fmt:message key="admin.properties" bundle="${lang}"/></a></li>
			</c:if>
		</c:when>
	</c:choose>
</ul>