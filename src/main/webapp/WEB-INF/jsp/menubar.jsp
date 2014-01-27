<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- i18n -->
<fmt:requestEncoding value="UTF-8" />
<c:choose>
	<c:when test="${!empty param.lang}">
		<c:set var="language" value="${param.lang}" scope="session"/>
		<fmt:setLocale value="${param.lang}"/>
	</c:when>
	<c:otherwise>
		<c:if test="${!empty sessionScope.language}">
			<fmt:setLocale value="${sessionScope.language}"/>
		</c:if>
	</c:otherwise>
</c:choose>
		
<fmt:setBundle basename="i18n.main" var="lang"/>
<!-- End of i18n --> 

<ul class="menu-obj">
	<li class="menu-obj-item"><a href="search.jsp"><fmt:message key="search" bundle="${lang}"/></a></li>
</ul>