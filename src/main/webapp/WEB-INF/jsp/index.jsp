<%@ include file="header.jsp" %>

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
    
    
        
<%@ include file="footer.jsp" %>
