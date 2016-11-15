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
		     http://www.w3schools.com/ajax/default.asp
		var xmlhttp;
		if (window.XMLHttpRequest) {
			// code for IE7+, Firefox, Chrome, Opera, Safari
		  	xmlhttp=new XMLHttpRequest();
		}
		else { 
			// code for IE6, IE5
		  	xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
				location.reload(true);
		}
		xmlhttp.open("DELETE", "post/delete" + e.target.parentNode.id, true);
		xmlhttp.send();
		
		$.ajax({
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
						<li class="active"><a href="<c:url value="post"/>">Post</a></li>
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

	<div class="col-sm-1"></div>
	<div class="col-sm-4 well">
		<div class="well">
			<div class="pa">
				<p>My Status</p>
			</div>
			<img src="<c:url value="/photo/${user.userName}"/>"
				class="img-circle" alt="Cinque Terre" width="159" height="159" />
		</div>
		<div class="par">
			<c:if test="${not empty total}">
				<p>
					You have already received: <font color="#ff6666"><strong>$
							${total}</strong></font>
				</p>
			</c:if>
			<c:if test="${not empty count}">
				<p>
					You have Already posted: <font color="#ff6666"><strong>${count}
							works!</strong></font>
				</p>
			</c:if>
			<c:if test="${not empty countPatron}">
				<p>
					<font color="#ff6666"><strong>${countPatron}
							Patrons </strong></font> have supported you!
				</p>
			</c:if>
			<p><a class="btn btn-primary btn-success" href="<c:url value="/post/viewPatrons"/>">Contact My Patrons</a></p>
			<c:if test="${not empty myPostList}">
				<table class="table">

					<c:forEach items="${myPostList}" var="a">

						<tbody>
							<tr>
								<td>${a.title}</td>
								<td><a class="btn btn-primary btn-success" href="<c:url value="/explore/view/${a.postId}"/>">Open</a></td>
								<td><a class="btn btn-primary btn-primary" href="<c:url value="/post/delete/${a.postId}"/>">Delete</a></td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</c:if>



		</div>

	</div>

	<div class="col-sm-7">
		<div class="well">
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default text-left">
						<div class="panel-body">
							<form:form modelAttribute="post" action="post/new" method="post"
								enctype="multipart/form-data">
								<fieldset>
									<legend>What work do you want to post?</legend>
									<p>
										<form:label for="title" path="title" cssErrorClass="error">Title:</form:label>
										<br />
										<form:input path="title" placeholder="title" />
										<form:errors path="title" />
									</p>
									<p>
										<form:label for="Description" path="Description"
											cssErrorClass="error">Content:</form:label>
										<br />
										<form:textarea path="Description" name="textarea"
											id="textarea" />
										<form:errors path="Description" />
									</p>
									<p>
										<form:label for="photo" path="photo" cssErrorClass="error">Attachment:</form:label>
										<br />
										<form:input path="photo" type="file" />
										<form:errors path="photo" />
									</p>
									<p>
										<form:label for="videoLink" path="videoLink"
											cssErrorClass="error">YouTube Video:</form:label>
										<br />
										<form:textarea rows="4" cols="50" path="videoLink"
											placeholder="upload iframe here..." />
										<form:errors path="videoLink" />
									</p>
									<p>
										<input type="submit" class="btn btn-lg btn-success" />
									</p>
								</fieldset>
							</form:form>
							<script>
								CKEDITOR.replace('textarea');
							</script>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
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

