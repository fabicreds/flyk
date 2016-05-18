<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<body>


<div class="row">
	<div class="col-sm-6">
		<form>
			<div class="form-group">
			<fieldset>
				<div  style="background-color:#f8f8f8 ;height:40%; padding:-1%">
					<legend> Inativar Usuario</legend>
					<div class="col-sm-9">
					<label for=lblsearchUser>Digite o nome/email que deseja:</label>
					
						<input type="text" id="serchUser" name="searchUser" class="form-control" />
						<br/>
						 <input type="button" class="btn btn-default" value="Pesquisar"
              		 	ng-click="pesquisar()" style="float:right; background-color:#005E2F; color:white" />
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
		<form>
			<div class="form-group">
			<fieldset>
				<div  style="background-color:#f8f8f8 ;height:40%; padding:-1%">
					<legend> Administrar promoções</legend>
					
				</div>
			</fieldset>
			</div>
		</form>
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
