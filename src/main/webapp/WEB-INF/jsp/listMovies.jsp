<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>

<h1>Movies App</h1>
<h2>List Movies</h2>

<!-- Filter by genre Form -->
<form name="filterByGenreForm" method='get'>
	<select name='genre'>
		<c:forEach var="aGenre" items="${genres}">
		   <option value='${aGenre}'>${aGenre}</option>     	   
	     </c:forEach>	  
	</select>
	<button type="submit">Filtrar</button>
</form>
<form name="filterByDirectorForm" method='get'>
	Director:
	<input  type="text" name="director"> <button type="submit">Filtrar</button> <br/> 
	
</form>


<!-- Movies Table -->
<table>
	<thead>
	     <tr>
	            <th>Title</th>
	            <th>Director</th>
	 			<th>Release Date</th>
	 	</tr>
	 </thead>
	<c:forEach var="aMovie" items="${movies}">
	    <tr>
	    	<td><a href="movie?id=${aMovie.id}">${aMovie.title}</a></td>
	    	<td>${aMovie.director}</td>
	    	<td>${aMovie.releaseDate}</td>	    	
	    </tr>        	   
     </c:forEach>
</table>



</html>
