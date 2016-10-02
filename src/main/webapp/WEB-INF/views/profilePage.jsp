<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">
<body>
	<form class="form-horizontal" name="inactivateForm" novalidate
		role="form">
		<div class="row  center-block container" data-ng-init="init()">
			<div class="well panel panel-default">
				<div class="panel-heading">
					<h3>Perfil do Usu�rio - {{data.nome}}</h3>
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
								<strong>Usu�rio: </strong>
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
							<!-- ################################################## PAINEL DOS PRESTADORES ##################################################							 -->
							<div class="panel-heading">
								<div ng-if="usuarioLogado !='' && tipoUsuarioLogado!=1">
									<uib-tabset active="active"> <uib-tab index="0"
										heading="Servi�os"> <br>
									<div class="panel panel-default">
										<div class="panel-body">
											<div
												ng-repeat="servico in $root.data.listaCategoriaServicosPrestados">
												<div class="col-md-4 col-sm-3 col-xs-6">
													<div class="panel panel-default">
														<div class="panel-heading">
															<strong>Servi�o {{servico.num}}: </strong>
														</div>
														<div class="panel-body">
															<p>
																<strong>Nome: </strong>
															<p style="text-indent: 1em;">{{servico.nome}}</p>
															</p>
															<p>
																<strong>Descri��o: </strong>
															<p style="text-indent: 1em;">{{servico.descricao}}</p>
															</p>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									</uib-tab> <uib-tab index="1" heading="Hist�rico de Servi�os Contratados"
										ng-if="$root.data.listaServicosContratados!=null">
									<div
										ng-repeat="compromisso in $root.data.listaServicosContratados">
										<div class="panel panel-default">
											<div class="panel-body">
												<div class="col-md-4 vcenter">
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
												<div class="col-md-4 vcenter">
													<p>
														<strong>Data de In�cio: </strong>
													<p style="text-indent: 1em;">{{compromisso.dataInicio}}</p>
													</p>
													<p>
														<strong>Data de Fim: </strong>
													<p style="text-indent: 1em;">{{compromisso.dataFim}}</p>
													</p>
												</div>
												<div class="col-md-3 vcenter" style="text-align: center;">
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
												</div>
											</div>
										</div>
									</div>
									</uib-tab> <uib-tab index="2" heading="Hist�rico de Contratos Prestados"
										ng-if="$root.data.listaContratosServicosPrestados!=null">
									<br>
									<div
										ng-repeat="compromisso in $root.data.listaContratosServicosPrestados">
										<div class="panel panel-default">
											<div class="panel-body">
												<div class="col-md-4 vcenter">
													<p>
														<strong>Cliente: </strong>
													<p style="text-indent: 1em;">{{compromisso.contrato.cliente.nome}}</p>
													</p>
													<p>
														<strong>Servi�o: </strong>
													<p style="text-indent: 1em;">{{compromisso.contrato.servico.nome}}
														- {{compromisso.contrato.servico.descricao}}</p>
													</p>
												</div>
												<div class="col-md-4 vcenter">
													<p>
														<strong>Data de In�cio: </strong>
													<p style="text-indent: 1em;">{{compromisso.dataInicio}}</p>
													</p>
													<p>
														<strong>Data de Fim: </strong>
													<p style="text-indent: 1em;">{{compromisso.dataFim}}</p>
													</p>
												</div>
												<div class="col-md-3 vcenter" style="text-align: center;">
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
												</div>
											</div>
										</div>
									</div>

									</uib-tab> <uib-tab index="3" heading="Amigos"
										ng-if="data.listaAmigos!=null"> <br>
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
														<strong>Data de In�cio da Amizade: </strong>
													<p style="text-indent: 1em;">{{amizade.dataInicioAmizade}}</p>
													</p>
												</div>
												<div class="col-md-3 col-sm-6 col-xs-12 vcenter"
													style="text-align: center;">
													<p>
														<strong>Status da Amizade: </strong>
													</p>
													<p ng-if="amizade.status == 1">
														<font color="green"> <span
															class="glyphicon glyphicon-ok-sign"></span>
															{{amizade.statusDescricao}}
														</font>
													</p>
													<p ng-if="amizade.status == 2">
														<font color="red"> <span
															class="glyphicon glyphicon-remove-sign"></span>
															{{amizade.statusDescricao}}
														</font>
													</p>
													<p ng-if="amizade.status == 3 || amizade.status == 4 ">
														<font color="blue"> <span
															class="glyphicon glyphicon-question-sign"></span>
															{{amizade.statusDescricao}}
														</font>
													</p>
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
									</uib-tab> </uib-tabset>
								</div>
								<!-- ################################################## PAINEL DOS CLIENTES ##################################################							 -->
								<div ng-if="usuarioLogado !='' && tipoUsuarioLogado==1">
									<uib-tabset active="active"> <uib-tab index="0"
										heading="Hist�rico de Contratos"
										ng-if="$root.data.listaServicosContratados !=null">
									<br>
									<div
										ng-repeat="compromisso in $root.data.listaServicosContratados">
										<div class="panel panel-default">
											<div class="panel-body">
												<div class="col-md-4 vcenter">
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
												<div class="col-md-4 vcenter">
													<p>
														<strong>Data de In�cio: </strong>
													<p style="text-indent: 1em;">{{compromisso.dataInicio}}</p>
													</p>
													<p>
														<strong>Data de Fim: </strong>
													<p style="text-indent: 1em;">{{compromisso.dataFim}}</p>
													</p>
												</div>
												<div class="col-md-3 vcenter" style="text-align: center;">
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
												</div>
											</div>
										</div>
									</div>

									</uib-tab> <uib-tab index="2" heading="Amigos"
										ng-if="data.listaAmigos!=null"> <br>
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
														<strong>Data de In�cio da Amizade: </strong>
													<p style="text-indent: 1em;">{{amizade.dataInicioAmizade}}</p>
													</p>
												</div>
												<div class="col-md-3 col-sm-6 col-xs-12 vcenter"
													style="text-align: center;">
													<p>
														<strong>Status da Amizade: </strong>
													<p ng-if="amizade.status == 1">
														<font color="green"> <span
															class="glyphicon glyphicon-ok-sign"></span>
															{{amizade.statusDescricao}}
														</font>
													</p>
													<p ng-if="amizade.status == 2">
														<font color="red"> <span
															class="glyphicon glyphicon-remove-sign"></span>
															{{amizade.statusDescricao}}
														</font>
													</p>
													<p ng-if="amizade.status == 2">
														<font color="blue"> <span
															class="glyphicon glyphicon-question-sign"></span>
															{{amizade.statusDescricao}}
														</font>
													</p>
													</p>
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