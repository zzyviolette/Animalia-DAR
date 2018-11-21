<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>DAR _ Login</title>
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link href="../css/sb-admin.css" rel="stylesheet">

</head>

<body class="bg-dark">

	<div class="container">
		<div class="card card-login mx-auto mt-5">
			<div class="card-header">Login</div>
			<div class="card-body">
				<form method="POST" action="login">
					<div class="form-group">
						<div class="form-label-group">
							<input type="email" name="email" id="email" class="form-control"
								placeholder="Email address" required="required"
								autofocus="autofocus"> <label for="email">Email
								address</label>
						</div>
					</div>
					<div class="form-group">
						<div class="form-label-group">

							<input type="password" name="password" id="password"
								class="form-control"
								placeholder="Uper,lower number/special char 8 char"
								required="required"> <label for="password">Password
							</label>
						</div>
					</div>
					<div class="form-group">
						<div class="checkbox">
							<label> <input type="checkbox" value="remember-me">
								Conserver Password
							</label>
						</div>
					</div>
					<input class="btn btn-primary btn-block" type="submit"
						value="Login" />

				</form>
				<div class="text-center">
					<a class="d-block small mt-3" href="register.jsp">Registrer un compte</a>

				</div>
			</div>
		</div>
	</div>
	<script src="../vendor/jquery/jquery.min.js"></script>
	<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

</body>

</html>
