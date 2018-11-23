$(document).ready(function() {
	setCount();

	window.setInterval(function(){setCount();},30000);

	$.ajax({
		"url" : "user",
		"type" : "get",
		"data" : {
			"action" : "getUser"
		},
		"dataType" : "text",
		"success" : function(data) {
			console.log(data);
			var user = JSON.parse(data);

			
			document.getElementById("acceuilusername").innerHTML = user.name ? user.name : "";
			if(user.avatar){
				if( document.getElementById("profileuserpic") ) document.getElementById("profileuserpic").src = "data:image/png;base64,"+user.avatar;
				if( document.getElementById("acceuilimgprofile") ) document.getElementById("acceuilimgprofile").src = "data:image/png;base64,"+user.avatar;
				if( parent.document.getElementById("acceuilimgprofile") ) parent.document.getElementById("acceuilimgprofile").src = "data:image/png;base64,"+user.avatar;
			}else{
				if( document.getElementById("profileuserpic") ) document.getElementById("profileuserpic").src = "../img/logo.png";
				if( document.getElementById("acceuilimgprofile") ) document.getElementById("acceuilimgprofile").src = "../img/logo.png";
				if( parent.document.getElementById("acceuilimgprofile") ) parent.document.getElementById("acceuilimgprofile").src = "../img/logo.png";
			}
			
		}
	});
	
	// Se Deconnecter//
	$("#logOut").click(function() {
		const swalWithBootstrapButtons = swal.mixin({
			  confirmButtonClass: 'btn btn-success',
			  cancelButtonClass: 'btn btn-danger',
			  buttonsStyling: false,
			})

			swalWithBootstrapButtons({
			  title: 'Vous &ecirctes sur? ',
			  text: "",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonText: 'Oui, Déconnecter!',
			  cancelButtonText: 'Non, Annuler!',
			  reverseButtons: true
			}).then((result) => {
			  if (result.value) {
				  swalWithBootstrapButtons(
					      'Deconnecté!',
					      '',
					      'success'
					    )
				setTimeout(function(){
					$.ajax({
						"url" : "login",
						"type" : "post",
						"data" : {"action":"logOut"},
						"dataType" : "text",
						"success" : function(data) {
							var url = window.location.host;
							var arr = "http://" +url+"/pages/login.jsp";
							
							window.location.href = arr;
						}
					});
				}, 2000);
				  
			  } else if (
			  
			    result.dismiss === swal.DismissReason.cancel
			  ) {
			    swalWithBootstrapButtons(
			      'Annulé',
			      ' ',
			      'error'
			    )
			  }
			})
	});
	
	// logOut();
	
	function menu(current){
		var listeMenu = ['li_actualite','li_allevents','li_events','li_myevents','li_publications','li_accessoires','li_comment','li_messages','li_aboutUs','li_updateprofile','li_notifications','li_favoris'];
		for(var i=0 ; i<listeMenu.length ; i++ ){
			var element = document.getElementById(listeMenu[i]);
			if(current == listeMenu[i]){
			    element.classList.add("active");
			    element.classList.add("open");
			}else{
				element.classList.remove("active");
				element.classList.remove("open");
			}
		}
	}

			function setCount() {
                
				$.ajax({
							"url" : "message",
							"type" : "post",
							"data" : {
								"action" : "getCountNoRead",
								"user_id" : $("#user_id").val()
							},
							"dataType" : "text",
							"success" : function(data) {
								$("#msg_count").text(data);
							}
						});
				$.ajax({
					
					"url" : "commentaire",
					"type" : "post",
					"data" : {
						"action" : "new_comment",
						"user_id" : $("#user_id").val()

					},
					"dataType" : "text",
					"success" : function(data) {
						console.log("i am here");
						$("#notif_count").text(data);
					}
				});
				

			}
			var obj = document.getElementById("iframe");

			$("#li_actualite").click(function() {
						obj.src = "posts.jsp";
						menu('li_actualite');
					});
			$("#li_events").click(function() {
				
				menu('li_events');
			});
	
			$("#li_allevents").click(function() {
						obj.src = "events.jsp";
						menu('li_events');
					});
			
			$("#li_myevents").click(function() {
				obj.src = "myevents.jsp";
				menu('li_events');
			});
			
			$("#li_publications").click(function() {
						obj.src = "myposts.jsp";
						menu('li_publications');

					});
			$("#li_accessoires").click(function() {
					obj.src = "accessoires.html";
					menu('li_accessoires');
					});
			$("#li_comment").click(function() {
						obj.src = "tellus.html";
						menu('li_comment');
					});
			$("#li_messages").click(function() {
				$("#msg_count").text(0);
						obj.src = "message.html";
						menu('li_messages');
					});
			$("#li_notifications").click(function() {
				$("#notif_count").text(0);

				obj.src = "notification.jsp";
				menu('li_notifications');
				
			});
			$("#li_favoris").click(function() {
				obj.src = "favoris.jsp";
				menu('li_favoris');
				
			});
			$("#li_aboutUs").click(function() {
						obj.src = "aboutUs.html";
						menu('li_aboutUs');

					});
			$("#li_updateprofile").click(function() {
				obj.src = "updateprofile.html";
				menu('li_updateprofile');
			});

		})
