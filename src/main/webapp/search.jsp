<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Search page</title>
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
	    		<p><span>Search page</span><p>
	    		<fmt:setLocale value="ru"/>
				<fmt:bundle basename="i18n.test">
				   <fmt:message key="one" var="direction"/><br/>
				   <fmt:message key="two"/><br/>
				   <p>${direction}</p>
				</fmt:bundle>
	    		
	    		<select id="langVal" onChange="i18n.changeLocale(this.value, '${currentLocale}');" selectedIndex="1">
		            <option value="${currentLocale}">${currentLanguage}</option>
		            <option value="en_US">English</option>
		            <option value="ja_JP">日本語</option>
		            <option value="fr_FR">Français</option>
		            <option value="de_DE">Deutsch</option>
		        </select>
	    		
	    		
	    		<!-- c:out value="Hello ${map.two}"/-->
	    		
	    		
	    		
	    		
             </div><!--end content-->
             <div class="footer">
                 <jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
             </div><!--end footer-->
             <script type="text/javascript">
           		//$('.menu-obj').append('<li class="menu-obj-item"><a href="index.jsp">Main</a></li>');
           		$('.menu-obj:first').replaceWith('<li class="menu-obj-item"><a href="index.jsp">Main</a></li>');
           		$( document ).ready(function () {
           			bindLongin();	
           		});
           		
           		
           		if (!window.i18n)
           		    i18n = {};
           		i18n.changeLocale = function (locale, current) {
           		    var request;
           		    // do nothing if not changed
           		    if (locale == current)
           		        return;

           		    // get a request
           		    if (request = this.getXmlHttpRequest()) {
           		        // do post and bring locale value as param['lang']
           		        request.open('POST', 'practice_three__internationalization_lang_page.jsp?lang='+locale);
           		        request.send(null);
           		    }
           		    // refresh document after request success
           		    request.onreadystatechange = function() {
           		        if(request.readyState === 4 && request.status === 200)
           		            document.location.href = document.location.href;
           		    };
           		};

           		//get the XmlHttpRequest object
           		i18n.getXmlHttpRequest = function () {
           		    if (window.XMLHttpRequest
           		        && (window.location.protocol !== 'file:' 
           		        || !window.ActiveXObject))
           		        return new XMLHttpRequest();
           		    try {
           		        return new ActiveXObject('Microsoft.XMLHTTP');
           		    } catch(e) {
           		        throw new Error('XMLHttpRequest not supported');
           		    }
           		};
           		
           	</script>
        </div><!--end page-wrapper-->
	</body>
</html>