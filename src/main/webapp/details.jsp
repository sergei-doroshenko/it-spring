<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
     <head>
             <meta charset="UTF-8">
             <title>Delails</title>
             <link rel="stylesheet" type="text/css" media="screen" href="css/default.css" />
             <script type="text/javascript" src="js/jquery-1.9.0.min.js"> </script>
             <script type="text/javascript" src="js/jquery.cookie.js"> </script>
             <script type="text/javascript" src="js/login.js"> </script>
     </head>
     <body>
        <div class="page-wrapper">
             <div class="header">
                <jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
             </div><!--end header-->
             <div id="menu-bar" class="menu-bar">
                <jsp:include page="/WEB-INF/jsp/menubar.jsp"></jsp:include>
             </div><!-- end menu-bar -->
             <div class="content">
                 <div class="table-container">
                      <table id="list"><tr><td></td></tr></table> 
                      <div id="pager"></div>
                 </div><!--end issue-table-->
             </div><!--end content-->
             <div class="footer">
                 <jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
             </div><!--end footer-->
        </div><!--end page-wrapper-->
             
      <!-------- Issue Template -------------->
      <div id="issue-template">
         <div class="issue-container">
               <div class="obj-fields">
                   <div id="id" class="obj-fields-item"><span>Id: </span></div>
                   <div id="createdate" class="obj-fields-item"><span>Create Date: </span></div>
                   <div id="createby" class="obj-fields-item"><span>Create By: </span></div>
                   <div id="modifydate" class="obj-fields-item"><span>Modify Date: </span></div>
                   <div id="modifyby" class="obj-fields-item"><span>Modified By: </span></div>
                   <div id="summary" class="obj-fields-item"><span>Summary: </span></div>
                   <div id="description" class="obj-fields-item"><span>Description: </span></div>
                   <div id="status" class="obj-fields-item"><span>Status: </span></div>
                   <div id="resolution" class="obj-fields-item"><span>Resolution: </span></div>
                   <div id="type" class="obj-fields-item"><span>Type: </span></div>
                   <div id="priority" class="obj-fields-item"><span>Priority: </span></div>
                   <div id="project" class="obj-fields-item"><span>Project: </span></div>
                   <div id="projectbuild" class="obj-fields-item"><span>Build found: </span></div>
                   <div id="assignee" class="obj-fields-item"><span>Assignee: </span></div>
               </div>
               <div class="com-att">
                   <div id="comment" class="comment">
                       <span class="field-label" >Comments: </span>
                       <div class="comments-container"></div>
                   </div><!-- comment end -->
               <div id="attachment" class="attachment">
                   <span class="field-label">Attachment: </span>
                   <div class="attachments-container"></div>
               </div><!-- attachment end -->
               </div><!-- isuue-container end -->
         </div>
      </div>
      <!-------- End of Issue Template -------------->
           <script type="text/javascript">
           		$('.menu-obj:first').before('<li class="menu-obj-item"><a href="index.jsp">Main</a></li>');
                $( document ).ready(function () {
                     getDetails();  
                     bindLongin();
                });
                   
                function getDetails () {
                    $.ajax({
                        url: 'Main.do',
                        data: window.location.search.substring(1),
                        type: 'GET',
                        dataType : "json",                     
                        success: function (data, textStatus) {
                     	   succesDetailsIssue(data, textStatus);
                        },
                        error: function(response, status){
                     	   errDetails(response, status);
                        }
                    });
                }
                   
                function succesDetailsIssue(data, textStatus){
             	   $('.table-container').empty();
                    var templ = $('.issue-container').clone();
                    var issue = data.issue;
                    $(templ).find('#id').append('<span>' + issue.id + '</span>');
                    $(templ).find('#createdate').append(issue.createdate);
                    $(templ).find('#createby').append(issue.createby);
                    $(templ).find('#modifydate').append(issue.modifydate);
                    $(templ).find('#modifyby').append(issue.modifyby);
                    $(templ).find('#summary').append(issue.summary);
                    $(templ).find('#description').append(issue.description);
                    $(templ).find('#status').append(issue.status);
                    $(templ).find('#resolution').append(issue.resolution);
                    $(templ).find('#type').append(issue.type);
                    $(templ).find('#priority').append(issue.priority);
                    $(templ).find('#project').append(issue.project);
                    $(templ).find('#projectbuild').append(issue.projectbuild);
                    $(templ).find('#assignee').append(issue.assignee);
                    templ.appendTo('.content');
                }
                   
                function errDetails(response, status){
             	   if(response.status == 400) {
                        var errText = response.responseText;
                        $('.table-container').empty().append('Error while getting url. ' + errText);     
                	}
                }
           </script>
     </body>
</html>