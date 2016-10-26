<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<form class="form-horizontal" name="userForm" novalidate role="form">
<div class="container">
	<fieldset>
		<legend>Cadastro - Novo cliente</legend>
		<div class="col-sm-3" data-ng-init="init()">
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
		<div class="col-sm-9">
			<div class="form-group" show-errors>
				<label for="fullName" class="col-sm-3 control-label">Nome*</label>
				<div class="col-sm-6">
					<input type="text" id="fullName" name="fullName"
						class="form-control" ng-model="fullName" ng-required="true" /> <span
						class="help-block" ng-if="userForm.fullName.$error.required">O
						nome é obrigatório!</span>
				</div>
			</div>

			<div class="form-group" show-errors>
				<label for="email" class="col-sm-3 control-label">Email*</label>
				<div class="col-sm-6">
					<input type="email" id="email" name="email" class="form-control"
						ng-model="email" ng-required="true" /> <span class="help-block"
						ng-if="userForm.email.$error">Um e-mail inválido foi
						digitado! </span>
				</div>
			</div>

			<div class="form-group" show-errors>
				<label for="nickname" class="col-sm-3 control-label">Apelido*</label>
				<div class="col-sm-6">
					<input type="text" id="nickname" name="nickname"
						class="form-control" ng-model="nickname" ng-required="true" /> <span
						class="help-block" ng-if="userForm.nickname.$error.required">O
						apelido é obrigatório!</span>
				</div>
			</div>

			<div class="form-group" show-errors>
				<label for="password" class="col-sm-3 control-label">Senha*</label>
				<div class="col-sm-3">
					<input type="password" id="password" name="password"
						class="form-control" ng-model="password" ng-required="true"
						ng-pattern="/((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/"
						ng- ng-maxlength="10"
						uib-tooltip="A senha deve conter letra minuscula, maiuscula, numero e caracter especial." />
					<!--Com o form-control, ele fica responsivel. Não use value para inserior o valor digitado. -->
					<span class="help-block" ng-if="userForm.password.$error.required">A
						senha é obrigatória! </span> <span class="help-block"
						ng-if="userForm.password.$error.minlength">A senha é muito
						curta! </span>
					<!-- mostra a mensagem até antes do usuario digitar. Assim que ele digita, a mensagem desaparece.-->
					<span class="help-block" ng-if="userForm.password.$error.maxlength">A
						senha é muito longa!</span> <span class="help-block"
						ng-if="userForm.password.$error.pattern"> A senha não
						atende aos requisitos! </span>
				</div>
			</div>

			<div class="form-group" show-errors>
				<label for="confirmPassword" class="col-sm-3 control-label">
					Confirme a senha*</label>
				<div class="col-sm-3">
					<input type="password" id="confirmPassword" name="confirmPassword"
						class="form-control" ng-model="confirmPassword" ng-required="true"
						ng-minlength="5" ng-maxlength="10"
						ng-blur="validatePassword(user)" />
					<!--Com o form-control, ele fica responsivel. Não use value para inserior o valor digitado. -->
				</div>
			</div>

			<div class="form-group" show-errors>
				<label for="CPF" class="col-sm-3 control-label">CPF*</label>
				<div class="col-sm-3">
					<input type="text" id="CPF" name="CPF" class="form-control"
						ng-model="CPF" ng-required="true" ui-cpf /> <span
						class="help-block" ng-if="userForm.CPF.$error.required">CPF
						é obrigatório!</span>
				</div>
			</div>


			<div class="form-group" show-errors>
				<label for="Date" class="col-sm-3 control-label">Data de
					nascimento</label>
				<div class="col-sm-3">
					<input type="text" id="date" name="date" class="form-control"
						ng-model="dateBirth" ui-date />
				</div>
			</div>

			<div class="form-group" show-errors>
				<label for="numberContact" class="col-sm-3 control-label">Telefone
					1*</label>
				<div class="col-sm-3">
					<input type="text" id="telephone1" name="telephone1"
						class="form-control" ng-model="telephone1.number"
						ng-required="true" ui-telefone /> <span class="help-block"
						ng-if="userForm.telephone1.$error.required">Este telefone é
						obrigatório!</span>

				</div>
				<div class="col-sm-2">
					<select class="form-control"
						ng-options="item as item.label for item in operadoras"
						ng-model="telephone1.operadora"></select>
				</div>
				<div class="col-sm-2">
					<select class="form-control"
						ng-options="item as item.label for item in categorias"
						ng-model="telephone1.categoria"></select>
				</div>
			</div>

			<div class="form-group" show-errors>
				<label for="numberContact2" class="col-sm-3 control-label">Telefone
					2</label>
				<div class="col-sm-3">
					<input type="text" id="telephone2" name="telephone2"
						class="form-control" ng-model="telephone2.number" ui-telefone />

				</div>
				<div class="col-sm-2">
					<select class="form-control"
						ng-options="item as item.label for item in operadoras"
						ng-model="telephone2.operadora"></select>
				</div>
				<div class="col-sm-2">
					<select class="form-control"
						ng-options="item as item.label for item in categorias"
						ng-model="telephone2.categoria"></select>
				</div>
			</div>



			<div class="form-group" show-errors>
				<label for="address1" class="col-sm-3 control-label">Endereço</label>
				<div class="col-sm-2">
					<input type="text" id="addressPostalCode" name="addressPostalCode"
						class="form-control" ng-model="address.cep"
						ng-blur="pesquisaCep(address.cep)" placeholder="CEP" />
					<!--Popular a partir do banco de dados-->
				</div>
			</div>

			<div class="form-group" show-errors>
				<div class="col-sm-3"></div>
				<div class="col-sm-5">
					<input type="text" id="addressStreet" name="addressStreet"
						class="form-control" ng-model="address.logradouro"
						placeholder="Logradouro" />
				</div>
				<div class="col-sm-1">
					<input type="text" id="addressNumber" name="addressNumber"
						class="form-control" ng-model="address.numero" placeholder="Nº" />
				</div>
				<div class="col-sm-2">
					<input type="text" id="addressComp" name="addressComp"
						class="form-control" ng-model="address.comp"
						placeholder="Complemento" />
				</div>

			</div>
			<div class="form-group" show-errors>
				<div class="col-sm-3"></div>
				<div class="col-sm-3">
					<input type="text" name="addressBairro" id="bairro"
						class="form-control" ng-model="address.bairro"
						placeholder="Bairro" />
				</div>
				<div class="col-sm-2">
					<input type="text" name="addressCity" id="city"
						class="form-control" ng-model="address.cidade"
						placeholder="Cidade" />
				</div>
				<div class="col-sm-1">
					<input type="text" name="addressState" id="state"
						class="form-control" ng-model="address.estado" placeholder="UF" />

				</div>
				<div class="col-sm-2">
					<input type="text" name="addressCountry" id="country"
						class="form-control" ng-model="address.pais" placeholder="Pais" />


				</div>
			</div>

			<div class="form-group">
				<label for="userType" class="col-sm-3 control-label">Perfil</label>
				<div class="col-sm-9">

					<div class="checkbox">
						<label> <input type="checkbox" name="userType"
							value="prestador" ng-model="prestador.flag" /> Deseja se tornar
							um prestador de serviço?
						</label><br />
					</div>


				</div>
			</div>

			<div class="form-group" uib-collapse="!prestador.flag">
				<div class="form-group">
					<label for="acesso" class="col-sm-3 control-label">
						Tipo de acesso </label>
					<div class="col-sm-9">
						<div class="col-sm-5">
						    <div class="card card-block">
						      <h3 style="text-align:center" class="card-title">FREE</h3>
						      <p class="card-text">
						      <span
								class="glyphicon glyphicon-ok"></span> <span class="text">Recursos básicos</span>
						      <br/>
						      <span
								class="glyphicon glyphicon-ok"></span> <span class="text">Suporte via e-mail</span>
							   <br/>
							   <span
								class="glyphicon glyphicon-remove"></span> <span class="text">Melhor posicionamento nas buscas de serviços</span>
						      <br/>
						      <span
								class="glyphicon glyphicon-ok"></span> <span class="text">Agendamento de serviços</span>
						      <br/>
						       <span
								class="glyphicon glyphicon-ok"></span> <span class="text">Feedback de serviço realizado</span>
						      <br/>
						      <span
								class="glyphicon glyphicon-ok"></span> <span class="text">Divulgação de serviços</span>
						      </p>
						      <h4 style="text-align:center">Grátis</h4> 
							      <div class="radio">
									<label>
										<input type="radio" name="serviceType" value="free"
										ng-model="$root.prestador.type" /> Adquirir free
									</label><br />
								</div>
							</div>
						  </div>
						  
						  
						  <div class="col-sm-5">
						    <div class="card card-block">
						      <h3 style="text-align:center" class="card-title">PREMIUM</h3>
						      <p class="card-text">
						       <span
								class="glyphicon glyphicon-ok"></span> <span class="text">Recursos básicos</span>
						      <br/>
						      <span
								class="glyphicon glyphicon-ok"></span> <span class="text">Suporte via e-mail</span>
								<br/>
							 <span
								class="glyphicon glyphicon-ok"></span> <span class="text">Melhor posicionamento nas buscas de serviços</span>
						      <br/>
						     <span
								class="glyphicon glyphicon-ok"></span> <span class="text">Agendamento de serviços</span>
						      <br/>
						       <span
								class="glyphicon glyphicon-ok"></span> <span class="text">Feedback de serviço realizado</span>
						      <br/>
						      <span
								class="glyphicon glyphicon-ok"></span> <span class="text">Divulgação de serviços</span>
						      </p>
						     
						      <h4 style="text-align:center">R$59,90/mensal</h4> 
						      <div class="radio">
									<label>
										<input type="radio" name="serviceType" value="premium"
										ng-model="$root.prestador.type" ng-click="showPagModal()" /> Adquirir Premium
									</label><br />
								</div>
								
								
						    
						
						</div>
						
						
					</div>
					</div>
				</div>
				
				
					
				<div class="form-group">
					<label for="servicos" class="col-sm-3 control-label">
						Número de Serviços </label>
					<div class=" col-sm-5">
						<select class="form-control col-s" ng-model="numServicos"
							ng-change="selecionarNumServicos()">
							<option value="1" selected="selected" ng-selected="prestador.flag">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<br> <br> 
					<label for="servicos" class="col-sm-3 control-label"> Serviços </label>
					<div ng-repeat="i in servicosSelecionados track by $index">
						<div ng-if="i.id!=0" >
						<label for="servicos" class="col-sm-3 control-label">  </label>
						</div>
						<div class="panel panel-default col-sm-8">
							<div class="panel-heading">Serviço {{i.id + 1}}</div>
							<div class="panel-body">
								<label for="nonCompeteNotes" class="col-sm-3 control-label">
									Categoria do Serviço </label>
								<div class=" col-sm-5">
									<select class="form-control"
										ng-model="servicos[i.id]"
										ng-options="item as item.nome for item in categoriasServico">
									</select>
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>




			<div class="form-group">
				<div class="col-sm-3"></div>
				<div class="col-sm-3">
					<label for="info">* Campos obrigatórios</label>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-3"></div>

				<div class="col-sm-9">
					<input type="submit" class="btn btn-success" value="Finalizar"
						ng-click="sendPostCadastroCliente()" /> <input type="reset"
						class="btn btn-default" value="Limpar"
						ng-click="resetcadastroform()" />
				</div>
			</div>

		</div>




	</fieldset>

	<br />



	<div class="form-group">
		<label for="name" class="col-sm-3 control-label"></label>
		<div class="col-sm-6">
			<font color="red"> {{messageErroCadastro}}</font> <font color="green">
				{{messageSucessoCadastro}}</font>
		</div>
	</div>
</div>

</form>

