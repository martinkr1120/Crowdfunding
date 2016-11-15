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
<script src="<c:url value='resources/bootstrap/js/jquery.min.js' />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>">
	
</script>
<link href="<c:url value="resources/bootstrap/css/bootstrap.min.css"/>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="resources/bootstrap/css/bootstrap-theme.min.css" />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="resources/bootstrap/css/bootstrap.css" />"
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

ul {
	text-align: center;
	list-style-type: none;
}
.logo{
font-style: oblique;
}
</style>
</head>
<body>

	<nav class="navbar navbar-inverse ">
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
						<li><a href="<c:url value="post"/>">Post</a></li>
					</sec:authorize>
					<li><a href="<c:url value="explore"/>">Explore</a></li>
					<li><a href="#">About</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
				<sec:authorize access="hasRole('ROLE_ANONYMOUS')">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#" data-toggle="modal"
							data-target="#login-modal"><span
								class="glyphicon glyphicon-log-in"></span> Login</a></li>
						<li><a href="#" data-toggle="modal"
							data-target="#register-modal"><span
								class="glyphicon glyphicon-registration-mark"></span> Register</a></li>
					</ul>
				</sec:authorize>
				<sec:authorize
					access="hasAnyRole('ROLE_Patron','ROLE_Creator','ROLE_ADMIN')">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="<c:url value="/j_spring_security_logout" />"><span
								class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
						<li class="active"><a href="profile"><span
								class="glyphicon glyphicon-th-list"></span> Profile</a></li>
					</ul>
				</sec:authorize>
			</div>
		</div>
	</nav>


	<div class="container">
		<div class="panel panel-default">
			<!-- Default panel contents -->

			<div class="col-sm-3"></div>

			<div class="col-sm-6">
				<div class="well">
					<div class="panel-heading">
						You Profile:
						<div class="btn-group pull-right">
							<a href="#" class="btn btn-sm btn-warning" data-toggle="modal"
								data-target="#modify-modal"><span
								class="glyphicon glyphicon-edit"></span> Modify</a>
						</div>
					</div>
					<div class="panel-body"></div>

					<!-- List group -->

					<ul class="list-group">
						<li><img src="<c:url value="/photo/${user.userName}"/>"
							class="img-rounded" alt="Cinque Terre" width="204" height="236" /></li>
						<li class="list-group-item">First Name: ${user.firstName}</li>
						<li class="list-group-item">Last Name: ${user.lastName}</li>
						<li class="list-group-item">Email Address: ${user.email}</li>
						<sec:authorize access="hasRole('ROLE_Patron')">
							<li class="list-group-item">You are a Patron!</li>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_Creator')">
							<li class="list-group-item">You are a Creator!</li>
						</sec:authorize>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-3"></div>

	<!-- update form -->
	<!-- update Logo -->

	<div class="modal fade" id="modify-modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" align="center">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
				</div>

				<!-- Begin # DIV Form -->
				<div id="div-forms">
					<div class=container>

						<!-- Begin # Login Form -->
						<c:url var="editAction" value="/profile/modify"></c:url>
						<form:form modelAttribute="user" method="post"
							action="${editAction}" commandName="user"
							enctype="multipart/form-data">
							<fieldset>
								<legend>Modify your profile</legend>
								<p>
									<form:label for="userName" path="userName"
										cssErrorClass="error">Username:</form:label>
									<br />
									<form:input path="userName" />
									<form:errors path="userName" />
								</p>
								<p>
									<form:label for="password" path="password"
										cssErrorClass="error">Password:</form:label>
									<br />
									<form:input type="PASSWORD" path="password" />
									<form:errors path="password" />
								</p>
								<p>
									<form:label for="photo" path="photo" cssErrorClass="error">Icon:</form:label>
									<br />
									<form:input path="photo" type="file" />
									<form:errors path="photo" />
								</p>
								<p>
									<form:label for="firstName" path="firstName"
										cssErrorClass="error">First Name:</form:label>
									<br />
									<form:input path="firstName" />
									<form:errors path="firstName" />
								</p>
								<p>
									<form:label for="lastName" path="lastName"
										cssErrorClass="error">Last Name:</form:label>
									<br />
									<form:input path="lastName" />
									<form:errors path="lastName" />
								</p>
								<p>
									<form:label for="email" path="email" cssErrorClass="error">Email Address:</form:label>
									<br />
									<form:input path="email" />
									<form:errors path="email" />
								</p>
								<p>
									<form:label for="Role" path="Role">You want to be a:</form:label>
									<br />
									<form:radiobutton value="ROLE_Creator" path="Role" />
									Creator
									<form:radiobutton value="ROLE_Patron" path="Role" />
									Patron
									<form:errors path="Role" />
								</p>
								<p>
									<input type="submit" class="btn btn-lg btn-success"
										value="modify" />
								</p>
							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
