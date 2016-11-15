<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script src="//cdn.ckeditor.com/4.5.8/full/ckeditor.js"></script>
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
<
style>
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

img.img-circle {
	display: block;
	margin-left: auto;
	margin-right: auto
}

.pa {
	font-size: 150%;
	color: #0088cc;
}

.par {
	font-size: 120%;
	color: #000d1a;
}
</style>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		$("input[type=submit], a, button").button();
	});
	function deletePost(e) {
		http:
		//www.w3schools.com/ajax/default.asp
		var xmlhttp;
		if (window.XMLHttpRequest) {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {
			// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
				location.reload(true);
		}
		xmlhttp.open("DELETE", "post/delete" + e.target.parentNode.id, true);
		xmlhttp.send();

		$
				.ajax({
					url : "post/delete" + e.target.parentNode.id,
					method : "DELETE",
					success : function(result) {
						$("#messagesContainer")
								.prepend(
										"<div class='success'>Post successfully deleted</div>");
						setTimeout(function() {
							location.reload(true);
						}, 5000);
					}
				});
	}
</script>
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
						<li><a href="<c:url value="post"/>">Post</a></li>
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
						<li><a href="profile"><span
								class="glyphicon glyphicon-th-list"></span> Profile</a></li>
					</ul>
				</sec:authorize>
			</div>
		</div>
	</nav>

	<div class="col-sm-5"></div>
	<div class="well">
		<div class="col-sm-6">
					<form action=<c:url value="${Patron.id}"/> method="post">
						<fieldset>
							<legend>Please edit your Email</legend>
							<p>From: ${Creator.email}</p>

							<p>To: ${Patron.email}</p>
							<p>
								<label>Subject:</label> <br /> <input name="Subject"
									placeholder="Subject" />
							</p>
							<p>
								<label>Content:</label> <br />
								<textarea name="textarea" id="textarea"></textarea>
							</p>

							<p>
								<input type="submit" class="btn btn-lg btn-success" />
							</p>
						</fieldset>
					</form>
					<script>
						CKEDITOR.replace('textarea');
					</script>

				</div>
			</div>
		
	<div class="col-sm-1"></div>


	<script type="text/javascript">
		$(document).ready(function() {
			$("textarea").ckeditor();
			$("#datePicker").datepicker();
			$("#pointsPossible").spinner();
			$("input[type=submit], a, button").button();
		});
		
	</script>
	
</body>
</html>

