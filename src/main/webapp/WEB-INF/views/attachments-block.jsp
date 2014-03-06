<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- i18n -->
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope[constants.KEY_LOCALE]}"/>
<fmt:setBundle basename="i18n.main" var="lang"/>
<!-- End of i18n -->
<c:forEach var="attch" items="${requestScope[constants.ATTACHMENTS]}">
	<div class="attachment">
		<c:out value="${constants.URL_DOWNLOAD_COMMAND}${constants.ROOT_PATH}${issue.id}${constants.ROOT_PATH}${attch.fileName}"/>
		<a href="${constants.URL_DOWNLOAD_COMMAND}${constants.ROOT_PATH}${issue.id}${constants.ROOT_PATH}${attch.fileName}" class="download-link">
			${attch.fileName}
		</a>
	</div>
</c:forEach>