<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="../js/jquery.min.js"></script>
<script src="../js/accessoires.js"></script>

<script src="../js/pagination.js"></script>
<script type="text/javascript" src="/js/spin.min.js"></script>

<script src="https://cdn.bootcss.com/spin.js/2.3.2/spin.min.js"></script>
<link rel="stylesheet" href="../js/pagination.css">
<link rel="stylesheet" type="text/css" href="../css/accessoires.css">
<link rel="stylesheet" type="text/css" href="../css/pagination.css">
<!-- Bootstrap core CSS-->
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template-->
<link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">

<!-- Page level plugin CSS-->
<link href="../vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="../css/sb-admin.css" rel="stylesheet">

<div id ="iframe-content" >

	<div id="search">
		<div class="input-group">
			<input type="text" class="form-control custom" placeholder="Rechercher..."
				aria-label="Search" aria-describedby="basic-addon2" id="itemKey"
				onkeydown="keyDown()">
			<div class="input-group-append">
				<button class="btn btn-primary" id = "sub" type="button" onclick="submitKey()">
					<i class="fas fa-search"></i>
				</button>
			</div>
		</div>
	</div>
	<div id="result"></div>
	<div>
		<table id="customers">
			<thead id="th" >
			</thead>
			<tbody id="tb"></tbody>
		</table>
	</div>
	<div id="pagination" class="pagination"></div>
	<div id="loading"></div>

</div>


