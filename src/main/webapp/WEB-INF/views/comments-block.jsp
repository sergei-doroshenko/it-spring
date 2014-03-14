<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:forEach var="comment" items="${requestScope[constants.COMMENTS]}">
	<div class="comment-block">
		<div class="comment-value">${comment.comment}</div>
		<security:authorize access="hasRole('ADMINISTRATOR')">
	    	<a href="${constants.URL_COMMENT_DEL_COMMAND}${constants.ROOT_PATH}${issue.id}${constants.ROOT_PATH}${comment.id}" class="download-link">-del- </a>
	    </security:authorize>
		<div class="comment-create-date">${comment.createDate}</div>
		<div class="comment-create-by">
			<c:out value="${comment.createBy.firstName} ${comment.createBy.lastName}"/>
		</div>
	</div>
</c:forEach>
