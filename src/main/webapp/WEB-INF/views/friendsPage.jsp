<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">
<body>
	<div class="row" data-ng-init="init()">
		<div ng-repeat="amizade in $root.data.listaAmigos">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-md-4 col-sm-6 col-xs-12 vcenter">
						<div class="text-center">
							<img
								src="${pageContext.request.contextPath}/images/pessoa-feliz.png"
								class="avatar img-circle img-thumbnail" alt="avatar"
								style="height: 180px; width: 180px;">
						</div>
					</div>
					<!--/col-->
					<div class="col-xs-8 col-sm-4 vcenter">
						<p>
							<strong>Nome: </strong>
						<p style="text-indent: 1em;">{{amizade.amigo.nome}}</p>
						</p>
						<p>
							<strong>Data de Início da Amizade: </strong>
						<p style="text-indent: 1em;">{{amizade.dataInicioAmizade}}</p>
						</p>
						<p>
							<strong>Status da Amizade: </strong>
						<p style="text-indent: 1em;">{{amizade.statusDescricao}}</p>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
