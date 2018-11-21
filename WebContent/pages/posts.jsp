
<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<!-- Title -->

<!-- Favicon -->

<!-- Style CSS -->
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/annonce.css">


</head>

<body>
	<input id="display" type="hidden" name="action" value="display_all" />

	<!-- Preloader -->
	<div id="preloader">
		<div class="preload-content">
			<div id="original-load"></div>
		</div>
	</div>

	<!-- Subscribe Modal -->


	<!-- ##### Header Area Start ##### -->

	<!-- ##### Header Area End ##### -->

	<!-- ##### Hero Area Start ##### -->
	<div class="single-blog-wrapper section-padding-0-100" id="pere">

		<div class="hero-area">
			<!-- Hero Slides Area -->
			<div class="hero-slides owl-carousel">
				<!-- Single Slide -->
				<div class="single-hero-slide bg-img"
					style="background-image: url(../img/slide3.jpg);">
					<div class="container h-100">
						<div class="row h-100 align-items-center">
							<div class="col-12">
								<div class="slide-content text-center">
									<div class="post-tag">
										<a href="#" data-animation="fadeInUp">Animalia</a>
									</div>
									<h2 data-animation="fadeInUp" data-delay="250ms">
										<a>Partagez votre amour avec nous
											!</a>
									</h2>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- Single Slide -->
				<div class="single-hero-slide bg-img"
					style="background-image: url(../img/slide1.jpg);">
					<div class="container h-100">
						<div class="row h-100 align-items-center">
							<div class="col-12">
								<div class="slide-content text-center">
									<div class="post-tag">
										<a href="#" data-animation="fadeInUp">Animalia</a>
									</div>
									<h2 data-animation="fadeInUp" data-delay="250ms">
										<a>Sauvez les animaux qui sont
											notre espoir!</a>
									</h2>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- Single Slide -->
				<div class="single-hero-slide bg-img"
					style="background-image: url(../img/chat.jpg);">
					<div class="container h-100">
						<div class="row h-100 align-items-center">
							<div class="col-12">
								<div class="slide-content text-center">
									<div class="post-tag">
										<a href="#" data-animation="fadeInUp">Animalia</a>
									</div>
									<h2 data-animation="fadeInUp" data-delay="250ms">
										<a>Les autres qui ne decoivent
											jamais</a>
									</h2>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- ##### Hero Area End ##### -->

		<!-- ##### Blog Wrapper Start ##### -->
		<div class="blog-wrapper section-padding-100 clearfix">

			<div class="container">
				<div class="row">
					<div class="col-12 col-lg-9" id="posts">

						



					</div>

					<!-- ##### Sidebar Area ##### -->
					<div class="col-12 col-md-4 col-lg-3">
						<div class="post-sidebar-area">

							<!-- Widget Area -->
							<div class="sidebar-widget-area">
								<form id="searchForm" class="search-form">
									<input type="hidden" name="action" value="search_annonce">

									<input type="search" name="search" placeholder="Rechercher">

								</form>
								<button class='button' onclick="rechercher()">Rechercher</button>

							</div>


							<!-- Widget Area -->
							<div class="sidebar-widget-area">
								<h5 class="title">Publicit&eacute</h5>
								<a onclick="advertissement()"><img src="../img/acce.gif" alt=""></a>
							</div>

							<!-- Widget Area -->
							<div class="sidebar-widget-area">
								<h5 class="title">R&eacutecentes Annonces</h5>

								<div class="widget-content" id="latest_posts">

								
								</div>
							</div>

							<!-- Widget Area -->
							<div class="sidebar-widget-area">
								<h5 class="title">Cat&eacutegories</h5>
								<div class="widget-content">
									<ul class="tags">


										<li><form id='a1'>
												<input type='hidden' name='action' value='filtrer'><input
													type='hidden' name='categorie' value='chercher animal'>
											</form> <a href="#" onclick="filtrer('a1')">chercher animal</a></li>
										<li><form id='a2'>
												<input type='hidden' name='action' value='filtrer'><input
													type='hidden' name='categorie' value='proposer animal'>
											</form> <a href="#" onclick="filtrer('a2')">proposer animal</a></li>
										<li><form id='a3'>
												<input type='hidden' name='action' value='filtrer'><input
													type='hidden' name='categorie'
													value='chercher garde animaux'>
											</form> <a href="#" onclick="filtrer('a3')">chercher garde
												animaux</a></li>
										<li><form id='a4'>
												<input type='hidden' name='action' value='filtrer'><input
													type='hidden' name='categorie' value='garder des animaux'>
											</form> <a href="#" onclick="filtrer('a4')">garder des animaux</a></li>
										<li><form id='a5'>
												<input type='hidden' name='action' value='filtrer'><input
													type='hidden' name='categorie' value='animaux perdus'>
											</form> <a href="#" onclick="filtrer('a5')">animaux perdus</a></li>
										<li><form id='a6'>
												<input type='hidden' name='action' value='filtrer'><input
													type='hidden' name='categorie' value='autre'>
											</form> <a href="#" onclick="filtrer('a6')">autre</a></li>

									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- ##### Blog Wrapper End ##### -->
	</div>
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
					<a href="https://www.instagram.com/animalia_officiel/" target="_blank"
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
					<a href="https://www.instagram.com/animalia_officiel/" target="_blank"
						class="d-flex align-items-center justify-content-center"><i
						class="fa fa-instagram"></i></a>
				</div>
			</div>
			<!-- Single Insta Feed -->
			<div class="single-insta-feed">
				<img src="../img/instagram-img/05.jpg" alt="">
				<!-- Hover Effects -->
				<div class="hover-effects">
					<a href="https://www.instagram.com/animalia_officiel/" target="_blank"
						class="d-flex align-items-center justify-content-center"><i
						class="fa fa-instagram"></i></a>
				</div>
			</div>
			<!-- Single Insta Feed -->
			<div class="single-insta-feed">
				<img src="../img/instagram-img/07.jpg" alt="">
				<!-- Hover Effects -->
				<div class="hover-effects">
					<a href="https://www.instagram.com/animalia_officiel/" target="_blank"
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

		

			<form action="message" method="post" id="envoyer">
				<div>

					<img id="photo" width="20%" height="20%"><input style="border:none;width:60%;padding:5px;" id="user_name"
						readonly>
				</div>
				<div>
					<input id="pop_message" class="champ_pop" name="content"> <input
						type="hidden" name="action" value="contact"> <input
						type="hidden" id="user_id" name="contact_id">

				</div>
				<button type="submit" id="update" value="Envoyer">Envoyer</button>

			</form>
			</div>
		</div>
	</div>
	<!-- ##### Instagram Feed Area End ##### -->


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
	<script src="../js/annonces.js"></script>

</body>
