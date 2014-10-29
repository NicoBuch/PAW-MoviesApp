<%@ include file="../header.jsp" %>

  <div class="page-header">
    <h2>Edit
      <br>
      <small>Please complete the fields you would like to edit</small>
    </h2>
  </div>

  <div class="col-md-10 col-md-offset-1">

    <c:if test='${!empty errors}'>
      <c:forEach var="error" items="${errors}">
        <div class="alert alert-warning alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <strong>Edition Error!</strong> ${error.message}
        </div>
      </c:forEach>
    </c:if>


    <form action="edit" method=POST novalidate>
      <div class="col-md-8 col-md-offset-2" id="feedbackPanel" />

        <fieldset>
          <input type="hidden" name="id" value = "<c:out value="${movie.id}" />" />
          <div class="col-xs-12">
            <div class="form-group">
              <label class="control-label">Title</label>
              <input class="form-control" required type="text" autofocus name="title" value = "<c:out value="${movie.title}" />" >
            </div>
          </div>


          <div class="col-xs-12">
            <div class="form-group">
              <label class="control-label">Release Date</label>
              <div class=" col-md-offset-2">
                <div class="col-xs-4">
                  <select class="form-control" name="releaseDay">
                    <option value='empty' disabled selected>Day</option>
                    <c:forEach var="i" begin="1" end="31">
                      <c:choose>
                        <c:when test="${movie.releaseDate.date == i}">
                          <option value='${i}'selected="selected">${i}</option>
                        </c:when>
                        <c:otherwise>
                          <option value='${i}'>${i}</option>
                        </c:otherwise>
                      </c:choose>
                    </c:forEach>
                  </select>
                </div>

                <div class="col-xs-4">
                  <select class="form-control" name="releaseMonth" >
                    <option value='empty' disabled selected>Month</option>
                    <c:forEach var="i" begin="1" end="12">
                      <c:choose>
                        <c:when test="${movie.releaseDate.month == i}">
                          <option value='${i}'selected="selected">${i}</option>
                        </c:when>
                        <c:otherwise>
                          <option value='${i}'>${i}</option>
                        </c:otherwise>
                      </c:choose>
                    </c:forEach>
                  </select>
                </div>

                <div class="col-xs-4">
                  <select class="form-control" name="releaseYear">
                    <option value='empty' disabled selected>Year</option>
                    <c:forEach var="i" begin="1930" end="2015">
                      <c:choose>
                        <c:when test="${movie.releaseDate.year + 1900 == i}">
                          <option value='${i}'selected="selected">${i}</option>
                        </c:when>
                        <c:otherwise>
                          <option value='${i}'>${i}</option>
                        </c:otherwise>
                      </c:choose>
                    </c:forEach>
                  </select>
                </div>
              </div>
            </div>
          </div>

          <div class="col-xs-12">
            <div class="form-group">
              <label class="control-label">Director</label>
              <input class="form-control" required type="text" name="director" value = "<c:out value="${movie.director}" />" >
            </div>
          </div>

          <div class="col-xs-12">
            <div class="form-group">
              <label class="control-label">Movie Length in minutes</label>
              <input class="form-control" required type="text" name="minutes" value = "<c:out value="${movie.minutes}" />" >
            </div>
          </div>

          <div class="col-xs-12">
            <div class="form-group">
              <label class="control-label">Description</label>
              <input class="form-control" required type="text" name="description" value = "<c:out value="${movie.description}" />" >
            </div>
          </div>

      <div class="col-xs-12">
        <label class="label-control">Genres: </label><br>
        <c:forEach var="aGenre" items="${genres}">
          <c:set var="contains" value="false" />
          <c:forEach var="moviesGenre" items="${movie.genres}">
            <c:if test="${aGenre eq moviesGenre}">
              <input style="margin: 2px;" type="checkbox" name="genres" value='${aGenre.id}' checked>${aGenre.name}</input>
              <c:set var="contains" value="false" />
            </c:if>
          </c:forEach>
            <c:if test="${contains eq false}">
              <input style="margin: 2px;" type="checkbox" name="genres" value='${aGenre.id}'>${aGenre.name}</input>
            </c:if>
        </c:forEach>
      </div>


      <div class="col-md-8 col-md-offset-2">
        <button class="btn btn-primary btn-block" type="submit">Edit</button>
        <a class="btn btn-default btn-block" href="./list">Cancel</a>
      </div>

        </fieldset>
      </div>
    </form>

  </div>





<%@ include file="../footer.jsp" %>
