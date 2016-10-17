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
							<strong>Categoria Servi�o: </strong>
						<p style="text-indent: 1em;">{{compromisso.contrato.servico.nome}}</p>
						</p>
					</div>
					
					<div class="col-md-3">
						<p>
							<strong>Data de In�cio: </strong>
						<p style="text-indent: 1em;">{{compromisso.dataInicio}}</p>
						</p>
						<p>
							<strong>Data de Fim: </strong>
						<p style="text-indent: 1em;">{{compromisso.dataFim}}</p>
						</p>
					</div>
					<div class="col-md-3">
						<p>
							<strong>Descri��o do Servico: </strong>
						<p style="text-indent: 1em;">{{compromisso.contrato.descricaoServico}}</p>
						</p>
						<p ng-if="compromisso.status >= 3">
							<strong>Descri��o do Servico: </strong>
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
								value="Or�ar Solicita��o" ng-if="compromisso.status == 1"> 
								<span class="glyphicon glyphicon-usd"></span>  Or�ar Servi�o
								</button>
							</p>
							<p>
								<button
								type="submit" class="btn btn-danger" ng-click="recusarServico(compromisso)"
								value="Recusar Solicita��o" ng-if="compromisso.status == 1"> 
								<span class="glyphicon glyphicon-remove-sign"></span>  Recusar Solicita��o
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
								value="Recusar Solicita��o" ng-if="compromisso.status == 3"> 
								<span class="glyphicon glyphicon-remove-sign"></span>  Cancelar Solicita��o
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
								value="Servi�o Realizado" ng-if="compromisso.status == 4"> 
								<span class="glyphicon glyphicon-ok"></span>  Realizar Servi�o
								</button> 
							</p>
							<p>
								<button
								type="submit" class="btn btn-danger" ng-click="cancelarServico(compromisso)"
								value="Cancelar Servi�o" ng-if="compromisso.status == 4"> 
								<span class="glyphicon glyphicon-remove-sign"></span>  Cancelar Servi�o
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
								<strong>Avalia��o Contratante: </strong>
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
			<h3>Voc� ainda n�o foi contratado!</h3>
		</div>
	</div>
</body>
