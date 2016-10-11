
<body>
	<form class="form-horizontal" name="inactivateForm" novalidate
		role="form" ng-controller="atualizaPerfilCtrl"
		ng-submit="atualizaPerfil()">
		<div class="row center-block" data-ng-init="init()">
			<div class="well panel panel-default">
				<div class="panel-heading">
					<h3>Perfil do Usu�rio</h3>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-4">
							<h4>Escolha sua foto de perfil</h4>
							<div class="text-center">
								<form>

									<input type="file" ng-file-select="onFileSelect($files)"
										ng-model="imageProfile">
									<!--  <input type="file" ng-file-select="onFileSelect($files)" multiple> -->


								</form>
								<b>Preview:</b><br /> <img ng-src="{{imageSrc}}"
									style="height: 200px; width: 200px; border-radius: 50%; border: none;" /><br />

								<b>Progress:</b>
								<progress value="{{progress}}"></progress>

							</div>

						</div>
						<!--/col-->
						<div class="col-sm-8">

							<h3>{{data.nome}}</h3>
							<div ng-show="$root.data.apelido != null">
								<label for="nickname">Apelido</label>
								<div class="form-group">
									<div class="col-sm-4">
										<input type="text" id="apelido" name="apelido"
											class="form-control" ng-model="cliente.apelido"
											ng-init="cliente.apelido = data.apelido" />
									</div>
								</div>
							</div>


							<label for="email">Email</label>

							<div class="form-group">
								<div class="col-sm-4">
									<input type="text" id="email" name="email" class="form-control"
										ng-model="cliente.email" ng-init="cliente.email = data.email" />
								</div>
							</div>

							<label for="email">Endere�o</label>

							<div class="form-group">
								<div class="col-sm-2">

									<input type="text" id="cep" name="cep" class="form-control"
										ng-model="cliente.endereco.cep"
										ng-init="cliente.endereco.cep = data.endereco.cep"
										placeholder="CEP" ng-blur="pesquisaCep(data.endereco.cep)" />

								</div>
								<div class="col-sm-2">
									<select class="form-control"
										ng-options="x as x.label for x in tipoprivacidade track by tipoprivacidade.id"
										ng-model="cliente.privacidade.endereco"></select>
								</div>
							</div>
							<div class="form-group">

								<div class="col-sm-5">
									<input type="text" id="logradouro" name="logradouro"
										class="form-control" ng-model="cliente.endereco.logradouro"
										ng-init="cliente.endereco.logradouro = data.endereco.logradouro"
										placeholder="Logradouro" />
								</div>
								<div class="col-sm-1">
									<input type="text" id="numero" name="numero"
										class="form-control" ng-model="cliente.endereco.numero"
										ng-init="cliente.endereco.numero = data.endereco.numero"
										placeholder="Nº" />
								</div>
								<div class="col-sm-3">
									<input type="text" id="" name="complemento"
										class="form-control" ng-model="cliente.endereco.complemento"
										ng-init="cliente.endereco.complemento = data.endereco.complemento"
										placeholder="Complemento" />
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-3">
									<input type="text" id="bairro" name="bairro"
										class="form-control" ng-model="cliente.endereco.bairro"
										ng-init="cliente.endereco.bairro=data.endereco.bairro"
										placeholder="Bairro" />
								</div>
								<div class="col-sm-2">
									<input type="text" id="cidade" name="cidade"
										class="form-control" ng-model="cliente.endereco.cidade"
										ng-init="cliente.endereco.cidade = data.endereco.cidade"
										placeholder="Cidade" />
								</div>
								<div class="col-sm-1">
									<input type="text" id="estado" name="estado"
										class="form-control" ng-model="cliente.endereco.estado"
										ng-init="cliente.endereco.estado=data.endereco.estado"
										placeholder="UF" />
								</div>
								<div class="col-sm-2">
									<input type="text" id="pais" name="pais" class="form-control"
										ng-model="cliente.endereco.pais"
										ng-init="cliente.endereco.pais = data.endereco.pais"
										placeholder="Pais" />
								</div>

							</div>



							<label for="telefone">Telefone:</label> <select
								ng-options="x as x.label for x in tipoprivacidade track by tipoprivacidade.id"
								ng-model="cliente.privacidade.telefone">
							</select>
							<div ng-repeat="telefone in data.listaTelefone">
								<div class="form-group">
									<div class="col-sm-1">
										<input type="text" id="ddd" name="ddd" class="form-control"
											ng-model="telefone.ddd" ng-bind="telefone.ddd" />
									</div>
									<div class="col-sm-2">
										<input type="text" id="numero" name="numero"
											class="form-control" ng-model="telefone.numero"
											ng-bind="telefone.numero" />
									</div>
									<div class="col-sm-2">
										<select class="form-control"
											ng-options="item as item.label for item in operadoras track by operadoras.id"
											selected="telefone.operadora" ng-model="telefone.operadora"></select>
									</div>
									<div class="col-sm-2">
										<select class="form-control"
											ng-options="item as item.label for item in categorias track by categorias.id"
											selected="telefone.categoria" ng-model="telefone.categoria"></select>
									</div>

								</div>
							</div>


							<label for="cpf">CPF/CNPJ:</label>
							<div class="form-group">
								<div class="col-sm-3">
									<input type="text" id="cpf" name="cpf" class="form-control"
										ng-model="cliente.cpf" ng-init=" cliente.cpf = data.cpf" />
								</div>
								<div class="col-sm-2">
									<select class="form-control"
										ng-options="x as x.label for x in tipoprivacidade track by tipoprivacidade.id"
										ng-model="cliente.privacidade.cpf"></select>
								</div>
							</div>

							<label for="cpf">Nascimento:</label>
							<div class="form-group">
								<div class="col-sm-3">
									<input type="text" id="nascimento" name="nascimento"
										class="form-control" ng-model="cliente.nascimento"
										ng-init="cliente.nascimento = data.nascimento" /  ui-date>
								</div>
							</div>


						</div>
					</div>


					<div class="row">

						<div class="panel panel-default">
							<!-- Se o usu�rio estiver logado e for um prestador -->
							<div class="panel-heading">
								<!-- Se o usu�rio estiver logado e for um cliente -->
								<div ng-if="usuarioLogado !=''">
									<uib-tabset active="active"> 
									<uib-tab index="0"
										heading="Tipo de perfil">

									<div class="panel panel-default">
										<div class="panel-body">

											<div class="row">
												<div class="col-sm-9">

													<div class="form-group">
														<div class="col-sm-3">

															<div class="checkbox">
																<label> <input type="checkbox" name="userType"
																	value="prestador" ng-model="prestador.flag" />
																	Prestador
																</label><br />
															</div>
														</div>
													</div>

													<div class="form-group" uib-collapse="!prestador.flag">
														<div class="col-sm-3">
														    <div class="card card-block">
														      <h3 style="text-align:center" class="card-title">FREE</h3>
														      <p class="card-text">
														      <span
																class="glyphicon glyphicon-ok"></span> <span class="text">Recursos b�sicos</span>
														      <br/>
														      <span
																class="glyphicon glyphicon-ok"></span> <span class="text">Suporte via e-mail</span>
															   <br/>
														      <span
																class="glyphicon glyphicon-ok"></span> <span class="text">Agendamento de servi�os</span>
														      <br/>
														       <span
																class="glyphicon glyphicon-ok"></span> <span class="text">Feedback de servi�o realizado</span>
														      <br/>
														      <span
																class="glyphicon glyphicon-ok"></span> <span class="text">Divulga��o de servi�os</span>
														      </p>
														      <h4 style="text-align:center">Gr�tis</h4> 
															      <div class="radio">
																	<label>
																		<input type="radio" name="serviceType" value="free"
																		ng-model="prestador.type" /> Adquirir free
																	</label><br />
																</div>
															</div>
														  </div>
														  
														  
														  <div class="col-sm-3">
														    <div class="card card-block">
														      <h3 style="text-align:center" class="card-title">PREMIUM</h3>
														      <p class="card-text">
														       <span
																class="glyphicon glyphicon-ok"></span> <span class="text">Recursos b�sicos</span>
														      <br/>
														      <span
																class="glyphicon glyphicon-ok"></span> <span class="text">Suporte via e-mail</span>
																<br/>
															 <span
																class="glyphicon glyphicon-ok"></span> <span class="text">Melhor posicionamento nas buscas de servi�os</span>
														      <br/>
														     <span
																class="glyphicon glyphicon-ok"></span> <span class="text">Agendamento de servi�os</span>
														      <br/>
														       <span
																class="glyphicon glyphicon-ok"></span> <span class="text">Feedback de servi�o realizado</span>
														      <br/>
														      <span
																class="glyphicon glyphicon-ok"></span> <span class="text">Divulga��o de servi�os</span>
														      </p>
														     
														      <h4 style="text-align:center">R$59,90/mensal</h4> 
														      <div class="radio">
																	<label>
																		<input type="radio" name="serviceType" value="premium"
																		ng-model="prestador.type" /> Adquirir Premium
																	</label><br />
																</div>
														    
														
														</div>
													</div>

												</div>
												
												
												
											</div>
											
											
										</div>
									</div>
									</div>	
									</uib-tab> 
									
									<uib-tab ng-hide="!prestador.flag" index="1"
										heading="Servi�os" data-ng-init="carregaCategorias()">

									<div class="panel panel-default">
										<div class="panel-body">

											<div class="col-sm-12">

												<div class="form-group">
													<label for="servicos" class="col-sm-1 control-label">
														N�mero de Servi�os </label>
													<div class=" col-sm-1">
														<select class="form-control" ng-model="numServicos" ng-controller = "atualizaPerfilCtrl"
															ng-change="selecionarNumServicos(numServicos)"
															ng-options="item.id as item.label for item in numeroServicos">
														</select>
													</div>
												</div>




												<div class="form-group">
													<label for="servicos" class="col-sm-1 control-label">
														Servi�os </label>
													<div class="col-sm-6">
														<div ng-repeat="i in $root.servicosSelecionados track by $index">
															<div ng-if="i.id!=0">
																<label for="servicos" class="col-sm-3 control-label">
																</label>
															</div>
															<div class="panel panel-default">
																<div class="panel-heading">Servi�o {{i.id + 1}}</div>
																<div class="panel-body">
																	<label for="servi�os" class="col-sm-4 control-label">
																		Categoria do Servi�o </label>
																	<div class=" col-sm-4">
																		<select class="form-control" ng-model="servicos[i.id]"
																			ng-options="item.id as item.nome for item in categoriasServico">
																		</select>
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
					<div class="form-group">
						<div class="col-sm-1 control-label"></div>
						<div class="col-sm-9" style="float: right;">
							<input type="submit" class="btn btn-success" value="Finalizar"
								ng-click="sendPostCadastroCliente()" /> <input type="reset"
								class="btn btn-default" value="Limpar"
								ng-click="resetcadastroform()" />
						</div>
					</div>
					
					
				</div>
			</div>
		</div>
	</form>
</body>