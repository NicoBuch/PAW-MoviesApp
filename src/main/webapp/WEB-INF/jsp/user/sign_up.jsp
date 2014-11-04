<%@ include file="../header.jsp" %>


	<div class="page-header">
		<h2>Register
			<br>
			<small>Please complete these fields to sign up</small>
		</h2>	
	</div>

	<div class="col-md-10 col-md-offset-1">

		<c:if test="${!empty errors}">
			<c:forEach var="error" items="${errors}">
				<div class="alert alert-warning alert-dismissible" role="alert">
		  			<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		  			<strong>Login Error!</strong> ${error.message}
				</div>
			</c:forEach>
		</c:if>


		<form:form action="sign_up" commandName="signUpForm" method="POST" novalidate="novalidate">
			

			<div class="col-md-8 col-md-offset-2" id="feedbackPanel" />
				
				<fieldset>

					<div class="col-xs-6">
						<div class="form-group">
							<form:label class="control-label" path="firstName">First Name</form:label>
							<span class="help-block"><form:errors class="text-danger" path="firstName"/></span>
							<form:input class="form-control" type="text" path="firstName" autofocus="autofocus" required="required"/>
						</div>
					</div>

					<div class="col-xs-6">
						<div class="form-group">
							<form:label class="control-label" path="lastName">Last Name</form:label>
							<span class="help-block"><form:errors class="text-danger" path="lastName"/></span>
							<form:input class="form-control" type="text" path="lastName" required="required"/>
						</div>
					</div>

					<div class="col-xs-12">
						<div class="form-group">
							<form:label class="control-label" path="email">Email</form:label>
							<span class="help-block"><form:errors class="text-danger" path="email"/></span>
							<form:input class="form-control" type="email" path="email" required="required"/>
						</div>
					</div>

					<div class="col-xs-12">
						<div class="form-group">
							<label class="control-label">Birth Date</label>
							<span class="help-block"><form:errors class="text-danger" path="birthDay"/></span>
							<div class=" col-md-offset-0">
								<div class="col-xs-4">
									<form:select class="form-control" path="birthDay">
										<form:option value="0" disabled="disabled" selected="selected">Day</form:option>
										<c:forEach var="i" begin="1" end="31">
										   <form:option value="${i}">${i}</form:option>
									     </c:forEach>	
									</form:select>
								</div>
								
								<div class="col-xs-4">
									<form:select class="form-control" path="birthMonth">
										<form:option value="0" disabled="disabled" selected="selected">Month</form:option>
										<c:forEach var="i" begin="1" end="12">
										   <form:option value="${i}">${i}</form:option>
									     </c:forEach>
									</form:select>
								</div>
								
								<div class="col-xs-4">
									<form:select class="form-control" path="birthYear">
										<form:option value="0" disabled="disabled" selected="selected">Year</form:option>
										<c:forEach var="i" begin="1930" end="2014">
										   <form:option value="${i}">${i}</form:option>
									     </c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<br><br>
					</div>

					<div class="col-xs-12">
						<div class="form-group">
							<form:label class="control-label" path="password">Password</form:label>
							<span class="help-block"><form:errors class="text-danger" path="password"/></span>
							<form:input class="form-control" type="password" path="password" required="required"/>
						</div>
					</div>

					<div class="col-xs-12">
						<div class="form-group">
							<form:label class="control-label" path="confirmPassword">Confirm your Password</form:label>
							<span class="help-block"><form:errors class="text-danger" path="confirmPassword"/></span>
							<form:input class="form-control" type="password" path="confirmPassword" required="required"/>
						</div>
					</div>

					<div class="col-xs-12">
						<div class="form-group">
							<form:label class="control-label" path="secretQuestion">Secret Question</form:label>
							<span class="help-block"><form:errors class="text-danger" path="secretQuestion"/></span>
							<form:input class="form-control" type="text" path="secretQuestion" required="required" />
						</div>
					</div>

					<div class="col-xs-12">
						<div class="form-group">
							<form:label class="control-label" path="secretAnswer">Secret Answer</form:label>
							<span class="help-block"><form:errors class="text-danger" path="secretAnswer"/></span>
							<form:input class="form-control" type="text" path="secretAnswer" required="required"/>
						</div>
					</div>


					<div class="col-md-8 col-md-offset-2">
						<button class="btn btn-primary btn-block" type="submit">Sign Up!</button>
						<a class="btn btn-default btn-block" href="../movie/index">Cancel</a>
					</div>

				</fieldset>
			</div>
		</form:form>
	
	</div>
	



<%@ include file="../footer.jsp" %>
