<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.training.issuetracker.domain.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- i18n -->
<spring:message code="issue.err.null"/>
<!-- End of i18n -->
<div class="logo">
	<h2>Issue Tracker</h2>
</div><!--end logo-->
<div id="lang">
   	<span id="en-loc" class="button-locale">en</span>
	<span id="ru-loc" class="button-locale">ru</span>
</div>
<div id="dialog-form" class="user-dialog hidden-block" title="User Info">
	<p class="validateTips"><spring:message code="user.form.message"/></p>
	<form>
		<fieldset>
			<label for="first_name"><spring:message code="user.form.firstname"/></label>
			<input type="text" name="first_name" id="first_name" class="text ui-widget-content ui-corner-all">
			<label for="last_name"><spring:message code="user.form.lastname"/></label>
			<input type="text" name="last_name" id="last_name" class="text ui-widget-content ui-corner-all">
			<label for="email"><spring:message code="user.form.email"/></label>
			<input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all">
			<label for="password"><spring:message code="user.form.password"/></label>
			<input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all">
			<label for="pass-conf"><spring:message code="user.form.passwordconfirm"/></label>
			<input type="password" id="pass-conf" value="" class="text ui-widget-content ui-corner-all">
		</fieldset>
	</form><!-- End of user form -->
</div><!-- End of user dialog form -->
<div id="user-info" class="user-info">
	<div id="error"><c:out value="${usermessage}"/></div>
	<div class="username"> 
	    <security:authorize access="isAuthenticated()">
	    	<spring:message code="user.message.welcom"/>, 
	        <strong><security:authentication property="principal.username"/></strong>
	        <strong><security:authentication property="authorities"/></strong>
	    </security:authorize>
	</div>
	<!-- form id="auth-form" method='POST' action='j_spring_security_check'-->
     <form id="auth-form" method='POST' action='j_spring_security_check'>
     	<input id="login-command" name="command" type="hidden" value="${constants.COMMAND_LOGIN}"/>
     	<c:choose>
     		<c:when test="${not empty user}">
     			<span id="view-user" class="logout"><c:out value="${user.firstName}  ${user.lastName}"/></span>
	      		<a class="logout" href="${constants.URL_LOGOUT_COMMAND}"><spring:message code="user.logout"/></a>
	      		<input id="oper" name="oper" type="hidden" value="${constants.OPER_EDIT}"/>
	      		<input id="oper-del" name="oper-del" type="hidden" value="${constants.OPER_DELETE}"/>
	      		<input id="send-command" name="command" type="hidden" value="${constants.COMMAND_EDIT_USER}"/>
	      		<input id="view-user-command" name="view-user-command" type="hidden" value="${constants.COMMAND_VIEW_USER}"/>
	      		<input id="user-id" name="user-id" type="hidden" value="${user.id}"/>
	      		<div id="dialog-confirm" title='<spring:message code="user.message.info"/>' class="hidden-block">
					<p><span class="ui-icon ui-icon-notice" style="float:left; margin:0 7px 20px 0;"></span>Hello</p>
					<p>
						<span class="user-view-label"><spring:message code="user.form.id"/></span>
						<span id="user-id-view"><c:out value="${user.id}"/></span>
					</p>
					<p>
						<span class="user-view-label"><spring:message code="user.form.firstname"/></span>
						<span id="user-first-name"><c:out value="${user.firstName}"/></span>
					</p>
					<p>
						<span class="user-view-label"><spring:message code="user.form.lastname"/></span>
						<span id="user-last-name"><c:out value="${user.lastName}"/></span>
					</p>
					<p>
						<span class="user-view-label"><spring:message code="user.form.email"/></span>
						<span id="user-email"><c:out value="${user.email}"/></span>
					</p>
				</div><!-- End of user dialog -->
     		</c:when>
     		<c:otherwise>
     			<label><spring:message code="user.name"/></label>
	        	<input name='j_username' id="login" class="login" type="text" value="<spring:message code="user.entername"/>"/>
	        	<label><spring:message code="user.password"/></label>
	        	<input name='j_password' id="password" class="password" type="password">
	        	<input id="remember_me" name="_spring_security_remember_me" type="checkbox"/>
	        	<label for="remember_me" class="inline"><spring:message code="user.remember"/></label>
	        	<input id="authsubmit" class="authsubmit" type="submit" value="<spring:message code="user.login"/>" />
	        	<!-- input id="authsubmit" class="authsubmit" type="button" value="<spring:message code="user.login"/>" /-->
	        	<span id="add-user" class="logout"><spring:message code="user.signin"/></span>
	        	<input id="oper" name="oper" type="hidden" value="${constants.OPER_ADD}"/>
	        	<input id="send-command" name="command" type="hidden" value="${constants.COMMAND_ADD_USER}"/>
     		</c:otherwise>
     	</c:choose>
     </form>
</div><!--end user-info-->
<script type="text/javascript">
	$( document ).ready(function () {
		builUserForm();
		buildUserView ();       
		$('#en-loc').click(function(ev) {
			changeLocaleUrl (ev);
		});
		$('#ru-loc').click(function(ev) {
			changeLocaleUrl (ev);
		});
		$('#add-user').click(function() {
	        $('#dialog-form').dialog('open');
	    });
		$('#view-user').click(function() {
	        $('#dialog-confirm').dialog('open');
	    });
	});
</script>
