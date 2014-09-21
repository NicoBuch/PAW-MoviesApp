<%@ include file="header.jsp" %>


<div class="page-header">
	<h2>Recovery
	<br>
	<small>Follow the steps to reset your password</small>
	</h2>	
</div>

<div class="col-md-10 col-md-offset-1">

	<c:if test="${!empty errorMessage}">
		<div class="alert alert-warning alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			<strong>Login Error!</strong> ${errorMessage}
		</div>
	</c:if>

	<c:if test="${empty email}">
		
		<form name="recoveryForm" method='post'>
			<div class="col-md-8 col-md-offset-2" id="feedbackPanel" />
				<div class="col-xs-12">
					<div class="form-group">
						<label class="control-label">Email</label>
						<input class="form-control" name='email' type='email' placeholder='user@example.com'/>
					</div>
				</div>
			
				<div class="col-md-6 col-md-offset-6 ">
					<button class="btn btn-primary btn-block" type="submit">Next Step<span class="pull-right glyphicon glyphicon-chevron-right"></span></button>
				</div>
			</div>
		</form>

	</c:if>

	<c:if test="${!empty email}">

		<div>
				<h4>Reseting password for: <span class="label label-primary">${email}</span></h4>
				<br><br>
		</div>
	</c:if>

	<c:if test="${!empty question}">
		
		<form name="answerForm" method='post'>
			
			<div class="col-md-8 col-md-offset-2" id="feedbackPanel" />

				<div class="col-xs-12">
					<div class="form-group">
						<label class="control-label">Question</label>
						<h4><span class="label label-info">${question}</label></span></h4>
					</div>
				</div>

				<div class="col-xs-12">
					<div class="form-group">
						<label class="control-label">Answer</label>
						<input class="form-control" name='answer' type='answer'/>
					</div>
				</div>
					
				<div class="col-xs-12">
					<div class="form-group">
						<label class="control-label">New Password</label>
						<input class="form-control" name='newPassword' type='password'/>
					</div>
				</div>
					
				<div class="col-xs-12">
					<div class="form-group">
						<label class="control-label">Confirm Password</label>
						<input class="form-control" name='newPasswordConfirmation' type='password'/>
					</div>
				</div>
			

				<input name='email' type="hidden" value="${email}"/>
				
				<div class="col-md-6 col-md-offset-6 ">
					<button class="btn btn-success btn-block" type="submit">Reset Password<span class="pull-right glyphicon glyphicon-ok"></span></button>
				</div>
			</div>
		</form>

	</c:if>
</div>


<%@ include file="footer.jsp" %>