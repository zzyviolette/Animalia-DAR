<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<!-- Title -->


<!-- Favicon -->

<link rel="icon" href="../img/core-img/favicon.ico">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!-- Style CSS -->
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/annonce.css">

</head>


	<input id="even" type="hidden" name="action" value="display_all" />
	<input id="myeven" type="hidden" name="action" value="display_mine" />

	<!-- Preloader -->
	<div id="preloader">
		<div class="preload-content">
			<div id="original-load"></div>
		</div>
	</div>

	<!-- Subscribe Modal -->


	<!-- ##### Header Area Start ##### -->

	<!-- ##### Header Area End ##### -->

	<!-- ##### Breadcumb Area Start ##### -->
	<div class="breadcumb-area bg-img"
		style="background-image: url(../img/myevents.jpg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="breadcumb-content text-center">
						<h2>Mes evénements</h2>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ##### Breadcumb Area End ##### -->

	<!-- ##### Blog Wrapper Start ##### -->

	<!-- ##### Blog Wrapper End ##### -->

	<!-- ##### Cool Facts Area Start ##### -->

	<!-- ##### Cool Facts Area End ##### -->

	<!-- ##### Blog Wrapper Start ##### -->
		<div class="container">
			<button style='margin-bottom: 20px;' class='button'
				onclick='afficher_form()'>+ evenement</button>
			<form id="new_event" action="event" method="POST"
				enctype="multipart/form-data"
				style="display: none; margin-bottom: 20px;">
				<input type="hidden" name="action" value="add_event" />

				<div class="row">

					<div class="col-12 col-md-6">
						<div class="group">
							<input type="text" name="title" required> <span
								class="highlight"></span> <span class="bar"></span> <label>Titre</label>
						</div>
					</div>
					<div class="col-12 col-md-6">
						<div class="group">
							<input name="location" required> <span class="highlight"></span>
							<span class="bar"></span> <label>Adresse</label>
						</div>
					</div>
					<div class="col-12 col-md-12">
						<div class="group">
							<input type="date" id="datepicker" class="datepicker" name="date"
								required> <span class="highlight"></span> <span
								class="bar"></span>
						</div>
					</div>

					<div class="col-12">
						<div class="group">
							<textarea name="description" required></textarea>
							<span class="highlight"></span> <span class="bar"></span> <label>Exprimez-vous</label>
						</div>
					</div>
					<label class="fileContainer" id="file" ><input name="file" type="file" accept="image/*" >
				<i class="material-icons" style="color:#1E90FF">linked_camera</i>  Ajouter une photo
				</label>
				
					<div class="col-12">
						<button type="submit" class='button'>Publier</button>
					</div>
				</div>
			</form>
			<div class="row" id='events'>

				<!-- Single Blog Area  -->

				<!--   <div class="col-12 col-md-6 col-lg-4">
                    <div class="single-blog-area blog-style-2 mb-100">
                        <div class="single-blog-thumbnail">
                            <img src="../img/blog-img/5.jpg" alt="">
                            <div class="post-date">
                                <a href="#">10 <span>march</span></a>
                            </div>
                        </div>
                        <div class="single-blog-content mt-50">
                            <div class="line"></div>
                            <a href="#" class="post-tag">Lifestyle</a>
                            <h4><a href="#" class="post-headline">Party people in the house</a></h4>
                            <p>Curabitur venenatis efficitur lorem sed tempor. Integer aliquet tempor cursus. Nullam vestibulum convallis risus vel condimentum. Nullam auctor lorem in libero luctus, vel volutpat quam tincidunt.</p>
                            <div class="post-meta">
                                <p>By <a href="#">james smith</a></p>
                                <p>3 comments</p>
                            </div>
                        </div>
                    </div>
                </div>
              
            </div>-->
			</div>
		</div>
		<!-- ##### Blog Wrapper End ##### -->

		<!-- ##### Instagram Feed Area Start ##### -->
		<div class="instagram-feed-area">
			<div class="container">
				<div class="row">
					<div class="col-12">
						<div class="insta-title">
							<h5>Follow us @ Instagram</h5>
						</div>
					</div>
				</div>
			</div>
			<!-- Instagram Slides -->
			<div class="instagram-slides owl-carousel">
				<!-- Single Insta Feed -->
				<div class="single-insta-feed">
					<img src="../img/instagram-img/01.jpg" alt="">
					<!-- Hover Effects -->
					<div class="hover-effects">
						<a href=""
							class="d-flex align-items-center justify-content-center"><i
							class="fa fa-instagram"></i></a>
					</div>
				</div>
				<!-- Single Insta Feed -->
				<div class="single-insta-feed">
					<img src="../img/instagram-img/02.jpeg" alt="">
					<!-- Hover Effects -->
					<div class="hover-effects">
						<a href="https://www.instagram.com/animalia_officiel/" target="_blank"
							class="d-flex align-items-center justify-content-center"><i
							class="fa fa-instagram"></i></a>
					</div>
				</div>
				<!-- Single Insta Feed -->
				<div class="single-insta-feed">
					<img src="../img/instagram-img/3.png" alt="">
					<!-- Hover Effects -->
					<div class="hover-effects">
						<a href="https://www.instagram.com/animalia_officiel/" target="_blank"
							class="d-flex align-items-center justify-content-center"><i
							class="fa fa-instagram"></i></a>
					</div>
				</div>
				<!-- Single Insta Feed -->
				<div class="single-insta-feed">
					<img src="../img/instagram-img/03.jpg" alt="">
					<!-- Hover Effects -->
					<div class="hover-effects">
						<a href="https://www.instagram.com/animalia_officiel/" target="_blank"
							class="d-flex align-items-center justify-content-center"><i
							class="fa fa-instagram"></i></a>
					</div>
				</div>
				<!-- Single Insta Feed -->
				<div class="single-insta-feed">
					<img src="../img/instagram-img/04.jpg" alt="">
					<!-- Hover Effects -->
					<div class="hover-effects">
						<a href="https://www.instagram.com/animalia_officiel/"target="_blank"
							class="d-flex align-items-center justify-content-center"><i
							class="fa fa-instagram"></i></a>
					</div>
				</div>
				<!-- Single Insta Feed -->
				<div class="single-insta-feed">
					<img src="../img/instagram-img/05.jpg" alt="">
					<!-- Hover Effects -->
					<div class="hover-effects">
						<a href="https://www.instagram.com/animalia_officiel/"target="_blank"
							class="d-flex align-items-center justify-content-center"><i
							class="fa fa-instagram"></i></a>
					</div>
				</div>
				<!-- Single Insta Feed -->
				<div class="single-insta-feed">
					<img src="../img/instagram-img/07.jpg" alt="">
					<!-- Hover Effects -->
					<div class="hover-effects">
						<a href="https://www.instagram.com/animalia_officiel/"target="_blank"
							class="d-flex align-items-center justify-content-center"><i
							class="fa fa-instagram"></i></a>
					</div>
				</div>
			</div>
		</div>
		 <footer class="footer-area text-center">
        <div class="container">
            <div class="row">
                <div class="col-12">
                   
                    <!-- Footer Nav Area -->
                    <div class="classy-nav-container breakpoint-off">
                        <!-- Classy Menu -->
                        <nav class="classy-navbar justify-content-center">

                            <!-- Navbar Toggler -->
                          
                            <!-- Menu -->
                           
                        </nav>
                    </div>
                    
                    <!-- Footer Social Area -->
                   
                </div>
            </div>
        </div>
        </footer>
		<div id="myModal" class="modal">


			<div class="modal-content">

				<span onclick="fermer()" class="close">&times;</span>
				<div style="width:100%;text-align:center;">
				<form action="event" method="post" id="modifier">

					<input type="hidden" name="action" value="update" /> <input
						type="hidden" id="id" name="id" />


					<div>

						<input id="pop_title" class="champ_pop" name="title">

					</div>
					<div>

						<textarea id="pop_content" class="champ_pop" name="content"></textarea>

					</div>
					<div>
						<input id="pop_location" class="champ_pop" name="location">

					</div>

					<button type="submit" id="update" value="Confirmer">Confirmer</button>
				</form>
				</div>
			</div>


		</div>
		<!-- ##### Instagram Feed Area End ##### -->

		<!-- ##### Footer Area Start ##### -->

		<!-- ##### Footer Area End ##### -->

		<!-- jQuery (Necessary for All JavaScript Plugins) -->

		<script src="../js/jquery/jquery-2.2.4.min.js"></script>

		<!-- Popper js -->
		<script src="../js/popper.min.js"></script>
		<!-- Bootstrap js -->
		<script src="../js/bootstrap.min.js"></script>
		<!-- Plugins js -->
		<script src="../js/plugins.js"></script>
		<!-- Active js -->
		<script src="../js/active.js"></script>
		<script src="../js/myevents.js"></script>
<script src="../js/plugins/sweetalert2.all.min.js" type="text/javascript"></script>
