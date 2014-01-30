<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="content">
	<p><span>Sorry, an error occurred.</span></p>
	<% if(exception != null) {%>
	
	<p>Here is the exception stack trace<span>(only during the learning process)</span>: </p>
	
	<% exception.printStackTrace(new PrintWriter(out));}%>
</div><!--end content-->