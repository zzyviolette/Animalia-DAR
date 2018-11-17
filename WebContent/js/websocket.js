

var ws = new WebSocket("ws://127.0.0.1:18888");
window.onload=function(){ 
	console.log("window.onload");
	check_status();
};
ws.onopen = function()
{
};
ws.onmessage = function(evt)
{
	var data = [];
	try {
		if(!Array.isArray(evt.data)){
			data = JSON.parse(evt.data);
		}
    } catch(e) {
    	console.log("onmessage : ",evt.data);
    }
    
	var span = document.getElementById('notificationnumber');
	while( span.firstChild ) {
	    span.removeChild( span.firstChild );
	}
	span.appendChild(document.createTextNode(data.length));
	
	var content = document.getElementById('notificationcontent');
	
	while( content.firstChild ) {
		content.removeChild( content.firstChild );
	}
	
	data.forEach(function(notif) {
		$('#notificationcontent').append(
				"<li> <a href=\"javascript:deleteNotification("+notif.id+");\"> <span class=\"details\"> <span class=\"label label-sm label-icon label-info\"> <i class=\"fa fa-bullhorn\"></i> </span>" +
				notif.content +
               "</span> </a> </li>"
		);
	});
	
};
ws.onclose = function(evt)
{
	console.log("ws.onclose");
	alert("connection interrupted!");
};
ws.onerror = function(evt)
{
	console.log("ws.onerror");
	alert("WebSocketError!");
};
function check_status(){
	console.log("check_status");
	$.ajax({
		url:'/Projet_dar/notification?action=initNotif',
		type: 'GET',
		async:false,
		success:function(json){
			console.log(json);
			sendMsg("data");
			/*if(json.code==200){
				$('#login_div').html('<form action="/websocket/LogoutServlet" >login user: <span id="user_name">'+json.data+'</span>  <input type="submit" method="post" value="logout"/></form>');
				$('#system_msg').show();
				$('#chat_area').show();
				sendMsg(json.data);
			}*/
		},
		error:function(json){
			alert('cannot connect to server, please check your network and server\'s status.');
		}
	})
}
check_status();
function sendMsg(message){
	console.log("sendMsg");
	var t;
	t=setInterval(function(){ 
		if(ws.readyState == ws.OPEN) { 
			clearInterval(t);
			ws.send(message);
		} 
	}, 200); 
}
function sendChatMsg(){
	console.log("sendChatMsg");
	sendMsg($('#input_content').val());
	$('#input_content').val('');
}
function selectImage(){
	$('#image_select').click();
}

function changeImage(file){
	console.log("changeImage");
	var name = $('#user_name').html();
	var date = new Date().getTime();
	$('#chat_content').append("<font style=\"color:green\">"+name+"  "+new Date().Format("yyyy-MM-dd HH:mm:ss")+"</font></br><div><img id=\""+date+"\" class=\"preview\" style=\"max-width:31%;\" src=\"<?=IMG_URL.$cost['cover'].'_s.jpg';?>\"><img src=\"assets/image/loading1.gif\"/></div><br>");
	if (file.files && file.files[0]){  
        var reader = new FileReader();  
        reader.onload = function(evt){  
	        $('#'+date).attr('src' , evt.target.result);
	    }    
        reader.readAsDataURL(file.files[0]);  
    }else{  
    	$('#'+date).attr('src' , file.value);
    } 
}
function login(){
	console.log("login");
	var content = document.getElementById("content").value;
	$.ajax({
		url:'/Projet_dar/notification',
		data: {notification:content, action:addNotif},
		type:'POST',
		async:false,
		success: function(data){
			if(data==200){
				if (content == null || content == "") {
					ws.send("tourist");
				} else {
					ws.send(content);
				}
				$('#login_div').html('<form action="/websocket/LogoutServlet" >login user:<span id="user_name">'+content+'</span>  <input type="submit" method="post" value="logout"/></form>');
				$('#system_msg').show();
				$('#chat_area').show();
			}else{
				alert('login failed. please check backend logs.');
			}
		},
		error:function(data){
			alert('login failed. please check your network.');
		}
	});
}

function deleteNotification(id){
	console.log(id);
	$.ajax({
		url:'/Projet_dar/notification',
		data: {"id":id, "action":"deleteNotif"},
		type:'POST',
		async:false,
		success: function(data){
			if(data==200){
				check_status();
			}else{
				console.log('login failed. please check backend logs.');
			}
		},
		error:function(data){
			console.log('login failed. please check your network.');
		}
	});
}

//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
 var o = {
     "M+": this.getMonth() + 1, 
     "d+": this.getDate(), 
     "h+": this.getHours(), 
     "m+": this.getMinutes(), 
     "s+": this.getSeconds(), 
     "q+": Math.floor((this.getMonth() + 3) / 3), 
     "S": this.getMilliseconds() 
 };
 if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
 for (var k in o)
 if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
 return fmt;
}

