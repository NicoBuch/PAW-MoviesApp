<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>

	<h1>Movies App</h1>
	<h2>Login</h2>

	<!-- Filter by genre Form -->
	<form name="loginForm" method='post'>
		<label for='email'>Username</label>
		<input name='email' type='email'placeholder='user@example.com'/><br>
		<label for='password'>Password</label>
		<input name='password' type='password' placeholder='Insert password'><br>
		<button type="submit">Log in</button>${errorMessage}
	</form>


</html>