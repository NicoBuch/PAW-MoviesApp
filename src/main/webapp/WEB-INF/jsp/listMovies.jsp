
<%@ include file="header.jsp" %>

	<legend><h2>List Movies</h2></legend>

	<!-- Filter by genre Form -->
	<div class="pull-right">
		<form class="form-inline" name="filterByGenreForm" method='get'>
			<label class="label-control">Director:</label>
			<input  type="text" name="director" class="form-control input-sm"> 
			<label class="label-control">Genre: </label>
			<select class="form-control input-sm" name='genre'>
				<c:forEach var="aGenre" items="${genres}">
				   <option value='${aGenre}'>${aGenre}</option>     	   
			     </c:forEach>	  
			</select>
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
		 	</tr>
		 </thead>
		<c:forEach var="aMovie" items="${movies}">
		    <tr>
		    	<td><a href="movie?id=${aMovie.id}">${aMovie.title}</a></td>
		    	<td>${aMovie.director}</td>
		    	<td>${aMovie.releaseDate}</td>	    	
		    </tr>        	   
	     </c:forEach>
	</table>



<%@ include file="footer.jsp" %>

