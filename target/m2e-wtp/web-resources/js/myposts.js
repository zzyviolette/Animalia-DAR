$( document ).ready(function() {
	displayAnnonces();
});

function displayAnnonces(){
		$.ajax({
		
		"url" : "annonce",
		"type" : "post",
		"data" : $("#me"),
		"dataType" : "json",
		"success" : function( annonces, textStatus, jqXHR) {
			afficher_annonce(annonces);
			latest_posts(annonces);
				}

	}); 
}
/***************************affichage de date**************************/
function jour(date){
	var moonLanding = new Date(date);
	return moonLanding.getDate();
	}
	function mois(date){
	const monthNames = ["Jan", "Fév", "Mars", "Avril", "Mai", "Juin",
	"Juil", "Aout", "Sept", "Oct", "Nov", "Dec"
	];
	var moonLanding = new Date(date);
	return monthNames[moonLanding.getMonth()];
	}
	function heure(date){
		var moonLanding = new Date(date);
		return moonLanding.getHours();
		}
		function minute(date){
		
		var moonLanding = new Date(date);
		return moonLanding.getMinutes();
		}
	
/***********************affichage des annonces***************************/	
function afficher_annonce(annonces){
	console.log("bien arrive");
var i=0;
$('#posts').empty();
 while(i<annonces.length){
	 var annonce=annonces[i];
	 var photo="../img/event.png";
	 if(annonce[1].photo!=null)
		 photo="data:image/jpg;base64,"+annonce[1].photo.base64Image;
	 var post= "<div class='single-blog-area blog-style-2 mb-50 wow fadeInUp' data-wow-delay='0.2s' data-wow-duration='1000ms'>"+
	  "<div class='row align-items-center'>"+
	    "<div class='col-12 col-md-6'>"+
	     " <div class='single-blog-thumbnail'>"+
	       " <img src='"+photo+"' alt=''>"+
	         " <div class='post-date'>"+
	            "<a href='#'>"+
	           jour(annonce[1].date)+"<span>"+mois(annonce[1].date)+"</span>"+
	           " </a>"+
	          "</div>"+
	        "</div>"+
	    "</div>"+
	   " <div class='col-12 col-md-6'>"+
	      "<div class='single-blog-content'>"+
	        "<div class='line'></div>"+
	        "<a href='#' class='post-tag'>"+annonce[1].category+"</a>"+
	        "<h4>"+
	        "<form class='"+annonce[1].id+"'><input type='hidden' name='action' value='display_annonce'><input type='hidden' name='id' value='"+annonce[1].id+"'> </form>"+
	          "<a onclick='display(&quot;"+annonce[1].id+"&quot;)' class='post-headline'>"+annonce[1].title+"</a>"+
	       "</h4>"+
	        "<p>"+annonce[1].content+"</p>"+
	        "<div class='post-meta'>"+
	          "<p>"+
	           " Par <a href='#'>"+annonce[0].name+"</a>"+
	         " </p>"+
	          "<p>"+annonce[1].location+"</p>"+
	        "</div>"+
	      "</div>"+
	    "</div>"+
	 " </div>"+
	"</div>";
	
	      
	      var objTo = document.getElementById('posts');
	      var new_post = document.createElement("div");
	      new_post.innerHTML = post;
	      objTo.appendChild(new_post);
	
	 i++;
 }
	
}
function latest_posts(annonces){
	var i=0;

	while((i<annonces.length)&&(i<4)){
		var photo="../img/event.png";

		var annonce=annonces[i];
		 if(annonce[1].photo!=null)
			 photo="data:image/jpg;base64,"+annonce[1].photo.base64Image;
		 var latest="<div class='single-blog-post d-flex align-items-center widget-post'>"+
	     "<input type='hidden' name='id' value='"+annonce[1].id+"' />"+
		  <!-- Post Thumbnail -->
		  "<div class='post-thumbnail'>"+
		   " <img src='"+photo+"'>"+
		    "</div>"+
		  <!-- Post Content -->
		  "<div class='post-content'>"+
		   " <a href='#' class='post-tag'>"+annonce[1].category+"</a>"+
		   " <h4>"+
		   "<form class='"+annonce[1].id+"'><input type='hidden' name='action' value='display_annonce'><input type='hidden' name='id' value='"+annonce[1].id+"'> </form>"+
	          "<a onclick='display(&quot;"+annonce[1].id+"&quot;)' class='post-headline'>"+annonce[1].title+"</a>"+
		    "</h4>"+
		   " <div class='post-meta'>"+
		      "<p>"+
		        "<a href='#'>"+jour(annonce[1].date)+" "+mois(annonce[1].date)+"</a>"+
		      "</p>"+
		    "</div>"+
		 " </div>"+
		"</div>";
		  var latest_posts = document.getElementById('latest_posts');
	      var recent_post = document.createElement("div");
	      recent_post.innerHTML = latest;
	      latest_posts.appendChild(recent_post);
	i++;
	 }
}
/******************************chercher une annonce******************************/
function rechercher(){
	var frm=$("#searchForm");
	$.ajax({
			
			"url" : "annonce",
			"type" : "post",
			"data" : frm.serialize(),
			"dataType" : "json",
			"success" : function( annonces, textStatus, jqXHR) {
			
					console.log("result"+annonces.length);
				afficher_annonce(annonces);

				
			}

		});	

}
/*******************************filtrer les annonces******************************/
function filtrer(categorie){
	
    var a="#"+categorie;
	$.ajax({
			
			"url" : "annonce",
			"type" : "post",
			"data" : $(a).serialize(),
			"dataType" : "json",
			"success" : function( annonces, textStatus, jqXHR) {
			
					console.log("result"+annonces.length);
				afficher_annonce(annonces);
				
			}

		});	
}
/************************afficher l'annonce detaillé**************************/
function details_annonce(annonces,id){
	
	var photo="../img/event.png";

	var i=0;
	//var id=1;
	
	var like;
	var mycom="";
	var com="";
	$('#posts').empty();
	var annonce=annonces[0];
	var bool=deja_favoris(id,annonces);
	if(bool==false)
    like="<button class='button' type='submit' name='action' value='like' >J'aime</button>";
	else
        like="<button class='button' type='submit' name='action' value='dislike' >Je n'aime plus</button>";

	 if(annonce[1].photo!=null)
		 photo="data:image/jpg;base64,"+annonce[1].photo.base64Image;
	// deja_favoris(id,annonces);
var an="<div class='single-blog-area blog-style-2 mb-50'>"+
" <div class='single-blog-thumbnail'>"+
"<img src='"+photo+"' >"+
  "<div class='post-tag-content'>"+
    "<div class='container'>"+
      "<div class='row'>"+
       " <div class='col-12'>"+
          "<div class='post-date'>"+
           " <a href='#'>"+
              jour(annonce[1].date)+"<span>"+mois(annonce[1].date)+"</span>"+
           " </a>"+
  "</div>"+
 "   </div>"+
"  </div>"+
" </div>"+
"</div>"+
" </div>"+
"</div>"+

"<div class='container'>"+
"<div class='row'>"+
"<div class='col-12 col-lg-9'>"+
  "<div class='single-blog-area blog-style-2 mb-50'>"+
    "<div class='single-blog-content'>"+
    "<div class='widget-content'>"+
    "<ul class='tags'>"+
    "<li>"+//supprimer
    "<button class='button' style='float:right;' onclick='supprimer(&quot;"+annonce[1].id+"&quot;)'>Supprimer</button>"+
    "</li>"+
    "<li><button class='button' style='float:right;' onclick='myBtn("+annonce[1].id+")'>Modifier</button></li>"+
    "<li><form  style='float:right;' action='annonce' method='post'>"+
    like+
    "<input type='hidden' name='id' value='"+annonce[1].id+"' />"+
    "</form></li></ul></div>"+
     " <div class='line'></div>"+
      "<a href='#' class='post-tag'>"+annonce[1].category+"</a>"+
     " <h4>"+
        "<a href='#' class='post-headline mb-0'>"+annonce[1].title+"</a>"+
      "</h4>"+
      "<div class='post-meta mb-50'>"+
        "<p>"+
          "PAR <a href='#'>"+annonce[0].name+"</a>"+
        "</p>"+
        "<p>A "+annonce[1].location+" &nbsp;</p>"+
       "<p>"+annonces.length+" commentaires</p>"+
      "</div>"+
     " <p>"+annonce[1].content+"</p>"+

      " </div>"+
  "</div>"+
  "<div class='comment_area clearfix mt-70' id='comments'>"+
    "<h5 class='title'>Commentaires</h5>";

  
 b=" </div>"+

 " <div class='post-a-comment-area mt-70'>"+
    "<h5>Laissez un commentaire</h5>"+
    "<form id='new_comment'>"+
      "<div class='row'>"+

        "<div class='col-12'>"+
         " <div class='group'>"+
         "<input type='hidden' name='id' value='"+annonce[1].id+"' />"+
         "<input type='hidden' name='action' value='add_comment' />"+
           " <textarea name='content_comment' id='message' required=''></textarea>"+
           " <span class='highlight'></span>"+
            "<span class='bar'></span>"+
            "<label>Commentaire</label>"+
          "</div>"+
       " </div>"+
        "<div class='col-12'>"+
          "<button onclick='add_comment(&quot;"+annonce[1].id+"&quot;)' class='btn original-btn'>Commenter</button>"+
        "</div>"+
      "</div>"+
    "</form>"+
  
 " </div>"+
"</div>";
	

	while(i<annonces.length){
		console.log("ligne"+i);
		annonce=annonces[i];
		//console.log("comme"+annonce[3]);
		var avatar="../img/inconnu1.jpg";


		if(annonce[3]!=null){
			if(annonce[4].avatar!=null)
				avatar="data:image/jpg;base64,"+annonce[4].avatar.base64Image;

			if(annonce[3].user_id.id == id){
				mycom= "<form method='post' action='commentaire' onsubmit='return valider()'><input type='hidden' name='action' value='delete_comment'><input type='hidden' name='id' value='"+annonce[3].id+"'><button type='submit' class='close'>&times;</button></form>";
			}
		  com=com+"<ol>"+
	      "<li class='single_comment_area'>"+
	     mycom+
	        "<div class='comment-content d-flex'>"+
	         " <div class='comment-author'>"+
	           " <img src='"+avatar+"' alt='author'>"+
	           "</div>"+
	         " <div class='comment-meta'>"+
	           " <a href='#' class='post-date'>"+jour(annonce[3].date)+" "+mois(annonce[3].date)+"</a>"+
	           " <p>"+
	              "<a href='#' class='post-author'>"+annonce[4].name+"</a>"+
	            "</p>"+
	            "<p>"+annonce[3].content+"</p>"+
	         " </div>"+
	        "</div>"+

	      "</li>"+

	    "</ol>";}
		i++;
	}
	 var latest_posts = document.getElementById('posts');
     var recent_post = document.createElement("div");
     recent_post.innerHTML = an+com+b;
     latest_posts.appendChild(recent_post);
	
}

