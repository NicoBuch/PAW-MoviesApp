<%@ include file="../header.jsp" %>

	<div class="page-header">
		<h2>Movie List
			<br>
			<small>A complete list of all users</small>
		</h2>
	</div>
	<br/>

	<!-- Users Table -->
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Email</th>
				<th>First Name</th>
				<th>Last Name</th>				
			</tr>
		</thead>
		<c:forEach var="aUser" items="${users}">
		    <tr>
		    	<td><a href="comments?user_id=${aUser.id}">${aUser.email}</a></td>
		    	<td>${aUser.firstName}</td>
		    	<td>${aUser.lastName}</td>
		    </tr>
	     </c:forEach>
	</table>

<%@ include file="../footer.jsp" %>