<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page isErrorPage="true" import="java.io.*"%>       
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<a href="main.jspage">Main</a>
    	<p><span>Sorry, an error occurred.</span></p>
    			
		<% if(exception != null) {%>">            
            <p>Here is the exception stack trace<span>(only during the learning process)</span>: </p>
        <% exception.printStackTrace(new PrintWriter(out));}%>
		
		           
    </div>
</body>
</html>