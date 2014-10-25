
<%@ include file="../header.jsp" %>

	<div class="page-header">
		<h2>Movie List
			<br>
			<small>A complete list of all movies</small>
		</h2>
		<c:if test= "${user.admin}">
			<div class="pull-right">
	    	<form action="./create", method="GET">
					<input type="submit" value="+ Create New Movie">
				</form>
			</div>
		</c:if>
	</div>
	<br/>

	<!-- Filter by genre Form -->
	<div class="pull-right">
		<form class="form-inline" name="filterByGenreForm" method='get'>
			<label class="label-control">Genre: </label>
			<select class="form-control input-sm" name='genre'>
				<option value='empty'>Select genre...</option>
				<c:forEach var="aGenre" items="${genres}">
				   <option value='${aGenre}'>${aGenre}</option>
			     </c:forEach>
			</select>
			<button class="btn btn-primary btn-sm" type="submit">Filter</button>
		</form>
	</div>
	<div class="pull-left">
		<form class="form-inline" name="filterByDirectorForm" method='get'>
			<label class="label-control">Director:</label>
			<input  type="text" name="director" class="form-control input-sm">
			<button class="btn btn-primary btn-sm" type="submit">Filter</button>
		</form>
	</div>



	<!-- Movies Table -->
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Title</th>
				<th>Director</th>
				<th>Release Date</th>
				<c:if test="${user.admin}">
					<th/>
					<th/>
				</c:if>
			</tr>
		</thead>
		<c:forEach var="aMovie" items="${movies}">
		    <tr>
		    	<td><a href="detail?id=${aMovie.id}">${aMovie.title}</a></td>
		    	<td>${aMovie.director}</td>
		    	<td>${aMovie.releaseDate}</td>
		    	<c:if test="${user.admin}">
		    	<td>
			    	<form action="./edit">
			    		<input  type="hidden" name="id" value="${aMovie.id}"/>
			    		<input type="submit" value="Edit">
			    	</form>
		    	</td>
		    	<td>
			    	<form action="./delete", method="POST">
			    		<input  type="hidden" name="id" value="${aMovie.id}"/>
			    		<input type="submit" value="Delete">
			    	</form>
			    </td>
			    </c:if>
		    </tr>
	     </c:forEach>
	</table>



<%@ include file="../footer.jsp" %>

