<script
	src="${pageContext.request.contextPath}/resources/angular/angular.js"></script>
<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/app.css"
	rel="stylesheet" />
</head>
<body>
	<form class="form-horizontal" name="inactivateForm" novalidate
		role="form" ng-submit="pesquisar()" ng-controller="perfilPageCtrl">
		<div class="row" data-ng-init="">
			<div class="well panel panel-default">
				<div class="panel-heading">
					<h3>Perfil do Usu�rio</h3>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-4 col-sm-6 col-xs-12 vcenter">
							<div class="text-center">
								<img
									src="${pageContext.request.contextPath}/images/pessoa-feliz.png"
									class="avatar img-circle img-thumbnail" alt="avatar"
									style="height: 200px; width: 200px;">
							</div>
						</div>
						<!--/col-->
						<div class="col-xs-8 col-sm-4 vcenter">
							<form class="form-horizontal" name="inactivateForm" novalidate
								role="form">
								<h3>{{usuario.nome}}</h3>
								<p>
									<strong>Apelido: </strong> {{data}}
								</p>
								<p>
									<strong>Email: </strong> {{data.nome}}
								</p>
								<p>
									<strong>Endereco: </strong> {{data.endereco}}
								</p>
								<p>
									<strong>Perfil: </strong> {{data.tipoCadastroDescricao}}
								</p>
								<p>
									<strong>Telefone: </strong> {{data.telefone}}
								</p>
								<p>
									<strong>CPF/CNPJ: </strong> {{data.cpfCnpj}}
								</p>
							</form>
						</div>
						<div class="col-xs-6 col-sm-3 vcenter"">
							<div class="panel panel-default ">
								<div class="panel-body">
									<p>
										<!-- 									<span class="glyphicon glyphicon-wrench"></span> -->
										<strong>Servi�os Contratados: </strong>
										{{data.numServicosContratados}}
									</p>
									<p>
										<strong>M�dia de Avalia��o: </strong> {{data.endereco}}
									</p>
									<p>
										<strong>N�vel Avaliador: </strong> {{data.telefone}}
									</p>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="panel panel-default">
							<!-- Se o usu�rio estiver logado e for um prestador -->
							<div class="panel-heading">
								<div ng-if="usuarioLogado !='' && tipoUsuarioLogado==2">
									<ul class="nav nav-tabs">
										<li class="active"><a data-toggle="tab" href="#home">Home</a></li>
										<li><a data-toggle="tab" href="#menu1">Menu 1</a></li>
										<li><a data-toggle="tab" href="#menu2">Menu 2</a></li>
									</ul>

									<div class="tab-content clearfix">
										<div id="home" class="tab-pane fade in active">
											<h3>HOME</h3>
											<p>Some content.</p>
										</div>
										<div id="menu1" class="tab-pane fade">
											<h3>Menu 1</h3>
											<p>Some content in menu 1.</p>
										</div>
										<div id="menu2" class="tab-pane fade">
											<h3>Menu 2</h3>
											<p>Some content in melnu 2.</p>
										</div>
									</div>
								</div>
								<!-- Se o usu�rio estiver logado e for um cliente -->
								<div ng-if="usuarioLogado !='' && tipoUsuarioLogado==1">
									<ul class="nav nav-tabs">
										<li class="active"><a data-toggle="tab" href="#home">Home</a></li>
										<li><a data-toggle="tab" href="#menu1">Menu 1</a></li>
										<li><a data-toggle="tab" href="#menu2">Menu 2</a></li>
									</ul>

									<div class="tab-content">
										<div id="home" class="tab-pane fade in active">
											<h3>HOME</h3>
											<p>Some content.</p>
										</div>
										<div id="menu1" class="tab-pane fade">
											<h3>Menu 1</h3>
											<p>Some content in menu 1.</p>
										</div>
										<div id="menu2" class="tab-pane fade">
											<h3>Menu 2</h3>
											<p>Some content in menu 2.</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</form>
</body>