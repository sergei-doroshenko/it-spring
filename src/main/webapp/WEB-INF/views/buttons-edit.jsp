<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="buttons-block">
    <div class="buttons-container">
    	<security:authorize access="isAuthenticated()">
	    	<button id="save-button" class="control-button" type="submit" form="edit-issue-form"><spring:message code="button.save"/></button>
			<button id="cancel-button" class="control-button" onclick="window.history.back()"><spring:message code="button.cancel"/></button>
	    </security:authorize>
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