$( document ).ready(function() {

getReceiveMessages();
	
});
function getReceiveMessages() {
	$.ajax({
		"url" : "message",
		"type" : "post",
		"data" : {
			"action" : "display_receive"
		},
		"dataType" : "json",
		"success" : function(messages, textStatus, jqXHR) {
			$('#msg_th').empty();
			$('#msg_tb').empty();
			$('#receive').addClass('active');
			$('#send').removeClass('active');
			if (messages.length > 0) {
				$('#msg_th').append('<tr><th>' + "D&eacutepart" + '</th><th>'
						+ "Contenu" + '</th><th>' + "Date" + '</th><th>'
						+ "Op&eacuteration" + '</th></tr>');
				for (var i = 0; i < messages.length; i++) {
					var m = messages[i];
					$('#msg_tb')
							.append('<tr><td>'
									+ m.id_user_departure
									+ '</td><td>'
									+ m.content
									+ '</td><td>'
									+ m.date
									+ '</td><td>'
									+ '<image src="../img/reply.png" style="width: 20px; height: 20px; margin-right: 15px;" onclick ="clickAdd(this)" id = "'
									+ m.id
									+ '" >'
									+ '</image>'
									+ '<image src="../img/delete.png" style="width: 20px; height: 20px; margin-left: 15px;" class = "1" onclick ="clickDelete(this)" id = "'
									+ m.id + '" >' + '</image>' + '</td></tr>');
				}

			}

		}
	})

}


function getSendMessages() {
	$.ajax({
		"url" : "message",
		"type" : "post",
		"data" : {
			"action" : "display_send"
		},
		"dataType" : "json",
		"success" : function(messages, textStatus, jqXHR) {
			$('#msg_th').empty();
			$('#msg_tb').empty();
			$('#send').addClass('active');
			$('#receive').removeClass('active');
			if (messages.length > 0) {
				$('#msg_th').append('<tr><th>' + "Destinataire" + '</th><th>'
						+ "Contenu" + '</th><th>' + "Date" + '</th><th>'
						+ "Op&eacuteration" + '</th></tr>');
			}

			for (var i = 0; i < messages.length; i++) {
				var m = messages[i];
				$('#msg_tb')
						.append('<tr><td>'
								+ m.id_user_destination
								+ '</td><td>'
								+ m.content
								+ '</td><td>'
								+ m.date
								+ '</td><td>'
								+ '<image src="../img/delete.png" style="width: 20px; height: 20px;" class = "2" onclick ="clickDelete(this)" id = "'
								+ m.id + '" >' + '</image>' + '</td></tr>');
			}

		}
	})

}
function clickAdd(span) {

	var id;
	var name;
	$.ajax({
				"url" : "message",
				"type" : "post",
				"async" : false,
				"data" : {
					"msg_id" : span.id,
					"action" : "getDeparture"
				},
				"dataType" : "json",
				"success" : function(data) {
					id = data.id;
					name = data.name;
				}
			});
	$('#recipient-name').attr('name', id);
	$('#recipient-name').val(name);
	$('#myModal').modal('show');

}

function addMsg() {
	$.ajax({
				"url" : "message",
				"type" : "post",
				"data" : {
					"content" : $("#message-text").val(),
					"action" : "add_message",
					"contact_id" : $('#recipient-name').attr('name')
				},

				"dataType" : "text",
				"success" : function(data) {
					swal({
						  position: 'top-end',
						  type: 'success',
						  title: 'Votre message est Enregistr&eacute ',
						  showConfirmButton: false,
						  timer: 2000
						});
					modal_close();
					getSendMessages();

				}
			});

}

function modal_close() {
	$('#recipient-name').attr('name', '');
	$('#recipient-name').val('');
	$("#message-text").val('');
	$('#myModal').modal('hide');

}

function clickDelete(span) {
	
	//Supprimer un message//
	
	const swalWithBootstrapButtons = swal.mixin({
		  confirmButtonClass: 'btn btn-success',
		  cancelButtonClass: 'btn btn-danger',
		  buttonsStyling: false,
		})

		swalWithBootstrapButtons({
		  title: 'Vous &ecirctes sur?',
		  text: "de le supprimer?",
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonText: 'Oui,Supprimer!',
		  cancelButtonText: 'Non, Annuler!',
		  reverseButtons: true
		}).then((result) => {
		  if (result.value) {

	$.ajax({
				"url" : "message",
				"type" : "post",
				"data" : {
					"msg_id" : span.id,
					"action" : "delete",
					"user_id" : $("#user_id", window.parent.document).val()
				},
				"dataType" : "text",
				"success" : function(data) {
					if ($(span).attr('class') == '1') {
						getReceiveMessages()
					} else {
						getSendMessages();

					}
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
		

