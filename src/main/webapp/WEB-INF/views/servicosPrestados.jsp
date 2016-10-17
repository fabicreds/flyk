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
						<p ng-if="compromisso.status >= 3">
							<strong>Descrição do Servico: </strong>
						<p style="text-indent: 1em;" ng-if="compromisso.status >= 3"> R$ {{compromisso.contrato.custo}}</p>
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
							<p>
								<button
								type="submit" class="btn btn-success" ng-click="orcarServico(compromisso)"
								value="Orçar Solicitação" ng-if="compromisso.status == 1"> 
								<span class="glyphicon glyphicon-usd"></span>  Orçar Serviço
								</button>
							</p>
							<p>
								<button
								type="submit" class="btn btn-danger" ng-click="recusarServico(compromisso)"
								value="Recusar Solicitação" ng-if="compromisso.status == 1"> 
								<span class="glyphicon glyphicon-remove-sign"></span>  Recusar Solicitação
								</button>
							</p>
						</p>
						<p ng-if="compromisso.status == 2">
							<font color="red"> <span
								class="glyphicon glyphicon-remove-sign"></span>
								{{compromisso.statusDescricao}}
							</font>
						</p>
						<p ng-if="compromisso.status == 3">
							<font color="green"> <span
								class="glyphicon glyphicon-ok-sign"></span>
								{{compromisso.statusDescricao}}
							</font>
							<p>
								<button
								type="submit" class="btn btn-danger" ng-click="cancelarServico(compromisso)"
								value="Recusar Solicitação" ng-if="compromisso.status == 3"> 
								<span class="glyphicon glyphicon-remove-sign"></span>  Cancelar Solicitação
								</button> 
							</p>
						</p>
						<p ng-if="compromisso.status == 4">
							<font color="green"> <span
								class="glyphicon glyphicon-ok-sign"></span>
								{{compromisso.statusDescricao}}
							</font>
							<p>
								<button
								type="submit" class="btn btn-success" ng-click="realizarServico(compromisso)"
								value="Serviço Realizado" ng-if="compromisso.status == 4"> 
								<span class="glyphicon glyphicon-ok"></span>  Realizar Serviço
								</button> 
							</p>
							<p>
								<button
								type="submit" class="btn btn-danger" ng-click="cancelarServico(compromisso)"
								value="Cancelar Serviço" ng-if="compromisso.status == 4"> 
								<span class="glyphicon glyphicon-remove-sign"></span>  Cancelar Serviço
								</button> 
							</p>
						</p>
						<p ng-if="compromisso.status == 5">
							<font color="red"> <span
								class="glyphicon glyphicon-remove-sign"></span>
								{{compromisso.statusDescricao}}
							</font>
						</p>
						<p ng-if="compromisso.status == 6">
							<font color="green"> <span
								class="glyphicon glyphicon-ok-sign"></span>
								{{compromisso.statusDescricao}}
							</font>
							<p ng-if="compromisso.contrato.avaliacaoContratante==null || compromisso.contrato.avaliacaoContratante==0 ">
								<button
								type="submit" class="btn btn-warning" ng-controller = "servicosPrestadosCtrl" ng-click="avaliarCliente(compromisso)"
								value="Avaliar" ng-if="compromisso.status == 6"> 
								<span class="glyphicon glyphicon-star"></span>  Avaliar Cliente
								</button> 
							</p>
							<p ng-if="compromisso.contrato.avaliacaoContratante!=null && compromisso.contrato.avaliacaoContratante!=0">
								<strong>Avaliação Contratante: </strong>
							<p>
								<div class="rating">
									<font color="yellow">
										<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoContratante>=1"></span>
										<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoContratante>=2"></span>
										<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoContratante>=3"></span>
										<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoContratante>=4"></span>
										<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoContratante>=5"></span>
									</font>
								</div>
							</p>
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
