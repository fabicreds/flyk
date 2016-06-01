<style>
.selected{
color:red;
}

.non-selected{
color:black;
}

</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<body>


<!-- <div class="row" data-ng-init="init()"> -->
<div class="row">
	<div class="col-sm-6">
		<form class="form-horizontal" name="inactivateForm" novalidate role="form" ng-submit="pesquisar()" ng-controller="adminPageCtrl">
			
			 <fieldset>
				<div class="card" style="height:50%; margin-bottom:1%">
					<legend class="tituloInat">Inativar Usuario</legend>
					<div class="form-group">
						<div class="col-sm-9">
					
							<label for=lblsearchUser>Digite o usu�rio que deseja inativar:</label>
					
							<input type="text" id="serchUser" name="searchUser" class="form-control" ng-model="usuarioBusca"/>
							<br/>
							<input type="checkbox" name="administrador" value="administrador" ng-model="checkAdministrador"/> Usu�rio Administrador
							 <input type="submit" class="btn btn-default btn-cad" value="Pesquisar" style="float:right;  color:white" ng-disabled="inactivateForm.$invalid"/>
              		 		<br/>
              		 		<br/>
              		 		<p>* Ao inativar um usu�rio do sistema, voc� bloquea imediatamente o acesso do mesmo. </p>
              		 		<p>
								<font color="red"> {{messageErroInativar}}</font> <font color="green">
									{{messageSucessoInativar}}</font>

							</p>
					</div>
				</div>
			</div>	
		</fieldset>
	</form>
 </div>
 

	<div class="col-sm-6">
			
			
		<form class="form-horizontal" name="promForm" novalidate role="form"  ng-submit="sendPostProm()">
		<fieldset>
		<div  class="card" style="height:50%;">
			<legend class="tituloProm"> Administrar promo��es</legend>
		
			<div class="form-group " >
				<label for="nomepromocao" class="col-sm-4 control-label">Nome da promo�ao</label>
				<div class="col-md-4">                
						   <input type="text" name="nomeprom"  class="form-control"  ng-model="promnome" >					
				</div>
			</div>	
			<div class="form-group">
				<label for="descricao" class="col-sm-4 control-label">Descri��o da promo��o</label>
				<div class="col-sm-6">                
					<input type="text" name="promdescricao" ng-model="promdescricao" class="form-control">
					
				</div>
			</div>
			<div class="form-group">
			
				<label for="categoria" class="col-sm-4 control-label">Categoria do servi�o</label>
				<div class="col-sm-4">                
					<select name="categoriaservico" class="form-control" ng-model="listCat" ng-click="init()">						
						<option value={{cat}}>{{cat}}</option>					
					</select>					
				</div>				
			</div>	
			<div class="form-group form-margem">
				<label for="valorprom" class="col-sm-4 control-label">Valor promocional</label>
				<div class="col-sm-6">                
					<div class="input-group">
								<div class="input-group-addon">$</div>
								<input type="text" class="form-control" ng-model="valorprom" name="valorpromocional"
									placeholder="Valor promocional">
								
							</div>
					
				</div>
			</div>
			<div style="float:right;margin-right:100px">	
			     <input type="submit" class="btn btn-default btn-cad" value="Adicionar" style="color:white" ng-disabled="promForm.$invalid"/>
					
			</div>
			<div ng-controller="adminPageCtrl" class="form-group">
				<div>{{mensagem.mensagem}}</div>
		    </div>
		        
		</div>
		</form>
					
		</div>
		</fieldset>
			
		
		
	


</div>

