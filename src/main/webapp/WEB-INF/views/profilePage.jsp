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
		role="form" ng-submit="pesquisar()" ng-controller="profilePageCtrl">
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
								<h3>{{data.nome}}</h3>
								<p>
									<strong>Apelido: </strong> 
									<p>{{data.apelido}}</p>
								</p>
								<p>
									<strong>Email: </strong> 
									<p>{{data.email}}</p>
								</p>
								<p>
									<strong>Endereco: </strong> 
									<p>
									{{data.endereco.logradouro}} , {{data.endereco.numero}} - {{data.endereco.bairro}} <br>
									{{data.endereco.cidade}} - {{data.endereco.estado}} , {{data.endereco.cep}}
									</p>
								</p>
								<p>
									<strong>Perfil: </strong> 
									<p>{{data.tipoCadastro}}</p>
								</p>
								<p>
									<strong>Telefone: </strong> 
									<div ng-repeat="telefone in data.listaTelefone"> 
										{{telefone.categoria}} - ({{telefone.ddd}})  {{telefone.numero}} - {{telefone.operadora}}
									</div>
								</p>
								<p>
									<strong>CPF/CNPJ: </strong> 
									<p>{{data.cpf}}</p>
								</p>
								<p>
									<strong>Nascimento: </strong> 
									<p>{{data.nascimento}}</p>
								</p>
							</form>
						</div>
						<div class="col-xs-6 col-sm-3 vcenter"">
							<div class="panel panel-default ">
								<div class="panel-body">
									<p>
										<!-- 									<span class="glyphicon glyphicon-wrench"></span> -->
										<strong>Servi�os Contratados: </strong>
									</p>
									<p>
										<strong>M�dia de Avalia��o: </strong> 
									</p>
									<p>
										<strong>N�vel Avaliador: </strong> 
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
									<uib-tabset active="active">
    									<uib-tab index="0" heading="Servi�os">
    									
    									</uib-tab>
    									<uib-tab index="1" heading="Hist�rico">TAB2</uib-tab>
    									<uib-tab index="2" heading="Agenda">TAB3</uib-tab>
    									<uib-tab index="3" heading="Amigos">TAB4</uib-tab>
   										
  									</uib-tabset>
								</div>
								<!-- Se o usu�rio estiver logado e for um cliente -->
								<div ng-if="usuarioLogado !='' && tipoUsuarioLogado==1">
									 <uib-tabset active="active">
    									<uib-tab index="0" heading="Servi�os Contratados">Teste 123</uib-tab>
    									<uib-tab index="1" heading="Agenda">TAB2</uib-tab>
    									<uib-tab index="2" heading="Amigos">TAB3</uib-tab>
  									</uib-tabset>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</form>
</body>