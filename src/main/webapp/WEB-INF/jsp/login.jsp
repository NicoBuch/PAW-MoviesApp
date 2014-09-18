<%@ include file="header.jsp" %>
	
	<legend><h2>Login</h2></legend>

	<!-- Filter by genre Form -->
	<c:if test="${!empty errorMessage}">
		<div class="alert alert-warning alert-dismissible" role="alert">
  			<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  			<strong>Login Error!</strong> ${errorMessage}
		</div>
	</c:if>
	
	
	<form name="loginForm" method='post'>
		<div class="col-md-8 col-md-offset-2" id="feedbackPanel" />
			<div class="col-xs-12">
				<div class="form-group">
					<label class="control-label">Username</label>
					<input class="form-control" name='email' type='email' placeholder='user@example.com' required/>
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
						Forgot your password? <a href="recovery" class="float-right"><strong>Recover it!</strong></a>

			</div>			
			
		</div>
		
	</form>

<%@ include file="footer.jsp" %>