function supprimer(id) {
	
	//Supprimer un message//
	
	const swalWithBootstrapButtons = swal.mixin({
		  confirmButtonClass: 'btn btn-success',
		  cancelButtonClass: 'btn btn-danger',
		  buttonsStyling: false,
		})

		swalWithBootstrapButtons({
		  postion : 'bottom-start',	
		  title: 'Vous &ecirctes sur?',
		  text: "",
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonText: 'Oui,Supprimer!',
		  cancelButtonText: 'Non, Annuler!',
		  reverseButtons: true
		}).then((result) => {
		  if (result.value) {

		  	$.ajax({
				"url" :  "annonce",
				"type" : "post",
				"data" : {
					"id" : id,
					"action" : "supprimer"
				},
			"dataType" : "",
		    "success" : function() {
			  	displayAnnonces();
			 
			    }
			});
			
		  } else if (
				    // Read more about handling dismissals
				    result.dismiss === swal.DismissReason.cancel
				  ) {
				    swalWithBootstrapButtons(
				      'Annuler',
				      'Vous avez annul&eacute :)',
				      'erreur'
				    )
				  }
				})
		  }
		  
	
		  
		  
/*************************afficher l'annonce modifier****************************/
function display(annonce){
	var a=document.getElementsByClassName(annonce);
	var id;
		$.ajax({
				
				"url" : "annonce",
				"type" : "post",
				"data" : $(a[0]).serialize(),
				"dataType" : "json",
				"success" : function( annonces, textStatus, jqXHR) {
					getId((data)=>{
						
						console.log(data);
						id =data;
						console.log(id);
					});
					setTimeout(function(){
						details_annonce(annonces,id);
					}, 2000);	
					
				}

			});	
}
/**************************ajouter un commentaire ***********************************/
function add_comment(id){

	var frm="#new_comment";
	$.ajax({
		
		"url" : "commentaire",
		"type" : "post",
		"data" : $(frm).serialize(),
		"dataType" : "text",
		"success" : function( annonces, textStatus, jqXHR) {
		
				console.log("result"+annonces);
			display(id);
			
		}

	});		

}
/************************si j'aime deja cette pub**********************/
function deja_favoris(id,annonces){
	var i=0;
	var an;
	while(i<annonces.length){
		an=annonces[i];
	
			if(an[2]!==null){
				console.log(id+"----"+an[2].id)
			if(an[2].id==id)
				return true;
			}
			else
				return false;
			i++;
		}
	

	return false;
}

