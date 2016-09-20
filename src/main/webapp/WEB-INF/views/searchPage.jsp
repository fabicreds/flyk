<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">

<body>
	<form class="form-horizontal" name="userForm" novalidate role="form">
	<div class="row form-group container center-block">
	
		<div style="background-color: #266691; color: white">
			<div class="col-sm-3">
			<select class="form-control"
						ng-options="item as item.label for item in tipoBusca track by tipoBusca.id"
						ng-model="tipoBusca"></select>
			</div>
		</div>
		
	</div>
	</form>
</body>
