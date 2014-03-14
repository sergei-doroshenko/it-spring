<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="attch" items="${requestScope[constants.ATTACHMENTS]}">
	<div class="attachment">
		<c:if test="${user.role.name eq constants.ROLE_ADMIN}">
			<a href="${constants.URL_ATTCH_DEL_COMMAND}${constants.ROOT_PATH}${attch.id}" class="download-link">del-> </a>
		</c:if>
		<a href="${constants.URL_DOWNLOAD_COMMAND}${constants.ROOT_PATH}${attch.id}" class="download-link">
			${attch.fileName}
		</a>
	</div>
</c:forEach>