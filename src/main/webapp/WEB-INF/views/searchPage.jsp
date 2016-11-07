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
	<nav class="navbar navbar-default" data-ng-init="carregaCategorias()">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<form class="navbar-form" ng-submit="find()">

					<select class="form-control" ng-model="campotipoBusca"
						ng-options="item as item.label for item in tipoBusca">

					</select> 
					<select class="form-control" ng-model="servicos"
						ng-options="item.id as item.nome for item in categoriasServico">
					</select>
					<div class="form-group">
						<div class="input-group">
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
			<div class="w3-card-4 ">

				<header class="w3-container">
					<h3>{{resultado.nome}}</h3>
				</header>

				<div class="w3-container">
					<p ng-if="resultado.apelido == null"> - </p>
					<p ng-if="resultado.apelido != null"> {{resultado.apelido}}</p>
					<hr>
					{{resultado.telefone}} <img ng-src={{resultado.fotoPerfil}}
						alt="Avatar" class="w3-left w3-circle"
						style="height: 90px; width: 90px; margin-right: 6px;">
					<p>{{resultado.endereco.cidade}}</p>
					
					<p>{{resultado.perfil}}</p>
					<div ng-repeat="categoria in resultado.listaCategoriaServicosPrestados track by $index ">
						<p>{{categoria.nome}}</p>
					</div>
				</div>
				
				<button class="w3-btn-block w3-teal"
					ng-click="sendPostPerfilAmigo(resultado.id, resultado.tipoCadastro)"
					ng-controller="friendsProfilePageCtrl">Ver perfil</button>
			</div>
		</div>
		
	</div>






</body>
