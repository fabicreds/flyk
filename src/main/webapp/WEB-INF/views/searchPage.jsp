<!--  <link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">-->
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<body>
	<!-- <form class="form-horizontal" name="userForm" novalidate role="form">
	<div class="row form-group container center-block">
	
	<div style="background-color: #266691; color: white">
			<div class="col-sm-12">
			<select class="form-control"
						ng-options="item as item.label for item in tipoBusca track by tipoBusca.id"
						ng-model="tipoBusca"></select>
			</div>
		</div	
		
	</div>
	</form>
	-->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<form class="navbar-form" ng-submit="find()">

					<select class="form-control" ng-model="campotipoBusca" 
					ng-options="item as item.label for item in tipoBusca">
						
					</select>
					<div class="form-group">
						<div class="input-group" >
							<input type="text" class="form-control" style="width: 400px"
								placeholder="" ng-model="valorBusca">
							<div class="input-group-btn">
								<button class="btn btn-default" style="padding: 9px 12px;"
									type="submit">
									<i class="glyphicon glyphicon-search"></i>
								</button>
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-default">Limpar</button>
				</form>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>


		<div ng-repeat="resultado in listaClientes">
		<div class="col-md-4">
<div class="w3-card-4">

<header class="w3-container w3-light-grey">
  <h3>{{resultado.nome}}</h3>
</header>

<div class="w3-container">
  <p>{{resultado.apelido}}uest</p>

  <hr>
  <!-- <img src="img_avatar3.png" alt="Avatar" class="w3-left w3-circle"> -->
  <div style="position: relative; top: -100px;margin-bottom: -100px;text-align:center">
  <img ng-src={{resultado.fotoPerfil}}
									class="avatar img-circle img-thumbnail" class="w3-left w3-circle" alt="avatar"
									style="height: 200px; width: 200px;">
  <p>{{resultado.perfil}}</p>
</div>
</div>

<button class="w3-btn-block w3-teal" ng-click="sendPostPerfilAmigo(resultado.id, resultado.tipoCadastro)"
								ng-controller="friendsProfilePageCtrl">Ver perfil</button>

</div>
</div>
</div>
	


</body>
