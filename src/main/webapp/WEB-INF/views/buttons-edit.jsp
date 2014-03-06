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
				<button id="save-button" class="control-button" type="submit" form="edit-issue-form"><fmt:message key="button.save" bundle="${lang}"/></button>
				<button id="cancel-button" class="control-button" onclick="window.history.back()"><fmt:message key="button.cancel" bundle="${lang}"/></button>
			</c:when>
		</c:choose>
    </div>
</div>
<script type="text/javascript">
	$( document ).ready(function () {
		bindNewIssueForm();
		bindEditIssueForm();
		$('#save-button').bind().on('click', function(){
			if ($('#save-command').val() == 'update_issue') {
				$('#edit-issue-form').submit();	
			} else {
				$('#new-issue-form').submit();
			}
		});               
		$('#cancel-button').bind().on('click', function(){
			window.history.back();
		});
		$('#modifydate').text(getCurrentDate ());
		$('#project').change(function() {
			var id = $('#project').val();
			getProjectBuilds (id);
		});
	});
</script>