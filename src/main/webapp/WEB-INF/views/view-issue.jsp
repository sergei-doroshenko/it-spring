<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
     <head>
        <meta charset="UTF-8">
        <title>Issue view page</title>
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
             	<jsp:include page="${constants.URL_BUTTONS_VIEW}"/>
	        	<c:set var="issue" scope="page" value="${requestScope[constants.ISSUE]}"/>
				 <div class="issue-container">
				 		<div id="error-issue"></div>
				 		<input id="delete-command" name="command" type="hidden" value="${constants.COMMAND_DELETE_ISSUE}"/>
				 		<input id="id" name="id" type="hidden" value="${issue.id}"/>
				       <div class="obj-fields">
				               <table>
				                   <tr>
				                       <td class="detail-col-name">
				                       		<spring:message code="page.id"/>
				                       </td>
				                       <td id="id" class="detail-col-value">
				                       		<c:out value="${issue.id}"/>
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
				                           <c:out value="${issue.modifyDate}"/>
				                       </td>
				                       <td class="detail-col-name"><spring:message code="page.issue.modifyby"/></td>
				                       <td class="detail-col-value">
				                           <c:out value="${issue.modifyBy.firstName} ${issue.modifyBy.lastName}"/>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><spring:message code="page.issue.type"/></td>
				                       <td class="detail-col-value">
				                       		<c:out value="${issue.type.name}"/>
				                       </td>
				                       <td class="detail-col-name"><spring:message code="page.issue.priority"/></td>
				                       <td class="detail-col-value">
				                           <c:out value="${issue.priority.name}"/>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><spring:message code="page.issue.status"/></td>
				                       <td class="detail-col-value">
				                           <c:out value="${issue.status.name}"/>
				                       </td>
				                       <td class="detail-col-name"><spring:message code="page.issue.resolution"/></td>
				                       <td class="detail-col-value">
				                           <c:out value="${issue.resolution.name}"/>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><spring:message code="page.issue.project"/></td>
				                       <td class="detail-col-value">
				                           <c:out value="${issue.project.name}"/>
				                       </td>
				                       <td class="detail-col-name"><spring:message code="page.issue.build"/></td>
				                       <td class="detail-col-value">
				                           <c:out value="${issue.build.name}"/>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><spring:message code="page.issue.assignee"/></td>
				                       <td class="detail-col-value" colspan="3">
				                           <c:out value="${issue.assignee.firstName} ${issue.assignee.lastName}"/>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><spring:message code="page.issue.summary"/></td>
				                           <td class="detail-col-value" colspan="3">
				                               <c:out value="${issue.summary}"/>
				                       </td>
				                   </tr>
				                    <tr>
				                       <td class="detail-col-name"><spring:message code="page.issue.description"/></td>
				                       <td class="detail-col-value" colspan="3">
				                           <textarea id="description" class="description">${issue.description}</textarea>
				                       </td>
				                   </tr>
				               </table>
				               <div id="attachments-container" class="attachments-container">                         
				                  <div class="field-label"><spring:message code="page.issue.attachments"/></div>
				                  <div id="attachments-list" class="attachments-list">
					                  <jsp:include page="${constants.URL_ATTACHMENTS_BLOCK}"/>
				                  </div>
				              </div><!-- attachments-container end -->
				       </div>
				       <div class="com-att">
				           <div id="comments-container" class="comments-container">
				               <div class="field-label" ><spring:message code="page.issue.comments"/></div>
				                  <div id="comments-list" class="comments-list">
				                  		<jsp:include page="${constants.URL_COMMENTS_BLOCK}"/>
				                  </div>
				           </div><!-- comment end -->
				       </div>
				 </div><!-- isuue-container end -->
             </div><!--end content-->
             <div class="footer">
                 <jsp:include page="${constants.URL_FOOTER}"></jsp:include>
             </div><!--end footer-->
        </div><!--end page-wrapper-->
     </body>
</html>