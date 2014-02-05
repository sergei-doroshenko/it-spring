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
        <title>Issue edit page</title>
        <link rel="stylesheet" type="text/css" media="screen" href="css/default.css" />
        <script type="text/javascript" src="js/jquery-1.9.0.min.js"> </script>
        <script type="text/javascript" src="js/jquery.cookie.js"> </script>
        <script type="text/javascript" src="js/login.js"> </script>
        <script type="text/javascript" src="js/issue-tracker-main.js"> </script>
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
				       <div class="obj-fields">
				       		<form id="edit-issue-form" >
				       			<input id="save-command" name="command" type="hidden" value="${constants.COMMAND_SAVE_ISSUE}"></input>
				               <table>
				                   <tr>
				                       <td class="detail-col-name">
				                       		<fmt:message key="page.id" bundle="${lang}"/>
				                       </td>
				                       <td id="id" class="detail-col-value">
				                       		<c:out value="${issue.id}"/>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.createdate" bundle="${lang}"/></td>
				                       <td class="detail-col-value">                           
				                           <c:out value="${issue.createDate}"/>
				                       </td>
				                       <td class="detail-col-name"><fmt:message key="page.issue.createby" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <c:out  value="${issue.createBy.firstName} ${issue.createBy.lastName}"/>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.modifydate" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                       		<span id="modifydate"></span>
				                           <!-- input id="modifydate" class="detail-col-input" type="text" value="${issue.modifyDate}"></input-->
				                       </td>
				                       <td class="detail-col-name"><fmt:message key="page.issue.modifyby" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <!-- input id="modifyby" class="detail-col-input" type="text" value="${user.firstName} ${user.lastName}"></input-->
				                           <c:out  value="${user.firstName} ${user.lastName}"/>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.type" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <select id="type" name="type" class="detail-col-select" size="1">
				                               <option value="1" selected="selected">${issue.type.name}</option>
				                               <option value="2">Test 2</option>
				                               <option value="3">Test 3</option>
				                               <option value="4">Test 4</option>
				                           </select>
				                       </td>
				                       <td class="detail-col-name"><fmt:message key="page.issue.priority" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <select id="priority" name="priority" class="detail-col-select" size="1">
				                               <option value="1" selected="selected">${issue.priority.name}</option>
				                               <option value="2">Test 2</option>
				                               <option value="3">Test 3</option>
				                               <option value="4">Test 4</option>
				                           </select>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.status" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <select id="status" name="status" class="detail-col-select" size="1">
				                               <option value="1" selected="selected">${issue.status.name}</option>
				                               <option value="2">Test 2</option>
				                               <option value="3">Test 3</option>
				                               <option value="4">Test 4</option>
				                           </select>
				                       </td>
				                       <td class="detail-col-name"><fmt:message key="page.issue.resolution" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <select id="resolution" name="resolution" class="detail-col-select" size="1">
				                               <option value="1" selected="selected">${issue.resolution.name}</option>
				                               <option value="2">Test 2</option>
				                               <option value="3">Test 3</option>
				                               <option value="4">Test 4</option>
				                           </select>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.project" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <select id="project" name="project" class="detail-col-select" size="1">
				                               <option value="1" selected="selected">Project</option>
				                               <option value="2">Test 2</option>
				                               <option value="3">Test 3</option>
				                               <option value="4">Test 4</option>
				                           </select>
				                       </td>
				                       <td class="detail-col-name"><fmt:message key="page.issue.build" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <select id="projectbuild" name="projectbuild" class="detail-col-select" size="1">
				                               <option value="1" selected="selected">${issue.build.name}</option>
				                               <option value="2">Test 2</option>
				                               <option value="3">Test 3</option>
				                               <option value="4">Test 4</option>
				                           </select>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.assignee" bundle="${lang}"/></td>
				                       <td class="detail-col-value" colspan="3">
				                           <select id="assignee" name="assignee" class="detail-col-select" size="1">
				                               <option value="1" selected="selected">${issue.assignee.firstName} ${issue.assignee.lastName}</option>
				                               <option value="2">Test 2</option>
				                               <option value="3">Test 3</option>
				                               <option value="4">Test 4</option>
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
				               <div id="attachments-container" class="attachments-container">                         
				                  <div class="field-label"><fmt:message key="page.issue.attachments" bundle="${lang}"/></div>
				                  <c:if test="${!empty issue}">
					                  <div class="upload-block">
					                  		<p>${uploadmessage}</p>
					                  		<form id="file-upload-form" action="FileUploadDownload.do?id=${issue.id}" 
					                  								method="post" enctype="multipart/form-data">
												Select File:<input id="button-attachment" class="button-attachment" type="file" name="file"/>
												<br>
												<input id="button-upload" type="submit" value="<fmt:message key="button.upload" bundle="${lang}"/>">
											</form>		          
					                  </div>
				                  </c:if>
				                  <div id="attachments-list" class="attachments-list">
					                  <jsp:include page="${constants.URL_ATTACHMENTS_BLOCK}"/>
				                  </div>
				              </div><!-- attachments-container end -->
				       </div>
				       <div class="com-att">
				           <div id="comments-container" class="comments-container">
				               <div class="field-label" ><fmt:message key="page.issue.comments" bundle="${lang}"/></div>
				               <c:if test="${!empty issue}">
					               <div id="comments-list" class="comments-list">
					               		<jsp:include page="${constants.URL_COMMENTS_BLOCK}"/>
					               </div>
					               <textarea id="comment" class="new-comment"></textarea>
					               <button id="add-comment"><fmt:message key="button.addcomment" bundle="${lang}"/></button>
					           </c:if>   
				           </div><!-- comment end -->
				       </div>
				 </div><!-- isuue-container end -->
             </div><!--end content-->
             <div class="footer">
                 <jsp:include page="${constants.URL_FOOTER}"></jsp:include>
             </div><!--end footer-->
        </div><!--end page-wrapper-->
        <script type="text/javascript">
             $( document ).ready(function () {
                  bindLongin();
                  bindEditIssueForm();
                  $('#save-button').bind().on('click', function(){
                	  alert('Click save!');
                  });
                  $('#cancel-button').bind().on('click', function(){
                	  alert('Click cancel!');
                  });
                  $('.description').attr('readonly','readonly');
                  $('#en-loc').click(function(ev) {
              		changeLocaleUrl (ev);
              		});
	              	$('#ru-loc').click(function(ev) {
	              		changeLocaleUrl (ev);
	              	});
	              $('#modifydate').text(getCurrentDate ());	
             });
        </script>
     </body>
</html>