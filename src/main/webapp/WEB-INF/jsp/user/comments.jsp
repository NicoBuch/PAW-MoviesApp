<%@ include file="../header.jsp" %>

	<div class="page-header">
		<h2>${aUser.firstName} ${aUser.lastName}'s Comments
			<br>
			<small>These are the movies ${aUser.firstName} has commented</small>
		</h2>
	</div>

	<div>
		<c:forEach var="interestUser" items="${user.usersOfInterest}">
			<c:if test="${interestUser.id == aUser.id}">
				<c:set var="isInterest" value="${true}"/>
			</c:if>
		</c:forEach>
		<c:choose>
			<c:when test="${isInterest}">
				<form action="removeUserOfInterest", method="POST">
	    		<input  type="hidden" name="userOfInterest" value="${aUser.id}"/>
	    		<input type="submit" class="btn btn-sm btn-danger pull-right" value="Unfollow">
				</form>
			</c:when>
			<c:otherwise>
				<form action="addUserOfInterest", method="POST">
	    		<input  type="hidden" name="userOfInterest" value="${aUser.id}"/>
	    		<input type="submit" class="btn btn-sm btn-success pull-right" value="Follow">
				</form>
			</c:otherwise>
		</c:choose>
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
		    	<td><a href="../movie/detail?id=${aComment.movie.id}">${aComment.movie.title}</a></td>
		    	<td>${aComment.creationDate}</td>
		    </tr>
	     </c:forEach>
	</table>


<%@ include file="../footer.jsp" %>
