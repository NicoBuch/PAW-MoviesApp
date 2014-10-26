<%@ include file="header.jsp" %>


	<div class="page-header">
		<h2>Register
			<br>
			<small>Please complete these fields to sign up</small>
		</h2>	
	</div>

	<div class="col-md-10 col-md-offset-1">

		<c:if test='${!empty errors}'>
			<c:forEach var="error" items="${errors}">
				<div class="alert alert-warning alert-dismissible" role="alert">
		  			<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		  			<strong>Login Error!</strong> ${error.message}
				</div>
			</c:forEach>
		</c:if>


		<form action="sign_up" name='signUpForm' method='POST' novalidate>
			<div class="col-md-8 col-md-offset-2" id="feedbackPanel" />
				
				<fieldset>

					<div class="col-xs-6">
						<div class="form-group">
							<label class="control-label">First Name</label>
							<input class="form-control" required type="text" autofocus name="firstName" value = <c:out value="${firstName}" /> >
						</div>
					</div>

					<div class="col-xs-6">
						<div class="form-group">
							<label class="control-label">Last Name</label>
							<input class="form-control" required type="text" name="lastName" value = <c:out value="${lastName}" /> >
						</div>
					</div>

					<div class="col-xs-12">
						<div class="form-group">
							<label class="control-label">Email</label>
							<input class="form-control" required type="email" name="email" value = <c:out value="${email}" /> >
						</div>
					</div>

					<div class="col-xs-12">
						<div class="form-group">
							<label class="control-label">Birth Date</label>
							<div class=" col-md-offset-2">
								<div class="col-xs-4">
									<select class="form-control" name="bDay" value = <c:out value="${bDay}" /> >
										<option value='empty' disabled selected>Day</option>
										<c:forEach var="i" begin="1" end="31">
										   <option value='${i}'>${i}</option>
									     </c:forEach>	
									</select>
								</div>
								
								<div class="col-xs-4">
									<select class="form-control" name="bMonth" value = <c:out value="${bMonth}" /> >
										<option value='empty' disabled selected>Month</option>
										<c:forEach var="i" begin="1" end="12">
										   <option value='${i}'>${i}</option>
									     </c:forEach>
									</select>
								</div>
								
								<div class="col-xs-4">
									<select class="form-control" name="bYear" value = <c:out value="${bYear}" />>
										<option value='empty' disabled selected>Year</option>
										<c:forEach var="i" begin="1930" end="2014">
										   <option value='${i}'>${i}</option>
									     </c:forEach>
									</select>
								</div>
							</div>
							<!-- <input class="form-control" required type="date" name="birthDate" value = <c:out value="${birthDate}" /> > --> <br/>
						</div>
					</div>

					<div class="col-xs-12">
						<div class="form-group">
							<label class="control-label">Password</label>
							<input class="form-control" required type="password" name="password"/>
						</div>
					</div>

					<div class="col-xs-12">
						<div class="form-group">
							<label class="control-label">Confirm your Password</label>
							<input class="form-control" required type="password" name="passwordConfirmation"/>
						</div>
					</div>

					<div class="col-xs-12">
						<div class="form-group">
							<label class="control-label">Secret Question</label>
							<input class="form-control" required type="text" name="secretQuestion" value = <c:out value="${secretQuestion}" /> >
						</div>
					</div>

					<div class="col-xs-12">
						<div class="form-group">
							<label class="control-label">Secret Answer</label>
							<input class="form-control" required type="text" name="secretAnswer" />
						</div>
					</div>


					<div class="col-md-8 col-md-offset-2">
						<button class="btn btn-primary btn-block" type="submit">Sign Up!</button>
						<a class="btn btn-default btn-block" href="/MoviesApp">Cancel</a>
					</div>

				</fieldset>
			</div>
		</form>
	
	</div>
	



<%@ include file="footer.jsp" %>
