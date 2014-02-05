<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- i18n -->
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope[constants.KEY_LOCALE]}"/>
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