function submitKey() {
	$.ajax({
				"url" : "accessoires",
				"type" : "post",
				"data" : {
					"action" : "search",
					"obj" : $('#itemKey').val()
				},
				"dataType" : "json",
				"beforeSend" : function() {
					$('#th').empty();
					$('#tb').empty();
					$('#result').empty();
					$('#pagination').empty();
					$("#loading")
							.append("<image src='../img/loading.gif'></image>");
				},
				"success" : function(items) {
						$("#loading").empty();
                    					
					if (items.length > 0) {
						data = items;
					    $("#th").append('<tr><th>Photo</th><th>Titre</th><th>Prix</th></tr>');
						initUI(1, 5);

					} else {
						$("#result").append("<label>Aucun résultat trouvé pour  "
								+ $('#itemKey').val() + "</label>");
					}
				}

			});
}

function keyDown() {

	if (event.keyCode == "13") {
		submitKey();
	}

}

function initUI(pageNo, pageSize) {
	 $('#tb').empty();
	for (var i = (pageNo - 1) * pageSize; i < pageNo * pageSize; i++) {
		var item = data[i];
		$('#tb')
				.append('<tr><td>' + '<image src="' + item.photo + '"></image>'
						+ '</td><td><a target= "_blank"' + ' href="'
						+ item.info + '">' + item.titre + '</a></td>' + '<td>'
						+ item.price + '</td></tr>');

	}

	pagination({
				cur : pageNo,
				total : Math.ceil(data.length/10),
				len : 5,
				targetId : 'pagination',
				callback : function() {
					var me = this;
					var oPages = document.getElementsByClassName('page-index');
					for (var i = 0; i < oPages.length; i++) {
						oPages[i].onclick = function() {
							initUI(this.getAttribute('data-index'), 5);
						}
					}
					var goPage = document.getElementById('go-search');
					goPage.onclick = function() {
						var index = document.getElementById('yeshu').value;
						if (!index || (+index > me.total) || (+index < 1)) {
							return;
						}
						initUI(index, 5);
					}
				}
			});

}
