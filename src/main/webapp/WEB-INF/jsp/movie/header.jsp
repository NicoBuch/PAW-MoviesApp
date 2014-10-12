<!--PAGE PROPERTIES -->
<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<!-- TAGLIB INCLUDES -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- HTML -->

<html>
	<head>
		<title>ITBA-MoviesApp</title>
		<!--
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
		<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.css"/>
		-->

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
		
			
	<!-- CONTENT BEGINS -->
	<body class="col-md-12">
		<!-- NAVIGATION BAR -->fo
		<nav class="navbar navbar-inverse" role="navigation">
		  	<div class="container-fluid">
			    <!-- Brand and toggle get grouped or better mobile display -->
			    <div class="navbar-header">
				    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				    <span class="sr-only">Toggle navigation</span>
				    <span class="icon-bar"></span>
				    <span class="icon-bar"></span>
				    <span class="icon-bar"></span>
				    </button>
				    <a class="navbar-brand" href="/MoviesApp">Movies App</a>
				</div>

			    <!-- Collect the nav links, forms, and other content for toggling -->
			    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				    <ul class="nav navbar-nav">
					    <li><a href="/MoviesApp/bin/movie/list">List Movies</a></li>
					</ul>
				    <ul class="nav navbar-nav navbar-right">
				    	<c:choose>							
							<c:when test='${empty user || user.email==""}'>
								<!-- User is not logged in -->
								<li><a href="/MoviesApp/bin/user/sign_in">Login</a></li>
						    	<li><a href="/MoviesApp/bin/user/sign_up">Register</a></li>
						    </c:when>						    
					    	<c:otherwise>
					    		<!-- User is logged in -->
							    <li class="dropdown">
								    <a href="#" class="dropdown-toggle" data-toggle="dropdown">${user.email}<span class="caret"></span></a>
								    <ul class="dropdown-menu" role="menu">
									    <li><a href="comments?user_id=${user.id}">My comments</a></li>
									    <li><a href="/MoviesApp/bin/user/recovery">Reset password</a></li>
									    <li class="divider"></li>
									    <li><a href="/MoviesApp/bin/user/sign_out">Logout</a></li>
								    </ul>
							    </li>
							</c:otherwise>
						</c:choose>
				    </ul>
		  	</div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>

		<div class="container col-md-8 col-md-offset-2">
		
		
		
