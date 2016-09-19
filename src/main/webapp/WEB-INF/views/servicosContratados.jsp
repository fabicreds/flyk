<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">
<body>
	<div class="row center-block" data-ng-init="init()">
		<div ng-repeat="compromisso in $root.data.listaServicosContratados">
			<div class="panel panel-primary">
				<div class="panel-heading">Contrato </div>
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
	</div>
</body>
