
<body>
	<div class="row" data-ng-init="">
		<div class="well panel panel-default">
			<div class="panel-heading">
				<h3>Perfil do Usuário</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-sm-3">
						<div class="text-center">
							<img
								src="${pageContext.request.contextPath}/images/pessoa-feliz.png"
								class="avatar img-circle img-thumbnail" alt="avatar"
								style="height: 200px; width: 200px;">
						</div>
					</div>
					<!--/col-->
					<div class="col-sm-9">
						<form class="form-horizontal" name="inactivateForm" novalidate
							role="form" ng-controller="atualizaPerfilCtrl"
							ng-submit="atualizaPerfil()">
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
										ng-model="cliente.email" ng-init="cliente.email =data.email" />
									<select
										ng-options="x as x.label for x in tipoprivacidade track by tipoprivacidade.id"
										ng-model="cliente.privacidade.email"></select>
								</div>
							</div>

							<!--  <input type="button" value="Get" ng-click="Get()" />-->

							<label for="email">Endereço</label>

							<div class="form-group">
								<div class="col-sm-2">
									<input type="text" id="cep" name="cep" class="form-control"
										ng-model="cliente.endereco.cep"
										ng-init="cliente.endereco.cep = data.endereco.cep"
										placeholder="CEP" ng-blur="pesquisaCep(data.endereco.cep)" />
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
								<div class="col-sm-2">
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
										ng-bind="cliente.endereco.estado=data.endereco.estado"
										placeholder="UF" />
								</div>
								<div class="col-sm-2">
									<input type="text" id="pais" name="pais" class="form-control"
										ng-model="cliente.endereco.pais"
										ng-bind="cliente.endereco.pais = data.endereco.pais"
										placeholder="Pais" />
								</div>

							</div>

							<div class="form-group">

								<div class="col-sm-9">

									<div class="checkbox">
										<label> <input type="checkbox" name="userType"
											value="prestador" ng-model="data.tipoCadastro" /> Prestador
										</label><br />
									</div>


								</div>
							</div>

							<div class="form-group" uib-collapse="!data.tipoCadastro">
								<label for="nonCompeteNotes" class="col-sm-3 control-label">
									Tipo de acesso </label>
								<div class="col-sm-8">
									<div class="radio">
										<label
											uib-tooltip="FREE: BLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLA">
											<input type="radio" name="serviceType" value="free"
											ng-model="prestador.type" /> Free
										</label><br />
									</div>
									<div class="radio">
										<label
											uib-tooltip="PREMIUM: BLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLA">
											<input type="radio" name="serviceType" value="premium"
											ng-model="prestador.type" /> Premium
										</label><br />
									</div>
								</div>
							</div>


							<label for="telefone">Telefone:</label>
							<div ng-repeat="telefone in data.listaTelefone">
								<div class="form-group">
									<div class="col-sm-1">
										<input type="text" id="ddd" name="ddd" class="form-control"
											ng-model="telefone.ddd" ng-bind="telefone.ddd" />
									</div>
									<div class="col-sm-2">
										<input type="text" id="ddd" name="ddd" class="form-control"
											ng-model="telefone.numero" ng-bind="telefone.numero" />
									</div>
									<div class="col-sm-2">
										<select
											ng-options="item as item.label for item in operadoras track by operadoras.id"
											ng-model="telefone.operadora"></select>
									</div>
									<div class="col-sm-2">
										<select
											ng-options="item as item.label for item in categorias track by categorias.id"
											ng-model="telefone.categoria"></select>
									</div>

								</div>
							</div>

							<label for="cpf">CPF/CNPJ:</label>
							<div class="form-group">
								<div class="col-sm-2">
									<input type="text" name="cpf" class="form-control"
										ng-model="cliente.cpf" ng-init=" cliente.cpf = data.cpf" />
								</div>
							</div>

							<label for="cpf">Nascimento:</label>
							<div class="form-group">
								<div class="col-sm-2">
									<input type="text" id="nascimento" name="nascimento"
										class="form-control" ng-model="cliente.nascimento"
										ng-bind="cliente.nascimento = data.nascimento" />
								</div>
							</div>


							<div class="form-group">
								<div class="col-sm-12" style="align: right">
									<input type="submit" class="btn btn-success" value="Finalizar" />
									<input type="reset" class="btn btn-default" value="Salvar"
										ng-click="resetcadastroform()" />
								</div>
							</div>

						</form>
					</div>

				</div>

				<div class="row">
					<div class="panel panel-default">
						<!-- Se o usuário estiver logado e for um prestador -->
						<div class="panel-heading">
							<div ng-if="usuarioLogado !='' && tipoUsuarioLogado==2">
								<uib-tabset active="active"> <uib-tab index="0"
									heading="TAB1">TAB1</uib-tab> <uib-tab index="1" heading="TAB2">TAB2</uib-tab>
								<uib-tab index="2" heading="TAB3">TAB3</uib-tab> <uib-tab
									index="3" heading="TAB4">TAB4</uib-tab> </uib-tabset>
							</div>
							<!-- Se o usuário estiver logado e for um cliente -->
							<div ng-if="usuarioLogado !='' && tipoUsuarioLogado==1">
								<uib-tabset active="active"> <uib-tab index="0"
									heading="TAB1">TAB1</uib-tab> <uib-tab index="1" heading="TAB2">TAB2</uib-tab>
								<uib-tab index="2" heading="TAB3">TAB3</uib-tab> <uib-tab
									index="3" heading="TAB4">TAB4</uib-tab> </uib-tabset>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>