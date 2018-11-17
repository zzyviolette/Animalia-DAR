$(document).ready(function() {

$("#loginForm").click(function() {
		$.ajax({
			"url" : "login",
			"type" : "post",
			"data" : {
				"actionId" : "login",
				"email" : $("#email").val(),
				"password" : $("#password").val()
			},
			"dataType" : "text",
			"success" : function(data) {
				console.log(data);
				var user = JSON.parse(data);
				console.log(user);
				if(user.avatar){
					document.getElementById("profileuserpic").src = "data:image/png;base64,"+user.avatar;
					parent.document.getElementById("acceuilimgprofile").src = "data:image/png;base64,"+user.avatar;
				}else{
					document.getElementById("profileuserpic").src = "../img/logo.png";
					parent.document.getElementById("acceuilimgprofile").src = "../img/logo.png";
				}
			}
		});	
	});


})
