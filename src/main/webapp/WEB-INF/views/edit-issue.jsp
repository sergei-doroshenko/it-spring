<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
     <head>
        <meta charset="UTF-8">
        <title>Issue edit page</title>
        <link rel="icon" href="<c:url value="/static/img/bug.ico"/>" type="image/x-icon">
        <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/static/css/ui-lightness/jquery-ui.css"/>" />
        <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/static/css/ui-lightness/jquery.ui.theme.css"/>" />
        <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/static/css/ui-lightness/jquery-ui-1.10.4.custom.css"/>" />
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/static/css/default.css"/>" />        
		
		<script type="text/javascript" src="<c:url value="/static/js/jquery-1.9.0.min.js"/>"> </script>		
		<script type="text/javascript" src="<c:url value="/static/js/jquery-ui-1.10.4.custom.min.js"/>"> </script>
		<script type="text/javascript" src="<c:url value="/static/js/jquery.cookie.js"/>"> </script>
        <script type="text/javascript" src="<c:url value="/static/js/jquery.form.js"/>"> </script>
		<script type="text/javascript" src="<c:url value="/static/js/issuetracker-main.js"/>"> </script>
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
				       		<form id="edit-issue-form">
				       			<input id="save-command" name="command" type="hidden" value="${constants.COMMAND_UPDATE_ISSUE}"/>	
				       			<table>
				                   <tr>
				                       <td class="detail-col-name">
				                       		<spring:message code="page.id"/>
				                       </td>
				                       <td id="issue-id" class="detail-col-value">
				                       		<c:out value="${issue.id}"/>
				                       		<input id="id" name="id" type="hidden" value="${issue.id}"/>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><spring:message code="page.issue.createdate"/></td>
				                       <td class="detail-col-value">                           
				                           <c:out value="${issue.createDate}"/>
				                       </td>
				                       <td class="detail-col-name"><spring:message code="page.issue.createby"/></td>
				                       <td class="detail-col-value">
				                           <c:out  value="${issue.createBy.firstName} ${issue.createBy.lastName}"/>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><spring:message code="page.issue.modifydate"/></td>
				                       <td class="detail-col-value">
				                       		<span id="modifydate"></span>
				                           <!-- input id="modifydate" class="detail-col-input" type="text" value="${issue.modifyDate}"></input-->
				                       </td>
				                       <td class="detail-col-name"><spring:message code="page.issue.modifyby"/></td>
				                       <td class="detail-col-value">
				                       		<!-- input id="modifyby" name="modifyby" type="hidden" value="${user.id}"/-->
				                           <c:out  value="${user.firstName} ${user.lastName}"/>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><spring:message code="page.issue.type"/></td>
				                       <td class="detail-col-value">
				                           <select id="type" name="type" class="detail-col-select" size="1" required>
				                               <c:forEach var="type" items="${requestScope[constants.TYPES]}">
				                           			<option value="${type.id}" <c:if test="${type.id eq issue.type.id}">selected</c:if>>${type.name}</option>
				                           		</c:forEach>
				                           </select>
				                       </td>
				                       <td class="detail-col-name"><spring:message code="page.issue.priority"/></td>
				                       <td class="detail-col-value">
				                           <select id="priority" name="priority" class="detail-col-select" size="1">
				                               <c:forEach var="priority" items="${requestScope[constants.PRIORITIES]}">
				                           			<option value="${priority.id}" <c:if test="${priority.id eq issue.priority.id}">selected</c:if>>${priority.name}</option>
				                           		</c:forEach>
				                           </select>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><spring:message code="page.issue.status"/></td>
				                       <td class="detail-col-value">
				                           <select id="status" name="status" class="detail-col-select" size="1">
				                           	<c:forEach var="status" items="${requestScope[constants.STATUSES]}">
				                           		<c:choose>
				                           			<c:when test="${issue.status.name eq constants.STATUS_NEW}">
				                           				<c:if test="${(status.name eq constants.STATUS_NEW) || (status.name eq constants.STATUS_ASSIGNED)}">
				                           					<option value="${status.id}" <c:if test="${status.id eq issue.status.id}">selected</c:if>>${status.name}</option>	
				                           				</c:if>
				                           			</c:when>
				                           			<c:when test="${issue.status.name eq constants.STATUS_ASSIGNED}">
				                           				<c:if test="${(status.name eq constants.STATUS_ASSIGNED) || (status.name eq constants.STATUS_IN_PROGRESS)}">
				                           					<option value="${status.id}" <c:if test="${status.id eq issue.status.id}">selected</c:if>>${status.name}</option>	
				                           				</c:if>
				                           			</c:when>
				                           			<c:when test="${issue.status.name eq constants.STATUS_IN_PROGRESS}">
				                           				<c:if test="${(status.name ne constants.STATUS_IN_PROGRESS) || (status.name ne constants.STATUS_RESOLVED)}">
				                           					<option value="${status.id}" <c:if test="${status.id eq issue.status.id}">selected</c:if>>${status.name}</option>	
				                           				</c:if>
				                           			</c:when>
				                           			<c:when test="${issue.status.name eq constants.STATUS_RESOLVED}">
				                           				<c:if test="${(status.name ne constants.STATUS_RESOLVED) || (status.name ne constants.STATUS_CLOSED)}">
				                           					<option value="${status.id}" <c:if test="${status.id eq issue.status.id}">selected</c:if>>${status.name}</option>	
				                           				</c:if>
				                           			</c:when>
				                           			<c:when test="${issue.status.name eq constants.STATUS_CLOSED}">
				                           				<c:if test="${(status.name ne constants.STATUS_CLOSED) || (status.name ne constants.STATUS_REOPENED)}">
				                           					<option value="${status.id}" <c:if test="${status.id eq issue.status.id}">selected</c:if>>${status.name}</option>	
				                           				</c:if>
				                           			</c:when>
				                           			<c:otherwise>
				                           				<c:if test="${(status.name eq constants.STATUS_REOPENED) || (status.name eq constants.STATUS_ASSIGNED)}">
				                           					<option value="${status.id}" <c:if test="${status.id eq issue.status.id}">selected</c:if>>${status.name}</option>	
				                           				</c:if>
				                           			</c:otherwise>
				                           		</c:choose>
				                           	</c:forEach>
				                           </select>
				                       </td>
				                       <td class="detail-col-name"><spring:message code="page.issue.resolution"/></td>
				                       <td class="detail-col-value">
				                           <select id="resolution" name="resolution" class="detail-col-select" size="1">
				   
				                               <c:forEach var="resolution" items="${requestScope[constants.RESOLUTIONS]}">
				                           			<option value="${resolution.id}" <c:if test="${resolution.id eq issue.resolution.id}">selected</c:if>>${resolution.name}</option>
				                           		</c:forEach>
				                           </select>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><spring:message code="page.issue.project"/></td>
				                       <td class="detail-col-value">
				                       		<input id="get-builds-command" name="command" type="hidden" value="${constants.COMMAND_GET_PROJECT_BUILDS}"/>
				                           <select id="project" name="project" class="detail-col-select" size="1">
				                               <c:forEach var="project" items="${requestScope[constants.PROJECTS]}">
				                           			<option value="${project.id}" <c:if test="${project.id eq issue.project.id}">selected</c:if>>${project.name}</option>
				                           		</c:forEach>
				                           </select>
				                       </td>
				                       <td class="detail-col-name"><spring:message code="page.issue.build"/></td>
				                       <td class="detail-col-value">
				                           <select id="projectbuild" name="projectbuild" class="detail-col-select" size="1">
				                               <c:forEach var="build" items="${requestScope[constants.BUILDS]}">
				                           			<option value="${build.id}" <c:if test="${build.id eq issue.build.id}">selected</c:if>>${build.name}</option>
				                           		</c:forEach>
				                           </select>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><spring:message code="page.issue.assignee"/></td>
				                       <td class="detail-col-value" colspan="3">
				                           <select id="assignee" name="assignee" class="detail-col-select" size="1">
				                           		<c:if test="${empty issue.assignee}">
				                           			<option></option>
				                           		</c:if>
				                               <c:forEach var="assignee" items="${requestScope[constants.ASSIGNEES]}">
				                           			<option value="${assignee.id}" <c:if test="${assignee.id eq issue.assignee.id}">selected</c:if>>${assignee.firstName} ${assignee.lastName}</option>
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
				               <div id="attachments-container" class="attachments-container">                         
				                  <div class="field-label"><spring:message code="page.issue.attachments"/></div>
				                  <c:if test="${!empty issue}">
					                  <div class="upload-block">
					                  		<p>${uploadmessage}</p>
					                  		<form id="file-upload-form" action="../attachment/${issue.id}" 
					                  								method="post" enctype="multipart/form-data">
												Select File:<input id="button-attachment" class="button-attachment" type="file" name="file"/>
												<br>
												<input id="button-upload" type="submit" value="<spring:message code="button.upload"/>">
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
				               <div class="field-label" ><spring:message code="page.issue.comments"/></div>
				               <c:if test="${!empty issue}">
					               <div id="comments-list" class="comments-list">
					               		<jsp:include page="${constants.URL_COMMENTS_BLOCK}"/>
					               </div>
					               <textarea id="comment" class="new-comment"></textarea>
					               <button id="add-comment"><spring:message code="button.addcomment"/></button>
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
	        //$('#add-comment').bind().on('click', sendComment);
        	$('#add-comment').click(sendComment);
        </script>
     </body>
</html>