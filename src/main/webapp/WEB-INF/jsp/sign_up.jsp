<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head><title>MoviesApp</title></head>
	<body>


	<h1>Please complete these fields to sign up</h1>


	<h6><c:out value="${error.message}" /></h6>

	<form action="sign_up" method=POST>  
		First Name
		<input required=true type="text" name="firstName" value = <c:out value="${user.firstName}" /> ><br/> 
		Last Name
		<input required=true type="text" name="lastName" value = <c:out value="${user.lastName}" /> ><br/>  
		Email
		<input required=true type="email" name="email" value = <c:out value="${user.email}" /> ><br/>  
		Password
		<input type="password" name="password"/><br/>
		Confirm your Password
		<input type="password" name="password_confirmation"/><br/>   
		Birth Date
		<input required=true type="date" name="birthDate" value = <c:out value="${user.email}" /> > <br/> 
		<br/>
		<input type="submit" value="Sign Up!"/>  
	</form>  
	
</body>	
</html>