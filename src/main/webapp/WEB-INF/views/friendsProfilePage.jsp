<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">
<body>
	<form class="form-horizontal" name="inactivateForm" novalidate
		role="form">
		<div class="row  center-block container" data-ng-init="">
			<div class="well panel panel-default">
				<div class="panel-heading">
					<div class="col-md-4 vcenter">
						<font size="5">Perfil Amigo - {{$root.data.amigo.usuario}}</font>
					</div>
					<div class="col-md-7 vcenter" style="text-align: right;">
						<input type="submit" class="btn btn-danger"
							value="Desfazer Amizade"
							ng-click="sendPostDesfazerAmizade(data.amigo.id)"
							ng-if="data.amigo.statusAmizade == 1"> 
						<input
							type="submit" class="btn btn-primary" value="Contratar Serviço"
							ng-click="carregarTelaContratarServico()"
							ng-controller="contratarServicoCtrl"
							ng-if="data.amigo.statusAmizade == 1 && data.amigo.tipoCadastro!=1">
						<input type="submit" class="btn btn-primary"
							value="Solicitar Amizade"
							ng-click="sendPostSolicitarAmizade(data.amigo.id)"
							ng-if="data.amigo.statusAmizade == 2"> 
						<input
							type="submit" class="btn btn-success disabled"
							value="Solicitação Enviada" ng-if="data.amigo.statusAmizade == 3"
							readonly="readonly"> 
						<input type="submit"
							class="btn btn-success" value="Aceitar Amizade"
							ng-click="sendPostAceitarAmizade(data.amigo.id)"
							ng-if="data.amigo.statusAmizade == 4"> 
						<input
							type="submit" class="btn btn-danger" value="Recusar Amizade"
							ng-click="sendPostDesfazerAmizade(data.amigo.id)"
							ng-if="data.amigo.statusAmizade == 4">
					</div>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-4 col-sm-6 col-xs-12 vcenter">
							<div class="text-center">
								<img ng-src={{$root.data.amigo.fotoPerfil}}
									class="avatar img-circle img-thumbnail" alt="avatar"
									style="height: 200px; width: 200px;">
							</div>
						</div>
						<!--/col-->
						<div class="col-md-3 col-sm-6 col-xs-12 vcenter">
							<p>
								<strong>Nome: </strong>
							<p style="text-indent: 1em;">{{$root.data.amigo.nome}}</p>
							</p>
							<p>
								<strong>Email: </strong>
							<p style="text-indent: 1em;">{{$root.data.amigo.email}}</p>
							</p>
							<p>
								<strong>CPF/CNPJ: </strong>
							<p style="text-indent: 1em;">{{$root.data.amigo.cpf}}</p>
							</p>
							<p>
								<strong>Nascimento: </strong>
							<p style="text-indent: 1em;">{{$root.data.amigo.nascimento}}</p>
							</p>
						</div>
						<div class="col-md-4 col-sm-6 col-xs-12 vcenter">
							<p>
								<strong>Perfil: </strong>
							<p style="text-indent: 1em;">{{$root.data.amigo.perfil}}</p>
							</p>
							<p>
								<strong>Telefone: </strong>
							<div ng-repeat="telefone in $root.data.amigo.listaTelefone">
								<p style="text-indent: 1em;">
									{{$root.data.amigo.amigo.telefone.categoriaDescricao}} -
									({{telefone.ddd}}) {{telefone.numero}} -
									{{telefone.operadoraDescricao}}</p>
							</div>
							</p>
							<p>
								<strong>Endereco: </strong>
							<p style="text-indent: 1em;">
								{{$root.data.amigo.endereco.logradouro}} ,
								{{$root.data.amigo.endereco.numero}} -
								{{$root.data.amigo.endereco.bairro}} <br>
							</p>
							<p style="text-indent: 1em;">{{$root.data.amigo.endereco.cidade}}
								- {{$root.data.amigo.endereco.estado}} ,
								{{$root.data.amigo.endereco.cep}}</p>
							</p>



						</div>
						<!-- 						<div class="col-xs-6 col-sm-3 vcenter""> -->
						<!-- 							<div class="panel panel-default "> -->
						<!-- 								<div class="panel-body"> -->
						<!-- 									<p> -->
						<!-- 										<strong>Serviços Contratados: </strong> -->
						<!-- 									<p style="text-indent: 1em;"> -->
						<!-- 										{{$root.data.amigo.numServicosContratados}}</p> -->
						<!-- 									</p> -->
						<!-- 									<p> -->
						<!-- 										<strong>Média de Avaliação: </strong> -->
						<!-- 									</p> -->
						<!-- 									<p> -->
						<!-- 										<strong>Nível Avaliador: </strong> -->
						<!-- 									</p> -->
						<!-- 								</div> -->
						<!-- 							</div> -->
						<!-- 						</div> -->
					</div>

					<div class="row">
						<div class="panel panel-default">
							<div class="panel-heading">
								<uib-tabset active="active">
									<uib-tab index="0" heading="Serviços" ng-if="$root.data.amigo.tipoCadastro!=1"> 
										<br>
										<div class="panel panel-default">
											<div class="panel-body">
												<div
													ng-repeat="servico in $root.data.amigo.listaCategoriaServicosPrestados">
													<div class="col-md-4 col-sm-3 col-xs-6">
														<div class="panel panel-default">
															<div class="panel-heading">
																<strong>Serviço {{servico.num}}: </strong>
															</div>
															<div class="panel-body">
																<p>
																	<strong>Nome: </strong>
																<p style="text-indent: 1em;">{{servico.nome}}</p>
																</p>
																<p>
																	<strong>Descrição: </strong>
																<p style="text-indent: 1em;">{{servico.descricao}}</p>
																</p>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</uib-tab>
									<uib-tab index="1" heading="Histórico de Serviços Contratados" ng-if="$root.data.amigo.listaServicosContratados!=null">
										<br>
										<div ng-repeat="compromisso in $root.data.amigo.listaServicosContratados">
											<div class="panel panel-default">
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
														</p>
														<p ng-if="compromisso.status == 4">
															<font color="green"> <span
																class="glyphicon glyphicon-ok-sign"></span>
																{{compromisso.statusDescricao}}
															</font>
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
														</p>
													</div>
												</div>
											</div>
										</div>
									</uib-tab>
									<uib-tab index="2" heading="Histórico de Contratos Prestados" ng-if="$root.data.amigo.listaContratosServicosPrestados!=null  && $root.data.amigo.tipoCadastro!=1 ">
										<br>
										<div ng-repeat="compromisso in $root.data.amigo.listaContratosServicosPrestados">
											<div class="panel panel-default">
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
														</p>
														<p ng-if="compromisso.status == 4">
															<font color="green"> <span
																class="glyphicon glyphicon-ok-sign"></span>
																{{compromisso.statusDescricao}}
															</font>
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
														</p>
													</div>
												</div>
											</div>
										</div>
							
									</uib-tab>
									<uib-tab index="3" heading="Amigos" ng-if="$root.data.amigo.listaAmigos!=null"> 
										<br>
										<div ng-repeat="amizade in $root.data.amigo.listaAmigos">
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
													<div class="col-md-3 vcenter">
														<p>
															<strong>Tipo de Cadastro: </strong>
														<p style="text-indent: 2em;">{{amizade.amigo.perfil}}</p>
														</p>
														<p>
															<strong>Status da Amizade: </strong>
														<p ng-if="amizade.status == 1" style="text-indent: 1em;">
															<font color="green"> <span
																class="glyphicon glyphicon-ok-sign"></span>
																{{amizade.statusDescricao}}
															</font>
														</p>
														<p ng-if="amizade.status == 2" style="text-indent: 1em;">
															<font color="red"> <span
																class="glyphicon glyphicon-remove-sign"></span>
																{{amizade.statusDescricao}}
															</font>
														</p>
														<p ng-if="amizade.status == 3 || amizade.status == 4 "
															style="text-indent: 1em;">
															<font color="blue"> <span
																class="glyphicon glyphicon-question-sign"></span>
																{{amizade.statusDescricao}}
															</font>
														</p>
														</p>
													</div>
													<div class="col-md-1 vcenter" style="text-align: center;">
														<p>
															<input type="submit" class="btn btn-primary"
																value="Visualizar Perfil"
																ng-click="sendPostPerfilAmigo(amizade.amigo.id, amizade.amigo.tipoCadastro)"
																ng-controller="friendsProfilePageCtrl" />
														</p>
													</div>
												</div>
											</div>
										</div>
									</uib-tab>
								</uib-tabset>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</form>
</body>