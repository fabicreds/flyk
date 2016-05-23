<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<body>


<div class="row">
	<div class="col-sm-6">
		<form class="form-horizontal" name="inactivateForm" novalidate role="form" ng-submit="pesquisar()" >
			<div class="form-group">
			<fieldset>
				<div  style="background-color:#f8f8f8 ;height:40%; padding:-1%">
					<legend> Inativar Usuario</legend>
					<div class="col-sm-9">
					
					<label for=lblsearchUser>Digite o nome/email que deseja:</label>
					
						<input type="text" id="serchUser" name="searchUser" class="form-control" ng-model="usuarioBusca"/>
						<br/>
						<input type="checkbox" name="administrador" value="administrador" ng-model="checkAdministrador"/> Usuário Administrador
						<br>
						 <input type="submit" class="btn btn-default" value="Pesquisar" style="float:right; background-color:#005E2F; color:white" />
              		 	<br/>
              		 	<br/>
              		 	<p>* Ao inativar um usuário do sistema, você bloquea imediatamente o acesso do mesmo. 
					</div>
					
				</div>
			</fieldset>
			</div>
		</form>
	</div>


	<div class="col-sm-6">
			
		<form class="form-horizontal" name="promform" novalidate role="form"  ng-submit="sendPostProm()">
		<fieldset>
				<div  style="background-color:#f8f8f8 ;height:40%; padding:-1%">
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
					
			<input type="submit" class="btn btn-default" value="Adicionar" style="background-color:#005E2F; color:white" />
				
			
			</div>		
			
		
				
			
			</form>
					
		</div>
		</fieldset>
			</div>
		
		
	


</div>

<div class="row">
	
	<div class="col-sm-6">
		<form>
			<fieldset>
				<div  style="background-color:#f8f8f8 ;height:40%; padding:-1%">
					<legend> Analisar rendimentos</legend>
				</div>
			</fieldset>
		</form>
	</div>

	<div class="col-sm-6">
		<form class="form-horizontal" name="userForm" novalidate role="form" ng-submit="sendPostAdm()">
			<fieldset>
				<div  style="background-color:#f8f8f8 ;height:40%; padding:-1%">
					<legend>Cadastrar administrador</legend>
					
					 <div class="form-group">
            			<label for="name" class="col-sm-3 control-label">Nome</label>
            				<div class="col-sm-6">
                				<input type="text" id="name" name="name" class="form-control" ng-model="admname"/>
                           </div>
                    </div>
                    
                     <div class="form-group">
            			<label for="name" class="col-sm-3 control-label">Usuario</label>
            				<div class="col-sm-6">
                				<input type="text" id="username" name="username" class="form-control" ng-model="admusername"/>
                           </div>
                    </div>
                    
                    <div class="form-group">
            			<label for="name" class="col-sm-3 control-label">Senha</label>
            				<div class="col-sm-6">
                				<input type="password" id="password" name="password" class="form-control" ng-model="admpassword"/>
                           </div>
                    </div>
				
					<div class=form-group>
						<div class="col-sm-3 control label">
						</div>
						<div class="col-sm-6">
					  <input type="submit" class="btn btn-default" value="Adicionar"
              			 style="background-color:#005E2F; color:white" />
              			 
              		 <input type="button" class="btn btn-default" value="Limpar"
              		 	ng-click="reset()" />
              		 	
   	           		 	
              			 
              			 </div>
              		</div>
					
				</div>
			</fieldset>
		</form>
	</div>


</div>

</body>
