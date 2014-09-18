<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<html>
<head><title>MoviesApp</title><head>
<body>

	<div>
		<h3> These are the movies you have commented </h3>
		<ul>
		  <c:forEach var="aComment" items="${comments}">
		  	${aComment.creationDate}</br>
	        <li> <a href="movie?id=${aComment.movie.id}">${aComment.movie.title}</a></li></br>
	        	 	   
    	  </c:forEach>
        </ul>
    </div>
    
</body>
</html>