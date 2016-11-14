<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">
<style>
.footer {
	position: fixed;
	bottom: 0;
	width: 100%;
}

.dropdown-menu {
	width: 300px;
	margin-top: 100px;
	margin-left: 100px;
	margin-right: 50px;
	text-align: left;
}
</style>
<html ng-app="flyk">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>FLYK ®</title>

<script
	src="${pageContext.request.contextPath}/resources/angular/angular.js"></script>
	
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.3/angular-animate.js"></script>
<script src="https://cdn.jsdelivr.net/ngstorage/0.3.6/ngStorage.min.js"></script>

<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/css/app.css"
	rel="stylesheet" />
<script
	src="${pageContext.request.contextPath}/resources/angular/angular-ui/ui-bootstrap-tpls.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/angular-route.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/app.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/controllers/cadastro.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/angular/app/controllers/adminPage.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/controllers/loginPage.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/controllers/userPageInfos.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/controllers/confirmaPromocao.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/controllers/profilePage.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/controllers/atualizaPerfil.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/controllers/searchPage.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/controllers/friendsPage.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/controllers/servicosContratados.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/controllers/servicosPrestados.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/controllers/friendsProfilePage.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/controllers/contratarServico.js"></script>
<script

	src="${pageContext.request.contextPath}/resources/angular/app/controllers/avaliarServico.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/controllers/talkWithFriend.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/controllers/contato.js"></script>
	<script
	src="${pageContext.request.contextPath}/resources/angular/app/controllers/homePage.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/uploadFactory.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/directives/ngFileSelect.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/directives/ValidationDirective.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/directives/uiCPFdirective.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/directives/uiDateDirective.js"></script>
	<script
	src="${pageContext.request.contextPath}/resources/angular/app/directives/uiDateCardDirective.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/directives/uiTelefoneDirective.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/directives/uiCEPDirective.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/directives/uiNumberCardDirective.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/angular/app/directives/fileReaderDirective.js"></script>
<!--   <script src="//rawgithub.com/GoDisco/ngFacebook/master/ngFacebook.js"></script> -->


</head>


<body class="">
	<!-- Responsável por responsible pages-->
	<!--  <employee-form/>  Directive created to this new form.-->

	<nav class="navbar navbar-default menu" ng-controller="flykCtrl">
		<div ng-if="usuarioLogado ==''">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand"> <img src="images/flyklogo.png"
						style="height: 30px; width: 30px" />
					</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#"> <span
								class="glyphicon glyphicon-home"></span> <span class="text">HOME</span>
						</a></li>
						<li><a href="#"> <span class="glyphicon glyphicon-user"></span>
								<span class="text">SOBRE NÓS</span>
						</a></li>
						<li><a href="#"> <span class="glyphicon glyphicon-search"></span>
								<span class="text">COMO USAR</span>
						</a></li>
						<li><a ng-click="showClientsPage()"> <span
								class="glyphicon glyphicon-heart-empty"></span> <span
								class="text">CLIENTES</span>
						</a></li>
						<li><a ng-click="showContatoPage()" ng-controller="contatoCtrl"> <span
								class="glyphicon glyphicon-envelope"></span> <span class="text">CONTATO</span>
						</a></li>

					</ul>
					<ul class="nav navbar-nav navbar-right">

						<li class="link"><a ng-click="showLoginModal()"
							ng-controller="loginPageCtrl"> <!-- <button type="submit" class="btn btn-success">
									<i class="glyphicon glyphicon-log-in"></i> Log In
								</button>--> <span class="glyphicon glyphicon-log-in"></span> <span
								class="text">LOGIN</span>
						</a></li>


						<!--   <li >
                    	<a ng-click="showFormCadastro()">
                    		<!-- <button type="submit" class="btn btn-info">
									<i class="glyphicon glyphicon-plus"></i> Crie uma conta
								</button>
								<span class="glyphicon glyphicon-plus"></span>
								<span class="text">CRIE UMA CONTA</span>
                    		</a></li>
                  -->
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container -->
		</div>
		<!-- ng-if -->
		<!-- Se já estiver logado e for administrador -->
		<div ng-if="usuarioLogado !='' && tipoUsuarioLogado==4">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand"> <img src="images/flyklogo.png"
						style="height: 30px; width: 30px" />
					</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="link"><a ng-click="adminPage()" ng-controller="adminPageCtrl"> <span
								class="glyphicon glyphicon-home"></span> <span class="text">HOME</span>
						</a></li>
