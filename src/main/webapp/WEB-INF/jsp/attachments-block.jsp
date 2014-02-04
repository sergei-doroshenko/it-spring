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
<c:forEach var="attch" items="${sessionScope[constants.ATTACHMENTS]}">
	<div class="attachment">
		<a href="${sessionScope[constants.URL_DOWNLOAD_COMMAND]}${attch.url}" class="download-link">
			${attch.url}
		</a>
	</div>
</c:forEach>