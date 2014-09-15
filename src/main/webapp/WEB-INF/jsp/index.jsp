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
		<h3> Most Ranked Movies </h3>
		<ul>
		  <c:forEach var="aMovie" items="${ranked}">
	        <li> <a href="movie?id=${aMovie.id}">${aMovie.title}</a></li> 	   
    	  </c:forEach>
        </ul>
    </div>
    
    <div>
    	<h3> Weekly Releases </h3>
		<ul>
		  <c:forEach var="aMovie" items="${releases}">
	        <li> <a href="movie?id=${aMovie.id}">${aMovie.title}</a></br>
	        	 ${aMovie.description}
	        </li> 	   
    	  </c:forEach>
        </ul>
   </div>
   
   <div>
        <h3> Recent Uploaded Movies </h3>
		<ul>
		  <c:forEach var="aMovie" items="${recents}">
	        <li> <a href="movie?id=${aMovie.id}">${aMovie.title}</a></br>
	        	 Upload Date: ${aMovie.creationDate}</br>
	        	 Comments: ${aMovie.commentCount}
	        </li> 	   
    	  </c:forEach>
        </ul>
   </div>   
   
   <div>
   		<h3><a href="comments?user_id=${user.id}"> See all my comments</h3>
   </div>
   		
    		
    
    
        
        
</body>
</html>
