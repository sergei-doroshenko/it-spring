<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
     <head>
        <meta charset="UTF-8">
        <title>Issue submit page</title>
        <link rel="stylesheet" type="text/css" media="screen" href="/issuetracker/css/ui-lightness/jquery-ui.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="/issuetracker/css/ui-lightness/jquery.ui.theme.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="/issuetracker/css/ui-lightness/jquery-ui-1.10.4.custom.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="/issuetracker/css/default.css" />        
		
		<script type="text/javascript" src="/issuetracker/js/jquery-1.9.0.min.js"> </script>		
		<script type="text/javascript" src="/issuetracker/js/jquery-ui-1.10.4.custom.min.js"> </script>
		<script type="text/javascript" src="/issuetracker/js/jquery.cookie.js"> </script>
        <script type="text/javascript" src="/issuetracker/js/jquery.form.js"> </script>
		<script type="text/javascript" src="/issuetracker/js/issuetracker-main.js"> </script>
     </head>
     <body>
        <div class="page-wrapper">
             <div class="header">
                <jsp:include page="${constants.URL_HEADER}"/>
             </div><!--end header-->
             <div id="menu-bar" class="menu-bar">
                <jsp:include page="${constants.URL_MENU_TOP}"/>
             </div><!-- end menu-bar -->
             <div class="content">
             	<jsp:include page="${constants.URL_BUTTONS_EDIT}"/>
	        	<c:set var="issue" scope="page" value="${requestScope[constants.ISSUE]}"/>
				 <div class="issue-container">
				 		<div id="error-issue"></div>
				       <div class="obj-fields">
				       		<form id="new-issue-form" >
				       			<input id="save-command" name="command" type="hidden" value="${constants.COMMAND_INSERT_ISSUE}"/><table>
				                   <tr>
				                       <td class="detail-col-name"><spring:message code="page.issue.createdate"/></td>
				                       <td class="detail-col-value">                           
				                           <span id="createdate"></span>
				                       </td>
				                       <td class="detail-col-name"><spring:message code="page.issue.createby"/></td>
				                       <td class="detail-col-value">
				                           <c:out  value="${user.firstName} ${user.lastName}"/>
				                       </td>
				                   </tr>				                   
				                   <tr>
				                       <td class="detail-col-name"><spring:message code="page.issue.type"/></td>
				                       <td class="detail-col-value">
				                           <select id="type" name="type" class="detail-col-select" size="1" required>
				                           		<option></option>
				                               <c:forEach var="type" items="${requestScope[constants.TYPES]}">
				                           			<option value="${type.id}">${type.name}</option>
				                           		</c:forEach>
				                           </select>
				                       </td>
				                       <td class="detail-col-name"><spring:message code="page.issue.priority"/></td>
				                       <td class="detail-col-value">
				                           <select id="priority" name="priority" class="detail-col-select" size="1" required>
				                           		<option></option>
				                               <c:forEach var="priority" items="${requestScope[constants.PRIORITIES]}">
				                           			<option value="${priority.id}">${priority.name}</option>
				                           		</c:forEach>
				                           </select>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><spring:message code="page.issue.status"/></td>
				                       <td class="detail-col-value">
				                       		<select id="status" name="status" class="detail-col-select" size="1" disabled="disabled">
					                           	<c:forEach var="status" items="${requestScope[constants.STATUSES]}">
					                           		<option value="${status.id}" <c:if test="${status.name eq constants.STATUS_NEW}">selected</c:if>>${status.name}</option>
					                           	</c:forEach>
				                           </select>
				                       </td>
				                       <td class="detail-col-name"><spring:message code="page.issue.resolution"/></td>
				                       <td class="detail-col-value">
				                          <span><spring:message code="page.issue.unresolved"/></span>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><spring:message code="page.issue.project"/></td>
				                       <td class="detail-col-value">
				                       		<input id="get-builds-command" name="command" type="hidden" value="${constants.COMMAND_GET_PROJECT_BUILDS}"/>
				                           <select id="project" name="project" class="detail-col-select" size="1" required>
				                           		<option></option>
				                               <c:forEach var="project" items="${requestScope[constants.PROJECTS]}">
				                           			<option value="${project.id}">${project.name}</option>
				                           		</c:forEach>
				                           </select>
				                       </td>
				                       <td class="detail-col-name"><spring:message code="page.issue.build"/></td>
				                       <td class="detail-col-value">
				                           <select id="projectbuild" name="projectbuild" class="detail-col-select" size="1">
				                               <c:forEach var="build" items="${requestScope[constants.BUILDS]}">
				                           			<option value="${build.id}">${build.name}</option>
				                           		</c:forEach>
				                           </select>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><spring:message code="page.issue.assignee"/></td>
				                       <td class="detail-col-value" colspan="3">
				                           <select id="assignee" name="assignee" class="detail-col-select" size="1">
				                           		<option></option>
				                               <c:forEach var="assignee" items="${requestScope[constants.ASSIGNEES]}">
				                           			<option value="${assignee.id}">${assignee.firstName} ${assignee.lastName}</option>
				                           		</c:forEach>
				                           </select>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><spring:message code="page.issue.summary"/></td>
				                           <td class="detail-col-value" colspan="3">
				                               <input id="summary" name="summary" class="detail-col-input" type="text" value="${issue.summary}"></input>
				                       </td>
				                   </tr>
				                    <tr>
				                       <td class="detail-col-name"><spring:message code="page.issue.description"/></td>
				                       <td class="detail-col-value" colspan="3">
				                           <textarea id="description" name="description" class="description">${issue.description}</textarea>
				                       </td>
				                   </tr>
				               </table>
				           </form>
				       </div>
				 </div><!-- isuue-container end -->
             </div><!--end content-->
             <div class="footer">
                 <jsp:include page="${constants.URL_FOOTER}"></jsp:include>
             </div><!--end footer-->
        </div><!--end page-wrapper-->
        <script type="text/javascript">
        	$('#createdate').text(getCurrentDate ());
        </script>
     </body>
</html>