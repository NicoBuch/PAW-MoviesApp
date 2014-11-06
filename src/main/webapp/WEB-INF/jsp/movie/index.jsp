<%@ include file="../header.jsp" %>


  <div class="jumbotron">
      <div class="col-md-offset-0">
        <h1 class="text-center">Welcome to our site.<br><small>A place for movie fans.</small></h1>
      </div>

  </div>

  <div class="row-fluid col-md-12">


    <div class="col-md-6 pull-left">
      <legend><h3>Weekly Releases</h3></legend>
      <c:choose>
        <c:when test="${!empty releases}">
          <div class="list-group">
            <c:forEach var="aMovie" items="${releases}">
             <a href="detail?id=${aMovie.id}" class="list-group-item ">
                <h4 class="text-center list-group-item-heading"><b><u>${aMovie.title}</u></b></h4>
                <p class="text-center list-group-item-text"><i>"${aMovie.shortDescription}"</i></p>
             </a>
            </c:forEach>
          </div>
        </c:when>
        <c:otherwise>
          <p>No weekly releases to show</p>
        </c:otherwise>
      </c:choose>
       
    </div>


  	<div class="col-md-6 pull-right">
  	 <legend><h3>Most Ranked Movies</h3></legend>
     <c:choose>
        <c:when test="${!empty ranked}">
          <div class="list-group">
           <c:forEach var="aMovie" items="${ranked}">
            <a class="list-group-item" href="detail?id=${aMovie.id}"><b>${aMovie.title}</b></a></li>
            </c:forEach>
          </div>
        </c:when>
        <c:otherwise>
          <p>No ranked movies to show</p>
        </c:otherwise>
      </c:choose>   
    </div>

    <div class="col-md-6 pull-left">
      <legend><h3>Recent Uploaded Movies</h3></legend>
      <c:choose>
        <c:when test="${!empty recents}">
          <div class="list-group">
            <c:forEach var="aMovie" items="${recents}">
              <a class="list-group-item" href="detail?id=${aMovie.id}"><u><b>${aMovie.title}</b></u></br>
                <b>Upload Date:</b> ${aMovie.creationDate}</br>
                <b>Comments:</b> ${aMovie.commentCount}
              </a>
            </c:forEach>
          </div>
        </c:when>
        <c:otherwise>
          <p>No recent uploads to show</p>
        </c:otherwise>
      </c:choose>      
    </div>

    <c:if test="${! empty user }">
      <div class="col-md-6 pull-right">
        <legend><h3>Users Of Interest</h3></legend>
        <c:choose>
        <c:when test="${!empty user.usersOfInterest}">
          <div class="list-group">
          <c:forEach var="aUser" items="${user.usersOfInterest}">
            <a class="list-group-item" href="../user/comments?user_id=${aUser.id}"><u><b>${aUser.email}</b></u>
            </a><br>
            <div class="list-group">
              <c:if test="${empty aUser.comments}">
                No comments to show
              </c:if>
              <c:forEach var="aComment" items="${aUser.comments}">
                <c:if test="${aComment.creationDate > aWeekBefore}">
                  <div class="list-group-item">
                    <blockquote class="blockquote-reverse">
                      <p>${aComment.body}</p>
                      <footer>Commented in <cite title="Source Title"><a href="detail?id=${aComment.movie.id}">${aComment.movie.title}</a></cite></footer>
                    </blockquote>
                  </div>
                </c:if>
              </c:forEach>
            </div> 
          </c:forEach>
        </div>
        </c:when>
        <c:otherwise>
          <p>No users to show</p>
        </c:otherwise>
      </c:choose>
        
      </div>
    </c:if>

  </div>

<%@ include file="../footer.jsp" %>
