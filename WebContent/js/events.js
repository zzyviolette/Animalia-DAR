$( document ).ready(function() {
     setTimeout(function(){
							
	setCount();
		}, 2000);
	
});
/**
 * **************recuperer et afficher toute les evenements de la
 * base****************
 */
function setCount(){
$.ajax({
		
		"url" : "event",
		"type" : "post",
		"data" : $("#even"),
		"dataType" : "json",
		"success" : function( annonces, textStatus, jqXHR) {
			getId((data)=>{
				
				console.log(data);
				id =data;
				console.log(id);
				display_events(annonces,id);
			});
		}
	});	
}
/** *************afficher les evenements ********** */
function display_events(events,id){
	var i=0;
	var tmp;
	var participant=0;
	$('#events').empty();
	var event;
	var bool;
	while(i<events.length){
		var photo="../img/event.png";
		var avatar="../img/inconnu1.jpg";

		event=events[i];
		while(tmp==event[1].event_id){
			console.log("je passe");
			i++;
			if(i==events.length)
				break;
			event=events[i];
		}
		if(i==events.length)
			break;
		tmp=event[1].event_id;
		bool=deja_interesse(events,id,event[1].event_id);
	participant=nombre_participant(event,events,i);
if(bool==true)
	interesse="<a><span><form action='event' method='post'>"+
	"<input type='hidden' name='id' value='"+event[1].event_id+"' /><button style='border:none;background-color:black;margin-top:10px;color:white;padding:5px;' type='submit'  name='action' value='notIntrested'>"+
	"<span class='glyphicon glyphicon-star'></span>pas interess&eacute"+
	"</button></form></a></span>";
else
interesse="<a><span><form action='event' method='post'>"+
"<input type='hidden' name='id' value='"+event[1].event_id+"' /><button style='border:none;background-color:black;margin-top:10px;color:white;padding:5px;' type='submit'  name='action' value='intrested'>"+
"<span class='glyphicon glyphicon-star'></span>interess&eacute"+
"</button></form></a></span>";
		 if(event[1].photo!=null)
			 photo="data:image/jpg;base64,"+event[1].photo.base64Image;
		 if(event[0].avatar!=null)
				avatar="data:image/jpg;base64,"+event[0].avatar.base64Image;
		 
	var even=""+
	  "<div class='single-blog-area blog-style-2 mb-100'>"+
	    "<div class='single-blog-thumbnail'>"+
	        "<img src='"+photo+"' alt=''>"+
	        "<div class='post-date'>"+
	          "<a href='#'>"+
	            jour(event[1].date)+"<span>"+mois(event[1].date)+"</span>"+
	          "</a>"+
	          interesse+
	        "</div>"+
	      "</div>"+
	    "<div class='single-blog-content mt-50'>"+
	      "<div class='line'></div>"+
	        "<button class='button' onclick='contacter(&quot;"+event[0].name+"&quot;,&quot;"+avatar+"&quot;,&quot;"+event[0].id+"&quot;)'>Contacter</button>"+
	      "<h4>"+
	        "<a href='#'class='post-headline'>"+event[1].title+"</a>"+ 
	     " </h4>"+
	     "<p>"+event[1].description+"</p>"+
	      "<div class='post-meta'>"+
	        "<p>"+
	         " PAR <a href='#'>"+event[0].name+"</a>"+
	        "</p>"+
	        "<p> A "+event[1].location+"</p>"+
	        "<p>&nbsp; &nbsp; &nbsp;"+participant+" participants</p>"
	        "</div>"+
	 " </div>"+
	"</div>";
	    var objTo = document.getElementById('events');
	      var new_post = document.createElement("div");
	      new_post.innerHTML = even;
	      objTo.appendChild(new_post);
	    i++;
	
	}
	
}
/** **************si l'user est déja interessé par l'evenement**************** */
function deja_interesse(events,id,id_ev){
	
	var i=0;
	var event;
	while(i<events.length){
		event=events[i];
		
		if(event[1].event_id==id_ev)
			if(event[3]!=null){
			if(event[3].id==id)
				return true;
			}
			else return false;
		i++;
	}
	return false;
}
/** **************affichage des dates******** */
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
function heure(date){
			var moonLanding = new Date(date);
			return moonLanding.getHours();
			}
function minute(date){
			
			var moonLanding = new Date(date);
			return moonLanding.getMinutes();
			}
			
			/** ************* contacter un utilisateur ******************** */
function contacter(name,photo,id){
			
				var modal = document.getElementById('myModal');
				var nom = document.getElementById('user_name');
				var image=document.getElementById('photo');
				var user_id = document.getElementById('user_id');

				nom.value=name;
				image.src=photo;
				user_id.value=id;
				modal.style.display = "block";
				console.log(id);
				console.log(nom.value);

			}
			/** *****compter le nombre de participant par event******* */
function nombre_participant(event,events,i){
				if(event[3]==null)
					return 0;
				var j=i;
				var cpt=0;
				var ev;
				ev=events[i];
				while((j<events.length)&&(ev[1]).event_id==event[1].event_id){
				
					cpt++;
				j++;
				ev=events[j];
				}
				return cpt;
			}

/****************current user id**************/
function getId(callback){
	var id;
	
	$.ajax({
		"url" : "annonce",
		"type" : "post",
		"data" : {
			"action" : "searchuserid"
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
