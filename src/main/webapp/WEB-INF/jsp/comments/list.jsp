<%@ include file="../header.jsp" %>

	<table class="table table-striped">
		<thead>
		     <tr>
		            <th>Comment</th>
		            <th>Comment Date</th>
		 	</tr>
		 </thead>
		<c:forEach var="aComment" items="${comments}">
		    <tr>
		    	<td><a href="../movie/detail?id=${aComment.movie.id}">${aComment.body}</a></td>
		    	<td>${aComment.creationDate}</td>
		    	<td>
		    		<form action="../comments/delete", method="POST" class="col-md-4">
					<input  type="hidden" name="commentId" value="${aComment.id}"/>
					<input type="submit" value="Delete">
			    	</form>
				</td>
				<td>
			    <form action="../comments/clean", method="POST" class="col-md-4">
					<input  type="hidden" name="commentId" value="${aComment.id}"/>
					<input type="submit" value="Clean">
			    </form>
			    </td>

		    </tr>
	     </c:forEach>
	</table>

<%@ include file="../footer.jsp" %>