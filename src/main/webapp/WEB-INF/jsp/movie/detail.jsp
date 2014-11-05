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
	  				<form action="setPicture" method="POST" enctype="multipart/form-data">
	  					<input type="hidden" name="movieId" value="${movie.id}" required>
	  					<input type="file" name="pic" required>
	  					<br>
							<input type="submit" value="Add Picture">
						</form>
					</c:if>
	  		</c:when>
			  <c:otherwise>
			  	<img src="/MoviesApp/bin/movie/showPicture?movieId=${movie.id}"/>
			  	<c:if test="${user.admin}">
			  		<form action="setPicture" method="POST" enctype="multipart/form-data">
			  			<input type="hidden" name="movieId" value="${movie.id}" required>
	  					<input type="file" name="pic" required>
							<input type="submit" value="Edit Picture">
						</form>
						<form action="setPicture" method="POST" enctype="multipart/form-data">
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


	<div class="col-md-5 col-md-offset-2">
		<c:if test="${!empty movie.prices}">
			<p class="text-center">
				<label><b>Prices:</b></label><br>
			</p>
				<c:forEach var="aPrice" items="${movie.prices}">
					<div>
						${aPrice.name}
						<c:if test="${user.admin}">
							<form action="../movie/deletePrize", method="POST" class="col-md-4">
								<input  type="hidden" name="prizeId" value="${aPrize.id}"/>
				    		<input type="submit" value="Delete">
			  			</form>
			  		</c:if>
		  		</div>
					<br>
				</c:forEach>
		</c:if>
		<c:if test="${!empty movie.nominations}">
			<br>
			<label><b>Nominations:</b></label><br>
			<c:forEach var="aNomination" items="${movie.nominations}">
				${aNomination.name}
				<c:if test="${user.admin}">
					<form action="../movie/deletePrize", method="POST" class="col-md-4">
						<input  type="hidden" name="prizeId" value="${aNomination.id}"/>
		    		<input type="submit" value="Delete">
	  			</form>
	  		</c:if>
				<br>
			</c:forEach>
			<br>
		</c:if>
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

	<c:if test= "${ user.admin }">
		<div>
			<br>
			<h4><strong>Add a new prize or nomination</strong></h4>
			<form action="addPrize" method="POST">
		  	<input type="hidden" name="movieId" value="${movie.id}" required>
		  	Name:
		  	<input type="text" name="name" required><br>
		  	<label><input type="checkbox" name="prize">Checked for prize, Not checked for nomination</label>
		  	<br>
				<input type="submit" value="Add new Price or Nomination">
				<br><br>
			</form>
		</div>
	</c:if>
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
							<div class="form-group <c:if test='${emptyComment}'>has-error</c:if>">
								<label class="control-label" for='body'>Comment</label>
								<c:if test='${emptyComment}'>
									<br><label class="control-label" for='body'>It can not be empty</label>
								</c:if>
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
		<c:if test="${!empty movie.comments}">

				<div class="panel panel-default">
				  <div class="panel-heading">
				    <h3 class="panel-title">Comments</h3>
				  </div>
				  <div class="panel-body">
					    <c:forEach var="aComment" items="${movie.comments}">
							<p class="col-md-8">
								<span class="glyphicon glyphicon-chevron-right"></span> ${aComment.user.firstName} ${aComment.user.lastName} (${aComment.user.email}) said: <br>
								<label><b>Rating:</b></label> ${aComment.rating}
								<c:choose>
							    <c:when test="${aComment.rating=='1'}"> star.</c:when>
						      <c:otherwise> stars.</c:otherwise>
								</c:choose>
								<br/>
								<i>"${aComment.body}"</i>
								<p>This Comment has ${aComment.avgCommentRatings} average rating.
							</p>
							<c:if test= "${user.admin}">
								<form action="../comments/delete", method="POST" class="col-md-4">
						    		<input  type="hidden" name="commentId" value="${aComment.id}"/>
						    		<input type="submit" value="Delete">
			    				</form>
							</c:if>

							<c:if test="${! empty user }">
								<c:if test="${! (user == aComment.user)}">
									<c:set var="canRate" value="${true}"/>
									<c:set var="canReport" value="${true}"/>
									<c:forEach var="aCommentRating" items="${aComment.commentRatings}">
										<c:if test= "${aCommentRating.user == user}">
											<c:set var="canRate" value="${false}"/>
										</c:if>
									</c:forEach>
									<c:if test= "${! empty aComment.reports}">
										<c:forEach var="aReport" items="${aComment.reports}">
											<c:if test= "${aReport.user == user}">
												<c:set var="canReport" value="${false}"/>
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
									<c:if test="${ canReport }">
										<form action="../comments/report", method="POST" class="col-md-4">
							    		<input  type="hidden" name="commentId" value="${aComment.id}"/>
							    		<input type="submit" value="Report this comment">
					    				</form>
									</c:if>

								</c:if>
							</c:if>
						</c:forEach>
					</div>
				</div>

		</c:if>
	</div>

<%@ include file="../footer.jsp" %>
