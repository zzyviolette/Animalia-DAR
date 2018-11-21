$(document).ready(function (){

	$.ajax({
		"url" : "comment",
		"type" : "post",
		"data" : {
			"action" : "searchusername",
		},
		"dataType" : "" +
				"",
		"success" : function(text) {
				if('401' == text){
					// TODO swall
				}else{
					$('#username').val(text);
				}

			} 

	});

	$("#register").click(function() {
		
		$.ajax({
			"url" : "comment",
			"type" : "post",
			"data" : {
				"name" : $("#username").val(),
				"description" : $("#description").val(),
				"mark" : $("#mark").val()
			},
			"dataType" : "text",
			"success" : function(data) {
				document.getElementById("description").value = "";
				document.getElementById("mark").value = "";

				swal({
					  position: 'top-end',
					  type: 'success',
					  title: 'Votre Commentaire est Enregistré ',
					  showConfirmButton: false,
					  timer: 2000
					});
			}
		});
		
	});
	
})
