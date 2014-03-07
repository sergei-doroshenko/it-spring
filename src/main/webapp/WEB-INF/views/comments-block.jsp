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
		<c:if test="${user.role.name eq constants.ROLE_ADMIN}">
				<a href="${constants.URL_COMMENT_DEL_COMMAND}${constants.ROOT_PATH}${issue.id}${constants.ROOT_PATH}${comment.id}" class="download-link">-del- </a>
			</c:if>
		<div class="comment-create-date">${comment.createDate}</div>
		<div class="comment-create-by">
			<c:out value="${comment.createBy.firstName} ${comment.createBy.lastName}"/>
		</div>
	</div>
</c:forEach>
