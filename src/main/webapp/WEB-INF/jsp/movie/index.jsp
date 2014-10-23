<%@ include file="../header.jsp" %>


  <div class="jumbotron">
      <div class="col-md-offset-0">
        <h1 class="text-center">Welcome to our site.<br><small>A place for movie fans.</small></h1>
      </div>

  </div>


  <div class="col-md-10 col-md-offset-1">
     <h2 class="col-md-offset-0 text-center">Weekly Releases</h2>
     <div class="list-group">
      <c:forEach var="aMovie" items="${releases}">
       <a href="detail?id=${aMovie.id}" class="list-group-item ">
          <h4 class="text-center list-group-item-heading"><b><u>${aMovie.title}</u></b></h4>
          <p class="text-center list-group-item-text"><i>"${aMovie.description}"</i></p>
       </a>

      </c:forEach>
    </div>
  </div>


	<div class="col-md-5 col-md-offset-0">
		<legend><h3>Most Ranked Movies</h3></legend>
		<div class="list-group">
		  <c:forEach var="aMovie" items="${ranked}">
	        <a class="list-group-item" href="detail?id=${aMovie.id}"><b>${aMovie.title}</b></a></li>
    	  </c:forEach>
    </div>
  </div>

  <div class="col-md-5 col-md-offset-2">
    <legend><h3>Recent Uploaded Movies</h3></legend>
    <div class="list-group">
      <c:forEach var="aMovie" items="${recents}">
        <a class="list-group-item" href="detail?id=${aMovie.id}"><u><b>${aMovie.title}</b></u></br>
          <b>Upload Date:</b> ${aMovie.creationDate}</br>
          <b>Comments:</b> ${aMovie.commentCount}
        </a>
      </c:forEach>
    </div>
  </div>

<%@ include file="../footer.jsp" %>
