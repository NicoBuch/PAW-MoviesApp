<%@ include file="../header.jsp" %>

  <div class="page-header">
    <h2>Save Movie
      <br>
      <small>Please complete these fields to save a movie</small>
    </h2>
  </div>

  <div class="col-md-10 col-md-offset-1">

    <c:if test='${!empty errors}'>
      <c:forEach var="error" items="${errors}">
        <div class="alert alert-warning alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <strong>Movie Error!</strong> ${error.message}
        </div>
      </c:forEach>
    </c:if>


    <form:form action="edit" commandName='movieForm' method='POST' novalidate='novalidate'>

      <form:errors path="*"/>
      <form:hidden path="id"/>

      <div class="col-md-8 col-md-offset-2" id="feedbackPanel" />

        <fieldset>

          <div class="col-xs-12">
            <div class="form-group">
              <form:label class="control-label" path="title">Title</form:label>
              <form:input class="form-control" type="text" path="title" autofocus='autofocus' required='required'/>
            </div>
          </div>

          <div class="col-xs-12">
            <div class="form-group">
              <form:label class="control-label" path="director">Director</form:label>
              <form:input class="form-control" type="text" path="director" required='required'/>
            </div>
          </div>

          <div class="col-xs-12">
            <div class="form-group">
              <label class="control-label">Release Date</label>
              <div class=" col-md-offset-1">
                <div class="col-xs-4">
                  <form:select class="form-control" path="releaseDay">
                    <form:option value='0' disabled='disabled' selected='selected'>Day</form:option>
                    <c:forEach var="i" begin="1" end="31">
                       <form:option value='${i}'>${i}</form:option>
                       </c:forEach>
                  </form:select>
                </div>

                <div class="col-xs-4">
                  <form:select class="form-control" path="releaseMonth">
                    <form:option value='0' disabled='disabled' selected='selected'>Month</form:option>
                    <c:forEach var="i" begin="1" end="12">
                      <form:option value='${i}'>${i}</form:option>
                    </c:forEach>
                  </form:select>
                </div>

                <div class="col-xs-4">
                  <form:select class="form-control" path="releaseYear">
                    <form:option value='0' disabled='disabled' selected='selected'>Year</form:option>
                    <c:forEach var="i" begin="1930" end="2014">
                       <form:option value='${i}'>${i}</form:option>
                       </c:forEach>
                  </form:select>
                </div>
              </div>
              <br/>
            </div>
          </div>

          <div class="col-xs-12">
            <div class="form-group">
              <form:label class="control-label" path="minutes">Movie Length in Minutes</form:label>
              <form:input class="form-control" type="number" path="minutes" required='required'/>
            </div>
          </div>

          <div class="col-xs-12">
            <div class="form-group">
              <form:label class="control-label" path="description">Description</form:label>
              <form:textarea class="form-control" path="description" rows="6" cols="60" required='required'/>
            </div>
          </div>

          <div class="col-xs-12">
            <div class="form-group">
              <form:checkboxes element="div" path="genres" items="${genresList}" itemValue="id" itemLabel="name" />
            </div>
          </div>

          <div class="col-md-8 col-md-offset-2">
            <button class="btn btn-primary btn-block" type="submit">Save</button>
            <a class="btn btn-default btn-block" href="../movie/index">Cancel</a>
          </div>

        </fieldset>
      </div>
    </form:form>

  </div>




<%@ include file="../footer.jsp" %>
