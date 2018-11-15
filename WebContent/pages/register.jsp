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

<title>DAR - Register</title>
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link href="../css/sb-admin.css" rel="stylesheet">

<link rel="icon" type="image/png" href="images/icons/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="../vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="../fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<link rel="stylesheet" type="text/css"
	href="../vendor/animate/animate.css">
<link rel="stylesheet" type="text/css"
	href="../vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css"
	href="../vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css" href="../css/util.css">
<link rel="stylesheet" type="text/css" href="../css/main.css">
</head>


<body class="bg-dark">

	<div class="container-contact100"
		style="background-image: url('../img/animal_reg.jpg');">
		<div class="wrap-contact100">
			<form class="contact100-form validate-form" method="POST"
				action="user">
				<span class="contact100-form-title">
					<center>Animalia</center>
				</span>

				<div class="wrap-input100 rs1-wrap-input100 validate-input"
					data-validate="Name is required">
					<span class="label-input100">Nom *</span> <input class="input100"
						type="text" name="name" placeholder="Enter your name">
				</div>


				<div class="wrap-input100 rs1-wrap-input100 validate-input"
					data-validate="Valid email is required: ex@abc.xyz">
					<span class="label-input100">Email *</span> <input type="email"
						name="email" id="inputEmail" class="input100"
						placeholder="Enter your email">
				</div>

				<div class="wrap-input100 rs1-wrap-input100 validate-input">
					<span class="label-input100">Occupation *</span> <input
						class="input100" type="text" name="occupation"
						placeholder="Enter your Occupation">
				</div>
				<div class="wrap-input100 rs1-wrap-input100 validate-input">
					<span class="label-input100">Interest*</span> <input
						class="input100" type="text" name="interest"
						placeholder="Enter your interest">
				</div>
				<div class="wrap-input100 rs1-wrap-input100 validate-input">
					<span class="label-input100">Password *</span> <input
						class="input100" type="password" name="password"
						id="inputPassword" class="form-control"
						pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
						placeholder="Password (maj,min,number/char spec,8char)"
						required="required">
				</div>
				<div class="container-contact100-form-btn">
					<div class="wrap-contact100-form-btn">
						<div class="contact100-form-bgbtn"></div>
						<input type="submit" value="S'inscrire"
							class="btn btn-primary btn-block" />
					</div>
				</div>
				<div class="text-center">
					<a class="d-block small mt-3" href="login.jsp">Login Page</a>

				</div>



			</form>
		</div>
	</div>


	<!-- div class="container">
		<div class="card card-register mx-auto mt-5">
			<div class="card-header">Register an Account</div>
			<div class="card-body">
				<form method="POST" action="user">
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<div class="form-label-group">
									<input type="text" name="name" id="firstName"
										class="form-control" placeholder="First name"
										required="required" autofocus="autofocus"> <label
										for="firstName"> name</label>
								</div>
							</div>

						
				
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<div class="form-label-group">
									<input type="password" name="password" id="inputPassword"
										class="form-control" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" placeholder=""
										required="required"> <label for="inputPassword">Password (maj,min,number/char spec,8char)</label>
								</div>
							</div>
							
							</div>
				</div>
				</div>
				</div>
				

					<input type= "submit"  value="Register" class="btn btn-primary btn-block" />
				</form>
				<div class="text-center">
					<a class="d-block small mt-3" href="login.jsp">Login Page</a>

				</div>
			</div>
		</div>
	</div-->

	<!-- Bootstrap core JavaScript-->
	<script src="../vendor/jquery/jquery.min.js"></script>
	<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>
	<script src="../vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="../vendor/bootstrap/js/popper.js"></script>
	<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="../vendor/select2/select2.min.js"></script>
	<!--===============================================================================================-->
	<script src="../js/main.js"></script>

	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async
		src="https://www.googletagmanager.com/gtag/js?id=UA-23581568-13"></script>
	<script>
		window.dataLayer = window.dataLayer || [];
		function gtag() {
			dataLayer.push(arguments);
		}
		gtag('js', new Date());

		gtag('config', 'UA-23581568-13');
	</script>
</body>

</html>
