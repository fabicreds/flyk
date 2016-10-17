<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">
<style>
.avg-half-rate span:after{
    
    transition-property: margin-left, text-indent;
    transition-duration: 500ms;
    
    -webkit-transition-property: margin-left, text-indent;
    -webkit-transition-duration: 500ms;
    
    color: grey;
    content: "\e006";
    text-indent: 0em;
    margin-left: -1em;
    overflow: hidden;
    display: inline-block;
    position: relative;
    text-indent: -0.45em;
    margin-left: -0.55em;
}

</style>
<body>
	<div class="row center-block container" data-ng-init="init()">
		<div ng-repeat="compromisso in $root.data.listaServicosContratados">
			<div class="panel" style="border-color: #266691">
				<div class="panel-heading" style="background-color: #266691; color: white">Contrato </div>
				<div class="panel-body">
					<div class="col-md-3">
						<p>
							<strong>Prestador: </strong>
						<p style="text-indent: 1em;">{{compromisso.contrato.prestador.nome}}</p>
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
					<div class="col-md-3" style="text-align: center;">
						<p>
							<strong>Status: </strong>
						<p ng-if="compromisso.status == 1">
							<font color="blue"> <span
								class="glyphicon glyphicon-question-sign"></span>
								{{compromisso.statusDescricao}}
							</font>
							<p>
								<button
								type="submit" class="btn btn-success disabled"
								value="Solicitação Enviada" ng-if="compromisso.status == 1"> 
								<span class="glyphicon glyphicon-ok"></span>  Solicitação Enviada
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
								type="submit" class="btn btn-success" ng-click="sendMarcarServico(compromisso)" ng-controller="servicosContratadosCtrl"
								value="Marcar Solicitação" ng-if="compromisso.status == 3"> 
								<span class="glyphicon glyphicon-ok"></span>  Marcar Solicitação
								</button>
							</p>
							<p>
								<button
								type="submit" class="btn btn-danger"  ng-click="cancelarServico(compromisso)"
								value="Cancelar Solicitação" ng-if="compromisso.status == 3"> 
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
								type="submit" class="btn btn-danger"  ng-click="cancelarServico(compromisso)"
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
						<p ng-if="compromisso.status == 6 ">
							<font color="green"> <span
								class="glyphicon glyphicon-ok-sign"></span>
								{{compromisso.statusDescricao}}
							</font>
							<p>
								<button
								type="submit" class="btn btn-warning"  ng-controller = "servicosContratadosCtrl" ng-click="avaliarPrestador(compromisso)"
								value="Avaliar" ng-if="compromisso.status == 6 && compromisso.contrato.dataAvaliacaoServico==null"> 
								<span class="glyphicon glyphicon-star"></span>  Avaliar Prestador
								</button> 
							</p>
						</p>
						<p ng-if="compromisso.contrato.dataAvaliacaoServico!=null">
							<strong>Média avaliação Prestador: </strong>
							<p>
								<div class="rating">
									<font color="yellow">
										<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.mediaAvaliacao>=1"></span>
										<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.mediaAvaliacao>=2"></span>
										<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.mediaAvaliacao>=3"></span>
										<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.mediaAvaliacao>=4"></span>
										<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.mediaAvaliacao>=5"></span>
										<i class="avg-half-rate">
											<span class="glyphicon glyphicon-star" ng-if="(compromisso.contrato.avaliacaoPrestador.mediaAvaliacao>1 && compromisso.contrato.avaliacaoPrestador.mediaAvaliacao<2) || (compromisso.contrato.avaliacaoPrestador.mediaAvaliacao>2 && compromisso.contrato.avaliacaoPrestador.mediaAvaliacao<3)|| (compromisso.contrato.avaliacaoPrestador.mediaAvaliacao>3 && compromisso.contrato.avaliacaoPrestador.mediaAvaliacao<4)|| (compromisso.contrato.avaliacaoPrestador.mediaAvaliacao>4 && compromisso.contrato.avaliacaoPrestador.mediaAvaliacao<5)"></span>
										</i>
									</font>
								</div>
							</p>
							<p ng-if="compromisso.contrato.dataAvaliacaoServico!=null">
							{{compromisso.contrato.avaliacaoPrestador.mediaAvaliacao}}
							</p>
						</p>
					</div>
				</div>
			</div>
		</div>
		<div ng-if="$root.data.listaServicosContratados == null" style="text-align: center;">
			<img
				src="${pageContext.request.contextPath}/images/no-friends-minios.png" />
			<h3>Você ainda não contratou nenhum serviço!</h3>
		</div>
	</div>
</body>
