<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- i18n -->
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope[constants.KEY_LOCALE]}"/>
<fmt:setBundle basename="i18n.main" var="lang"/>
<!-- End of i18n -->
<c:forEach var="comment" items="${requestScope[constants.COMMENTS]}">
	<div class="comment-block">
		<div class="comment-value">${comment.comment}</div>
		<div class="comment-create-date">${comment.createDate}</div>
		<div class="comment-create-by">${comment.createBy.firstName} ${comment.createBy.lastName}</div>
	</div>
</c:forEach>
