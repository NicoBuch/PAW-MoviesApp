<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>


<h1>Movies App</h1>
<h2>Recovery</h2>
<c:if test="${empty email}">
	<form name="recoveryForm" method='post'>
		<label for='email'>Email</label>
		<input name='email' type='email'placeholder='user@example.com'/><br>
		<button type="submit">Submit</button>${errorMessage}
	</form>
</c:if>
<br/>
<c:if test="${!empty email}">
	<div>${email}</div>
</c:if>
<c:if test="${!empty question}">
	<form name="answerForm" method='post'>
		<div>${question}</div>
		<label for='answer'>Answer</label>
		<input name='answer' type='answer'/><br>
		<label for='newPassword'>New Password</label>
		<input name='newPassword' type='password'/><br>
		<label for='newPasswordConfirmation'>Confirm Password</label>
		<input name='newPasswordConfirmation' type='password'/><br>
		<input name='email' type="hidden" value="${email}"/>
		<button type="submit">Submit</button>${errorMessage}
	</form>
</c:if>


</html>