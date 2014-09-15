<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>

	<h1>Movies App</h1>
	<h2>${movie.title}</h2>
	<!-- Movie Details -->
	<h3>Details</h3>

	<label><b>Genre:</b></label> ${movie.genre} <br>
	<label><b>Director:</b></label> ${movie.director} <br>
	<label><b>Release Date:</b></label> ${movie.releaseDate} <br>
	<label><b>Duration:</b></label> ${movie.minutes} minutes. <br>
	<label><b>Description:</b></label><br> 
	<p><i>"${movie.description}"</i></p>

	<!-- Show CommentForm -->
	<c:if test="${canComment}">
		<form name="commentForm" method="post">
		<input  type="hidden"name="movieId" value="${movie.id}"/>
		<label for='rating'>Rating</label>
		<select name='rating'>
			<c:forEach var="i" begin="0" end="5">
		   		<option value='${i}'>${i}</option>
			</c:forEach>
		</select>
		<br>
		<label for='body'>Comment</label><br/> <textarea name='body' rows="6" cols="60"></textarea> <button type='submit'>Comment</button> </form>
	</c:if>

	<!-- Comments Table -->
	<c:forEach var="aComment" items="${comments}">
		<p>
			${aComment.user.firstName} ${aComment.user.lastName} (${aComment.user.email}) said: <br>
			<label><b>Rating:</b></label> ${aComment.rating}
			<c:choose>
		      	<c:when test="${aComment.rating=='1'}"> star.</c:when>
	      		<c:otherwise> stars.</c:otherwise>
			</c:choose>
			<br/>
			<i>"${aComment.body}"</i>
		</p>
	</c:forEach>

</html>
