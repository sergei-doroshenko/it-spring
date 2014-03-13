<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.training.issuetracker.domain.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- i18n -->
<spring:message code="issue.err.null"/>
<c:forEach var="cookieVal" items="${cookie}">
	<c:if test="${cookieVal.key eq  'org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE'}">
		<strong>Locale:</strong> <c:out value="${cookieVal.value.value}" />
	</c:if>
</c:forEach>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope[constants.KEY_LOCALE]}"/>
<fmt:setBundle basename="i18n.main" var="lang"/>
<!-- End of i18n -->
<div class="logo">
	<h2>Issue Tracker</h2>
</div><!--end logo-->
<div id="lang">
   	<span id="en-loc" class="button-locale">en</span>
	<span id="ru-loc" class="button-locale">ru</span>
</div>
<div id="dialog-form" class="user-dialog hidden-block" title="User Info">
	<p class="validateTips">All form fields are required.</p>
	<form>
		<fieldset>
			<label for="first_name">First Name</label>
			<input type="text" name="first_name" id="first_name" class="text ui-widget-content ui-corner-all">
			<label for="last_name">Last Name</label>
			<input type="text" name="last_name" id="last_name" class="text ui-widget-content ui-corner-all">
			<label for="email">Email</label>
			<input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all">
			<label for="password">Password</label>
			<input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all">
			<label for="pass-conf">Password confirmation</label>
			<input type="password" id="pass-conf" value="" class="text ui-widget-content ui-corner-all">
		</fieldset>
	</form><!-- End of user form -->
</div><!-- End of user dialog form -->
<div id="user-info" class="user-info">
	<div id="error"><c:out value="${usermessage}"/></div>
	<div class="username"> 
	    <security:authorize access="isAuthenticated()">
	    	Welcome, 
	        <strong><security:authentication property="principal.username"/></strong>
	        <strong><security:authentication property="authorities"/></strong>
	    </security:authorize>
	</div>
     <form id="auth-form" method='POST' action='j_spring_security_check'>
     	<input id="login-command" name="command" type="hidden" value="${constants.COMMAND_LOGIN}"/>
     	<c:choose>
     		<c:when test="${not empty user}">
     			<span id="view-user" class="logout"><c:out value="${user.firstName}  ${user.lastName}"/></span>
	      		<a class="logout" href="${constants.URL_LOGOUT_COMMAND}"><fmt:message key="user.logout" bundle="${lang}"/></a>
	      		<input id="oper" name="oper" type="hidden" value="${constants.OPER_EDIT}"/>
	      		<input id="oper-del" name="oper-del" type="hidden" value="${constants.OPER_DELETE}"/>
	      		<input id="send-command" name="command" type="hidden" value="${constants.COMMAND_EDIT_USER}"/>
	      		<input id="view-user-command" name="view-user-command" type="hidden" value="${constants.COMMAND_VIEW_USER}"/>
	      		<input id="user-id" name="user-id" type="hidden" value="${user.id}"/>
	      		<div id="dialog-confirm" title="User Info" class="hidden-block">
					<p><span class="ui-icon ui-icon-notice" style="float:left; margin:0 7px 20px 0;"></span>Hello</p>
					<p>
						<span class="user-view-label">Id </span>
						<span id="user-id-view"><c:out value="${user.id}"/></span>
					</p>
					<p>
						<span class="user-view-label">First Name </span>
						<span id="user-first-name"><c:out value="${user.firstName}"/></span>
					</p>
					<p>
						<span class="user-view-label">Last Name </span>
						<span id="user-last-name"><c:out value="${user.lastName}"/></span>
					</p>
					<p>
						<span class="user-view-label">E-mail </span>
						<span id="user-email"><c:out value="${user.email}"/></span>
					</p>
				</div><!-- End of user dialog -->
     		</c:when>
     		<c:otherwise>
     			<label><fmt:message key="user.name" bundle="${lang}"/></label>
	        	<input name='j_username' id="login" class="login" type="text" value="<fmt:message key="user.entername" bundle="${lang}"/>"/>
	        	<label><fmt:message key="user.password" bundle="${lang}"/></label>
	        	<input name='j_password' id="password" class="password" type="password">
	        	<input id="remember_me" name="_spring_security_remember_me" type="checkbox"/>
	        	<label for="remember_me" class="inline"><fmt:message key="user.remember" bundle="${lang}"/></label>
	        	<input id="authsubmit" class="authsubmit" type="submit" value="<fmt:message key="user.login" bundle="${lang}"/>" />
	        	<span id="add-user" class="logout"><fmt:message key="user.signin" bundle="${lang}"/></span>
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
