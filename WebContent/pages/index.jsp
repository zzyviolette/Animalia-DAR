<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Accueil</title>

<!-- Bootstrap core CSS -->

<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Varela+Round"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<link href="../css/grayscale.min.css" rel="stylesheet">
<link rel="stylesheet" href="../font-awesome/css/font-awesome.min.css">
</head>

<body id="page-top">

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-light fixed-top"
		id="mainNav">
		<div class="container">

			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="login.jsp">Se Connecter</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="register.jsp">S'inscrire</a></li>
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="#signup">Contactez Nous</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<header class="masthead">
		<div class="container d-flex h-100 align-items-center">
			<div class="mx-auto text-center">
				<h1 class="mx-auto my-0 text-uppercase">Animalia</h1>
				<h2 class="text-white-50 mx-auto mt-2 mb-5"></h2>
			</div>
		</div>
	</header>
	
	<%@ page import="java.util.List"%>
		<%@ page import="model.bo.Comment"%>
		<%@ page import="java.util.ArrayList"%>

		<%!List<Comment> comments = new ArrayList<Comment>();%>
		<%
			comments = (List<Comment>) request.getAttribute("listeComments");
		%>
	

	
<c:if test="<%=comments !=null && comments.size()>=1 %>">	
	<section>
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-6 order-lg-2">
					<div class="p-5">
						<img class="img-fluid rounded-circle"
							src="../assets/admin/layout/img/inconnu1.jpg" alt="">
					</div>
				</div>
				<div class="col-lg-6 order-lg-1">
					<div class="p-5">
						<h6 class="display-4">
							Commentaire :
							<br/>
							 <%=comments.get(0).getName()%>
						</h6>

						<p>
							<%=comments.get(0).getDescription()%>
						</p>
						<c:forEach var="i" begin="1" end="<%=comments.get(0).getMark()%>">
							<span class="fa fa-star checked"></span>
						</c:forEach>
						<c:forEach var="i" begin="<%=comments.get(0).getMark() + 1%>"
							end="5">
							<span class="fa fa-star"></span>
						</c:forEach>

					</div>
				</div>
			</div>
		</div>
	</section>
</c:if>

<c:if test="<%=comments !=null && comments.size()>=2 %>">	
	<section>
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-6">
					<div class="p-5">
						<img class="img-fluid rounded-circle"
							src="../assets/admin/layout/img/inconn2.jpg" alt="">
					</div>
				</div>
				<div class="col-lg-6">
					<div class="p-5">
						<h5 class="display-4">
							<small>Commentaire :
							<br/> <%=comments.get(1).getName()%> 
							</small>
						</h5>

						<p>
							<%=comments.get(1).getDescription()%>
</p>
							<c:forEach var="i" begin="1" end="<%=comments.get(1).getMark()%>">
								<span class="fa fa-star checked"></span>
							</c:forEach>
							<c:forEach var="i" begin="<%=comments.get(1).getMark() + 1%>"
								end="5">
								<span class="fa fa-star"></span>
							</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</section>
</c:if>
<c:if test="<%=comments !=null && comments.size()>=3 %>">	
	<section>
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-6 order-lg-2">
					<div class="p-5">
						<img class="img-fluid rounded-circle"
							src="../assets/admin/layout/img/inconn2.jpg" alt="">
					</div>
				</div>
				<div class="col-lg-6 order-lg-1">
					<div class="p-5">

						<h5 class="display-4">
							Commentaire :
							<br/><%=comments.get(2).getName()%>
						
							</h6>
							<p>
								<%=comments.get(2).getDescription()%>
							</p>
							<c:forEach var="i" begin="1" end="<%=comments.get(2).getMark()%>">
								<span class="fa fa-star checked"></span>
							</c:forEach>
							<c:forEach var="i" begin="<%=comments.get(2).getMark() + 1%>"
								end="5">
								<span class="fa fa-star"></span>
							</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</section>
	</c:if>
<br/>
	<!-- Contact Section -->
	<section id="signup">
		<div class="container wow fadeInUp">
			<div class="section-header">
				<center>
					<h3 class="section-title">Contactez Nous <h3>
				</center>
				<p class="section-description">Pour nous contacter, pour vous en donner plus
informations, répondez à toutes vos questions et créez une
solution efficace pour vos besoins </p>
			</div>
		</div>
		<div class="container wow fadeInUp">
			<div class="row justify-content-center">

				<div class="col-lg-3 col-md-4">

					<div class="info">
						<div>
							<i class="fa fa-map-marker"></i>
							<p>
								4 Place Jussieu<br>Sorbonne Université, 75005
							</p>
						</div>

						<div>
							<i class="fa fa-envelope"></i>
							<p>
								benaddisoukaina@gmail.com 
								<br> louahdiasma@gmail.com 
								<br> Ziyizhou@gmail.com
							</p>
						</div>

						<div>
							<i class="fa fa-phone"></i>
							<p>+33623115060</p>
						</div>
					</div>
					<div class="social-links">
						<a href="#" class="facebook"><i class="fa fa-facebook"></i></a> <a
							href="#" class="instagram"><i class="fa fa-instagram"></i></a> <a
							href="#" class="google-plus"><i class="fa fa-google-plus"></i></a>
						<a href="#" class="linkedin"><i class="fa fa-linkedin"></i></a>
					</div>
				</div>
				<div class="col-lg-5 col-md-8">
					<div class="form">

						<div id="errormessage"></div>
						<form action="mail" method="post" role="form" class="contactForm">
							<div class="form-group">
								<input type="text" name="name" class="form-control" id="name"
									placeholder="Your Name" data-rule="minlen:4"
									data-msg="Please enter at least 4 chars" />
								<div class="validation"></div>
							</div>
							<div class="form-group">
								<input type="email" class="form-control" name="email" id="email"
									placeholder="Your Email" data-rule="email"
									data-msg="Please enter a valid email" />
								<div class="validation"></div>
							</div>
							<div class="form-group">
								<input type="text" class="form-control" name="subject"
									id="subject" placeholder="Subject" data-rule="minlen:4"
									data-msg="Please enter at least 8 chars of subject" />
								<div class="validation"></div>
							</div>
							<div class="form-group">
								<textarea class="form-control" name="content" rows="5"
									data-rule="required" data-msg="Please write something for us"
									placeholder="Message"></textarea>
								<div class="validation"></div>
							</div>
							<div class="text-center">

								<button class="col-md-12  btn btn-primary" type="submit">Envoyer
									Message</button>
							</div>
						</form>
					</div>
				</div>

			</div>

		</div>
	</section>
	<!-- #contact -->
<br/>
	<!-- Footer -->
	<footer class="bg-black small text-center text-white-50">
		SU,M2 STL 2018_2019 </footer>

	<script src="../vendor/jquery/jquery.min.js"></script>
	<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>
	<script src="../js/grayscale.min.js"></script>

</body>

</html>
