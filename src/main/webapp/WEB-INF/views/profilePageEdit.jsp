
<body>
	<form class="form-horizontal" name="inactivateForm" novalidate
		role="form" ng-submit="pesquisar()">
		<div class="row container" data-ng-init="">
			<div class="well panel panel-default">
				<div class="panel-heading">
					<h3>Perfil do Usuário</h3>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-4">
								<h4>Escolha sua foto de perfil</h4>
						      	  <div class="text-center">
						        	<form>
						        		
						        		<input type="file" ng-file-select="onFileSelect($files)" ng-model="imageProfile" >
										 <!--  <input type="file" ng-file-select="onFileSelect($files)" multiple> -->
						
						  
						   			</form>
						   			<b>Preview:</b><br />
						  			
						   			<img ng-src="{{imageSrc}}" style="height: 200px; width: 200px; border-radius: 50%; border: none;"/><br/>
						   			
						   			<b>Progress:</b>
						  			<progress value="{{progress}}"></progress>
						  			
						      	  </div>
							
						</div>
						<!--/col-->
						<div class="col-sm-8">
							<form class="form-horizontal" name="inactivateForm" novalidate
								role="form">
								
								<h3>{{data.nome}}</h3>
								<div ng-show="$root.data.apelido != null">
									 <label for="nickname">Apelido</label>
										<div class="form-group">
											<div class="col-sm-4"> 
                								<input type="text" id="apelido" name="apelido" class="form-control"
                   							 		ng-model="data.apelido"  ng-bind="{{data.apelido}}"/> 
                   							</div>
                   						</div>	   
								</div>
								
									
										 <label for="email">Email</label>
										 
											<div class="form-group">
												<div class="col-sm-4"> 
													<input type="email" id="email" name="email" class="form-control"
                   							 			ng-model="data.email"   ng-bind="{{data.email}}" />
                   					 			</div>
                   					 		</div>
							
									<label for="email">Endereço</label>
									
									<div class="form-group">
										<div class="col-sm-2">
											<input type="text" id="cep" name="cep" class="form-control"
                   							 ng-model="data.endereco.cep"  ng-bind="{{data.endereco.cep}}" placeholder="CEP" ng-blur="pesquisaCep(data.endereco.cep)"  /> 
										</div>
									</div>
									<div class="form-group">

												<div class="col-sm-5">
													<input type="text" id="logradouro" name="logradouro" class="form-control"
                   							 				ng-model="data.endereco.logradouro"  ng-bind="{{data.endereco.logradouro}}" placeholder="Logradouro"/> 
												</div>
												<div class="col-sm-1">
													<input type="text" id="numero" name="numero" class="form-control"
                   							 				ng-model="data.endereco.numero"  ng-bind="{{data.endereco.numero}}" placeholder="Nº"/> 
												</div>
												<div class="col-sm-3">
												<input type="text" id="" name="comp" class="form-control"
                   							 				ng-model="data.endereco.comp"  ng-bind="{{data.endereco.comp}}" placeholder="Complemento"/> 
												</div>
									</div>
										
								<div class="form-group">
										<div class="col-sm-3">
										<input type="text" id="bairro" name="bairro" class="form-control"
                   							 ng-model="data.endereco.bairro"  ng-bind="{{data.endereco.bairro}}" placeholder="Bairro"/> 
										</div>
										<div class="col-sm-2">
										<input type="text" id="cidade" name="cidade" class="form-control"
                   							 ng-model="data.endereco.cidade"  ng-bind="{{data.endereco.cidade}}" placeholder="Cidade"/> 
										</div>
										<div class="col-sm-2">
										<input type="text" id="estado" name="estado" class="form-control"
                   							 ng-model="data.endereco.estado"  ng-bind="{{data.endereco.estado}}" placeholder="UF"/> 
										</div>
										<div class="col-sm-3">
										<input type="text" id="pais" name="pais" class="form-control"
                   							 ng-model="data.endereco.pais"  ng-bind="{{data.endereco.pais}}" placeholder="Pais"/> 
										</div>

								</div>
							
							
								
								<label for="telefone">Telefone:</label>
								<div ng-repeat="telefone in data.listaTelefone"> 
								<div class="form-group">
										<div class="col-sm-1">
										<input type="text" id="ddd" name="ddd" class="form-control"
                   							 ng-model="telefone.ddd"  ng-bind="telefone.ddd" /> 
										</div>
										<div class="col-sm-2">
										<input type="text" id="ddd" name="ddd" class="form-control"
                   							 ng-model="telefone.numero"  ng-bind="telefone.numero" /> 
										</div>
										<div class="col-sm-2">
										<select class="form-control" ng-options="item as item.label for item in operadoras track by operadoras.id" selected="telefone.operadora" ng-model="telefone.operadora"></select>
										</div>
										<div class="col-sm-2">
										<select class="form-control" ng-options="item as item.label for item in categorias track by categorias.id" selected="telefone.categoria"  ng-model="telefone.categoria"></select>
										</div>

								</div>
								</div>
								
								<label for="cpf">CPF/CNPJ:</label>
									<div class="form-group">
										<div class="col-sm-3"> 
											<input type="text" id="cpf" name="cpf" class="form-control"
                   							   ng-model="data.cpf" ng-bind="{{data.cpf}}" /> 
                   					    </div>
                   					 </div>
								
									<label for="cpf">Nascimento:</label>
									<div class="form-group">
										<div class="col-sm-3"> 
											<input type="text" id="nascimento" name="nascimento" class="form-control"
                   							   ng-model="data.nascimento" ng-bind="{{data.nascimento}}"/  ui-date> 
                   					    </div>
                   					 </div>
                   					
                   					 
									
								<div class="form-group">
								 <div class="col-sm-12" style="align:right">
       								 <input type="submit" class="btn btn-success" value="Finalizar"
          								     ng-click="sendPostCadastroCliente()"  />
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
									<uib-tabset active="active">
    									<uib-tab index="0" heading="Tipo de perfil"></uib-tab>
    									<uib-tab index="1" heading="Privacidade"></uib-tab>
   										
  									</uib-tabset>
								</div>
								<!-- Se o usuário estiver logado e for um cliente -->
								<div ng-if="usuarioLogado !='' && tipoUsuarioLogado==1">
									 <uib-tabset active="active">
    									<uib-tab index="0" heading="Tipo de perfil">
    										
    										<div class="panel panel-default">
													<div class="panel-body">
													
														<div class="row">
														<div class="col-sm-6">
														 <div class="form-group">

												            <div class="col-sm-3">
												
												                <div class="checkbox">
												                    <label>
												                        <input type="checkbox" name="userType" value="prestador"
												                               ng-model="data.tipoCadastro" /> Prestador
												                    </label><br />
												                </div>
												              
												
												            </div>
												        </div>
														<div class="form-group" uib-collapse="!data.tipoCadastro">
																<div class="col-sm-6"> 
																		<label for="cpf">Tipo de acesso:</label>
																
																	<div class="radio">
														                    <label uib-tooltip="FREE: BLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLA">
														                        <input type="radio" name="serviceType" value="free"
														                               ng-model="prestador.type" /> Free 
														                    </label><br />
														                </div>
														                <div class="radio">
														                    <label uib-tooltip="PREMIUM: BLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLABLA">
														                        <input type="radio" name="serviceType" value="premium"
														                               ng-model="prestador.type"  /> Premium 
														                    </label><br />
														                </div>
						                   					    </div>
						                   				</div>
						                   				
						                   				{{data}}
														
														</div>							
													  </div>
													</div>
												</div>
    									
    									</uib-tab>
    									<uib-tab ng-hide="!data.tipoCadastro" index="1" heading="Serviços">
    									
    										<div class="panel panel-default">
												<div class="panel-body">
												<div class="row">
												<div class="col-sm-6">
									    		 <div class="form-group col-md-8 form-inline" >
									               <label for="categoria" class="control-label">Valor por categoria do serviÃ§o</label>
									               <fieldset  data-ng-repeat="categoria in listaCategorias">
									                  <select ng-model="categoria.nome" class="form-control" style="margin-top:15px;">
									                     <option ng-repeat="nomeCat in categorias track by $index" value="{{nomeCat}}">{{nomeCat}}</option>
									                  </select>
									                  
									               
									                  <div class="input-group" style="margin-top:15px;">
									                     <div class="input-group-addon">$</div>
									                      <input type="text" class="form-control" ng-model="categoria.valorpromocional" 	 name="" placeholder="Valor promocional">
									                     
									                  </div>
									                   
									                  
									                  
									                  <button class="btn" ng-show="$last" style="margin-top:15px"  ng-click="removeCategoria()">-</button>
									               </fieldset>
									                <!-- <button class="btn" ng-click="addNewCategoria()">+</button> 
									               <div id="choicesDisplay">
									                  {{ choices }}
									               </div>-->
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
			</div>

		</div>
	</form>
</body>