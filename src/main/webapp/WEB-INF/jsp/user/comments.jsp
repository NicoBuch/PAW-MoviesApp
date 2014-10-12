<%@ include file="../header.jsp" %>

	<div class="page-header">
		<h2>My Comments
			<br>
			<small> These are the movies you have commented </small>
		</h2>	
	</div>
	


	<table class="table table-striped">
		<thead>
		     <tr>
		            <th>Movie</th>
		            <th>Comment Date</th>
		 	</tr>
		 </thead>
		<c:forEach var="aComment" items="${comments}">
		    <tr>
		    	<td><a href="movie?id=${aComment.movie.id}">${aComment.movie.title}</a></td>
		    	<td>${aComment.creationDate}</td>	    	
		    </tr>        	   
	     </c:forEach>
	</table>

	    
<%@ include file="footer.jsp" %>
