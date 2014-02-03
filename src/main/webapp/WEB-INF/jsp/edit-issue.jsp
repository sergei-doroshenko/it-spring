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
             	<jsp:include page="${constants.URL_BUTTONS_EDIT}"/>
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
				                           <input id="modifydate" class="detail-col-input" type="text" value="${issue.modifyDate}"></input>
				                       </td>
				                       <td class="detail-col-name"><fmt:message key="page.issue.modifyby" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <input id="modifyby" class="detail-col-input" type="text" value="${issue.modifyBy.firstName} ${issue.modifyBy.lastName}"></input>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.type" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <select id="type" name="type" class="detail-col-select" size="1">
				                               <option value="first" selected="selected">${issue.type.name}</option>
				                               <option value="second">Test 2</option>
				                               <option value="third">Test 3</option>
				                               <option value="fourth">Test 4</option>
				                           </select>
				                       </td>
				                       <td class="detail-col-name"><fmt:message key="page.issue.priority" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <select id="priority" name="priority" class="detail-col-select" size="1">
				                               <option value="first" selected="selected">${issue.priority.name}</option>
				                               <option value="second">Test 2</option>
				                               <option value="third">Test 3</option>
				                               <option value="fourth">Test 4</option>
				                           </select>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.status" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <select id="status" name="status" class="detail-col-select" size="1">
				                               <option value="first" selected="selected">${issue.status.name}</option>
				                               <option value="second">Test 2</option>
				                               <option value="third">Test 3</option>
				                               <option value="fourth">Test 4</option>
				                           </select>
				                       </td>
				                       <td class="detail-col-name"><fmt:message key="page.issue.resolution" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <select id="resolution" name="resolution" class="detail-col-select" size="1">
				                               <option value="first" selected="selected">${issue.resolution.name}</option>
				                               <option value="second">Test 2</option>
				                               <option value="third">Test 3</option>
				                               <option value="fourth">Test 4</option>
				                           </select>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.project" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <select id="project" name="project" class="detail-col-select" size="1">
				                               <option value="first" selected="selected">Project</option>
				                               <option value="second">Test 2</option>
				                               <option value="third">Test 3</option>
				                               <option value="fourth">Test 4</option>
				                           </select>
				                       </td>
				                       <td class="detail-col-name"><fmt:message key="page.issue.build" bundle="${lang}"/></td>
				                       <td class="detail-col-value">
				                           <select id="projectbuild" name="projectbuild" class="detail-col-select" size="1">
				                               <option value="first" selected="selected">${issue.build.name}</option>
				                               <option value="second">Test 2</option>
				                               <option value="third">Test 3</option>
				                               <option value="fourth">Test 4</option>
				                           </select>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.assignee" bundle="${lang}"/></td>
				                       <td class="detail-col-value" colspan="3">
				                           <select id="assignee" name="assignee" class="detail-col-select" size="1">
				                               <option value="first" selected="selected">${issue.assignee.firstName} ${issue.assignee.lastName}</option>
				                               <option value="second">Test 2</option>
				                               <option value="third">Test 3</option>
				                               <option value="fourth">Test 4</option>
				                           </select>
				                       </td>
				                   </tr>
				                   <tr>
				                       <td class="detail-col-name"><fmt:message key="page.issue.summary" bundle="${lang}"/></td>
				                           <td class="detail-col-value" colspan="3">
				                               <input id="summary" class="detail-col-input" type="text" value="${issue.summary}"></input>
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
				                  <div class="field-label"><fmt:message key="page.issue.attachments" bundle="${lang}"/>
				                  		<p>${uploadmessage}</p>
				                  		<form action="FileUploadDownload.do" method="post" enctype="multipart/form-data">
											Select File to Upload:<input id="attachment" class="attachment" type="file" name="file"/>
											<br>
											<input type="submit" value="Upload">
										</form>		                  
				                  </div>
				                  <div id="attachments-list" class="attachments-list"></div>
				              </div><!-- attachments-container end -->
				       </div>
				       <div class="com-att">
				           <div id="comments-container" class="comments-container">
				               <div class="field-label" ><fmt:message key="page.issue.comments" bundle="${lang}"/></div>
				                  <div id="comments-list" class="comments-list"></div>
				                  <textarea id="comment" class="comment" rows="7" cols="40"></textarea>
				                  <button id="add-comment"><fmt:message key="button.addcomment" bundle="${lang}"/></button>
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
                  $('#save-button').bind().on('click', function(){
                	  alert('Click save!');
                  });
                  $('#cancel-button').bind().on('click', function(){
                	  alert('Click cancel!');
                  });
                  $('.description').attr('readonly','readonly');
             });
        </script>
     </body>
</html>