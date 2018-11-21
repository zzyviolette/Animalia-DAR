getReceiveMessages();
function getAllNewMessages(){
		$.ajax({
				"url" : "message",
				"type" : "post",
				"data" : {
					"action" : "newMessages",
					"user_id" : $("#user_id", window.parent.document).val()
				},
				"dataType" : "json",
				"success" : function(messages, textStatus, jqXHR) {
					$('#msg_th').empty();
					$('#msg_tb').empty();
					if (messages.length > 0) {
						$('#msg_th').append('<tr><td>' + "Departure"
								+ '</td><td>' + "Content" + '</td><td>'
								+ "Date" + '</td><td>' + "Reply" + '</td><td>'
								+ "Delete" + '</td></tr>');

					}

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
										+ '<span onclick ="clickAdd(this)" id = "'
										+ m.id
										+ '" >Reply'
										+ '</span>'
										+ '</td><td>'
										+ '<span onclick ="clickDelete(this)" id = "'
										+ m.id + '" >Delete' + '</span>'
										+ '</td></tr>');
					}

				}
			})

}
function getReceiveMessages() {
	$.ajax({
				"url" : "message",
				"type" : "post",
				"data" : {
					"action" : "display_receive",
					"user_id" : $("#user_id", window.parent.document).val()
				},
				"dataType" : "json",
				"success" : function(messages, textStatus, jqXHR) {
					$('#msg_th').empty();
					$('#msg_tb').empty();
					if (messages.length > 0) {
						$('#msg_th').append('<tr><td>' + "Departure"
								+ '</td><td>' + "Content" + '</td><td>'
								+ "Date" + '</td><td>' + "Reply" + '</td><td>'
								+ "Delete" + '</td></tr>');

					}

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
										+ '<span onclick ="clickAdd(this)" id = "'
										+ m.id
										+ '" >Reply'
										+ '</span>'
										+ '</td><td>'
										+ '<span class = "1" onclick ="clickDelete(this)" id = "'
										+ m.id + '" >Delete' + '</span>'
										+ '</td></tr>');
					}

				}
			})

}
			
//function reloadMessages(){
//	$('#iframe', window.parent.document).attr('src',
//							$('#iframe', window.parent.document).attr('src'));
//}

function getSendMessages() {
	$.ajax({
				"url" : "message",
				"type" : "post",
				"data" : {
					"action" : "display_send",
					"user_id" : $("#user_id", window.parent.document).val()
				},
				"dataType" : "json",
				"success" : function(messages, textStatus, jqXHR) {
					$('#msg_th').empty();
					$('#msg_tb').empty();
					if (messages.length > 0) {
						$('#msg_th').append('<tr><td>' + "Destination"
								+ '</td><td>' + "Content" + '</td><td>'
								+ "Date" + '</td><td>' + "Delete"
								+ '</td></tr>');
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
										+ '<span class = "2" onclick ="clickDelete(this)" id = "'
										+ m.id + '" >Delete' + '</span>'
										+ '</td></tr>');
					}

				}
			})

}
function clickAdd(span) {
	$.ajax({
				"url" : "message",
				"type" : "post",
				"data" : {
					"msg_id" : span.id,
					"action" : "getDeparture"
				},
				"dataType" : "json",
				"success" : function(data) {
					$("#contact_id", window.parent.document).val(data).change();
					var obj = window.parent.document.getElementById("iframe");
					obj.src = "add_message.jsp";
				}
			});

}

function clickDelete(span) {

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
				    if($(span).attr('class')=='1'){
				    	getReceiveMessages()
				    }else{
				    	getSendMessages();
				    	
				    }
				}
			});

}
