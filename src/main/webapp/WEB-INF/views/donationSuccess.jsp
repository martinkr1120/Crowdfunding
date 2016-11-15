<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to CrowdFunding Playground!</title>
<script src="<c:url value='/resources/bootstrap/js/jquery.min.js' />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>">
	
</script>
<link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="/resources/bootstrap/css/bootstrap-theme.min.css" />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />"
	rel="stylesheet" type="text/css" />
<style>
/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

/* Add a gray background color and some padding to the footer */
footer {
	background-color: #75a3a3;
	padding: 25px;
}

.carousel-inner img {
	width: 100%; /* Set width to 100% */
	margin: auto;
	min-height: 200px;
}

/* Hide the carousel text when the screen is less than 600 pixels wide */
@media ( max-width : 600px) {
	.carousel-caption {
		display: none;
	}
}

.pleft {
	text-align: left;
	font-size: 150%
}

h3 {
	color: #666699;
}

.logo {
	font-style: oblique;
	text-decoration: underline;
}

.floatT {
	position: relative;
}

.t {
	padding-top: 500px;
}
</style>
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<div class=logo>
					<a class="navbar-brand" href="<c:url value="/"/>"><Strong>CrowdFunding</Strong></a>
				</div>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="<c:url value="/"/>">Home</a></li>
					<sec:authorize access="hasRole('ROLE_Creator')">
						<li><a href="<c:url value="/post"/>">Post</a></li>
					</sec:authorize>
					<li class="active"><a href="<c:url value="/explore"/>">Explore</a></li>
					<li><a href="#">About</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
				<sec:authorize
					access="hasAnyRole('ROLE_Patron','ROLE_Creator','ROLE_ADMIN')">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="<c:url value="/j_spring_security_logout" />"><span
								class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
						<li><a href="profile"><span
								class="glyphicon glyphicon-th-list"></span> Profile</a></li>
					</ul>
				</sec:authorize>
			</div>
		</div>
	</nav>

	<!-- main -->
	<p></p>
	<p></p>
	<p></p>
	<p></p>
	<p></p>
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<img src="<c:url value="/resources/image/success.jpg"/>"
			alt="Image" class="img-rounded" alt="Cinque Terre" width="304" height="236">
		<h3>
				Thank you for your contribution!<br>
				After your donation, 
		</h3>

		<div class="col-sm-2"></div>



	</div>
	
</body>
</html>