/***********************************popup modifier*****************************************/
function myBtn(id_an) {
	
	var modal = document.getElementById('myModal');
	var title = document.getElementById('pop_title');
	var contenu = document.getElementById('pop_content');
	var location = document.getElementById('pop_location');
	var annonce = document.getElementById('id');
$.ajax({
		
		"url" : "annonce",
		"type" : "post",
		 "data" :{
			"action" : "display_annonce",
			"id":id_an
		},
		"dataType" : "json",
		"success" : function( annonces, textStatus, jqXHR) {
			title.value=annonces[0][1].title;
			annonce.value=annonces[0][1].id;
			contenu.value=annonces[0][1].content;
			location.value=annonces[0][1].location;
			modal.style.display = "block";
		}

	});	

	
	
}
var span = document.getElementsByClassName("close")[0];

function fermer() {
	var modal = document.getElementById('myModal');
	console.log("couckfslkfou!!");

	modal.style.display = "none";
}

window.onclick = function(event) {
	var modal = document.getElementById('myModal');

	if (event.target == modal) {
		modal.style.display = "none";
	}
}
/***************************afficher le formulaire d'ajout******************************/
function afficher_form(){
	var f=document.getElementById("new_annonce");
	if( $("#new_annonce").css('display') == 'none'){
		console.log(f.style.display);

		f.style.display="block";
	}
	else
		f.style.display="none";
}
/*********************supprimer un commentaire********************/
function delete_comment(id){
     var a = document.createElement("form");
    a.innerHTML = "<input name='action' value='delete_comment'><input name='id' value='"+id+"'>";
	$.ajax({
		
		"url" : "commentaire",
		"type" : "post",
		"data" : $(a).serialize(),
		"dataType" : "text",
		"success" : function( annonces, textStatus, jqXHR) {
		
				console.log("result"+annonces);
			display(id);
			
		}

	});	
	
}
/******************valider la suppression********************/
function valider(){
	    var r = confirm("Etes-vous sur de vouloir supprimer!");
	    if (r == true) {
	      return true;
	    } else {
	       return false;
	    }
	
}
/***************************rediriger vers accessoires*****************************/
function advertissement(){
	var obj = window.parent.document.getElementById("iframe");
	obj.src="accessoires.html";
	
}
function getId(callback){
	var id;
	
	$.ajax({
		"url" : "annonce",
		"type" : "post",
		"data" : {
			"action" : "searchuserid",
		},
		"dataType" : "" +
				"",
		"success" : function(text) {
           id=text;
           console.log(id);
         

           callback(id);
          
			} 

	});

}

