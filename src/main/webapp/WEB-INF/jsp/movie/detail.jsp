<%@ include file="../header.jsp" %>


	<div class="page-header">
		<div class="col-md-8">
			<h2>Movie Detail
				<br>
				<small>${movie.title}</small>
			</h2>
		</div>
	</div>

	<div class="col-md-12">
		<div class="col-md-6">
			<p class="text-center">
				<label><b>Director</b></label><br>${movie.director}<br>
				<br>
				<label><b>Genres</b></label><br>
				<c:forEach var="aGenre" items="${movie.genres}">
					${aGenre.name} -
				</c:forEach>
				<br><br>
				<label><b>Release Date</b></label><br>${movie.releaseDate}<br>
				<br>
				<label><b>Duration</b></label><br>${movie.minutes} minutes.<br>
				<br>
				<label><b>Prizes</b></label><br>
				<c:choose>
					<c:when test="${!empty movie.prices}">
						<c:forEach var="aPrice" items="${movie.prices}">
							<div class=" col-md-6 col-md-offset-5">	
									${aPrice.name}
									<c:if test="${user.admin}">
										<form action="../movie/deletePrize", method="POST" class="pull-right">
											<input  type="hidden" name="prizeId" value="${aPrize.id}"/>
											<input type="submit" class="btn btn-danger btn-xs" value="Delete">
						  				</form>
						  			</c:if>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						None
					</c:otherwise>
				</c:choose>
				<br><br>
				
				<c:choose>
					<c:when test="${!empty movie.nominations}">
						<div class="col-md-offset-5">
							<label><b>Nominations</b></label><br>
						</div>
						<c:forEach var="aNomination" items="${movie.nominations}">
							<div class=" col-md-6 col-md-offset-5">
								${aNomination.name}
								<c:if test="${user.admin}">
									<form action="../movie/deletePrize", method="POST" class="pull-right">
										<input  type="hidden" name="prizeId" value="${aNomination.id}"/>
						    			<input type="submit" class="btn btn-danger btn-xs" value="Delete">
					  				</form>
					  			</c:if>
					  		</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<label><b>Nominations</b></label><br>
						None
					</c:otherwise>
				</c:choose>
			</p>
		</div>

		<div class="col-md-6">
			<c:choose>
		  		<c:when test="${movie.picture == null}">
		  			<img src="http://www.jordans.com/~/media/Jordans%20Redesign/No-image-found.jpg" class="img-responsive" />
		  		</c:when>
				<c:otherwise>
					<img src="/MoviesApp/bin/movie/showPicture?movieId=${movie.id}" class="img-responsive" />
				  	<c:if test="${user.admin}">
						<form action="deletePicture" method="POST" enctype="multipart/form-data">
							<input type="hidden" name="movieId" value="${movie.id}" required>
							<input type="submit" class="btn btn-danger btn-sm" value="Delete">
						</form>
					</c:if>
		  		</c:otherwise>
			</c:choose>
		</div>


	</div>


	<div class="col-md-6 col-md-offset-3">
		<div class="well">
			<p class="text-center">
				<label><b>Description</b></label><br>
				<i>${movie.description}</i>
			</p>
		</div>
	</div>



	<div class="col-md-10 col-md-offset-1">
		<c:if test= "${ user.admin }">

			<h3><strong>Admin Settings</strong></h3>
			<div class="col-md-5">
				<h4><strong>Prizes and Nominations</strong></h4>
				<form action="addPrize" method="POST">
				  	<input type="hidden" name="movieId" value="${movie.id}" required>
				  	<div class='col-md-12'>
				  		<input type="text" class="form-control" name="name" placeholder="Prize or Nomination name..."required>
				   	</div>
				   	<div class='col-md-6'>
				  		<label><input type="checkbox" class="pull-left" name="prize">Awarded</label>
				  	</div>
				  	<div class='col-md-5 pull-right'>
				  		<input type="submit" class="pull-right btn btn-default btn-block" value="Add">
				  	</div>
				</form>
			</div>

			<div class="col-md-5 pull-right">
				<h4><strong>Picture</strong></h4>
				<form action="setPicture" method="POST" enctype="multipart/form-data">
			  		<input type="hidden" name="movieId" value="${movie.id}" required>
			  		<input type="file" name="pic" required>
			  		<div class='col-md-5 pull-right'>
						<input type="submit" class="btn btn-default btn-block" value="Upload">
					</div>
				</form>
			</div>

		</c:if>

	</div>
	<div class="col-md-10 col-md-offset-1"/>
		<br><br>
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
							<div class="col-md-12"> 
								<p>
									<span class="glyphicon glyphicon-chevron-right"></span> ${aComment.user.firstName} ${aComment.user.lastName} (${aComment.user.email}) said: <br>
									<label><b>Rating:</b></label> ${aComment.rating}
									<c:choose>
									    <c:when test="${aComment.rating=='1'}"> star.</c:when>
								      	<c:otherwise> stars.</c:otherwise>
									</c:choose>
									<br/>
									<i>"${aComment.body}"</i>
									<p class="pull-right">This comment has ${aComment.avgCommentRatings} average rating.</p>
								</p>
							</div>
							
							<div class="col-md-12">
								<c:if test="${! empty user }">
									<!-- User Settings -->
									<div class="col-md-8">
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
												<form action="../comments/rate", method="POST" class="form-inline"> 
									    			<input  type="hidden" name="commentId" value="${aComment.id}"/>
									    			<select class="form-control input-sm" name='rating'>
														<c:forEach var="i" begin="0" end="5">
												   			<option value='${i}'>${i}</option>
														</c:forEach>
													</select>
									    			<input type="submit" class="btn btn-default btn-sm" value="Rate Comment">
						    					</form>
						    				</c:if>

											
										</c:if>
								
									</div>
									<div class="col-md-3 pull-right">
										<div class="col-md-6">
											<c:if test="${ canReport }">
												<form action="../comments/report", method="POST">
										    		<input  type="hidden" name="commentId" value="${aComment.id}"/>
										    		<input type="submit" class="btn btn-warning btn-xs btn-block"  value="Report">
							    				</form>
											</c:if>
										</div>
										<div class="col-md-6 pull-right">
											<c:if test= "${user.admin}">
												<form action="../comments/delete", method="POST">
								    				<input  type="hidden" name="commentId" value="${aComment.id}"/>
								    				<input type="submit" class="btn btn-danger btn-xs btn-block" value="Delete">
					    						</form>
											</c:if>
										</div>
									</div>	
								</c:if>
								<br><br>
							</div>
						
						</c:forEach>
					</div>
				</div>

		</c:if>
	</div>

<%@ include file="../footer.jsp" %>
