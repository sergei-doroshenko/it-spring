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
<c:forEach var="comment" items="${sessionScope[constants.COMMENTS]}">
	<div class="comment-block">
		<div class="comment-value">${comment.comment}</div>
		<div class="comment-create-date">${comment.createDate}</div>
		<div class="comment-create-by">${comment.createBy.firstName} ${comment.createBy.lastName}</div>
	</div>
</c:forEach>
