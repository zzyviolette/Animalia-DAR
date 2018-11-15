<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<meta content="" name="description" />

<meta content="" name="author" />


<script src = "../js/jquery.min.js"></script>	
	
<script src = "../js/bootstrap.js"></script>	
	
<script src="../js/message.js"></script>	

<link href="../assets/media/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />

<link href="../assets/media/css/bootstrap-responsive.min.css" rel="stylesheet"
	type="text/css" />

<link href="../assets/media/css/style-metro.css" rel="stylesheet" type="text/css" />

<link href="../assets/media/css/style.css" rel="stylesheet" type="text/css" />

<link href="../assets/media/css/style-responsive.css" rel="stylesheet"
	type="text/css" />
	

<link href="../assets/media/css/inbox.css" rel="stylesheet" type="text/css" />

<link href="../css/message.css" rel="stylesheet" type="text/css" />
<script src="../js/plugins/sweetalert2.all.min.js" type="text/javascript"></script>


<!-- BEGIN PAGE CONTAINER-->
<div class="content" ></div>
<div class="page_content">
<div class="container-fluid" >
	<div class="row-fluid">
		<div class="span10">
			<h3 class="page-title pull-left">
				Boîte de réception
			</h3>
		</div>		
	</div>

	<div class="row-fluid inbox">

		<div class="span2">

			<ul class="inbox-nav margin-bottom-20 pull-left">

				<li id= "receive" class=" inbox receive"  onclick="getReceiveMessages()"><a
					class="btn" href="javascript:;" data-title="receive">Messages reçus</a><b></b></li>

				<li id= "send" class="send" onclick="getSendMessages()"><a class="btn"
					href="javascript:;" data-title="Send">Messages envoyés</a><b></b></li>

			</ul>
		</div>
		<table class="pull-right" id="msg_table">
			<thead id="msg_th">
			</thead>
			<tbody id="msg_tb">
			</tbody>
		</table>

	</div>

<div class="modal fade" id="myModal"  tabindex="-1" role="dialog"  hidden="true" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="myModalLabel">Nouveau message</h5>
      </div>
      <div class="modal-body">
        <form>
         <div class="form-group">
            <label for="recipient-name" class="col-form-label">Destinataire:</label>
            <input type="text" name = ""  class="form-control custom-input" id="recipient-name" disabled="disabled">
          </div> 
          <div class="form-group">
            <label for="message-text" class="col-form-label">Message:</label>
            <textarea class="form-control custom-textarea" id="message-text"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary custom-btn-close" onclick="modal_close()">Fermer</button>
        <button type="button" class="btn btn-primary custom-btn-send" onclick="addMsg()">Envoyer message</button>
      </div>
    </div>
  </div>
</div>
</div>
</div>




