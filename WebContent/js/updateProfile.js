$(document).ready(function (){

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
			document.getElementById('userId').value = user.id;
			document.getElementById('userName').value = user.name ? user.name : "";
			document.getElementById('userEmail').value = user.email ? user.email : "";
			document.getElementById('userOccupation').value = user.occupation ? user.occupation : "";
			document.getElementById('userInterest').value = user.interest ? user.interest : "";
			document.getElementById("usernametitle").innerHTML = user.name ? user.name : "";
			if(user.avatar){
				document.getElementById("profileuserpic").src = "data:image/png;base64,"+user.avatar;
				parent.document.getElementById("acceuilimgprofile").src = "data:image/png;base64,"+user.avatar;
			}else{
				document.getElementById("profileuserpic").src = "../img/logo.png";
				parent.document.getElementById("acceuilimgprofile").src = "../img/logo.png";
			}
			
			parent.document.getElementById("acceuilusername").innerHTML = user.name ? user.name : "";
			
		}
	});
	
	
$("#updateProfile").click(function() {
		
		$.ajax({
			"url" : "user",
			"type" : "post",
			"data" : {
				"actionId" : "updateUser",
				"userId" : $("#userId").val(),
				"name" : $("#userName").val(),
				"email" : $("#userEmail").val(),
				"occupation" : $("#userOccupation").val(),
				"interest" : $("#userInterest").val()
				
			},
			"dataType" : "text",
			"success" : function(data) {
				parent.document.getElementById("acceuilusername").innerHTML = $("#userName").val() ? $("#userName").val() : "";
				swal({
					  position: 'top-end',
					  type: 'success',
					  title: 'Modification Enregistre ',
					  showConfirmButton: false,
					  timer: 2000
					});
				parent.check_status();
			}
		});	
});


$("#changeAvatar").click(function() {
	document.getElementById("changeAvatar-form").submit();
});


$("#changePassword").click(function() {
	$.ajax({
		"url" : "user",
		"type" : "post",
		"data" : {
			"actionId" : "changePassword",
			"currentPassword" : $("#currentPassword").val(),
			"newPassword" : $("#newPassword").val(),
			"newPassword2" : $("#newPassword2").val()
		},
		"dataType" : "text",
		"success" : function(data) {
			if(data == "SUCCESS" ){
				swal({
					  position: 'top-end',
					  type: 'success',
					  title: 'SUCCESS :)'
					});
				

			}else if(data == "INVALID_OLD_PW"){
				swal({
					  position: 'top-end',
					  type: 'error',
					  title: 'Ancien mot de passe est invalide'
					});

			}else if(data == "INVALID_NEW_PW"){
				swal({
					  position: 'top-end',
					  type: 'error',
					  title: 'Le noveau mot de passe est invalide'
					});
			}
			
		}
	});	
});



	
})
