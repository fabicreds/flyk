<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">
<body>
	<form class="form-horizontal" name="inactivateForm" novalidate
		role="form">
		<div class="row  center-block container" data-ng-init="init()">
			<div class="well panel panel-default">
				<div class="panel-heading">
					<font size="5">Perfil do Usuário - {{data.nome}}</font>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-4 col-sm-6 col-xs-12 vcenter">
							<div class="text-center">
								<img ng-src={{data.fotoPerfil}}
									class="avatar img-circle img-thumbnail" alt="avatar"
									style="height: 200px; width: 200px;">
							</div>
						</div>
						<!--/col-->
						<div class="col-md-3 col-sm-6 col-xs-12 vcenter">
							<p>
								<strong>Usuário: </strong>
							<p style="text-indent: 1em;">{{data.usuario}}</p>
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
						</div>
						<div class="col-md-4 col-sm-6 col-xs-12 vcenter">
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



						</div>
						<!-- 						<div class="col-xs-6 col-sm-3 vcenter""> -->
						<!-- 							<div class="panel panel-default "> -->
						<!-- 								<div class="panel-body"> -->
						<!-- 									<p> -->
						<!-- 										<strong>Serviços Contratados: </strong> -->
						<!-- 									<p style="text-indent: 1em;"> -->
						<!-- 										{{data.numServicosContratados}}</p> -->
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
								<uib-tab index="0" heading="Serviços" ng-if="usuarioLogado !='' && tipoUsuarioLogado!=1"> 
									<br>
									<div class="panel panel-default">
										<div class="panel-body">
											<div
												ng-repeat="servico in $root.data.listaCategoriaServicosPrestados">
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
								<uib-tab index="1" heading="Histórico de Serviços Contratados" ng-if="$root.data.listaServicosContratados!=null">
									<br>
									<div ng-repeat="compromisso in $root.data.listaServicosContratados">
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
								<uib-tab index="2" heading="Histórico de Contratos Prestados" ng-if="$root.data.listaContratosServicosPrestados!=null  && tipoUsuarioLogado!=1 ">
									<br>
									<div ng-repeat="compromisso in $root.data.listaContratosServicosPrestados">
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
								<uib-tab index="3" heading="Amigos" ng-if="data.listaAmigos!=null"> 
									<br>
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
								<uib-tab index="4" heading="Avaliação Serviços Contratados" ng-if="$root.data.listaServicosContratados!=null"> 
								<br>
								<div class="panel panel-default">
									<div class="panel-body">
										<div
											ng-repeat="compromisso in $root.data.listaServicosContratados">
											<div class="col-md-12 col-sm-3 col-xs-6" ng-if="compromisso.status == 6">
												<div class="panel panel-default">
													<div class="panel-heading">
														<strong> {{compromisso.contrato.servico.nome}} - {{compromisso.contrato.servico.descricao}} </strong>
													</div>
													<div class="panel-body">
														<div class="col-md-3">
															<div class="panel panel-default">
																<div class="panel-heading">
																	<strong> Cliente: {{$root.data.nome}}   </strong>
																</div>
																<div class="panel-body">
																	<div style="text-align: center;">
																		<strong>Avaliação <br> Contratante:   </strong>
																		<div>
																			<font color="yellow" size="5">
																			<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoContratante>=1"></span>
																			<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoContratante>=2"></span>
																			<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoContratante>=3"></span>
																			<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoContratante>=4"></span>
																			<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoContratante>=5"></span>
																			</font>
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<div class="col-md-9">
															<div class="panel panel-default">
																<div class="panel-heading">
																	<strong> Prestador: {{compromisso.contrato.prestador.nome}} </strong>
																</div>
																<div class="panel-body">
																	<div class="col-md-3" style="text-align: center;">
																		
																			<strong>Avaliação <br> Qualidade:</strong>
																			<div>
																				<font color="yellow" size="5">
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoQualidade>=1"></span>
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoQualidade>=2"></span>
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoQualidade>=3"></span>
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoQualidade>=4"></span>
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoQualidade>=5"></span>
																				</font>
																			</div>
																	</div>
																	<div class="col-md-3" style="text-align: center;">
																			<strong>Avaliação <br> Preço:</strong>
																			<div>
																				<font color="yellow" size="5">
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoPreco>=1"></span>
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoPreco>=2"></span>
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoPreco>=3"></span>
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoPreco>=4"></span>
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoPreco>=5"></span>
																				</font>
																			</div>
																	</div>
																	<div class="col-md-3" style="text-align: center;">
																			<strong>Avaliação <br> Pontualidade: </strong>
																			<div>
																				<font color="yellow" size="5">
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoTempo>=1"></span>
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoTempo>=2"></span>
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoTempo>=3"></span>
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoTempo>=4"></span>
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoTempo>=5"></span>
																				</font>
																			</div>
																	</div>
																	<div class="col-md-3" style="text-align: center;">
																			<strong>Avaliação <br> Profissionalismo: </strong>
																			<div>
																				<font color="yellow" size="5">
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoProfissionalismo>=1"></span>
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoProfissionalismo>=2"></span>
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoProfissionalismo>=3"></span>
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoProfissionalismo>=4"></span>
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoProfissionalismo>=5"></span>
																				</font>
																			</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											
										</div>
									</div>
								</div>
							</uib-tab>
							<uib-tab index="4" heading="Avaliação Serviços Prestados" ng-if="$root.data.listaContratosServicosPrestados!=null  && tipoUsuarioLogado!=1"> 
									<br>
									<div class="panel panel-default">
										<div class="panel-body">
											<div
												ng-repeat="compromisso in $root.data.listaContratosServicosPrestados">
												<div class="col-md-12 col-sm-3 col-xs-6" ng-if="compromisso.status == 6">
													<div class="panel panel-default">
														<div class="panel-heading">
															<strong> {{compromisso.contrato.servico.nome}} - {{compromisso.contrato.servico.descricao}} </strong>
														</div>
														<div class="panel-body">
															<div class="col-md-3">
																<div class="panel panel-default">
																	<div class="panel-heading">
																		<strong> Cliente: {{compromisso.contrato.cliente.nome}}   </strong>
																	</div>
																	<div class="panel-body">
																		<div style="text-align: center;">
																			<strong>Avaliação <br> Contratante:   </strong>
																			<div>
																				<font color="yellow" size="5">
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoContratante>=1"></span>
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoContratante>=2"></span>
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoContratante>=3"></span>
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoContratante>=4"></span>
																				<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoContratante>=5"></span>
																				</font>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<div class="col-md-9">
																<div class="panel panel-default">
																	<div class="panel-heading">
																		<strong> Prestador: {{$root.data.nome}} </strong>
																	</div>
																	<div class="panel-body">
																		<div class="col-md-3" style="text-align: center;">
																			
																				<strong>Avaliação <br> Qualidade:</strong>
																				<div>
																					<font color="yellow" size="5">
																					<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoQualidade>=1"></span>
																					<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoQualidade>=2"></span>
																					<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoQualidade>=3"></span>
																					<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoQualidade>=4"></span>
																					<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoQualidade>=5"></span>
																					</font>
																				</div>
																		</div>
																		<div class="col-md-3" style="text-align: center;">
																				<strong>Avaliação <br> Preço:</strong>
																				<div>
																					<font color="yellow" size="5">
																					<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoPreco>=1"></span>
																					<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoPreco>=2"></span>
																					<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoPreco>=3"></span>
																					<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoPreco>=4"></span>
																					<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoPreco>=5"></span>
																					</font>
																				</div>
																		</div>
																		<div class="col-md-3" style="text-align: center;">
																				<strong>Avaliação <br> Pontualidade: </strong>
																				<div>
																					<font color="yellow" size="5">
																					<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoTempo>=1"></span>
																					<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoTempo>=2"></span>
																					<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoTempo>=3"></span>
																					<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoTempo>=4"></span>
																					<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoTempo>=5"></span>
																					</font>
																				</div>
																		</div>
																		<div class="col-md-3" style="text-align: center;">
																				<strong>Avaliação <br> Profissionalismo: </strong>
																				<div>
																					<font color="yellow" size="5">
																					<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoProfissionalismo>=1"></span>
																					<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoProfissionalismo>=2"></span>
																					<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoProfissionalismo>=3"></span>
																					<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoProfissionalismo>=4"></span>
																					<span class="glyphicon glyphicon-star" ng-if="compromisso.contrato.avaliacaoPrestador.avaliacaoProfissionalismo>=5"></span>
																					</font>
																				</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
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
	</form>
</body>