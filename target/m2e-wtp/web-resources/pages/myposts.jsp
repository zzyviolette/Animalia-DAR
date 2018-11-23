<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link
	href="../assets/global/plugins/simple-line-icons/simple-line-icons.min.css">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/annonce.css">

</head>

<body>
	<input id="me" type="hidden" name="action" value="display_mine" />

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
					style="background-image: url(../img/horses.jpg);">
					<div class="container h-100">
						<div class="row h-100 align-items-center">
							<div class="col-12">
								<div class="slide-content text-center">
									<div class="post-tag">
										<a  data-animation="fadeInUp">Animalia</a>
									</div>
									<h2 data-animation="fadeInUp" data-delay="250ms">
										<a >Partagez votre amour avec nous !</a>
									</h2>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- Single Slide -->
				<div class="single-hero-slide bg-img"
					style="background-image: url(../img/fish.jpg);">
					<div class="container h-100">
						<div class="row h-100 align-items-center">
							<div class="col-12">
								<div class="slide-content text-center">
									<div class="post-tag">
										<a  data-animation="fadeInUp">Animalia</a>
									</div>
									<h2 data-animation="fadeInUp" data-delay="250ms">
										<a >Sauvez les animaux qui sont notre éspoir!</a>
									</h2>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- Single Slide -->
				<div class="single-hero-slide bg-img"
					style="background-image: url(../img/birds.jpg);">
					<div class="container h-100">
						<div class="row h-100 align-items-center">
							<div class="col-12">
								<div class="slide-content text-center">
									<div class="post-tag">
										<a  data-animation="fadeInUp">Animalia</a>
									</div>
									<h2 data-animation="fadeInUp" data-delay="250ms">
										<a >Les êtres qui ne deçoivent jamais!</a>
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

			<div  class="container">
			<button class='button' onclick='afficher_form()'> + Annonce</button>
				<form id="new_annonce" action="annonce" method="POST"
					enctype="multipart/form-data" style="display:none;">
					<input type="hidden" name="action" value="add_annonce" />

					<div class="row">

						<div class="col-12 col-md-6">
							<div class="group">
								<input type="text" name="title" required> <span
									class="highlight"></span> <span class="bar"></span> <label>Titre</label>
							</div>
						</div>
						<div class="col-12 col-md-6">
							<div class="group">
								<input  name="location" required> <span
									class="highlight"></span> <span class="bar"></span> <label>Adresse</label>
							</div>
						</div>
						
						<div class="col-12">
							<div class="group">
								<textarea name="content" required></textarea>
								<span class="highlight"></span> <span class="bar"></span> <label>Exprimez-vous</label>
							</div>
						</div>
						<div class="col-12">
							<div class="group">
								<select id="annonce_categorie" name="categorie" >
									<option>-selectionner une cat&eacutegorie-</option>
									<option value="chercher animal">chercher un animal</option>
									<option value="proposer animal">proposer un animal</option>
									<option value="chercher garde animaux">chercher garde
										animaux</option>
									<option value="garder des animaux">garder des animaux</option>
									<option value="animaux perdus">animaux perdus</option>
									<option value="autre">autre</option>

								</select>
							</div>
						</div>
						<label class="champ" >
	
			<label class="fileContainer" id="file" ><input name="file" type="file" accept="image/*" >
				<i class="material-icons" style="color:#1E90FF">linked_camera</i>  Ajouter une photo
				</label>
							
					
				</label>
				
							
						<div class="col-12">
							<button class='button' type="submit" >Publier</button>
						</div>
					</div>
				</form>
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
								<a onclick='advertissement()'><img src="../img/acce.gif" alt=""></a>
							</div>
							

							<!-- Widget Area -->
							<div class="sidebar-widget-area">
								<h5 class="title">R&eacutecentes Annonces</h5>

								<div class="widget-content" id="latest_posts">

									<!-- Single Blog Post -->
									


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
					<a href="https://www.instagram.com/animalia_officiel/"target="_blank"
						class="d-flex align-items-center justify-content-center"><i
						class="fa fa-instagram"></i></a>
				</div>
			</div>
			<!-- Single Insta Feed -->
			<div class="single-insta-feed">
				<img src="../img/instagram-img/03.jpg" alt="">
				<!-- Hover Effects -->
				<div class="hover-effects">
					<a href="https://www.instagram.com/animalia_officiel/"target="_blank"
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
	<!-- ##### Instagram Feed Area End ##### -->

	<!-- ##### Footer Area Start ##### -->
	
		<div id="myModal" class="modal">


			<div class="modal-content">

				<span onclick="fermer()" class="close">&times;</span>
				<div style="width:100%;text-align:center;">
				<form action="annonce" method="post">

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
	<script src="../js/myposts.js"></script>
<script src="../js/plugins/sweetalert2.all.min.js" type="text/javascript"></script>

</body>

