<%@ include file="../header.jsp" %>


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


		<form:form action="sign_up" commandName='signUpForm' method='POST' novalidate='novalidate'>
			
			<form:errors path="*"/>

			<div class="col-md-8 col-md-offset-2" id="feedbackPanel" />
				
				<fieldset>

					<div class="col-xs-6">
						<div class="form-group">
							<form:label class="control-label" path="firstName">First Name</form:label>
							<form:input class="form-control" type="text" path="firstName" autofocus='autofocus' required='required'/>
						</div>
					</div>

					<div class="col-xs-6">
						<div class="form-group">
							<form:label class="control-label" path="lastName">Last Name</form:label>
							<form:input class="form-control" type="text" path="lastName" required='required'/>
						</div>
					</div>

					<div class="col-xs-12">
						<div class="form-group">
							<form:label class="control-label" path="email">Email</form:label>
							<form:input class="form-control" type="email" path="email" required='required'/>
						</div>
					</div>

					<div class="col-xs-12">
						<div class="form-group">
							<label class="control-label">Birth Date</label>
							<div class=" col-md-offset-2">
								<div class="col-xs-4">
									<form:select class="form-control" path="birthDay">
										<form:option value='empty' disabled='disabled' selected='selected'>Day</form:option>
										<c:forEach var="i" begin="1" end="31">
										   <form:option value='${i}'>${i}</form:option>
									     </c:forEach>	
									</form:select>
								</div>
								
								<div class="col-xs-4">
									<form:select class="form-control" path="birthMonth">
										<form:option value='empty' disabled='disabled' selected='selected'>Month</form:option>
										<c:forEach var="i" begin="1" end="12">
										   <form:option value='${i}'>${i}</form:option>
									     </c:forEach>
									</form:select>
								</div>
								
								<div class="col-xs-4">
									<form:select class="form-control" path="birthYear">
										<form:option value='empty' disabled='disabled' selected='selected'>Year</form:option>
										<c:forEach var="i" begin="1930" end="2014">
										   <form:option value='${i}'>${i}</form:option>
									     </c:forEach>
									</form:select>
								</div>
							</div>
							<br/>
						</div>
					</div>

					<div class="col-xs-12">
						<div class="form-group">
							<form:label class="control-label" path="password">Password</form:label>
							<form:input class="form-control" type="password" path="password" required='required'/>
						</div>
					</div>

					<div class="col-xs-12">
						<div class="form-group">
							<form:label class="control-label" path="confirmPassword">Confirm your Password</form:label>
							<form:input class="form-control" type="password" path="confirmPassword" required='required'/>
						</div>
					</div>

					<div class="col-xs-12">
						<div class="form-group">
							<form:label class="control-label" path="secretQuestion">Secret Question</form:label>
							<form:input class="form-control" type="text" path="secretQuestion" required='required' />
						</div>
					</div>

					<div class="col-xs-12">
						<div class="form-group">
							<form:label class="control-label" path="secretAnswer">Secret Answer</form:label>
							<form:input class="form-control" type="text" path="secretAnswer" required='required'/>
						</div>
					</div>


					<div class="col-md-8 col-md-offset-2">
						<button class="btn btn-primary btn-block" type="submit">Sign Up!</button>
						<a class="btn btn-default btn-block" href="/MoviesApp">Cancel</a>
					</div>

				</fieldset>
			</div>
		</form:form>
	
	</div>
	



<%@ include file="../footer.jsp" %>
