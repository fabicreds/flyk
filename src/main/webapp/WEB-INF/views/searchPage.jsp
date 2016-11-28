
<!--  <link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">-->
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<body>

	<nav class="navbar navbar-default" data-ng-init="carregaCategorias()">
		<div class="container-fluid" ng-init="init()">
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<form class="navbar-form" ng-submit="find()" class="form-group">

					<div class="form-group">
						<div class="input-group">
							<input type="text" class="form-control" style="width: 100px"
								ng-model="nomeCidade" ng-init="nomeCidade = cidade">
							<span class="glyphicon glyphicon-globe form-control-feedback"></span>
						</div>
					</div>

					<div auto-complete-multi display-property="nome" class="form-group"
						list-formatter="listFormatter"
						placeholder="Seleciona uma categoria" source="source">
						<select ng-model="foo"></select>
					</div>
					
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

		</div>

	</nav>

	<div ng-repeat="resultado in listaClientes">
		<div class="col-md-4">

			<div class="w3-card-4" style="height: 400px">

				<div class="w3-container w3-center">
					<header class="w3-container">
						<h3>{{resultado.nome}}</h3>
						<p>{{resultado.perfil}}</p>
					</header>

					<img ng-src={{resultado.fotoPerfil}} class="w3-center w3-circle"
						alt="Avatar" style="width: 150px; height: 150px">

					<div class="w3-container">
						<div
							ng-if="resultado.tipoCadastro ==2 || resultado.tipoCadastro ==3">
							<div
								ng-repeat="categoria in resultado.listaCategoriaServicosPrestados track by $index"
								ng-show="$index<3">
								<ul class="w3-ul">
									<li>{{categoria.nome}}</li>
								</ul>
							</div>
							<div class="w3-margin-top"
								ng-hide="isUndefined(resultado.listaRecomendacoesRecebidas)">
								<p>
									<span class="glyphicon glyphicon-heart " style="color: #e91e63"></span>
									Recomendado
								</p>
								<div class="w3-center">
									<font color="yellow" size="5"> <span
										class="glyphicon glyphicon-star"
										ng-if="resultado.mediaEstrelas>=1"></span> <span
										class="glyphicon glyphicon-star"
										ng-if="resultado.mediaEstrelas>=2"></span> <span
										class="glyphicon glyphicon-star"
										ng-if="resultado.mediaEstrelas>=3"></span> <span
										class="glyphicon glyphicon-star"
										ng-if="resultado.mediaEstrelas>=4"></span> <span
										class="glyphicon glyphicon-star"
										ng-if="resultado.mediaEstrelas>=5"></span>
									</font>
								</div>
							</div>
						</div>
						<div ng-if="resultado.tipoCadastro ==1">
							<div
								ng-repeat="categoria in resultado.listaCategoriaServicosPrestados track by $index ">
								<ul class="w3-ul">
									<li>{{categoria.nome}}</li>
								</ul>
							</div>

							<div class="w3-margin-top">
								<ul class="w3-ul">
									<p>{{resultado.numServicosContratados}} serviços
										contratador</p>
									<p>{{resultado.endereco.cidade}}</p>
								</ul>

							</div>

						</div>
					</div>
					<footer class="w3-container w3-display-bottommiddle"
						style="padding: 0; width: 96%">
						<button class="w3-btn-block w3-teal" style="width: 100%;"
							ng-click="sendPostPerfilAmigo(resultado.id, resultado.tipoCadastro)"
							ng-controller="friendsProfilePageCtrl">Ver perfil</button>
					</footer>
				</div>

			</div>
		</div>



	</div>

	<div ng-hide="isUndefined(msgErro)">
		<p>Nenhum resultado encontrado. Tente novamente.</p>
	</div>


</body>
