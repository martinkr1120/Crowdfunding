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
	float: right;
	padding-right: 315px;
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
					<li><a href="<c:url value="/explore"/>">Explore</a></li>
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
						<li><a href="/project/profile"><span
								class="glyphicon glyphicon-th-list"></span> Profile</a></li>
					</ul>
				</sec:authorize>
			</div>
		</div>
	</nav>
	<!-- main -->


	<div class="col-sm-3"></div>
	<div class="col-sm-8">
		<h1>${post.title}</h1>
		<dfn> Added Time: ${post.postDate} </dfn>
		<div class=t>
			<sec:authorize access="hasRole('ROLE_Patron')">
				<a class="btn btn-primary btn-success"
					href="<c:url value="/donate/${post.user.id}"/>">Donate!</a>
			</sec:authorize>
			<sec:authorize access="hasAnyRole('ROLE_Patron','ROLE_Creator')">

				<a class="btn btn-primary btn-warning"
					"
				href="<c:url value="/explore/view/${post.postId}/download"/>">Download</a>
			</sec:authorize>
		</div>
		<h2>Content:</h2>
		<p>${post.videoLink}</p>
		<article>${post.description}</article>
		<legend>Comments</legend>
		<c:if test="${not empty commentList}">
			<c:forEach items="${commentList}" var="c">
                  <p><img src="<c:url value="/photo/${c.user.userName}"/>"
				class="img-circle" alt="Cinque Terre" width="40" height="40" />  ${c.user.firstName} ${c.user.lastName} commented at ${c.commentDate}</p>
                  <table class="table">
                  <tbody>
                  <tr>
                  <td><p>${c.content}</p><td>
                  </tr>
                  </tbody>
                  </table>
			</c:forEach>
		</c:if>
		<sec:authorize access="hasRole('ROLE_Patron')">
			<form:form modelAttribute="comment" method="post">
				<fieldset>
					<legend>What do you think?</legend>
					<p>
						<form:label for="Content" path="Content" cssErrorClass="error">Comment:</form:label>
						<br />
						<form:textarea cols="50" path="Content" name="textarea"
							id="textarea" />
						<form:errors path="Content" />
					</p>
					<p>
						<input type="submit" class="btn btn-lg btn-success" />
					</p>
				</fieldset>
			</form:form>
		</sec:authorize>
	</div>
	<div class="col-sm-1"></div>






	<!-- Login Logo -->
	<div class="modal fade" id="login-modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" align="center">
					<img class="img-circle" id="img_logo"
						src="<c:url value="/resources/image/Login_Logo.jpg"/>">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
				</div>

				<!-- Begin # DIV Form -->
				<div id="div-forms">

					<!-- Begin # Login Form -->
					<form action="<c:url value="/j_spring_security_check"/>"
						method="POST">
						<div class="modal-body">
							<div id="div-login-msg">
								<div id="icon-login-msg"
									class="glyphicon glyphicon-chevron-right"></div>
								<span id="text-login-msg">Please Login</span>
							</div>
							<input name="username" class="form-control" type="text"
								placeholder="Username" required> <input name="password"
								class="form-control" type="password" placeholder="Password"
								required>
						</div>
						<div class="modal-footer">
							<div>
								<button type="submit" class="btn btn-primary btn-lg btn-block">Login</button>
							</div>
							<div>
								<a href="#" data-toggle="modal" data-target="#register-modal"><button
										id="login_register_btn" type="button" class="btn btn-link"
										data-dismiss="modal" aria-label="Close">Register</button></a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- registration form -->
	<!-- Register Logo -->

	<div class="modal fade" id="register-modal" tabindex="-1" role="dialog"
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
						<form:errors path="*">
							<div class="msg error">
								<h4>ATTENTION!</h4>
								<p>Please make the following correction(s) before
									proceeding.</p>
							</div>
						</form:errors>
						<!-- Begin # Login Form -->
						<form:form modelAttribute="user" action="register" method="post"
							enctype="multipart/form-data">
							<fieldset>
								<legend>Please Fill the Information</legend>
								<p>
									<form:label for="userName" path="userName"
										cssErrorClass="error">Username:</form:label>
									<br />
									<form:input path="userName" placeholder="Username" />
									<form:errors path="userName" />
								</p>
								<p>
									<form:label for="password" path="password"
										cssErrorClass="error">Password:</form:label>
									<br />
									<form:input type="PASSWORD" path="password"
										placeholder="Password" />
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
									<form:input path="firstName" placeholder="First Name" />
									<form:errors path="firstName" />
								</p>
								<p>
									<form:label for="lastName" path="lastName"
										cssErrorClass="error">Last Name:</form:label>
									<br />
									<form:input path="lastName" placeholder="Last Name" />
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
									<input type="submit" class="btn btn-lg btn-success" />
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