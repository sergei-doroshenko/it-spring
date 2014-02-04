<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>      
<!DOCTYPE html>
<html>
     <head>
        <meta charset="UTF-8">
        <title>Issue edit page</title>
        <link rel="stylesheet" type="text/css" media="screen" href="css/default.css" />
        <script type="text/javascript" src="js/jquery-1.9.0.min.js"> </script>
        <script type="text/javascript" src="js/jquery.cookie.js"> </script>
        <script type="text/javascript" src="js/login.js"> </script>
     </head>
     <body>
     	<!-- i18n -->
		<c:set var="lang" value="${sessionScope[constants.KEY_LOCALE].language}"/>
		<fmt:requestEncoding value="UTF-8" />
		<c:choose>
			<c:when test="${!empty lang}">
				<fmt:setLocale value="${lang}"/>
			</c:when>
			<c:otherwise>
				<fmt:setLocale value="constants.DEFAULT_LANGUAGE"/>
			</c:otherwise>
		</c:choose>
		<fmt:setBundle basename="i18n.main" var="lang"/>
		<!-- End of i18n --> 
        <div class="page-wrapper">
             <div class="header">
                <jsp:include page="${constants.URL_HEADER}"/>
             </div><!--end header-->
             <div id="menu-bar" class="menu-bar">
                <jsp:include page="${constants.URL_MENU_TOP}"/>
             </div><!-- end menu-bar -->
             <div class="content">
             	<jsp:include page="${constants.URL_BUTTONS_VIEW}"/>
	        	<c:set var="issue" scope="page" value="${sessionScope[constants.ISSUE]}"/>
				 <div class="issue-container">
				       <div class="obj-fields">
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
				                           <c:out value="${issue.modifyDate}"/>
				                       </td>
				                       <td class="detail-col-name"><fmt:message key="page.issue.modifyby" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <c:out value="${issue.modifyBy.firstName} ${issue.modifyBy.lastName}"/>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.type" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                       		<c:out value="${issue.type.name}"/>
				                       </td>
				                       <td class="detail-col-name"><fmt:message key="page.issue.priority" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <c:out value="${issue.priority.name}"/>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.status" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <c:out value="${issue.status.name}"/>
				                       </td>
				                       <td class="detail-col-name"><fmt:message key="page.issue.resolution" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <c:out value="${issue.resolution.name}"/>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.project" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <c:out value="Test Project"/>
				                       </td>
				                       <td class="detail-col-name"><fmt:message key="page.issue.build" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <c:out value="${issue.build.name}"/>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.assignee" bundle="${lang}"/></td>
				                       <td class="detail-col-value" colspan="3">
				                           <c:out value="${issue.assignee.firstName} ${issue.assignee.lastName}"/>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.summary" bundle="${lang}"/></td>
				                           <td class="detail-col-value" colspan="3">
				                               <c:out value="${issue.summary}"/>
				                       </td>
				                   </tr>
				                    <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.description" bundle="${lang}"/></td>
				                       <td class="detail-col-value" colspan="3">
				                           <textarea id="description" class="description">${issue.description}</textarea>
				                       </td>
				                   </tr>
				               </table>
				               <div id="attachments-container" class="attachments-container">                         
				                  <div class="field-label"><fmt:message key="page.issue.attachments" bundle="${lang}"/></div>
				                  <div id="attachments-list" class="attachments-list">
					                  <jsp:include page="${constants.URL_ATTACHMENTS_BLOCK}"/>
				                  </div>
				              </div><!-- attachments-container end -->
				       </div>
				       <div class="com-att">
				           <div id="comments-container" class="comments-container">
				               <div class="field-label" ><fmt:message key="page.issue.comments" bundle="${lang}"/></div>
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
        <script type="text/javascript">
             $( document ).ready(function () {
                  bindLongin();
                  $('#new-button').bind().on('click', function(){
                	  alert('Click new!');
                	  window.location.href = '/issuetracker/Main.do?command=submitissue';
                  });
                  $('#edit-button').bind().on('click', function(){
                	  alert('Click edit!');
                	  window.location.href = '/issuetracker/Main.do?command=editissue';
                  });
                  $('#delete-button').bind().on('click', function(){
                	  alert('Click delete!');
                  });
                  $('.description').attr('readonly','readonly');
             });
        </script>
     </body>
</html>