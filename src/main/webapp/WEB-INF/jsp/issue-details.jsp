<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- i18n -->
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope[constants.KEY_LOCALE]}"/>
<fmt:setBundle basename="i18n.main" var="lang"/>
<!-- End of i18n -->        
<c:set var="issue" scope="page" value="${sessionScope[constants.ENTITY]}"/>
 <div class="issue-container">
       <div class="obj-fields">
               <table>
                   <tr>
                       <td class="detail-col-name">
                       		<fmt:message key="page.details.id" bundle="${lang}"/>
                       </td>
                       <td id="id" class="detail-col-value">
                       		<c:out value="${issue.id}"/>
                       </td>
                   </tr>
                   <tr>
                       <td class="detail-col-name">Create Date:</td>
                       <td class="detail-col-value">                           
                           <c:out value="${issue.createDate}"/>
                       </td>
                       <td class="detail-col-name">Create By:</td>
                       <td class="detail-col-value">
                           <c:out  value="${issue.createBy.firstName} ${issue.createBy.lastName}"/>
                       </td>
                   </tr>
                   <tr>
                       <td class="detail-col-name">Modify Date:</td>
                       <td class="detail-col-value">
                           <input id="modifydate" class="detail-col-input" type="text" value="${issue.modifyDate}"></input>
                       </td>
                       <td class="detail-col-name">Modified By:</td>
                       <td class="detail-col-value">
                           <input id="modifyby" class="detail-col-input" type="text" value="${issue.modifyBy.firstName} ${issue.modifyBy.lastName}"></input>
                       </td>
                   </tr>
                   <tr>
                       <td class="detail-col-name">Type:</td>
                       <td class="detail-col-value">
                           <select id="type" name="type" class="detail-col-select" size="1">
                               <option value="first" selected="selected">${issue.type.name}</option>
                               <option value="second">Test 2</option>
                               <option value="third">Test 3</option>
                               <option value="fourth">Test 4</option>
                           </select>
                       </td>
                       <td class="detail-col-name">Priority:</td>
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
                       <td class="detail-col-name">Status:</td>
                       <td class="detail-col-value">
                           <select id="status" name="status" class="detail-col-select" size="1">
                               <option value="first" selected="selected">${issue.status.name}</option>
                               <option value="second">Test 2</option>
                               <option value="third">Test 3</option>
                               <option value="fourth">Test 4</option>
                           </select>
                       </td>
                       <td class="detail-col-name">Resolution:</td>
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
                       <td class="detail-col-name">Project:</td>
                       <td class="detail-col-value">
                           <select id="project" name="project" class="detail-col-select" size="1">
                               <option value="first" selected="selected">Project</option>
                               <option value="second">Test 2</option>
                               <option value="third">Test 3</option>
                               <option value="fourth">Test 4</option>
                           </select>
                       </td>
                       <td class="detail-col-name">Build found:</td>
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
                       <td class="detail-col-name">Assignee:</td>
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
                       <td class="detail-col-name">Summary:</td>
                           <td class="detail-col-value" colspan="3">
                               <input id="summary" class="detail-col-input" type="text" value="${issue.summary}"></input>
                       </td>
                   </tr>
                    <tr>
                       <td class="detail-col-name">Description:</td>
                       <td class="detail-col-value" colspan="3">
                           <textarea id="description" class="description">${issue.description}</textarea>
                       </td>
                   </tr>
               </table>
               <div id="attachments-container" class="attachments-container">                         
                  <div class="field-label">Attachments: 
                  <c:if test="${!empty user}">
                  		<input id="attachment" class="attachment" type="file"/>
                  </c:if>
                  </div>
                  <div id="attachments-list" class="attachments-list"></div>
              </div><!-- attachments-container end -->
       </div>
       <div class="com-att">
           <div id="comments-container" class="comments-container">
               <div class="field-label" >Comments: </div>
                  <div id="comments-list" class="comments-list"></div>
                  <textarea id="comment" class="comment" rows="7" cols="40"></textarea>
                  <c:if test="${!empty user}">
                  		<input type="button" value="Add Comment"></input>		
                  </c:if>
           </div><!-- comment end -->
       </div>
 </div><!-- isuue-container end -->