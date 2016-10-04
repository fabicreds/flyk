<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">
<body>
	<div class="row center-block container" data-ng-init="init()">
		<div ng-repeat="compromisso in $root.data.listaContratosServicosPrestados">
			<div class="panel" style="border-color: #266691">
				<div class="panel-heading" style="background-color: #266691; color: white">Contrato </div>
				<div class="panel-body">
					<div class="col-md-3">
						<p>
							<strong>Cliente: </strong>
						<p style="text-indent: 1em;">{{compromisso.contrato.cliente.nome}}</p>
						</p>
						<p>
							<strong>Categoria Serviço: </strong>
						<p style="text-indent: 1em;">{{compromisso.contrato.servico.nome}}</p>
						</p>
					</div>
					
					<div class="col-md-3">
						<p>
							<strong>Data de Início: </strong>
						<p style="text-indent: 1em;">{{compromisso.dataInicio}}</p>
						</p>
						<p>
							<strong>Data de Fim: </strong>
						<p style="text-indent: 1em;">{{compromisso.dataFim}}</p>
						</p>
					</div>
					<div class="col-md-3">
						<p>
							<strong>Descrição do Servico: </strong>
						<p style="text-indent: 1em;">{{compromisso.contrato.descricaoServico}}</p>
						</p>
					</div>
					<div class="col-md-2" style="text-align: center;">
						<p>
							<strong>Status: </strong>
						<p ng-if="compromisso.status == 1">
							<font color="blue"> <span
								class="glyphicon glyphicon-question-sign"></span>
								{{compromisso.statusDescricao}}
							</font>
						</p>
						<p ng-if="compromisso.status == 2">
							<font color="green"> <span
								class="glyphicon glyphicon-ok-sign"></span>
								{{compromisso.statusDescricao}}
							</font>
						</p>
						<p ng-if="compromisso.status == 3">
							<font color="red"> <span
								class="glyphicon glyphicon-remove-sign"></span>
								{{compromisso.statusDescricao}}
							</font>
						</p>
						<p ng-if="compromisso.status == 4">
							<font color="green"> <span
								class="glyphicon glyphicon-ok-sign"></span>
								{{compromisso.statusDescricao}}
							</font>
						</p>
						
						<p ng-if="compromisso.status == 4">
							<font color="green"> <span
								class="glyphicon glyphicon-ok-sign"></span>
								{{compromisso.statusDescricao}}
							</font>
						</p>
					</div>
				</div>
			</div>
		</div>
		<div ng-if="$root.data.listaContratosServicosPrestados == null" style="text-align: center;">
			<img
				src="${pageContext.request.contextPath}/images/no-friends-minios.png" />
			<h3>Você ainda não foi contratado!</h3>
		</div>
	</div>
</body>
