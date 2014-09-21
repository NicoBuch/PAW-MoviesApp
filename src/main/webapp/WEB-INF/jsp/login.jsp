<%@ include file="header.jsp" %>
	

	<div class="page-header">
		<h2>Login
			<br>
			<small>Please complete these fields to sign in</small>
		</h2>	
	</div>
	

	<div class="col-md-10 col-md-offset-1">

		<c:if test="${!empty errorMessage}">
			<div class="alert alert-warning alert-dismissible" role="alert">
	  			<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	  			<strong>Login Error!</strong> ${errorMessage}
			</div>
		</c:if>

		<c:if test="${!empty prevSuccess}">
			<div class="alert alert-success alert-dismissible" role="alert">
	  			<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	  			<strong>Success!</strong> ${prevSuccess}
			</div>
		</c:if>
		
		
		<form name="loginForm" method='post'>
			<div class="col-md-8 col-md-offset-2" id="feedbackPanel" />
				<div class="col-xs-12">
					<div class="form-group">
						<label class="control-label">Username</label>
						<input class="form-control" name='email' autofocus type='email' placeholder='user@example.com' required/>
					</div>
				</div>

				<div class="col-xs-12">
					<div class="form-group">
						<label class="control-label">Password</label>
						<input class="form-control" name='password' type='password' placeholder='Insert password' required>
					</div>
				</div>
				
				<div class="col-md-6 col-md-offset-6 ">
							<button class="btn btn-primary btn-block" type="submit">Log in</button>
							Can't remember your password? <a href="recovery" class="float-right"><strong>Recover it!</strong></a>

				</div>			
				
			</div>
			
		</form>
	</div>

<%@ include file="footer.jsp" %>



