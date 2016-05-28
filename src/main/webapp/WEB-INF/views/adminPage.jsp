<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<body>


<div class="row">
	<div class="col-sm-6">
		<form class="form-horizontal" name="inactivateForm" novalidate role="form" ng-submit="pesquisar()" ng-controller="adminPageCtrl">
			<div class="form-group">
			<fieldset>
				<div  style="background-color:#f8f8f8 ;height:50%; padding:-2%">
					<legend> Inativar Usuario</legend>
					<div class="col-sm-9">
					
					<label for=lblsearchUser>Digite o usuário que deseja inativar:</label>
					
						<input type="text" id="serchUser" name="searchUser" class="form-control" ng-model="usuarioBusca"/>
						<br/>
						<input type="checkbox" name="administrador" value="administrador" ng-model="checkAdministrador"/> Usuário Administrador
						<br>
						 <input type="submit" class="btn btn-default" value="Pesquisar" style="float:right; background-color:#005E2F; color:white" ng-disabled="inactivateForm.$invalid"/>
              		 	<br/>
              		 	<br/>
              		 	<p>* Ao inativar um usuário do sistema, você bloquea imediatamente o acesso do mesmo. </p>
              		 	<p>
								<font color="red"> {{messageErroInativar}}</font> <font color="green">
									{{messageSucessoInativar}}</font>

						</p>
					</div>
					
				</div>
			</fieldset>
			</div>
		</form>
	</div>


	<div class="col-sm-6">
			
		<form class="form-horizontal" name="promForm" novalidate role="form"  ng-submit="sendPostProm()">
		<fieldset>
				<div  style="background-color:#f8f8f8 ;height:50%; padding:-1%">
					<legend> Administrar promoções</legend>
		
			<div class="form-group" >
				<label for="nomepromocao" class="col-sm-3 control-label">Nome da promoçao</label>
				<div class="col-sm-6">                
						   <input type="text" name="nomeprom" class="form-control"  ng-model="promnome" >
					
				</div>
				</div>
		
			<div class="form-group">
				<label for="descricao" class="col-sm-3 control-label">Descrição da promoção</label>
				<div class="col-sm-6">                
					<input type="text" name="promdescricao" ng-model="promdescricao" class="form-control">
					
				</div>
			</div>
					
			<div class="form-group">
			
				<label for="categoria" class="col-sm-3 control-label">Categoria do serviço</label>
				<div class="col-sm-3">                
					<select name="categoriaservico">
						<c:forEach items="${catList}" var="categList">
						<option value="${categList}">${categList}</option>
					</c:forEach>
					</select>
					
				</div>
				
				<label for="dataval" class="col-sm-3 control-label">Data de validade</label>
					<div class="col-sm-3">                
						<input type="text" name="datavalidade" ng-model="dataVal"class="form-control">
						
					</div>
			</div>	
		  	
			<div class="form-group">
				<label for="valorprom" class="col-sm-3 control-label">Valor promocional</label>
				<div class="col-sm-6">                
					<div class="input-group">
								<div class="input-group-addon">$</div>
								<input type="text" class="form-control" ng-model="valorprom" name="valorpromocional"
									placeholder="Valor promocional">
								<div class="input-group-addon">.00</div>
							</div>
					
				</div>
					
			<input type="submit" class="btn btn-default" value="Adicionar" style="background-color:#005E2F; color:white" ng-disabled="promForm.$invalid"/>
				
			
			</div>		
			
		
				
			
			</form>
					
		</div>
		</fieldset>
			</div>
		
		
	


</div>

<div class="row">
	
	<div class="col-sm-6">
		<form class="form-horizontal" name="serviceForm" novalidate role="form" ng-submit="sendPostServices()">
			<fieldset>
				<div  style="background-color:#f8f8f8 ;height:50%; padding:-1%">
					<legend> Adicionar categoria de serviços</legend>
					
					 <div class="form-group" show-errors>
            			<label for="name" class="col-sm-3 control-label">Nome</label>
            				<div class="col-sm-6">
                				<input type="text" id="servname" name="servname" class="form-control" ng-model="servname" ng-required="true"/>
                				<span class="help-block"
                     			 ng-if="serviceForm.servname.$error.required">O nome do serviço é obrigatório!</span>
                           </div>
                    </div>
                    
                     <div class="form-group" show-errors>
            			<label for="desricao" class="col-sm-3 control-label">Descrição</label>
            				<div class="col-sm-6">
                				<textarea rows="5" cols="50" id="servdescription" name="servdescription" class="form-control" ng-model="servdescription" ng-required="true"> </textarea>
                				<span class="help-block"
                     			 ng-if="serviceForm.servdescription.$error.required">A descrição do serviço é obrigatória! </span>
                           </div>
                    </div>
                     <div class=form-group>
						<div class="col-sm-3 control label">
						</div>
						<div class="col-sm-6">
					  <input type="submit" class="btn btn-default" value="Adicionar"
              			 style="background-color:#005E2F; color:white" ng-disabled="serviceForm.$invalid" />
              			 
              		 <input type="button" class="btn btn-default" value="Limpar"
              		 	ng-click="resetservform()" />
              		 	
   	           		 	
              			 
              		 </div>
              	  </div>
				</div>
			</fieldset>
		</form>
	</div>

	<div class="col-sm-6">
		<form class="form-horizontal" name="admForm" novalidate role="form" ng-submit="sendPostAdm()">
			<fieldset>
				<div  style="background-color:#f8f8f8 ;height:50%; padding:-1%">
					<legend>Cadastrar administrador</legend>
					
					 <div class="form-group" show-errors>
            			<label for="name" class="col-sm-3 control-label">Nome</label>
            				<div class="col-sm-6">
                				<input type="text" id="admname" name="admname" class="form-control" ng-model="admname" ng-required="true"/>
                				<span class="help-block"
                     			 ng-if="admForm.admname.$error.required">O usuário é obrigatório!</span>
                           </div>
                    </div>
                    
                     <div class="form-group" show-errors>
            			<label for="name" class="col-sm-3 control-label">Usuario</label>
            				<div class="col-sm-6">
                				<input type="text" id="admusername" name="admusername" class="form-control" ng-model="admusername" ng-required="true"/>
                				<span class="help-block"
                     			 ng-if="admForm.admusername.$error.required">O usuário é obrigatório!</span>
                           </div>
                    </div>
                    
                    <div class="form-group" show-errors>
            			<label for="name" class="col-sm-3 control-label">Senha</label>
            				<div class="col-sm-6">
                				<input type="password" id="admpassword" name="admpassword" class="form-control" ng-model="admpassword" ng-required="true"/>
                				 <span class="help-block"
                     			 ng-if="admForm.admpassword.$error.required">A senha é obrigatória!</span>
                           </div>
                    </div>
				
					<div class=form-group>
						<div class="col-sm-3 control label">
						</div>
						<div class="col-sm-6">
					  <input type="submit" class="btn btn-default" value="Adicionar"
              			 style="background-color:#005E2F; color:white" ng-disabled="admForm.$invalid" />
              			 
              		 <input type="button" class="btn btn-default" value="Limpar"
              		 	ng-click="resetadmform()" />
              		 	
   	           		 	
              			 
              		 </div>
              	</div>
						<div class=form-group>
							<div class="col-sm-3 control label"></div>
							<div class="col-sm-6">
								<font color="red"> {{messageErroCadastro}}</font> <font color="green">
									{{messageSucessoCadastro}}</font>

							</div>
						</div>

					</div>
			</fieldset>
		</form>
	</div>


</div>

</body>
