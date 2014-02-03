<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- i18n -->
<c:set var="lang" value="${sessionScope[constants.KEY_LOCALE].language}"/>
<fmt:requestEncoding value="UTF-8" />
<c:choose>
	<c:when test="${!empty lang}">
		<fmt:setLocale value="${lang}"/>
	</c:when>
	<c:otherwise>
		<fmt:setLocale value="constants.DEFAULT_LANGUAGE"/>
	</c:otherwise>
</c:choose>
<fmt:setBundle basename="i18n.main" var="lang"/>
<!-- End of i18n --> 
<div class="buttons-block">
    <div class="buttons-container">
    	<c:choose>
			<c:when test="${(user.role.name eq constants.ROLE_USER) or (user.role.name eq constants.ROLE_ADMIN)}">
				<button id="edit-button" class="control-button"><fmt:message key="button.edit" bundle="${lang}"/></button>
				<c:if test="${user.role.name eq constants.ROLE_ADMIN}">
					<button id="new-button" class="control-button"><fmt:message key="button.new" bundle="${lang}"/></button>
					<button id="delete-button" class="control-button"><fmt:message key="button.delete" bundle="${lang}"/></button>
				</c:if>
			</c:when>
		</c:choose>
    </div>
</div>