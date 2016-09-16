<script src="${pageContext.request.contextPath}/resources/angular/app/controllers/calendar.js"></script>
<link href="https://fonts.googleapis.com/css?family=Amaranth" rel="stylesheet">
<body>
	<form class="form-horizontal" name="inactivateForm" novalidate
		role="form">
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
								<h3>{{data.usuario}}</h3>
								<p>
									<strong>Nome: </strong>
								<p style="text-indent: 1em;">{{data.nome}}</p>
								</p>
								<p>
									<strong>Email: </strong>
								<p style="text-indent: 1em;">{{data.email}}</p>
								</p>
								<p>
									<strong>CPF/CNPJ: </strong>
								<p style="text-indent: 1em;">{{data.cpf}}</p>
								</p>
								<p>
									<strong>Nascimento: </strong>
								<p style="text-indent: 1em;">{{data.nascimento}}</p>
								</p>
								<p>
									<strong>Perfil: </strong>
								<p style="text-indent: 1em;">{{data.perfil}}</p>
								</p>
								<p>
									<strong>Telefone: </strong>
								<div ng-repeat="telefone in $root.data.listaTelefone">
									<p style="text-indent: 1em;">
										{{telefone.categoriaDescricao}} - ({{telefone.ddd}})
										{{telefone.numero}} - {{telefone.operadoraDescricao}}</p>
								</div>
								</p>
								<p>
									<strong>Endereco: </strong>
								<p style="text-indent: 1em;">
									{{data.endereco.logradouro}} , {{data.endereco.numero}} -
									{{data.endereco.bairro}} <br>
								</p>
								<p style="text-indent: 1em;">{{data.endereco.cidade}} -
									{{data.endereco.estado}} , {{data.endereco.cep}}</p>
								</p>
								
								

							</form>
						</div>
<!-- 						<div class="col-xs-6 col-sm-3 vcenter""> -->
<!-- 							<div class="panel panel-default "> -->
<!-- 								<div class="panel-body"> -->
<!-- 									<p> -->
<!-- 										<strong>Servi�os Contratados: </strong> -->
<!-- 									<p style="text-indent: 1em;"> -->
<!-- 										{{data.numServicosContratados}}</p> -->
<!-- 									</p> -->
<!-- 									<p> -->
<!-- 										<strong>M�dia de Avalia��o: </strong> -->
<!-- 									</p> -->
<!-- 									<p> -->
<!-- 										<strong>N�vel Avaliador: </strong> -->
<!-- 									</p> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
					</div>

					<div class="row">
						<div class="panel panel-default">
							<!-- Se o usu�rio estiver logado e for um prestador -->
							<div class="panel-heading">
								<div ng-if="usuarioLogado !='' && tipoUsuarioLogado!=1">
									<uib-tabset active="active"> <uib-tab index="0"
										heading="Servi�os"> </uib-tab> <uib-tab index="1"
										heading="Hist�rico">TAB2</uib-tab> <uib-tab index="2"
										heading="Agenda">TAB3</uib-tab> <uib-tab index="3"
										heading="Amigos"><br>
										<div ng-repeat="amizade in $root.data.listaAmigos">
											<div class="panel panel-default">
												<div class="panel-body">
													<p>
														<strong>Nome: </strong>
														<p style="text-indent: 1em;">{{amizade.amigo.nome}}</p>
													</p>
													<p>
														<strong>Data de In�cio da Amizade: </strong>
														<p style="text-indent: 1em;">{{amizade.dataInicioAmizade}}</p>
													</p>
													<p>
														<strong>Status da Amizade: </strong>
														<p style="text-indent: 1em;">{{amizade.statusDescricao}}</p>
													</p>
												</div>
											</div>
										</div>
									</uib-tab> </uib-tabset>
								</div>
								<!-- Se o usu�rio estiver logado e for um cliente -->
								<div ng-if="usuarioLogado !='' && tipoUsuarioLogado==1">
									<uib-tabset active="active"> <uib-tab index="0"
										heading="Servi�os Contratados"> <br>
									<div ng-repeat="compromisso in $root.data.agenda">
										<div class="panel panel-default">
											<div class="panel-body">
												<p>
													<strong>Data de In�cio: </strong>
												<p style="text-indent: 1em;">{{compromisso.dataInicio}}</p>
												</p>
												<p>
													<strong>Data de Fim: </strong>
												<p style="text-indent: 1em;">{{compromisso.dataFim}}</p>
												</p>
												<p>
													<strong>Prestador: </strong>
												<p style="text-indent: 1em;">{{compromisso.contrato.prestador.nome}}</p>
												</p>
												<p>
													<strong>Servi�o: </strong>
												<p style="text-indent: 1em;">{{compromisso.contrato.servico}}</p>
												</p>
											</div>
										</div>
									</div>

									</uib-tab> 
									<uib-tab index="1" heading="Agenda">
									</uib-tab> 
									<uib-tab index="2" heading="Amigos">
										<br>
									<div ng-repeat="amizade in $root.data.listaAmigos">
										<div class="panel panel-default">
											<div class="panel-body">
												<p>
													<strong>Nome: </strong>
													<p style="text-indent: 1em;">{{amizade.amigo.nome}}</p>
												</p>
												<p>
													<strong>Data de In�cio da Amizade: </strong>
													<p style="text-indent: 1em;">{{amizade.dataInicioAmizade}}</p>
												</p>
												<p>
													<strong>Status da Amizade: </strong>
													<p style="text-indent: 1em;">{{amizade.statusDescricao}}</p>
												</p>
											</div>
										</div>
									</div>
									</uib-tab> </uib-tabset>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</form>
</body>