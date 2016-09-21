<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">
<body>
	<div class="row center-block container" data-ng-init="init()">
		<div ng-repeat="compromisso in $root.data.listaServicosContratados">
			<div class="panel" style="border-color: #266691">
				<div class="panel-heading" style="background-color: #266691; color: white">Contrato </div>
				<div class="panel-body">
					<div class="col-md-6 col-sm-6 col-xs-12">
						<p>
							<strong>Prestador: </strong>
						<p style="text-indent: 1em;">{{compromisso.contrato.prestador.nome}}</p>
						</p>
						<p>
							<strong>Servi�o: </strong>
						<p style="text-indent: 1em;">{{compromisso.contrato.servico.nome}}
							- {{compromisso.contrato.servico.descricao}}</p>
						</p>
					</div>
					<div class="col-md-4 col-sm-6 col-xs-12">
						<p>
							<strong>Data de In�cio: </strong>
						<p style="text-indent: 1em;">{{compromisso.dataInicio}}</p>
						</p>
						<p>
							<strong>Data de Fim: </strong>
						<p style="text-indent: 1em;">{{compromisso.dataFim}}</p>
						</p>
					</div>
				</div>
			</div>
		</div>
		<div ng-if="$root.data.listaServicosContratados == null" style="text-align: center;">
			<img
				src="${pageContext.request.contextPath}/images/no-friends-minios.png" />
			<h3>Voc� ainda n�o contratou nenhum servi�o!</h3>
		</div>
	</div>
</body>
