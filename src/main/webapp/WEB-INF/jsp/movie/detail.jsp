<%@ include file="../header.jsp" %>


	<div class="page-header">
		<div class="col-md-8">
		<h2>Movie Detail
			<br>
			<small>${movie.title}</small>
		</h2>
		</div>
		<div class="col-md-4">
			<c:choose>
	  		<c:when test="${movie.picture == null}">
	  			<c:if test="${user.admin}">
	  				<form action="setPicture" method="POST">
	  					<input type="hidden" name="movieId" value="${movie.id}" required>
	  					<input type="file" name="pic" required>
	  					<br>
							<input type="submit" value="Add Picture">
						</form>
					</c:if>
	  		</c:when>
			  <c:otherwise>
			  	<img src="${movie.picture}"/>
			  	<c:if test="${user.admin}">
			  		<form action="setPicture" method="POST" enctype="multipart/form-data">
			  			<input type="hidden" name="movieId" value="${movie.id}" required>
	  					<input type="file" name="pic" required>
							<input type="submit" value="Edit Picture">
						</form>
						<form action="setPicture" method="POST">
							<input type="hidden" name="movieId" value="${movie.id}" required>
	  					<input type="hidden" value="null" name="pic" required>
							<input type="submit" value="Delete Picture">
						</form>
					</c:if>
	  		</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div class="col-md-5 col-md-offset-0">
		<p class="text-center">
			<label><b>Director</b></label><br>${movie.director}<br>
			<br>
			<label><b>Genres</b></label>
			<c:forEach var="aGenre" items="${movie.genres}">
				<br>${aGenre.name}<br>
			</c:forEach>
			<br>
		</p>
	</div>
	<div class="col-md-5 col-md-offset-2">
		<p class="text-center">
			<label><b>Release Date</b></label><br>${movie.releaseDate}<br>
			<br>
			<label><b>Duration:</b></label><br>${movie.minutes} minutes.<br>
			<br>
		</p>

	</div>

	<div class="col-md-6 col-md-offset-3">
		<div class="well">
			<p class="text-center">
				<label><b>Description</b></label><br>
				<i>${movie.description}</i>
			</p>
		</div>
	</div>

	<div class="col-md-10 col-md-offset-1"/>

		<!-- Show CommentForm -->
		<c:if test="${canComment}">



			<div class="panel panel-primary">
				  <div class="panel-heading">
				    <h3 class="panel-title">Tell us your opinion!</h3>
				  </div>
				  <div class="panel-body">

						<form name="commentForm" action="../comments/create" class="" method="post">

						<input  type="hidden" name="movieId" value="${movie.id}"/>

						<div class="col-xs-2">
							<div class="form-group">
								<label class="control-label" for='rating'>Rating</label>
								<select class="form-control" name='rating'>
									<c:forEach var="i" begin="0" end="5">
								   		<option value='${i}'>${i}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="col-xs-12">
							<div class="form-group">
								<label class="control-label" for='body'>Comment</label>
								<textarea class="form-control" name='body' rows="6" cols="60"></textarea>
							</div>
						</div>

						<div class="col-md-3 col-md-offset-2 pull-right">
							<button class="btn btn-primary btn-block" type='submit'>Comment</button>
						</div>

					</form>
				</div>
			</div>


		</c:if>

		<!-- Comments Table -->
		<c:if test="${!empty comments}">

				<div class="panel panel-default">
				  <div class="panel-heading">
				    <h3 class="panel-title">Comments</h3>
				  </div>
				  <div class="panel-body">
					    <c:forEach var="aComment" items="${comments}">
							<p class="col-md-8">
								<span class="glyphicon glyphicon-chevron-right"></span> ${aComment.user.firstName} ${aComment.user.lastName} (${aComment.user.email}) said: <br>
								<label><b>Rating:</b></label> ${aComment.rating}
								<c:choose>
							    <c:when test="${aComment.rating=='1'}"> star.</c:when>
						      <c:otherwise> stars.</c:otherwise>
								</c:choose>
								<br/>
								<i>"${aComment.body}"</i>
							</p>
							<c:if test= "${user.admin}">
								<form action="../comments/delete", method="POST" class="col-md-4">
									<input  type="hidden" name="movieId" value="${movie.id}"/>
					    		<input  type="hidden" name="commentId" value="${aComment.id}"/>
					    		<input type="submit" value="Delete">
			    			</form>
							</c:if>
							<c:if test="${ aComment.user != user }">
							<c:set var="canRate" value="${true}"/>
								<c:forEach var="aCommentRating" items="${aComment.commentRatings}">
									<c:if test= "${aCommentRating.user == user}">
										<c:set var="canRate" value="${false}"/>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${ canRate }">
								<form action="../comments/rate", method="POST" class="col-md-4">
					    		<input  type="hidden" name="commentId" value="${aComment.id}"/>
					    		<select class="form-control" name='rating'>
										<c:forEach var="i" begin="0" end="5">
								   		<option value='${i}'>${i}</option>
										</c:forEach>
									</select>
					    		<input type="submit" value="Rate this comment">
			    			</form>
							</c:if>
						</c:forEach>
					</div>
				</div>

		</c:if>
	</div>

<%@ include file="../footer.jsp" %>
