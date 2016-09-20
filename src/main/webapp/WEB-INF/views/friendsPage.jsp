<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">
<body>
	<div class="row center-block container" data-ng-init="init()">
		<div ng-repeat="amizade in $root.data.listaAmigos">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-md-4 col-sm-6 col-xs-12 vcenter">
						<div class="text-center">
							<img
								src="${pageContext.request.contextPath}/images/pessoa-feliz.png"
								class="avatar img-circle img-thumbnail" alt="avatar"
								style="height: 150px; width: 150px;">
						</div>
					</div>
					<!--/col-->
					<div class="col-md-3 col-sm-6 col-xs-12 vcenter">
						<p>
							<strong>Nome: </strong>
						<p style="text-indent: 1em;">{{amizade.amigo.nome}}</p>
						</p>
						<p>
							<strong>Data de Início da Amizade: </strong>
						<p style="text-indent: 1em;">{{amizade.dataInicioAmizade}}</p>
						</p>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12 vcenter"
						style="text-align: center;">
						<p>
							<strong>Status da Amizade: </strong>
						<p ng-if="amizade.status == 1">
							<font color="green"> <span
								class="glyphicon glyphicon-ok-sign"></span>
								{{amizade.statusDescricao}}
							</font>
						</p>
						<p ng-if="amizade.status == 2">
							<font color="red"> <span
								class="glyphicon glyphicon-remove-sign"></span>
								{{amizade.statusDescricao}}
							</font>
						</p>
						<p ng-if="amizade.status == 2">
							<font color="blue"> <span
								class="glyphicon glyphicon-question-sign"></span>
								{{amizade.statusDescricao}}
							</font>
						</p>
						</p>
					</div>
				</div>
			</div>
		</div>
		<div ng-if="$root.data.numAmigos == 0" style="text-align: center;">
			<img
				src="${pageContext.request.contextPath}/images/no-friends-minios.png" />
			<h3>Você ainda não tem amigos!</h3>
		</div>
	</div>
</body>