<!-- 						<li><a href="#"> <span class="glyphicon glyphicon-user"></span> -->
<!-- 								<span class="text">SOBRE NÓS</span> -->
<!-- 						</a></li> -->
<!-- 						<li><a href="#"> <span class="glyphicon glyphicon-search"></span> -->
<!-- 								<span class="text">COMO USAR</span> -->
<!-- 						</a></li> -->
<!-- 						<li id="linkadm"><a href="#"> <span -->
<!-- 								class="glyphicon glyphicon-heart-empty"></span> <span -->
<!-- 								class="text">ADMINISTRADOR</span> -->
<!-- 						</a></li> -->
<!-- 						<li><a ng-click="showFormLogin()"> <span -->
<!-- 								class="glyphicon glyphicon-envelope"></span> <span class="text">CONTATO</span> -->
<!-- 						</a></li> -->

					</ul>
					<ul class="nav navbar-nav navbar-right">

						<li class="link"><a ng-click="logOff()"
							ng-controller="loginPageCtrl"> <!-- <button type="submit" class="btn btn-success">
									<i class="glyphicon glyphicon-log-out"></i> Sair
							</button>--> <span class="glyphicon glyphicon-log-out"></span> <span
								class="text">SAIR</span>
						</a></li>

					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container -->
		</div>
		<!-- ng-if -->

		<!-- Se já estiver logado e  NÃO for administrador -->
		<div ng-if="usuarioLogado !='' && tipoUsuarioLogado!=4">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand"> <img src="images/flyklogo.png"
						style="height: 30px; width: 30px" />
					</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="link"><a ng-click="showSearchPage()" ng-controller="searchPageCtrl"> <span
								class="glyphicon glyphicon-search"></span> <span class="text">BUSCA</span>
						</a></li>
						<li class="link"><a ng-click="showProfilePage()"
							ng-controller="profilePageCtrl"> <span
								class="glyphicon glyphicon-user"></span> <span class="text">PERFIL</span>
						</a></li>
						<li class="link"><a ng-click="showFriendsPage()"
							ng-controller="friendsPageCtrl"> <span
								class="glyphicon glyphicon-heart-empty"></span> <span
								class="text">AMIGOS</span>
						</a></li>
						<li class="link"><a ng-click="showServicosContratados()"
							ng-controller="servicosContratadosCtrl"> <span
								class="glyphicon glyphicon-list-alt"></span> <span class="text">SERVIÇOS
									CONTRATADOS</span>
						</a></li>
						<li class="link" ng-if="tipoUsuarioLogado!=1"><a ng-click="showServicosPrestados()"
							ng-controller="servicosPrestadosCtrl"> <span
								class="glyphicon glyphicon-wrench"></span> <span class="text">SERVIÇOS
									PRESTADOS</span>
						</a></li>
						<li class="link" ><a ng-click="showTalkWithFriend()"
							ng-controller="talkCtrl"> <span
								class="glyphicon glyphicon-comment"></span> <span class="text">CHAT</span>
						</a></li>

					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a ng-click="showProfilePageEdit()"> <span
								class="glyphicon glyphicon-user"></span>
						</a></li>
						<li class="link"><a ng-click="logOff()"
							ng-controller="loginPageCtrl"> <!-- <button type="submit" class="btn btn-success">
									<i class="glyphicon glyphicon-log-out"></i> Sair
							</button>--> <span class="glyphicon glyphicon-log-out"></span> <span
								class="text">SAIR</span>
						</a></li>

					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container -->
		</div>
		<!-- ng-if -->
	</nav>
	<div ng-view>
<%-- 	<div ng-view style="background-image: url('${pageContext.request.contextPath}/images/ferramentas-fundo.jpg')"> --%>
		<!--Permite rotear a página-->

	</div>


</body>


</html>

