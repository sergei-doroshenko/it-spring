<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>      
<!-- i18n -->
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope[constants.KEY_LOCALE]}"/>
<fmt:setBundle basename="i18n.main" var="lang"/>
<!-- End of i18n --> 
<!DOCTYPE html>
<html>
     <head>
        <meta charset="UTF-8">
        <title>Issue submit page</title>
        <link rel="stylesheet" type="text/css" media="screen" href="css/ui-lightness/jquery-ui.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/ui-lightness/jquery.ui.theme.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/ui-lightness/jquery-ui-1.10.4.custom.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="css/ui.jqgrid.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="css/default.css" />        
		
		<script type="text/javascript" src="js/jquery-1.9.0.min.js"> </script>		
		<script type="text/javascript" src="js/jquery-ui-1.10.4.custom.min.js"> </script>
		<script type="text/javascript" src="js/i18n/grid.locale-en.js"> </script>
		<script type="text/javascript" src="js/jquery.jqGrid.min.js"> </script>
		<script type="text/javascript" src="js/jquery.cookie.js"> </script>
        <script type="text/javascript" src="js/jquery.form.js"> </script>
        <script type="text/javascript" src="js/user-dialog.js"> </script>	
		<script type="text/javascript" src="js/issue-tracker-main.js"> </script>
		<script type="text/javascript" src="js/login.js"> </script>
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
				       			<input id="insert-command" name="command" type="hidden" value="${constants.COMMAND_INSERT_ISSUE}"/>	
				       			<table>
				                   <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.createdate" bundle="${lang}"/></td>
				                       <td class="detail-col-value">                           
				                           <span id="createdate"></span>
				                       </td>
				                       <td class="detail-col-name"><fmt:message key="page.issue.createby" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <c:out  value="${user.firstName} ${user.lastName}"/>
				                       </td>
				                   </tr>				                   
				                   <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.type" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <select id="type" name="type" class="detail-col-select" size="1" required>
				                           		<option></option>
				                               <c:forEach var="type" items="${requestScope[constants.TYPES]}">
				                           			<option value="${type.id}">${type.name}</option>
				                           		</c:forEach>
				                           </select>
				                       </td>
				                       <td class="detail-col-name"><fmt:message key="page.issue.priority" bundle="${lang}"/></td>
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
				                       <td class="detail-col-name"><fmt:message key="page.issue.status" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                       		<span><c:out  value="${constants.STATUS_NEW}"/></span>
				                       </td>
				                       <td class="detail-col-name"><fmt:message key="page.issue.resolution" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                          <span><fmt:message key="page.issue.unresolved" bundle="${lang}"/></span>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.project" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                       		<input id="get-builds-command" name="command" type="hidden" value="${constants.COMMAND_GET_PROJECT_BUILDS}"/>
				                           <select id="project" name="project" class="detail-col-select" size="1" required>
				                           		<option></option>
				                               <c:forEach var="project" items="${requestScope[constants.PROJECTS]}">
				                           			<option value="${project.id}">${project.name}</option>
				                           		</c:forEach>
				                           </select>
				                       </td>
				                       <td class="detail-col-name"><fmt:message key="page.issue.build" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <select id="projectbuild" name="projectbuild" class="detail-col-select" size="1">
				                               <c:forEach var="build" items="${requestScope[constants.BUILDS]}">
				                           			<option value="${build.id}">${build.name}</option>
				                           		</c:forEach>
				                           </select>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.assignee" bundle="${lang}"/></td>
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
				                       <td class="detail-col-name"><fmt:message key="page.issue.summary" bundle="${lang}"/></td>
				                           <td class="detail-col-value" colspan="3">
				                               <input id="summary" name="summary" class="detail-col-input" type="text" value="${issue.summary}"></input>
				                       </td>
				                   </tr>
				                    <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.description" bundle="${lang}"/></td>
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
             $( document ).ready(function () {
                  builUserForm();
  				  buildUserView ();
                  bindNewIssueForm();
                  $('#save-button').bind().on('click', function(){
                	  $('#new-issue-form').submit();
                  }); 
                  $('#en-loc').click(function(ev) {
              		changeLocaleUrl (ev);
              	  });
	              $('#ru-loc').click(function(ev) {
	              		changeLocaleUrl (ev);
	              });
	              $('#view-user').click(function() {
				        $('#dialog-confirm').dialog('open');
				  });
	              $('#createdate').text(getCurrentDate ());	              
	              $('#project').change(function() {
	            	  var id = $('#project').val();
	            	  getProjectBuilds (id);
	              });
	            		  
             });
        </script>
     </body>
</html>