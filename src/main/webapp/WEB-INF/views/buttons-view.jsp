<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="buttons-block">
    <div class="buttons-container">
    	<security:authorize access="isAuthenticated()">
	    	<button id="new-button" class="control-button"><spring:message code="button.new"/></button>
			<button id="edit-button" class="control-button"><spring:message code="button.edit"/></button>
			<button id="cancel-button" class="control-button" onclick="window.history.back()"><spring:message code="button.cancel"/></button>
	    </security:authorize>
	    <security:authorize access="hasRole('ADMINISTRATOR')">
	    	<button id="delete-button" class="control-button"><spring:message code="button.delete"/></button>
	    </security:authorize>
    </div>
</div>
<script type="text/javascript">
      $( document ).ready(function () {
           $('#new-button').bind().on('click', function(){
          	  window.location.href = '/issuetracker/issue/new';
           });
           $('#edit-button').bind().on('click', function(){
         	  window.location.href = '/issuetracker/issue/edit?id='+ getParameterByName('id');
           });
           $('#delete-button').bind().on('click', function(){
         	  executeDeleteIssue();
           });
           $('.description').attr('readonly','readonly');
      });
</script>