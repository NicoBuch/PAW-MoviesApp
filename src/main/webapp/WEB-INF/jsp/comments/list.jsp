<%@ include file="../header.jsp" %>
	
	<div class="page-header">
		<h2>Report List
			<br>
			<small>A complete list of reported comments</small>
		</h2>
	</div>
	<br/>
	<c:choose>
		<c:when test="${!empty comments}">
			<table class="table table-striped">
				<thead>
				     <tr>
				            <th>Comment</th>
				            <th>Comment Date</th>
				            <th></th>
				            <th></th>
				 	</tr>
				 </thead>
				<c:forEach var="aComment" items="${comments}">
				    <tr>
				    	<td><a href="../movie/detail?id=${aComment.movie.id}">${aComment.body}</a></td>
				    	<td>${aComment.creationDate}</td>
				    	
				    	<td width="10%">
						    <form action="../comments/clean" method="POST">
								<input  type="hidden" name="commentId" value="${aComment.id}"/>
								<input type="submit" class="btn btn-default btn-xs btn-block" value="Clean">
						    </form>
					    </td>
				    	<td width="10%">
				    		<form action="../comments/delete" method="POST">
								<input  type="hidden" name="commentId" value="${aComment.id}"/>
								<input type="submit" class="btn btn-danger btn-xs btn-block" value="Delete">
					    	</form>
						</td>
						

				    </tr>
			     </c:forEach>
			</table>					
		</c:when>
		<c:otherwise>
			<div class="alert alert-info col-md-6 col-md-offset-3" role="alert"><p class="text-center"><strong>No reports to show</strong></p></div>
		</c:otherwise>
	</c:choose>

	

<%@ include file="../footer.jsp" %>