<div class="row">
	
	<div class="col-sm-6">
		<form class="form-horizontal" name="serviceForm" novalidate role="form" ng-submit="sendPostServices()">
			<fieldset>
				<div class="card" style="height:50%;">
					<legend class="tituloCadCat">Categoria de servi�os
						<span class="glyphicon glyphicon-pencil" style="float:right; margin-right:1%" ng-click="editCategory()"
						 ng-class="{'selected': edit, 'non-selected': !edit}"> </span>
						<span class="glyphicon glyphicon-plus" style="float:right; margin-right:1%" ng-click="addCategory()"
						 ng-class="{'selected': add, 'non-selected': !add}"></span>
					</legend>
					<div ng-if="add===true">
					 <div class="form-group" show-errors>
            			<label for="name" class="col-sm-3 control-label">Nome</label>
            				<div class="col-sm-6">
                				<input type="text" id="servname" name="servname" class="form-control" ng-model="servname" ng-required="true"/>
                				<span class="help-block"
                     			 ng-if="serviceForm.servname.$error.required">O nome do servi�o � obrigat�rio!</span>
                           </div>
                    </div>
                    
                     <div class="form-group" show-errors>
            			<label for="desricao" class="col-sm-3 control-label">Descri��o</label>
            				<div class="col-sm-6">
                				<textarea rows="5" cols="50" id="servdescription" name="servdescription" class="form-control" ng-model="servdescription" ng-required="true"> </textarea>
                				<span class="help-block"
                     			 ng-if="serviceForm.servdescription.$error.required">A descri��o do servi�o � obrigat�ria! </span>
                           </div>
                    </div>
                     <div class=form-group>
						<div class="col-sm-3 control label">
						</div>
						<div class="col-sm-6">
					  			<input type="submit" class="btn btn-default btn-cad" value="Adicionar"
              			 		style=" color:white" ng-disabled="serviceForm.$invalid" />
              			 
              		 			<input type="button" class="btn btn-default" value="Limpar"
              		 			ng-click="resetservform()" />
              		 	
   	           		 	
              			 
              		 </div>
              	  </div>
              	 </div> <!-- ng-if -->
				</div>
			</fieldset>
		</form>
	</div>

	<div class="col-sm-6">
		<form class="form-horizontal" name="admForm" novalidate role="form" ng-submit="sendPostAdm()">
			<fieldset>
				<div  class="card" style="height:50%;">
					<legend class="tituloCadAdm" >Cadastrar administrador</legend>
					
					 <div class="form-group" show-errors>
            			<label for="name" class="col-sm-3 control-label">Nome</label>
            				<div class="col-sm-6">
                				<input type="text" id="admname" name="admname" class="form-control" ng-model="admname" ng-required="true"/>
                				<span class="help-block"
                     			 ng-if="admForm.admname.$error.required">O usu�rio � obrigat�rio!</span>
                           </div>
                    </div>
                    
                     <div class="form-group" show-errors>
            			<label for="name" class="col-sm-3 control-label">Usuario</label>
            				<div class="col-sm-6">
                				<input type="text" id="admusername" name="admusername" class="form-control" ng-model="admusername" ng-required="true"/>
                				<span class="help-block"
                     			 ng-if="admForm.admusername.$error.required">O usu�rio � obrigat�rio!</span>
                           </div>
                    </div>
                    
                    <div class="form-group" show-errors>
            			<label for="name" class="col-sm-3 control-label">Senha</label>
            				<div class="col-sm-6">
                				<input type="password" id="admpassword" name="admpassword" class="form-control" ng-model="admpassword" ng-required="true"/>
                				 <span class="help-block"
                     			 ng-if="admForm.admpassword.$error.required">A senha � obrigat�ria!</span>
                           </div>
                    </div>
				
					<div class=form-group>
						<div class="col-sm-3 control label">
						</div>
						<div class="col-sm-6">
					  <input type="submit" class="btn btn-default btn-cad" value="Adicionar"
              			 style=" color:white" ng-disabled="admForm.$invalid" />
              			 
              		 <input type="button" class="btn btn-default" value="Limpar"
              		 	ng-click="resetadmform()" />
              		 	
   	           		 	
              			 
              		 </div>
              	</div>
						<div class=form-group>
							<div class="col-sm-3 control label"></div>
							<div class="col-sm-6">
								<font color="red"> {{messageErroCadastro}}</font>
								 <font color="green">
									{{messageSucessoCadastro}}</font>

							</div>
						</div>

					</div>
			</fieldset>
		</form>
	</div>


</div>

</body>
